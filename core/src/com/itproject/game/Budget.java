package com.itproject.game;

import java.util.HashMap;

public class Budget {
    private final City city;

    int amountMoney;

    boolean taxationType;
    byte fixedTaxPercentage;
    HashMap<Short, Byte> progressiveTaxPercentage;

    public Budget(City city) {
        this.city = city;
    }

    public void changeBudget(int amountMoney) {
        this.amountMoney += amountMoney;
    }

    public void makeFixed(byte taxPercentage) {
        this.fixedTaxPercentage = taxPercentage;
        this.progressiveTaxPercentage = null;
        this.taxationType = false;

        city.citizens.forEach(citizen -> citizen.tax = (short) Math.round(citizen.salary * taxPercentage / 100));
    }

    public void makeProgressive() {
        this.fixedTaxPercentage = 0;
        this.progressiveTaxPercentage = new HashMap<>();
        progressiveTaxPercentage.put((short)1000, (byte)0);
        progressiveTaxPercentage.put((short)2500, (byte)12);
        progressiveTaxPercentage.put((short)3800, (byte)16);
        progressiveTaxPercentage.put((short)6700, (byte)20);
        progressiveTaxPercentage.put((short)50000, (byte)24);

        this.taxationType = true;

        city.citizens.forEach(citizen -> progressiveTaxPercentage.keySet().forEach(upper -> {
            if(citizen.salary < upper) {
                citizen.tax = (short) Math.round(citizen.salary * progressiveTaxPercentage.get(upper) / 100);
            }
        }));
    }

    public void recalculateTax(Citizen citizen) {
        if (taxationType) {
            progressiveTaxPercentage.keySet().forEach(upper -> {
                if(citizen.salary < upper) {
                    citizen.tax = (short) Math.round(citizen.salary * progressiveTaxPercentage.get(upper) / 100);
                }
            });
        } else {
            citizen.tax = (short) Math.round(citizen.salary * fixedTaxPercentage / 100);
        }

        citizen.isSalaryChanged = false;
    }
}
