package safich.montecarlo.main;

import safich.montecarlo.main.model.MainStorage;
import safich.montecarlo.main.view.MainFrame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MainStorage storage = new MainStorage();
        new MainFrame(storage);
    }
}
