package com.company.main.view;

import com.company.main.control.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcDialog extends JDialog {
    private Calculator calculator;

    public CalcDialog(Calculator calculator) {
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.calculator = calculator;
        setTitle("Расчетные факторы");

        String[] params = {
                "Выручка Итого",
                "Себестоимость Итого",
                "Амортизация",
                "Операционная прибыль",
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

        JComboBox paramsComboBox = new JComboBox(params);
        JComboBox yearsComboBox = new JComboBox(years);

        JLabel testsNum = new JLabel("Кол-во испытаний:");
        JTextField testsNumField = new JTextField("10000");
        testsNumField.setColumns(10);

        JPanel selectionPane = new JPanel();
        selectionPane.add(paramsComboBox);
        selectionPane.add(yearsComboBox);

        JPanel numbersPane = new JPanel();
        numbersPane.setLayout(new BoxLayout(numbersPane, BoxLayout.Y_AXIS));
        numbersPane.add(testsNum);
        numbersPane.add(testsNumField);

        JPanel deviationPane = new JPanel();
        JLabel mean = new JLabel("Среднее:");
        JTextField meanField = new JTextField();
        meanField.setColumns(8);
        JTextField standDevField = new JTextField();
        standDevField.setColumns(5);
        JLabel standDev = new JLabel("Отклонение:");
        deviationPane.add(mean);
        deviationPane.add(meanField);
        deviationPane.add(standDev);
        deviationPane.add(standDevField);

        numbersPane.add(deviationPane);

        JButton selectButton = new JButton("OK");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcSelected(String.valueOf(paramsComboBox.getSelectedItem()), Integer.parseInt(String.valueOf(yearsComboBox.getSelectedItem())),
                        Double.parseDouble(meanField.getText()), Double.parseDouble(standDevField.getText()), Integer.parseInt(testsNumField.getText()));
                dispose();
            }
        });

        JPanel buttonPane = new JPanel();
        buttonPane.add(selectButton);

        add(selectionPane, BorderLayout.NORTH);
        add(numbersPane,BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calcSelected(String par, int year, double mean, double standDev, int testsNum) {
        double[] array = calculator.calcNpvForRevRes(mean, standDev, testsNum, year);
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < array.length; i++) {
            listModel.addElement(array[i]);
        }
        JList list = new JList(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);
        JDialog dialog = new JDialog();
        dialog.add(listScrollPane);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
