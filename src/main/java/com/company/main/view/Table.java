package com.company.main.view;

import com.company.main.model.Storage;
import com.company.main.model.TableModel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.awt.*;
import java.util.Arrays;

public class Table extends JFrame {
    private JTable table;
    private TableColumn columnSize;

    public Table(Storage storage) {
        super("Полученные данные");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        table = new JTable(new TableModel(storage));
        columnSize = null;
        for (int i = 0; i < storage.getPeriod(); i++) {
            columnSize = table.getColumnModel().getColumn(i);
            if (i == 0) {
                columnSize.setMaxWidth(200);
                columnSize.setMinWidth(170);
            } else {
                columnSize.setMinWidth(70);
            }
        }
        JScrollPane jScrollPane = new JScrollPane(table);

        add(jScrollPane, BorderLayout.CENTER);
        setPreferredSize(new Dimension(1500,500));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
