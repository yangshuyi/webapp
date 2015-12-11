package com.echodrama.mongo.collection;


import com.echodrama.enumtype.RiskEnum;

/**
 * Created by shuyi on 15/12/11.
 */
public class Product {
    private int id;

    private String code;

    private String name;

    private RiskEnum risk;

    private double monthlyRise;

    private double expectedRate;

    private int duration;

    private double netValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RiskEnum getRisk() {
        return risk;
    }

    public void setRisk(RiskEnum risk) {
        this.risk = risk;
    }

    public double getMonthlyRise() {
        return monthlyRise;
    }

    public void setMonthlyRise(double monthlyRise) {
        this.monthlyRise = monthlyRise;
    }

    public double getExpectedRate() {
        return expectedRate;
    }

    public void setExpectedRate(double expectedRate) {
        this.expectedRate = expectedRate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getNetValue() {
        return netValue;
    }

    public void setNetValue(double netValue) {
        this.netValue = netValue;
    }
}
