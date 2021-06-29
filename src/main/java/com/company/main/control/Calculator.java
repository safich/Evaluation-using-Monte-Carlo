package com.company.main.control;

import com.company.main.model.Storage;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.poi.ss.formula.functions.Irr;

import java.util.Arrays;

public class Calculator {
    private Storage storage;
    private double riskProbLev;
    private double discRate = 0.12;

    public Calculator(Storage storage) {
        this.storage = storage;
    }

    public double getNormDist(double mean, double standDev) {
        riskProbLev = Math.random();
        NormalDistribution distribution = new NormalDistribution(mean, standDev);
        return distribution.inverseCumulativeProbability(riskProbLev);
    }

    public double getNpv() {
        double npv = 0;
        double[] array = storage.getDiscMonFlow();
        for (double d: array) {
            npv = npv + d;
        }
        return npv;
    }

    public double getIrr() {
        return Irr.irr(storage.getMonFlow());
    }

    public double getPi() {
        double res;
        double sum1 = 0;
        double sum2;
        for (int i = 2; i < storage.getPeriod() - 1; i++) {
            sum1 = sum1 + storage.getDiscMonFlow()[i];
        }
        sum2 = storage.getDiscMonFlow()[0] + storage.getDiscMonFlow()[1];
        res = sum1/(-1 * sum2);
        return res;
    }

    private double getNpvForRevRes(double mean, double standDev, int year) {
        int y = 0;
        switch (year) {
            case (2021): y = 0;
            case (2022): y = 1;
            case (2023): y = 2;
            case (2024): y = 3;
            case (2025): y = 4;
            case (2026): y = 5;
            case (2027): y = 6;
            case (2028): y = 7;
            case (2029): y = 8;
            case (2030): y = 9;
            case (2031): y = 10;
            case (2032): y = 11;
            case (2033): y = 12;
            case (2034): y = 13;
            case (2035): y = 14;
            case (2036): y = 15;
            case (2037): y = 16;
        }
        double npv = ((((getNormDist(mean,standDev) - storage.getCostRes()[y] - storage.getSevCost()[y] - storage.getDep()[y] - storage.getOpRev()[y])
                - storage.getIntPays()[y]) - storage.getRevTaxPays()[y]) - storage.getCapex()[y])/Math.pow((1 + discRate),(year - storage.getRepPeriod()));
        for (int i = 1; i < 16; i++) {
            npv = npv + storage.getDiscMonFlow()[i];
        }
        return npv;
    }

    public double[] calcNpvForRevRes(double mean, double standDev, int testsNum, int year) {
        double[] array = new double[testsNum];
        for (int i = 0; i < testsNum; i++) {
            array[i] = getNpvForRevRes(mean, standDev, year);
        }
        Arrays.sort(array);
        return array;
    }
}
