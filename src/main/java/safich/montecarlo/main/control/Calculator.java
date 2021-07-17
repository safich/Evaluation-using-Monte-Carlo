package safich.montecarlo.main.control;

import safich.montecarlo.main.model.MainStorage;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.poi.ss.formula.functions.Irr;

import java.util.HashMap;

public class Calculator {
    private final MainStorage storage;
    private final int startYear;
    private final int repYear;


    public Calculator(MainStorage storage) {
        this.storage = storage;
        startYear = 2021;
        repYear = 2022;
    }

    public double getNormDist(double mean, double standDev) {
        NormalDistribution distribution = new NormalDistribution(mean, standDev);
        return distribution.inverseCumulativeProbability(Math.random());
    }

    public double calcStorageNpv() {
        int y = startYear;
        double npv = 0;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            npv += storage.getDiscMonFlow(y);
            y++;
        }
        return npv;
    }

    public double calcStorageIrr() {
        double[] array = new double[storage.getRepPeriod()];
        int y = startYear;
        for (int i = 0; i < storage.getRepPeriod(); i++) {
            array[i] = storage.getMonFlow(y);
            y++;
        }
        return Irr.irr(array);
    }

    public double calcStoragePi() {
        double sum1 = 0;
        int y = startYear + 2;
        for (int i = 2; i < storage.getRepPeriod(); i++) {
            sum1 += storage.getDiscMonFlow(y);
            y++;
        }
        double sum2 = storage.getDiscMonFlow(startYear) + storage.getDiscMonFlow(startYear + 1);
        return sum1/(-1 * sum2);
    }

    public double getNpvForRevRes(int year, double mean, double standDev) {
        double revRes = getNormDist(mean,standDev);
        double costRes = storage.getCostRes(year);
        double sevCost = storage.getSevCost(year);
        double dep = storage.getDep(year);
        double commCost = storage.getCommCost(year);
        double intPays = storage.getIntPays(year);
        double revTaxPays = storage.getRevTaxPays(year);
        double capex = storage.getCapex(year);
        double discRate = storage.getDiscRate();

        double monFlow = revRes - costRes - sevCost - dep - commCost - intPays - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repYear));

        return calcStorageNpv() - storage.getDiscMonFlow(year) + discMonFlowForYear;
    }

    public double[] calcNpvForRevRes(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = Math.round(getNpvForRevRes(year, mean, standDev));
        }
        return array;
    }

    public double getIrrForRevRes(int year, double mean, double standDev) {
        double revRes = getNormDist(mean,standDev);
        double costRes = storage.getCostRes(year);
        double sevCost = storage.getSevCost(year);
        double dep = storage.getDep(year);
        double commCost = storage.getCommCost(year);
        double intPays = storage.getIntPays(year);
        double revTaxPays = storage.getRevTaxPays(year);
        double capex = storage.getCapex(year);

        double monFlowForYear = revRes - costRes - sevCost - dep - commCost - intPays - revTaxPays - capex;

        return getResForIrr(year, monFlowForYear);
    }

    public double[] calcIrrForRevRes(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = getIrrForRevRes(year, mean, standDev);
        }
        return array;
    }

    public double getPiForRevRes(int year, double mean, double standDev) {
        double revRes = getNormDist(mean,standDev);
        double costRes = storage.getCostRes(year);
        double sevCost = storage.getSevCost(year);
        double dep = storage.getDep(year);
        double commCost = storage.getCommCost(year);
        double intPays = storage.getIntPays(year);
        double revTaxPays = storage.getRevTaxPays(year);
        double capex = storage.getCapex(year);
        double discRate = storage.getDiscRate();

        double monFlow = revRes - costRes - sevCost - dep - commCost - intPays - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repYear));

        return getSumForPi(year, discMonFlowForYear);
    }

    public double[] calcPiForRevRes(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = getPiForRevRes(year, mean, standDev);
        }
        return array;
    }

    public double getNpvForCleanRev(int year, double mean, double standDev) {
        double discRate = storage.getDiscRate();
        double cleanRev = getNormDist(mean, standDev);
        double capex = storage.getCapex(year);
        double monFlow = cleanRev - capex;

        double discMonFlowForYear= monFlow/Math.pow((1 + discRate),(year - repYear));
        return calcStorageNpv() - storage.getDiscMonFlow(year) + discMonFlowForYear;
    }

    public double[] calcNpvForCleanRev(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = Math.round(getNpvForCleanRev(year, mean, standDev));
        }
        return array;
    }

    public double getIrrForCleanRev(int year, double mean, double standDev) {
        double cleanRev = getNormDist(mean, standDev);
        double capex = storage.getCapex(year);

        double monFlowForYear = cleanRev - capex;

        return getResForIrr(year, monFlowForYear);
    }

    public double[] calcIrrForCleanRev(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = getIrrForCleanRev(year, mean, standDev);
        }
        return array;
    }

    public double getPiForCleanRev(int year, double mean, double standDev) {
        double discRate = storage.getDiscRate();
        double cleanRev = getNormDist(mean, standDev);
        double capex = storage.getCapex(year);

        double monFlow = cleanRev - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repYear));

        return getSumForPi(year, discMonFlowForYear);
    }

    public double[] calcPiForCleanRev(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = getPiForCleanRev(year, mean, standDev);
        }
        return array;
    }

    public double getNpvForCostRes(int year, double mean, double standDev) {
        double revRes = storage.getRevRes(year);
        double costRes = getNormDist(mean,standDev);
        double sevCost = storage.getSevCost(year);
        double dep = storage.getDep(year);
        double commCost = storage.getCommCost(year);
        double intPays = storage.getIntPays(year);
        double revTaxPays = storage.getRevTaxPays(year);
        double capex = storage.getCapex(year);
        double discRate = storage.getDiscRate();

        double monFlow = revRes - costRes - sevCost - dep - commCost - intPays - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repYear));

        return calcStorageNpv() - storage.getDiscMonFlow(year) + discMonFlowForYear;
    }

    public double[] calcNpvForCostRes(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = Math.round(getNpvForCostRes(year, mean, standDev));
        }
        return array;
    }

    public double getIrrForCostRes(int year, double mean, double standDev) {
        double revRes = storage.getRevRes(year);
        double costRes = getNormDist(mean,standDev);
        double sevCost = storage.getSevCost(year);
        double dep = storage.getDep(year);
        double commCost = storage.getCommCost(year);
        double intPays = storage.getIntPays(year);
        double revTaxPays = storage.getRevTaxPays(year);
        double capex = storage.getCapex(year);

        double monFlowForYear = revRes - costRes - sevCost - dep - commCost - intPays - revTaxPays - capex;

        return getResForIrr(year, monFlowForYear);
    }

    public double[] calcIrrForCostRes(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = getIrrForCostRes(year, mean, standDev);
        }
        return array;
    }

    public double getPiForCostRes(int year, double mean, double standDev) {
        double revRes = storage.getRevRes(year);
        double costRes = getNormDist(mean,standDev);
        double sevCost = storage.getSevCost(year);
        double dep = storage.getDep(year);
        double commCost = storage.getCommCost(year);
        double intPays = storage.getIntPays(year);
        double revTaxPays = storage.getRevTaxPays(year);
        double capex = storage.getCapex(year);
        double discRate = storage.getDiscRate();

        double monFlow = revRes - costRes - sevCost - dep - commCost - intPays - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repYear));

        return getSumForPi(year, discMonFlowForYear);
    }

    public double[] calcPiForCostRes(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = getPiForCostRes(year, mean, standDev);
        }
        return array;
    }

    public double getNpvForOpRev(int year, double mean, double standDev) {
        double discRate = storage.getDiscRate();
        double opRev = getNormDist(mean, standDev);
        double intPays = storage.getIntPays(year);
        double revTaxPays = storage.getRevTaxPays(year);
        double capex = storage.getCapex(year);

        double monFlow = opRev - intPays - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repYear));

        return calcStorageNpv() - storage.getDiscMonFlow(year) + discMonFlowForYear;
    }

    public double[] calcNpvForOpRev(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = getNpvForOpRev(year, mean, standDev);
        }
        return array;
    }

    public double getIrrForOpRev(int year, double mean, double standDev) {
        double opRev = getNormDist(mean, standDev);
        double intPays = storage.getIntPays(year);
        double revTaxPays = storage.getRevTaxPays(year);
        double capex = storage.getCapex(year);

        double monFlowForYear = opRev - intPays - revTaxPays - capex;

        return getResForIrr(year, monFlowForYear);
    }

    public double[] calcIrrForOpRev(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = getIrrForOpRev(year, mean, standDev);
        }
        return array;
    }

    public double getPiForOpRev(int year, double mean, double standDev) {
        double discRate = storage.getDiscRate();
        double opRev = getNormDist(mean, standDev);
        double intPays = storage.getIntPays(year);
        double revTaxPays = storage.getRevTaxPays(year);
        double capex = storage.getCapex(year);

        double monFlow = opRev - intPays - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repYear));

        return getSumForPi(year, discMonFlowForYear);
    }

    public double[] calcPiForOpRev(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = getPiForOpRev(year, mean, standDev);
        }
        return array;
    }

    public double getNpvForRevBefTax(int year, double mean, double standDev) {
        double discRate = storage.getDiscRate();
        double revBefTax = getNormDist(mean, standDev);
        double revTaxPays = storage.getRevTaxPays(year);
        double capex = storage.getCapex(year);

        double monFlow = revBefTax - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repYear));

        return calcStorageNpv() - storage.getDiscMonFlow(year) + discMonFlowForYear;
    }

    public double[] calcNpvForRevBefTax(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = getNpvForRevBefTax(year, mean, standDev);
        }
        return array;
    }

    public double getIrrForRevBefTax(int year, double mean, double standDev) {
        double revBefTax = getNormDist(mean, standDev);
        double revTaxPays = storage.getRevTaxPays(year);
        double capex = storage.getCapex(year);

        double monFlowForYear = revBefTax - revTaxPays - capex;

        return getResForIrr(year, monFlowForYear);
    }

    public double[] calcIrrForRevBefTax(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = getIrrForRevBefTax(year, mean, standDev);
        }
        return array;
    }

    public double getPiForRevBefTax(int year, double mean, double standDev) {
        double discRate = storage.getDiscRate();
        double revBefTax = getNormDist(mean, standDev);
        double revTaxPays = storage.getRevTaxPays(year);
        double capex = storage.getCapex(year);

        double monFlow = revBefTax - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repYear));

        return getSumForPi(year, discMonFlowForYear);
    }

    public double[] calcPiForRevBefTax(int year, double mean, double standDev, int testsNum) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = getPiForRevBefTax(year, mean, standDev);
        }
        return array;
    }

    public double getSumForPi(int year, double discMonFlowForYear) {
        HashMap<Integer, Double> map = storage.getDiscMonFlowMap();
        map.put(year, discMonFlowForYear);
        double sum1 = 0;
        int y = startYear + 2;
        for (int i = 2; i < storage.getRepPeriod(); i++) {
            sum1 += map.get(y);
            y++;
        }
        double sum2 = map.get(startYear) + map.get(startYear + 1);
        return sum1/(-1 * sum2);
    }

    public double getResForIrr(int year, double discMonFlowForYear) {
        HashMap<Integer, Double> monFlowMap = storage.getMonFlowMap();
        monFlowMap.put(year, discMonFlowForYear);

        double[] array = new double[storage.getRepPeriod()];
        int y = startYear;

        for (int i = 0; i < array.length; i++) {
            array[i] = monFlowMap.get(y);
            y++;
        }

        return Irr.irr(array);
    }
}
