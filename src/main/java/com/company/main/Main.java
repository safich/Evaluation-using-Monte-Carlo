package com.company.main;

import com.company.main.control.Calculator;
import com.company.main.model.DocReader;
import com.company.main.model.MainStorage;
import com.company.main.view.MainFrame;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MainStorage storage = new MainStorage();
        DocReader docReader = new DocReader(storage);
        docReader.readData(new File("C://Users/safic/Desktop/гранит/диплом/Форма модели.xlsx"));
        Calculator calculator = new Calculator(storage);
        System.out.println(calculator.getIrrForRevRes(2023, 4908445,1));
    }
}
