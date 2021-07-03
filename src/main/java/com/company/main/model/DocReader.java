package com.company.main.model;

import com.company.main.control.StorageController;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DocReader {
    private final StorageController sc;

    public DocReader(StorageController sc) {
        this.sc = sc;
    }

    public void readData(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workBook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workBook.getSheet("Денежные потоки");

        XSSFRow row = sheet.getRow(2);
        int c = 2;
        for (int i = 0; i < sc.getStorage().getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            sc.getStorage().getCapex()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(4);
        c = 2;
        for (int i = 0; i < sc.getStorage().getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            sc.getStorage().getRevRes()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(9);
        c = 2;
        for (int i = 0; i < sc.getStorage().getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            sc.getStorage().getCostRes()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(13);
        c = 2;
        for (int i = 0; i < sc.getStorage().getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            sc.getStorage().getSevCost()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(14);
        c = 2;
        for (int i = 0; i < sc.getStorage().getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            sc.getStorage().getDep()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(15);
        c = 2;
        for (int i = 0; i < sc.getStorage().getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            sc.getStorage().getCommCost()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(17);
        c = 2;
        for (int i = 0; i < sc.getStorage().getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            sc.getStorage().getOpRev()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(19);
        c = 2;
        for (int i = 0; i < sc.getStorage().getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            sc.getStorage().getIntPays()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(21);
        c = 2;
        for (int i = 0; i < sc.getStorage().getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            sc.getStorage().getRevBefTax()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(23);
        c = 2;
        for (int i = 0; i < sc.getStorage().getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            sc.getStorage().getRevTaxPays()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(25);
        c = 2;
        for (int i = 0; i < sc.getStorage().getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            sc.getStorage().getCleanRev()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(27);
        c = 2;
        for (int i = 0; i < sc.getStorage().getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            sc.getStorage().getMonFlow()[i] = cell.getNumericCellValue();
            c++;
        }

        row = sheet.getRow(28);
        c = 2;
        for (int i = 0; i < sc.getStorage().getPeriod(); i++) {
            XSSFCell cell = row.getCell(c);
            sc.getStorage().getDiscMonFlow()[i] = cell.getNumericCellValue();
            c++;
        }
    }
}
