package com.company.main.control;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.poi.ss.formula.functions.Irr;

public class Calculator {
    private final StorageController sc;
    private double riskProbLev;
    private int periodIndex;

    public Calculator(StorageController sc) {
        this.sc = sc;
    }

    public double getNormDist(double mean, double standDev) {
        riskProbLev = Math.random();
        NormalDistribution distribution = new NormalDistribution(mean, standDev);
        return distribution.inverseCumulativeProbability(riskProbLev);
    }

    public double calcStorageNpv() {
        double[] array = sc.getStorage().getDiscMonFlow();
        double npv = 0;
        for (double d: array) {
            npv = npv + d;
        }
        return npv;
    }

    public double calcStorageIrr() {
        return Irr.irr(sc.getStorage().getMonFlow());
    }

    public double calcStoragePi() {
        double sum1 = 0;
        double sum2;
        for (int i = 2; i < sc.getStorage().getPeriod() - 1; i++) {
            sum1 = sum1 + sc.getStorage().getDiscMonFlow()[i];
        }
        sum2 = sc.getStorage().getDiscMonFlow()[0] + sc.getStorage().getDiscMonFlow()[1];
        return sum1 / (-1 * sum2);
    }

    public double getRiskProbLev() {
        return riskProbLev;
    }

    private void definePeriod(int year) {
        periodIndex = year - sc.getStorage().getStartPeriod();
    }

