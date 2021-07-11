package com.company.main.model;

import java.util.HashMap;

public class MainStorage {
    private HashMap<Integer, Double> capex;
    private HashMap<Integer, Double> revRes;
    private HashMap<Integer, Double> costRes;
    private HashMap<Integer, Double> sevCost;
    private HashMap<Integer, Double> dep;
    private HashMap<Integer, Double> commCost;
    private HashMap<Integer, Double> opRev;
    private HashMap<Integer, Double> intPays;
    private HashMap<Integer, Double> revBefPays;
    private HashMap<Integer, Double> revTaxPays;
    private HashMap<Integer, Double> cleanRev;
    private HashMap<Integer, Double> monFlow;
    private HashMap<Integer, Double> discMonFlow;
    private final double discRate;
    private final int repPeriod;
    private double npv;
    private double irr;
    private double pi;

    public MainStorage() {
        capex = new HashMap<>();
        revRes = new HashMap<>();
        costRes = new HashMap<>();
        sevCost = new HashMap<>();
        dep = new HashMap<>();
        commCost = new HashMap<>();
        opRev = new HashMap<>();
        intPays = new HashMap<>();
        revBefPays = new HashMap<>();
        revTaxPays = new HashMap<>();
        cleanRev = new HashMap<>();
        monFlow = new HashMap<>();
        discMonFlow = new HashMap<>();

        discRate = 0.12;
        repPeriod = 17;
    }

    public void setCapex(Integer year, Double value) {
        capex.put(year, value);
    }

    public void setRevRes(Integer year, Double value) {
        revRes.put(year, value);
    }

    public void setCostRes(Integer year, Double value) {
        costRes.put(year, value);
    }

    public void setSevCost(Integer year, Double value) {
        sevCost.put(year, value);
    }

    public void setDep(Integer year, Double value) {
        dep.put(year, value);
    }

    public void setCommCost(Integer year, Double value) {
        commCost.put(year, value);
    }

    public void setOpRev(Integer year, Double value) {
        opRev.put(year, value);
    }

    public void setIntPays(Integer year, Double value) {
        intPays.put(year, value);
    }

    public void setRevBefPays(Integer year, Double value) {
        revBefPays.put(year, value);
    }

    public void setRevTaxPays(Integer year, Double value) {
        revTaxPays.put(year, value);
    }

    public void setCleanRev(Integer year, Double value) {
        cleanRev.put(year, value);
    }

    public void setMonFlow(Integer year, Double value) {
        monFlow.put(year, value);
    }

    public void setDiscMonFlow(Integer year, Double value) {
        discMonFlow.put(year, value);
    }

    public void setNpv(double npv) {
        this.npv = npv;
    }

    public void setIrr(double irr) {
        this.irr = irr;
    }

    public void setPi(double pi) {
        this.pi = pi;
    }

    public double getCapex(int year) {
        return capex.get(year);
    }

    public double getRevRes(int year) {
        return revRes.get(year);
    }

    public double getCostRes(int year) {
        return costRes.get(year);
    }

    public double getSevCost(int year) {
        return sevCost.get(year);
    }

    public double getDep(int year) {
        return dep.get(year);
    }

    public double getCommCost(int year) {
        return commCost.get(year);
    }

    public double getOpRev(int year) {
        return opRev.get(year);
    }

    public double getIntPays(int year) {
        return intPays.get(year);
    }

    public double getRevBefPays(int year) {
        return revBefPays.get(year);
    }

    public double getRevTaxPays(int year) {
        return revTaxPays.get(year);
    }

    public double getCleanRev(int year) {
        return cleanRev.get(year);
    }

    public double getMonFlow(int year) {
        return monFlow.get(year);
    }

    public double getDiscMonFlow(int year) {
        return discMonFlow.get(year);
    }

    public double getDiscRate() {
        return discRate;
    }

    public int getRepPeriod() {
        return repPeriod;
    }

    public double getNpv() {
        return npv;
    }

    public double getIrr() {
        return irr;
    }

    public double getPi() {
        return pi;
    }

    public HashMap<Integer, Double> getMonFlowMap() {
        return monFlow;
    }

    public HashMap<Integer, Double> getDiscMonFlowMap() {
        return discMonFlow;
    }
}
