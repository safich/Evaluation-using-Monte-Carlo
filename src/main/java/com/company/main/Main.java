package com.company.main;

import com.company.main.model.MainStorage;
import com.company.main.view.MainFrame;

public class Main {
    public static void main(String[] args) {
        MainStorage storage = new MainStorage();
        new MainFrame(storage);
    }
}
