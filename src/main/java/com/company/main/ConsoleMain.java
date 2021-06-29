package com.company.main;

import com.company.main.control.Calculator;
import com.company.main.model.DocReader;
import com.company.main.model.Storage;

import java.io.File;
import java.io.IOException;

public class ConsoleMain {
    public static void main(String[] args) throws IOException {
        Storage storage = new Storage();
        DocReader docReader = new DocReader(storage);
        docReader.readData(new File("C:\\Users\\safic\\Desktop\\Форма модели.xlsx"));
        Calculator calculator = new Calculator(storage);
        System.out.println(storage.getCapex()[0]);
    }
}