    public double getNpvForRevRes(int year, double mean, double standDev) {
        definePeriod(year);
        double revRes = getNormDist(mean,standDev);
        double costRes = sc.getStorage().getCostRes()[periodIndex];
        double sevCost = sc.getStorage().getSevCost()[periodIndex];
        double dep = sc.getStorage().getDep()[periodIndex];
        double commCost = sc.getStorage().getCommCost()[periodIndex];
        double intPays = sc.getStorage().getIntPays()[periodIndex];
        double revTaxPays = sc.getStorage().getRevTaxPays()[periodIndex];
        double capex = sc.getStorage().getCapex()[periodIndex];
        double discRate = sc.getStorage().getDiscRate();
        double repPeriod = sc.getStorage().getRepPeriod();

        double monFlow = revRes - costRes - sevCost - dep - commCost - intPays - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repPeriod));
        double[] array = sc.getStorage().getDiscMonFlow();
        array[periodIndex] = discMonFlowForYear;
        double sum = 0;
        for (double d : array) {
            sum = sum + d;
        }
        return sum;
    }

    public double[][] calcNpvForRevRes(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = Math.round(getNpvForRevRes(year, mean, standDev));
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getIrrForRevRes(int year, double mean, double standDev) {
        definePeriod(year);
        double revRes = getNormDist(mean,standDev);
        double costRes = sc.getStorage().getCostRes()[periodIndex];
        double sevCost = sc.getStorage().getSevCost()[periodIndex];
        double dep = sc.getStorage().getDep()[periodIndex];
        double commCost = sc.getStorage().getCommCost()[periodIndex];
        double intPays = sc.getStorage().getIntPays()[periodIndex];
        double revTaxPays = sc.getStorage().getRevTaxPays()[periodIndex];
        double capex = sc.getStorage().getCapex()[periodIndex];

        double monFlow = revRes - costRes - sevCost - dep - commCost - intPays - revTaxPays - capex;
        double[] array = sc.getStorage().getMonFlow();
        array[periodIndex] = monFlow;
        return Irr.irr(array);
    }

    public double[][] calcIrrForRevRes(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = getIrrForRevRes(year, mean, standDev);
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getPiForRevRes(int year, double mean, double standDev) {
        definePeriod(year);
        double revRes = getNormDist(mean,standDev);
        double costRes = sc.getStorage().getCostRes()[periodIndex];
        double sevCost = sc.getStorage().getSevCost()[periodIndex];
        double dep = sc.getStorage().getDep()[periodIndex];
        double commCost = sc.getStorage().getCommCost()[periodIndex];
        double intPays = sc.getStorage().getIntPays()[periodIndex];
        double revTaxPays = sc.getStorage().getRevTaxPays()[periodIndex];
        double capex = sc.getStorage().getCapex()[periodIndex];
        double discRate = sc.getStorage().getDiscRate();
        double repPeriod = sc.getStorage().getRepPeriod();

        double monFlow = revRes - costRes - sevCost - dep - commCost - intPays - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repPeriod));
        double[] array = sc.getStorage().getDiscMonFlow();
        array[periodIndex] = discMonFlowForYear;
        double sum1 = 0;
        double sum2;
        for (int i = 2; i < sc.getStorage().getPeriod() - 1; i++) {
            sum1 = sum1 + array[i];
        }
        sum2 = array[0] + array[1];
        return sum1/(-1 * sum2);
    }

    public double[][] calcPiForRevRes(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = getPiForRevRes(year, mean, standDev);
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getNpvForCleanRev(int year, double mean, double standDev) {
        definePeriod(year);
        double discRate = sc.getStorage().getDiscRate();
        int repYear = sc.getStorage().getRepPeriod();
        double cleanRev = getNormDist(mean, standDev);
        double capex = sc.getStorage().getCapex()[periodIndex];
        double monFlow = cleanRev - capex;
        double discMonFlow = monFlow/Math.pow((1 + discRate),(year - repYear));
        return calcStorageNpv() - sc.getStorage().getDiscMonFlow()[periodIndex] + discMonFlow;
    }

    public double[][] calcNpvForCleanRev(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = Math.round(getNpvForCleanRev(year, mean, standDev));
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getIrrForCleanRev(int year, double mean, double standDev) {
        definePeriod(year);
        double cleanRev = getNormDist(mean, standDev);
        double capex = sc.getStorage().getCapex()[periodIndex];
        double monFlow = cleanRev - capex;
        double[] array = sc.getStorage().getMonFlow();
        array[periodIndex] = monFlow;
        return Irr.irr(array);
    }

    public double[][] calcIrrForCleanRev(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = getIrrForCleanRev(year, mean, standDev);
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getPiForCleanRev(int year, double mean, double standDev) {
        definePeriod(year);
        double discRate = sc.getStorage().getDiscRate();
        int repYear = sc.getStorage().getRepPeriod();
        double cleanRev = getNormDist(mean, standDev);
        double capex = sc.getStorage().getCapex()[periodIndex];
        double monFlow = cleanRev - capex;
        double discMonFlow = monFlow/Math.pow((1 + discRate),(year - repYear));
        double[] array = sc.getStorage().getDiscMonFlow();
        array[periodIndex] = discMonFlow;
        double sum1 = 0;
        double sum2;
        for (int i = 2; i < sc.getStorage().getPeriod() - 1; i++) {
            sum1 = sum1 + array[i];
        }
        sum2 = array[0] + array[1];
        return sum1/(-1 * sum2);
    }

    public double[][] calcPiForCleanRev(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = getPiForCleanRev(year, mean, standDev);
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getNpvForCostRes(int year, double mean, double standDev) {
        definePeriod(year);
        double revRes = sc.getStorage().getRevRes()[periodIndex];
        double costRes = getNormDist(mean,standDev);
        double sevCost = sc.getStorage().getSevCost()[periodIndex];
        double dep = sc.getStorage().getDep()[periodIndex];
        double commCost = sc.getStorage().getCommCost()[periodIndex];
        double intPays = sc.getStorage().getIntPays()[periodIndex];
        double revTaxPays = sc.getStorage().getRevTaxPays()[periodIndex];
        double capex = sc.getStorage().getCapex()[periodIndex];
        double discRate = sc.getStorage().getDiscRate();
        double repPeriod = sc.getStorage().getRepPeriod();

        double monFlow = revRes - costRes - sevCost - dep - commCost - intPays - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repPeriod));
        double[] array = sc.getStorage().getDiscMonFlow();
        array[periodIndex] = discMonFlowForYear;
        double sum = 0;
        for (double d : array) {
            sum = sum + d;
        }
        return sum;
    }

    public double[][] calcNpvForCostRes(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = Math.round(getNpvForCostRes(year, mean, standDev));
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getIrrForCostRes(int year, double mean, double standDev) {
        definePeriod(year);
        double revRes = sc.getStorage().getRevRes()[periodIndex];
        double costRes = getNormDist(mean,standDev);
        double sevCost = sc.getStorage().getSevCost()[periodIndex];
        double dep = sc.getStorage().getDep()[periodIndex];
        double commCost = sc.getStorage().getCommCost()[periodIndex];
        double intPays = sc.getStorage().getIntPays()[periodIndex];
        double revTaxPays = sc.getStorage().getRevTaxPays()[periodIndex];
        double capex = sc.getStorage().getCapex()[periodIndex];

        double monFlow = revRes - costRes - sevCost - dep - commCost - intPays - revTaxPays - capex;
        double[] array = sc.getStorage().getMonFlow();
        array[periodIndex] = monFlow;
        return Irr.irr(array);
    }

    public double[][] calcIrrForCostRes(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = getIrrForCostRes(year, mean, standDev);
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getPiForCostRes(int year, double mean, double standDev) {
        definePeriod(year);
        double revRes = sc.getStorage().getRevRes()[periodIndex];
        double costRes = getNormDist(mean,standDev);
        double sevCost = sc.getStorage().getSevCost()[periodIndex];
        double dep = sc.getStorage().getDep()[periodIndex];
        double commCost = sc.getStorage().getCommCost()[periodIndex];
        double intPays = sc.getStorage().getIntPays()[periodIndex];
        double revTaxPays = sc.getStorage().getRevTaxPays()[periodIndex];
        double capex = sc.getStorage().getCapex()[periodIndex];
        double discRate = sc.getStorage().getDiscRate();
        double repPeriod = sc.getStorage().getRepPeriod();

        double monFlow = revRes - costRes - sevCost - dep - commCost - intPays - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repPeriod));
        double[] array = sc.getStorage().getDiscMonFlow();
        array[periodIndex] = discMonFlowForYear;
        double sum1 = 0;
        double sum2;
        for (int i = 2; i < sc.getStorage().getPeriod() - 1; i++) {
            sum1 = sum1 + array[i];
        }
        sum2 = array[0] + array[1];
        return sum1/(-1 * sum2);
    }

    public double[][] calcPiForCostRes(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = getPiForCostRes(year, mean, standDev);
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getNpvForOpRev(int year, double mean, double standDev) {
        definePeriod(year);
        double discRate = sc.getStorage().getDiscRate();
        int repPeriod = sc.getStorage().getRepPeriod();
        double opRev = getNormDist(mean, standDev);
        double intPays = sc.getStorage().getIntPays()[periodIndex];
        double revTaxPays = sc.getStorage().getRevTaxPays()[periodIndex];
        double capex = sc.getStorage().getCapex()[periodIndex];

        double monFlow = opRev - intPays - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repPeriod));
        double[] array = sc.getStorage().getDiscMonFlow();
        array[periodIndex] = discMonFlowForYear;
        double sum = 0;
        for (double d : array) {
            sum = sum + d;
        }
        return sum;
    }

    public double[][] calcNpvForOpRev(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = getNpvForOpRev(year, mean, standDev);
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getIrrForOpRev(int year, double mean, double standDev) {
        definePeriod(year);
        double opRev = getNormDist(mean, standDev);
        double intPays = sc.getStorage().getIntPays()[periodIndex];
        double revTaxPays = sc.getStorage().getRevTaxPays()[periodIndex];
        double capex = sc.getStorage().getCapex()[periodIndex];

        double monFlow = opRev - intPays - revTaxPays - capex;
        double[] array = sc.getStorage().getMonFlow();
        array[periodIndex] = monFlow;
        return Irr.irr(array);
    }

    public double[][] calcIrrForOpRev(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = getIrrForOpRev(year, mean, standDev);
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getPiForOpRev(int year, double mean, double standDev) {
        definePeriod(year);
        double discRate = sc.getStorage().getDiscRate();
        int repPeriod = sc.getStorage().getRepPeriod();
        double opRev = getNormDist(mean, standDev);
        double intPays = sc.getStorage().getIntPays()[periodIndex];
        double revTaxPays = sc.getStorage().getRevTaxPays()[periodIndex];
        double capex = sc.getStorage().getCapex()[periodIndex];

        double monFlow = opRev - intPays - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repPeriod));
        double[] array = sc.getStorage().getDiscMonFlow();
        array[periodIndex] = discMonFlowForYear;
        double sum1 = 0;
        double sum2;
        for (int i = 2; i < sc.getStorage().getPeriod() - 1; i++) {
            sum1 = sum1 + array[i];
        }
        sum2 = array[0] + array[1];
        return sum1/(-1 * sum2);
    }

    public double[][] calcPiForOpRev(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = getPiForOpRev(year, mean, standDev);
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getNpvForRevBefTax(int year, double mean, double standDev) {
        definePeriod(year);
        double discRate = sc.getStorage().getDiscRate();
        int repYear = sc.getStorage().getRepPeriod();
        double revBefTax = getNormDist(mean, standDev);
        double revTaxPays = sc.getStorage().getRevTaxPays()[periodIndex];
        double capex = sc.getStorage().getCapex()[periodIndex];

        double monFlow = revBefTax - revTaxPays - capex;
        double discMonFlow = monFlow/Math.pow((1 + discRate),(year - repYear));
        return calcStorageNpv() - sc.getStorage().getDiscMonFlow()[periodIndex] + discMonFlow;
    }

    public double[][] calcNpvForRevBefTax(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = getNpvForRevBefTax(year, mean, standDev);
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getIrrForRevBefTax(int year, double mean, double standDev) {
        definePeriod(year);
        double revBefTax = getNormDist(mean, standDev);
        double revTaxPays = sc.getStorage().getRevTaxPays()[periodIndex];
        double capex = sc.getStorage().getCapex()[periodIndex];

        double monFlow = revBefTax - revTaxPays - capex;
        double[] array = sc.getStorage().getMonFlow();
        array[periodIndex] = monFlow;
        return Irr.irr(array);
    }

    public double[][] calcIrrForRevBefTax(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = getIrrForRevBefTax(year, mean, standDev);
            array[i][1] = getRiskProbLev();
        }
        return array;
    }

    public double getPiForRevBefTax(int year, double mean, double standDev) {
        definePeriod(year);
        double discRate = sc.getStorage().getDiscRate();
        int repYear = sc.getStorage().getRepPeriod();
        double revBefTax = getNormDist(mean, standDev);
        double revTaxPays = sc.getStorage().getRevTaxPays()[periodIndex];
        double capex = sc.getStorage().getCapex()[periodIndex];

        double monFlow = revBefTax - revTaxPays - capex;
        double discMonFlowForYear = monFlow/Math.pow((1 + discRate),(year - repYear));
        double[] array = sc.getStorage().getDiscMonFlow();
        array[periodIndex] = discMonFlowForYear;
        double sum1 = 0;
        double sum2;
        for (int i = 2; i < sc.getStorage().getPeriod() - 1; i++) {
            sum1 = sum1 + array[i];
        }
        sum2 = array[0] + array[1];
        return sum1/(-1 * sum2);
    }

    public double[][] calcPiForRevBefTax(int year, double mean, double standDev, int testsNum) {
        double[][] array = new double[testsNum][2];
        for (int i = 0; i < testsNum; i++) {
            array[i][0] = getPiForRevBefTax(year, mean, standDev);
            array[i][1] = getRiskProbLev();
        }
        return array;
    }
}
