package com.company.main.model;

import com.company.main.control.StorageController;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    private final StorageController sc;
    private final int rowsNum;

    public TableModel(StorageController sc) {
        this.sc = sc;
        this.rowsNum = 13;
    }

    @Override
    public int getRowCount() {
        return rowsNum;
    }

    @Override
    public int getColumnCount() {
        return sc.getStorage().getPeriod() + 1;
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
                case (0): return Math.round(sc.getStorage().getCapex()[columnIndex - 1]);
                case (1): return Math.round(sc.getStorage().getRevRes()[columnIndex - 1]);
                case (2): return Math.round(sc.getStorage().getCostRes()[columnIndex - 1]);
                case (3): return Math.round(sc.getStorage().getSevCost()[columnIndex - 1]);
                case (4): return Math.round(sc.getStorage().getDep()[columnIndex - 1]);
                case (5): return Math.round(sc.getStorage().getCommCost()[columnIndex - 1]);
                case (6): return Math.round(sc.getStorage().getOpRev()[columnIndex - 1]);
                case (7): return Math.round(sc.getStorage().getIntPays()[columnIndex - 1]);
                case (8): return Math.round(sc.getStorage().getRevBefTax()[columnIndex - 1]);
                case (9): return Math.round(sc.getStorage().getRevTaxPays()[columnIndex - 1]);
                case (10): return Math.round(sc.getStorage().getCleanRev()[columnIndex - 1]);
                case (11): return Math.round(sc.getStorage().getMonFlow()[columnIndex - 1]);
                case (12): return Math.round(sc.getStorage().getDiscMonFlow()[columnIndex - 1]);
            }
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case (0): return "";
            case (1): return String.valueOf(sc.getStorage().getStartPeriod());
            case (2): return String.valueOf(sc.getStorage().getStartPeriod() + 1);
            case (3): return String.valueOf(sc.getStorage().getStartPeriod() + 2);
            case (4): return String.valueOf(sc.getStorage().getStartPeriod() + 3);
            case (5): return String.valueOf(sc.getStorage().getStartPeriod() + 4);
            case (6): return String.valueOf(sc.getStorage().getStartPeriod() + 5);
            case (7): return String.valueOf(sc.getStorage().getStartPeriod() + 6);
            case (8): return String.valueOf(sc.getStorage().getStartPeriod() + 7);
            case (9): return String.valueOf(sc.getStorage().getStartPeriod() + 8);
            case (10): return String.valueOf(sc.getStorage().getStartPeriod() + 9);
            case (11): return String.valueOf(sc.getStorage().getStartPeriod() + 10);
            case (12): return String.valueOf(sc.getStorage().getStartPeriod() + 11);
            case (13): return String.valueOf(sc.getStorage().getStartPeriod() + 12);
            case (14): return String.valueOf(sc.getStorage().getStartPeriod() + 13);
            case (15): return String.valueOf(sc.getStorage().getStartPeriod() + 14);
            case (16): return String.valueOf(sc.getStorage().getStartPeriod() + 15);
            case (17): return String.valueOf(sc.getStorage().getStartPeriod() + 16);
        }
        return "";
    }
}
