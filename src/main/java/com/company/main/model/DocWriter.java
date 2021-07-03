package com.company.main.model;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class DocWriter {
    public void writeData(double[][] array) throws IOException {
        XSSFWorkbook book = new XSSFWorkbook();
        FileOutputStream fos = new FileOutputStream("Monte-Carlo.xlsx");

        XSSFSheet sheet = book.createSheet("Результаты");

        for (int i = 0; i < array.length; i++) {
            XSSFRow row = sheet.createRow(i);
            XSSFCell cell1 = row.createCell(0);
            cell1.setCellType(CellType.NUMERIC);
            cell1.setCellValue(array[i][0]);
            XSSFCell cell2 = row.createCell(1);
            cell2.setCellType(CellType.NUMERIC);
            cell2.setCellValue(array[i][1]);
        }
        book.write(fos);
        fos.close();
    }
}
