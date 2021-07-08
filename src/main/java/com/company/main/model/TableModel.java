package com.company.main.model;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    private final MainStorage storage;
    private final int rowsNum;

    public TableModel(MainStorage storage) {
        this.storage = storage;
        this.rowsNum = 13;
    }

    private int getYearForColumn(int column) {
        switch(column) {
            case 1:return 2021;
            case 2:return 2022;
            case 3:return 2023;
            case 4:return 2024;
            case 5:return 2025;
            case 6:return 2026;
            case 7:return 2027;
            case 8:return 2028;
            case 9:return 2029;
            case 10:return 2030;
            case 11:return 2031;
            case 12:return 2032;
            case 13:return 2033;
            case 14:return 2034;
            case 15:return 2035;
            case 16:return 2036;
            case 17:return 2037;
        }
        return 0;
    }

    @Override
    public int getRowCount() {
        return rowsNum;
    }

    @Override
    public int getColumnCount() {
        return storage.getRepPeriod() + 1;
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
                case (0): return Math.round(storage.getCapex(getYearForColumn(columnIndex)));
                case (1): return Math.round(storage.getRevRes(getYearForColumn(columnIndex)));
                case (2): return Math.round(storage.getCostRes(getYearForColumn(columnIndex)));
                case (3): return Math.round(storage.getSevCost(getYearForColumn(columnIndex)));
                case (4): return Math.round(storage.getDep(getYearForColumn(columnIndex)));
                case (5): return Math.round(storage.getCommCost(getYearForColumn(columnIndex)));
                case (6): return Math.round(storage.getOpRev(getYearForColumn(columnIndex)));
                case (7): return Math.round(storage.getIntPays(getYearForColumn(columnIndex)));
                case (8): return Math.round(storage.getRevBefPays(getYearForColumn(columnIndex)));
                case (9): return Math.round(storage.getRevTaxPays(getYearForColumn(columnIndex)));
                case (10): return Math.round(storage.getCleanRev(getYearForColumn(columnIndex)));
                case (11): return Math.round(storage.getMonFlow(getYearForColumn(columnIndex)));
                case (12): return Math.round(storage.getDiscMonFlow(getYearForColumn(columnIndex)));
            }
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        int header = 2021;
        switch (column) {
            case (0): return "";
            case (1): return String.valueOf(header);
            case (2): return String.valueOf(header + 1);
            case (3): return String.valueOf(header + 2);
            case (4): return String.valueOf(header + 3);
            case (5): return String.valueOf(header + 4);
            case (6): return String.valueOf(header + 5);
            case (7): return String.valueOf(header + 6);
            case (8): return String.valueOf(header + 7);
            case (9): return String.valueOf(header + 8);
            case (10): return String.valueOf(header + 9);
            case (11): return String.valueOf(header + 10);
            case (12): return String.valueOf(header + 11);
            case (13): return String.valueOf(header + 12);
            case (14): return String.valueOf(header + 13);
            case (15): return String.valueOf(header + 14);
            case (16): return String.valueOf(header + 15);
            case (17): return String.valueOf(header + 16);
        }
        return "";
    }
}
