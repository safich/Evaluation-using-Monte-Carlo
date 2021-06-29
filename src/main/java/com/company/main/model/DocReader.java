package com.company.main.model;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DocReader {
    private final Storage storage;

    public DocReader(Storage storage) {
        this.storage = storage;
    }

    public void readData(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workBook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workBook.getSheet("Денежные потоки");

        XSSFRow row = sheet.getRow(2);
        int c = 2;
        for (int i = 0; i < storage.getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.getCapex()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(4);
        c = 2;
        for (int i = 0; i < storage.getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.getRevRes()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(9);
        c = 2;
        for (int i = 0; i < storage.getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.getCostRes()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(13);
        c = 2;
        for (int i = 0; i < storage.getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.getSevCost()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(14);
        c = 2;
        for (int i = 0; i < storage.getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.getDep()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(15);
        c = 2;
        for (int i = 0; i < storage.getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.getCommCost()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(17);
        c = 2;
        for (int i = 0; i < storage.getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.getOpRev()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(19);
        c = 2;
        for (int i = 0; i < storage.getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.getIntPays()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(21);
        c = 2;
        for (int i = 0; i < storage.getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.getRevBefTax()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(23);
        c = 2;
        for (int i = 0; i < storage.getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.getRevTaxPays()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(25);
        c = 2;
        for (int i = 0; i < storage.getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.getCleanRev()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(27);
        c = 2;
        for (int i = 0; i < storage.getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.getMonFlow()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(28);
        c = 2;
        for (int i = 0; i < storage.getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            storage.getDiscMonFlow()[i] = cell.getNumericCellValue();
            c++;
        }
    }
}
