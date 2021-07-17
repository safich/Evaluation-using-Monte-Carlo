package safich.montecarlo.main.control;

import safich.montecarlo.main.model.DocReader;
import safich.montecarlo.main.model.MainStorage;
import org.junit.*;

import java.io.File;

import static org.junit.Assert.*;

public class CalculatorTest {
    private MainStorage storage;
    private Calculator calculator;
    private int testYear;

    @Before
    public void setUp() throws Exception {
        testYear = 2023;
        storage = new MainStorage();
        DocReader docReader = new DocReader(storage);
        docReader.readData(new File("C://Users/safic/Desktop/гранит/диплом/Форма модели.xlsx"));
        calculator = new Calculator(storage);
    }

    @Test
    public void calcStorageNpv() {
        double actual = calculator.calcStorageNpv();
        double expected = storage.getNpv();
        assertEquals(expected, actual, 1);
    }

    @Test
    public void calcStorageIrr() {
        double actual = calculator.calcStorageIrr();
        double expected = storage.getIrr();
        assertEquals(expected, actual, 1);
    }

    @Test
    public void calcStoragePi() {
        double actual = calculator.calcStoragePi();
        double expected = storage.getPi();
        assertEquals(expected, actual, 1);
    }

    @Test
    public void getNpvForRevRes() {
        double expected = storage.getNpv();
        double actual = calculator.getNpvForRevRes(testYear, storage.getRevRes(testYear), 0.1);
        assertEquals(expected, actual, 1);
    }


    @Test
    public void getIrrForRevRes() {
        double expected = storage.getIrr();
        double actual = calculator.getIrrForRevRes(testYear, storage.getRevRes(testYear), 0.1);
        assertEquals(expected, actual, 1);
    }

    @Test
    public void getPiForRevRes() {
        double expected = storage.getPi();
        double actual = calculator.getPiForRevRes(testYear, storage.getRevRes(testYear), 0.1);
        assertEquals(expected, actual, 0.1);
    }

    @Test
    public void getNpvForCleanRev() {
        double expected = storage.getNpv();
        double actual = calculator.getNpvForCleanRev(testYear,storage.getCleanRev(testYear), 0.1);
        assertEquals(expected, actual, 1);
    }

    @Test
    public void getIrrForCleanRev() {
        double expected = storage.getIrr();
        double actual = calculator.getIrrForCleanRev(testYear, storage.getCleanRev(testYear), 0.1);
        assertEquals(expected, actual, 1);
    }

    @Test
    public void getPiForCleanRev() {
        double expected = storage.getPi();
        double actual = calculator.getPiForCleanRev(testYear, storage.getCleanRev(testYear), 0.1);
        assertEquals(expected, actual, 0.1);
    }

    @Test
    public void getNpvForCostRes() {
        double expected = storage.getNpv();
        double actual = calculator.getNpvForCostRes(testYear,storage.getCostRes(testYear), 0.1);
        assertEquals(expected, actual, 1);
    }

    @Test
    public void getIrrForCostRes() {
        double expected = storage.getIrr();
        double actual = calculator.getIrrForCostRes(testYear, storage.getCostRes(testYear), 0.1);
        assertEquals(expected, actual, 1);
    }

    @Test
    public void getPiForCostRes() {
        double expected = storage.getPi();
        double actual = calculator.getPiForCostRes(testYear, storage.getCostRes(testYear), 0.1);
        assertEquals(expected, actual, 0.1);
    }

    @Test
    public void getNpvForOpRev() {
        double expected = storage.getNpv();
        double actual = calculator.getNpvForOpRev(testYear,storage.getOpRev(testYear), 0.1);
        assertEquals(expected, actual, 1);
    }

    @Test
    public void getIrrForOpRev() {
        double expected = storage.getIrr();
        double actual = calculator.getIrrForOpRev(testYear, storage.getOpRev(testYear), 0.1);
        assertEquals(expected, actual, 1);
    }

    @Test
    public void getPiForOpRev() {
        double expected = storage.getPi();
        double actual = calculator.getPiForOpRev(testYear, storage.getOpRev(testYear), 0.1);
        assertEquals(expected, actual, 0.1);
    }

    @Test
    public void getNpvForRevBefTax() {
        double expected = storage.getNpv();
        double actual = calculator.getNpvForRevBefTax(testYear,storage.getRevBefPays(testYear), 0.1);
        assertEquals(expected, actual, 1);
    }

    @Test
    public void getIrrForRevBefTax() {
        double expected = storage.getIrr();
        double actual = calculator.getIrrForRevBefTax(testYear, storage.getRevBefPays(testYear), 0.1);
        assertEquals(expected, actual, 1);
    }

    @Test
    public void getPiForRevBefTax() {
        double expected = storage.getPi();
        double actual = calculator.getPiForRevBefTax(testYear, storage.getRevBefPays(testYear), 0.1);
        assertEquals(expected, actual, 0.1);
    }
}
