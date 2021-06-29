package com.company.main.model;

public class Storage {
    private double[] capex;
    private double[] goldRev;
    private double[] silverRev;
    private double[] servRev;
    private double[] fullProdCost;
    private double[] servCost;
    private double[] dep;
    private double[] intPays;
    private double[] revRes;
    private double[] costRes;
    private double[] sevCost;
    private double[] commCost;
    private double[] opRev;
    private double[] revBefTax;
    private double[] revTaxPays;
    private double[] cleanRev;
    private double[] monFlow;
    private double[] discMonFlow;

    private double discRate;
    private double sevTax;
    private double revTax;
    private double npv;
    private double irr;
    private double pi;

    private int period = 17;
    private static final int REPPERIOD = 2022;
    private int startPeriod;

    public Storage() {
        this.capex = new double[period];
        this.goldRev = new double[period];
        this.silverRev = new double[period];
        this.servRev = new double[period];
        this.fullProdCost = new double[period];
        this.servCost = new double[period];
        this.dep = new double[period];
        this.intPays = new double[period];
        this.revRes = new double[period];
        this.costRes = new double[period];
        this.sevCost = new double[period];
        this.commCost = new double[period];
        this.opRev = new double[period];
        this.revBefTax = new double[period];
        this.revTaxPays = new double[period];
        this.cleanRev = new double[period];
        this.monFlow = new double[period];
        this.discMonFlow = new double[period];

        this.discRate = 0.12;
        this.sevTax = 0.06;
        this.revTax = 0.2;

        this.startPeriod = 2021;
    }

    public double[] getCapex() {
        return capex;
    }

    public double[] getGoldRev() {
        return goldRev;
    }

    public double[] getSilverRev() {
        return silverRev;
    }

    public double[] getServRev() {
        return servRev;
    }

    public double[] getFullProdCost() {
        return fullProdCost;
    }

    public double[] getServCost() {
        return servCost;
    }

    public double[] getDep() {
        return dep;
    }

    public double[] getIntPays() {
        return intPays;
    }

    public double[] getRevRes() {
        return revRes;
    }

    public double[] getCostRes() {
        return costRes;
    }

    public double[] getSevCost() {
        return sevCost;
    }

    public double[] getCommCost() {
        return commCost;
    }

    public double[] getOpRev() {
        return opRev;
    }

    public double[] getRevBefTax() {
        return revBefTax;
    }

    public double[] getRevTaxPays() {
        return revTaxPays;
    }

    public double[] getCleanRev() {
        return cleanRev;
    }

    public double[] getMonFlow() {
        return monFlow;
    }

    public double[] getDiscMonFlow() {
        return discMonFlow;
    }

    public double getNpv() {
        return npv;
    }

    public double getIrr() {
        return irr;
    }

    public double getDiscRate() {
        return discRate;
    }

    public double getSevTax() {
        return sevTax;
    }

    public double getRevTax() {
        return revTax;
    }

    public int getPeriod() {
        return period;
    }

    public int getStartPeriod() {
        return startPeriod;
    }

    public int getRepPeriod() {
        return REPPERIOD;
    }
}
