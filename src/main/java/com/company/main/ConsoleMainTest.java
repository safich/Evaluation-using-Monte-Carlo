package com.company.main;

import com.company.main.control.Calculator;
import com.company.main.control.GraphicBuilder;
import com.company.main.model.DocReader;
import com.company.main.model.MainStorage;

import java.io.File;
import java.io.IOException;

public class ConsoleMainTest {
    public static void main(String[] args) throws IOException {
        MainStorage mainStorage = new MainStorage();
        DocReader docReader = new DocReader(mainStorage);
        docReader.readData(new File("C:путь к файлу"));
        Calculator calculator = new Calculator(mainStorage);
        GraphicBuilder gb = new GraphicBuilder();
        gb.build(calculator.calcNpvForCleanRev(2023,1200000,500000,10000));
    }
}
