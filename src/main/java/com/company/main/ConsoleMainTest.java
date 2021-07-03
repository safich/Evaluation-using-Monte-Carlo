package com.company.main;

import com.company.main.control.Calculator;
import com.company.main.control.StorageController;
import com.company.main.model.DocReader;
import com.company.main.model.Storage;

import java.io.File;
import java.io.IOException;

public class ConsoleMainTest {
    public static void main(String[] args) throws IOException {
        Storage storage = new Storage();
        StorageController sc = new StorageController(storage);
        DocReader docReader = new DocReader(sc);
        docReader.readData(new File("C:\\Users\\safic\\Desktop\\Форма модели.xlsx"));
        Calculator calculator = new Calculator(sc);
    }
}
