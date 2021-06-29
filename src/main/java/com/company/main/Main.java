package com.company.main;

import com.company.main.control.StorageController;
import com.company.main.model.Storage;
import com.company.main.view.MainFrame;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        StorageController sc = new StorageController(storage);
        new MainFrame(sc);
    }
}
