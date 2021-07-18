package safich.montecarlo.main.view;

import safich.montecarlo.main.control.Calculator;
import safich.montecarlo.main.model.DocWriter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CalcDialog extends JDialog {
    private final Calculator calculator;
    private String selectedPar;
    private int year;
    private double mean;
    private double standDev;
    private int testsNum;

    public CalcDialog(Calculator calculator) {
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.calculator = calculator;
        mean = 0;
        standDev = 0;
        testsNum = 0;
        setTitle("Расчетные факторы");

        String[] params = {
                "Выручка Итого",
                "Себестоимость Итого",
                "Операционная прибыль",
                "Прибыль до налогообложения",
                "Чистая прибыль"
        };

        String[] years = {
                "2021",
                "2022",
                "2023",
                "2024",
                "2025",
                "2026",
                "2027",
                "2028",
                "2029",
                "2030",
                "2031",
                "2032",
                "2033",
                "2034",
                "2035",
                "2036",
                "2037"
        };

        //Выбор параметра и года
        JPanel selectionPane = new JPanel();

        JComboBox paramsComboBox = new JComboBox(params);
        JComboBox yearsComboBox = new JComboBox(years);

        selectionPane.add(paramsComboBox);
        selectionPane.add(yearsComboBox);

        //Панель с вводом
        JPanel inputPane = new JPanel();
        inputPane.setLayout(new BoxLayout(inputPane, BoxLayout.Y_AXIS));

        JLabel testsNumLabel = new JLabel("Кол-во испытаний:");
        JLabel meanLabel = new JLabel("Среднее:");
        JLabel standDevLabel = new JLabel("Отклонение:");

        JTextField testsNumField = new JTextField("10000");
        testsNumField.setColumns(5);
        testsNumField.setDragEnabled(false);
        JTextField meanField = new JTextField();
        meanField.setColumns(7);
        JTextField standDevField = new JTextField();
        standDevField.setColumns(7);

        inputPane.add(testsNumLabel);
        inputPane.add(testsNumField);
        inputPane.add(meanLabel);
        inputPane.add(meanField);
        inputPane.add(standDevLabel);
        inputPane.add(standDevField);

        //Кнопки для выбора итогового показателя
        JPanel radioButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JRadioButton npvButton = new JRadioButton("NPV");
        JRadioButton irrButton = new JRadioButton("IRR");
        JRadioButton piButton = new JRadioButton("PI");
        ButtonGroup group = new ButtonGroup();
        group.add(npvButton);
        group.add(irrButton);
        group.add(piButton);
        radioButtonsPanel.add(npvButton);
        radioButtonsPanel.add(irrButton);
        radioButtonsPanel.add(piButton);

        inputPane.add(radioButtonsPanel);

        JButton selectButton = new JButton("OK");

        //Вычисление выбранных параметров
        selectButton.addActionListener(e -> {
            selectedPar = String.valueOf(paramsComboBox.getSelectedItem());
            year = Integer.parseInt(String.valueOf(yearsComboBox.getSelectedItem()));
            mean = Double.parseDouble(meanField.getText());
            standDev = Double.parseDouble(standDevField.getText());
            testsNum = Integer.parseInt(testsNumField.getText());

            if (npvButton.isSelected()) {
                calcNpv(selectedPar, year, mean, standDev, testsNum);
            }
            else if (irrButton.isSelected()) {
                calcIrr(selectedPar, year, mean, standDev, testsNum);
            }
            else if (piButton.isSelected()) {
                calcPi(selectedPar, year, mean, standDev, testsNum);
            } else {
                JOptionPane.showMessageDialog(this,"Выберите показатель", "Предупреждение", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPane = new JPanel();
        buttonPane.add(selectButton);


        JPanel mainPane = new JPanel();
        mainPane.add(inputPane, BorderLayout.CENTER);

        add(selectionPane, BorderLayout.NORTH);
        add(mainPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calcNpv(String par, int year, double mean, double standDev, int testsNum) {
        switch (par) {
            case "Выручка Итого": {
                try {
                    new DocWriter().writeData(calculator.calcNpvForRevRes(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Себестоимость Итого": {
                try {
                    new DocWriter().writeData(calculator.calcNpvForCostRes(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Операционная прибыль": {
                try {
                    new DocWriter().writeData(calculator.calcNpvForOpRev(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Прибыль до налогообложения": {
                try {
                    new DocWriter().writeData(calculator.calcNpvForRevBefTax(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Чистая прибыль": {
                try {
                    new DocWriter().writeData(calculator.calcNpvForCleanRev(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void calcIrr(String par, int year, double mean, double standDev, int testsNum) {
        switch (par) {
            case "Выручка Итого": {
                try {
                    new DocWriter().writeData(calculator.calcIrrForRevRes(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Себестоимость Итого": {
                try {
                    new DocWriter().writeData(calculator.calcIrrForCostRes(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Операционная прибыль":{
                try {
                    new DocWriter().writeData(calculator.calcIrrForOpRev(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Прибыль до налогообложения":{
                try {
                    new DocWriter().writeData(calculator.calcIrrForRevBefTax(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Чистая прибыль":{
                try {
                    new DocWriter().writeData(calculator.calcIrrForCleanRev(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void calcPi(String par, int year, double mean, double standDev, int testsNum) {
        switch (par) {
            case "Выручка Итого": {
                try {
                    new DocWriter().writeData(calculator.calcPiForRevRes(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Себестоимость Итого": {
                try {
                    new DocWriter().writeData(calculator.calcPiForCostRes(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Операционная прибыль":{
                try {
                    new DocWriter().writeData(calculator.calcPiForOpRev(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Прибыль до налогообложения":{
                try {
                    new DocWriter().writeData(calculator.calcPiForRevBefTax(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Чистая прибыль":{
                try {
                    new DocWriter().writeData(calculator.calcPiForCleanRev(year, mean, standDev, testsNum));
                    getSuccessMessage();
                    return;
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Ошибка создания файла", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void getSuccessMessage() {
        JOptionPane.showMessageDialog(this, "Результаты записаны в файл Monte-Carlo.xlsx", "Успех",JOptionPane.INFORMATION_MESSAGE);
    }
}
