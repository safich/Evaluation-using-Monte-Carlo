package com.company.main.view;

import com.company.main.control.Calculator;
import com.company.main.control.StorageController;
import com.company.main.model.DocReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends JFrame{
    private final StorageController sc;
    private final JFileChooser fileChooser;
    private Calculator calculator;
    private final DefaultListModel listModel;
    private boolean isCalculable;

    public MainFrame(StorageController sc) {
        super("Расчет Монте-Карло");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.sc = sc;
        isCalculable = false;

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createAboutMenu());
        setJMenuBar(menuBar);

        listModel = new DefaultListModel();
        listModel.addElement("Ожидание ввода...");
        JList list = new JList(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);

        JPanel buttonPane = new JPanel();
        JButton calcButton = new JButton("Расчет");
        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isCalculable) {
                    new CalcDialog(calculator);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this,"Необходимо ввести данные");
                }
            }
        });

        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(calcButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        fileChooser = new JFileChooser();

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
        setPreferredSize(new Dimension(500,400));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JMenu createFileMenu() {
        JMenu file = new JMenu("Файл");
        JMenuItem exit = new JMenuItem(new ExitAction());
        JMenuItem open = new JMenuItem(new OpenAction());
        JMenuItem enter = new JMenuItem("Ввести вручную");
        file.add(open);
        file.add(enter);
        file.addSeparator();
        file.add(exit);
        return file;
    }

    private JMenu createAboutMenu() {
        JMenu aboutMenu = new JMenu("Справка");
        JMenuItem guide = new JMenuItem("Инструкция");
        JMenuItem about = new JMenuItem("О программе");
        aboutMenu.add(guide);
        aboutMenu.add(about);
        return aboutMenu;
    }

    private void openDialog() {
        fileChooser.setDialogTitle("Выбор файла");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int res = fileChooser.showOpenDialog(MainFrame.this);
        if(res == JFileChooser.APPROVE_OPTION) {
            DocReader docReader = new DocReader(sc);
            try {
                docReader.readData(fileChooser.getSelectedFile());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(MainFrame.this, ex.getMessage());
            }
            new Table(sc);
            calculator = new Calculator(sc);

            listModel.removeAllElements();
            listModel.addElement("NPV = " + Math.round(calculator.calcStorageNpv()));
            listModel.addElement("IRR = " + calculator.calcStorageIrr());
            listModel.addElement("PI = " + calculator.calcStoragePi());
            listModel.addElement("Ставка дисконтироваия = " + sc.getStorage().getDiscRate());

            revalidate();
            repaint();
            isCalculable = true;
        }
    }

    class ExitAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        ExitAction() {
            putValue(NAME, "Выход");
        }
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    class OpenAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        OpenAction() {
            putValue(NAME, "Открыть");
        }
        public void actionPerformed(ActionEvent e) {
            openDialog();
        }
    }
}
