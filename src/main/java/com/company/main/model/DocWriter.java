package com.company.main.model;

import com.company.main.control.Analyzer;
import com.company.main.control.GraphicBuilder;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class DocWriter {
    public void writeData(double[][] array) throws IOException {
        GraphicBuilder gb = new GraphicBuilder();
        gb.build(array);
        XSSFWorkbook book = new XSSFWorkbook();
        FileOutputStream fos = new FileOutputStream("Monte-Carlo.xlsx");
        XSSFSheet resSheet = book.createSheet("Данные частотных распределений");

        Analyzer.sortArr(array);
        for (int i = 0; i < array.length; i++) {
            XSSFRow row = resSheet.createRow(i);
            XSSFCell cell1 = row.createCell(0);
            cell1.setCellType(CellType.NUMERIC);
            cell1.setCellValue(array[i][0]);
            XSSFCell cell2 = row.createCell(1);
            cell2.setCellType(CellType.NUMERIC);
            cell2.setCellValue(array[i][1]);
        }

        XSSFSheet analyzingSheet = book.createSheet("ЧР");
        XSSFRow row1 = analyzingSheet.createRow(0);
        XSSFCell cell1 = row1.createCell(0);
        cell1.setCellType(CellType.STRING);
        cell1.setCellValue("Максимум");

        XSSFCell cell11 = row1.createCell(1);
        cell11.setCellType(CellType.NUMERIC);
        cell11.setCellValue(Analyzer.getMaxValue(array));

        XSSFRow row2 = analyzingSheet.createRow(1);
        XSSFCell cell2 = row2.createCell(0);
        cell2.setCellType(CellType.STRING);
        cell2.setCellValue("Минимум");

        XSSFCell cell21 = row2.createCell(1);
        cell21.setCellType(CellType.NUMERIC);
        cell21.setCellValue(Analyzer.getMinValue(array));

        XSSFRow row3 = analyzingSheet.createRow(2);
        XSSFCell cell3 = row3.createCell(0);
        cell3.setCellType(CellType.STRING);
        cell3.setCellValue("Среднее");

        XSSFCell cell31 = row3.createCell(1);
        cell31.setCellType(CellType.NUMERIC);
        cell31.setCellValue(Analyzer.getAverage(array));

        book.write(fos);
        fos.close();
    }
}
