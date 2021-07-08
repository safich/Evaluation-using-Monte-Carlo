package com.company.main.model;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DocReader {
    private final MainStorage storage;

    public DocReader(MainStorage storage) {
        this.storage = storage;
    }

    public void readData(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workBook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workBook.getSheet("Денежные потоки");

        XSSFRow row = sheet.getRow(2);
        int c = 2;
        int y = 2021;

        for (int i = 0; i < storage.getRepPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.setCapex(y, cell.getNumericCellValue());
            c++;
            y++;
        }

        row = sheet.getRow(4);
        c = 2;
        y = 2021;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.setRevRes(y, cell.getNumericCellValue());
            c++;
            y++;
        }

        row = sheet.getRow(9);
        c = 2;
        y = 2021;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.setCostRes(y, cell.getNumericCellValue());
            c++;
            y++;
        }

        row = sheet.getRow(13);
        c = 2;
        y = 2021;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.setSevCost(y, cell.getNumericCellValue());
            c++;
            y++;
        }

        row = sheet.getRow(14);
        c = 2;
        y = 2021;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.setDep(y, cell.getNumericCellValue());
            c++;
            y++;
        }

        row = sheet.getRow(15);
        c = 2;
        y = 2021;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.setCommCost(y, cell.getNumericCellValue());
            c++;
            y++;
        }

        row = sheet.getRow(17);
        c = 2;
        y = 2021;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.setOpRev(y, cell.getNumericCellValue());
            c++;
            y++;
        }

        row = sheet.getRow(19);
        c = 2;
        y = 2021;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.setIntPays(y, cell.getNumericCellValue());
            c++;
            y++;
        }

        row = sheet.getRow(21);
        c = 2;
        y = 2021;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.setRevBefPays(y, cell.getNumericCellValue());
            c++;
            y++;
        }

        row = sheet.getRow(23);
        c = 2;
        y = 2021;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.setRevTaxPays(y, cell.getNumericCellValue());
            c++;
            y++;
        }

        row = sheet.getRow(25);
        c = 2;
        y = 2021;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.setCleanRev(y, cell.getNumericCellValue());
            c++;
            y++;
        }

        row = sheet.getRow(27);
        c = 2;
        y = 2021;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.setMonFlow(y, cell.getNumericCellValue());
            c++;
            y++;
        }

        row = sheet.getRow(28);
        c = 2;
        y = 2021;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.setDiscMonFlow(y, cell.getNumericCellValue());
            c++;
            y++;
        }
    }
}
