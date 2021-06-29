package com.company.main.model;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    private final Storage storage;
    private int rowsNum;

    public TableModel(Storage storage) {
        this.storage = storage;
        this.rowsNum = 13;
    }

    @Override
    public int getRowCount() {
        return rowsNum;
    }

    @Override
    public int getColumnCount() {
        return storage.getPeriod() + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            switch (rowIndex) {
                case (0): return "Капитальные затраты";
                case (1): return "Выручка итого";
                case (2): return "Себестоимость итого";
                case (3): return "Налог на добычу ПИ";
                case (4): return "Амортизация";
                case (5): return "Общехозяйственные расходы";
                case (6): return "Операционная прибыль";
                case (7): return "Процентные платежи";
                case (8): return "Прибыль до налогооблажения";
                case (9): return "Налог на прибыль";
                case (10): return "Чистая прибыль";
                case (11): return "Денежные потоки";
                case (12): return "Дисконтированные ден потоки";
            }
        }
        else {
            switch (rowIndex) {
                case (0): return Math.round(storage.getCapex()[columnIndex - 1]);
                case (1): return Math.round(storage.getRevRes()[columnIndex - 1]);
                case (2): return Math.round(storage.getCostRes()[columnIndex - 1]);
                case (3): return Math.round(storage.getSevCost()[columnIndex - 1]);
                case (4): return Math.round(storage.getDep()[columnIndex - 1]);
                case (5): return Math.round(storage.getCommCost()[columnIndex - 1]);
                case (6): return Math.round(storage.getOpRev()[columnIndex - 1]);
                case (7): return Math.round(storage.getIntPays()[columnIndex - 1]);
                case (8): return Math.round(storage.getRevBefTax()[columnIndex - 1]);
                case (9): return Math.round(storage.getRevTaxPays()[columnIndex - 1]);
                case (10): return Math.round(storage.getCleanRev()[columnIndex - 1]);
                case (11): return Math.round(storage.getMonFlow()[columnIndex - 1]);
                case (12): return Math.round(storage.getDiscMonFlow()[columnIndex - 1]);
            }
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case (0): return "";
            case (1): return String.valueOf(storage.getStartPeriod());
            case (2): return String.valueOf(storage.getStartPeriod() + 1);
            case (3): return String.valueOf(storage.getStartPeriod() + 2);
            case (4): return String.valueOf(storage.getStartPeriod() + 3);
            case (5): return String.valueOf(storage.getStartPeriod() + 4);
            case (6): return String.valueOf(storage.getStartPeriod() + 5);
            case (7): return String.valueOf(storage.getStartPeriod() + 6);
            case (8): return String.valueOf(storage.getStartPeriod() + 7);
            case (9): return String.valueOf(storage.getStartPeriod() + 8);
            case (10): return String.valueOf(storage.getStartPeriod() + 9);
            case (11): return String.valueOf(storage.getStartPeriod() + 10);
            case (12): return String.valueOf(storage.getStartPeriod() + 11);
            case (13): return String.valueOf(storage.getStartPeriod() + 12);
            case (14): return String.valueOf(storage.getStartPeriod() + 13);
            case (15): return String.valueOf(storage.getStartPeriod() + 14);
            case (16): return String.valueOf(storage.getStartPeriod() + 15);
            case (17): return String.valueOf(storage.getStartPeriod() + 16);
        }
        return "";
    }
}
