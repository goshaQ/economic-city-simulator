package com.itproject.game;

import com.itproject.game.buildings.*;

import java.util.HashMap;

public class Budget {
    private final City city;

    public int amountMoney;

    boolean taxationType;
    byte fixedTaxPercentage;
    HashMap<Short, Byte> progressiveTaxPercentage;

    public Budget(City city) {
        this.city = city;
        amountMoney = 10000000;
    }

    public void changeBudget(int amountMoney) {
        this.amountMoney += amountMoney;
    }

    public void makeFixed(byte taxPercentage) {
        this.fixedTaxPercentage = taxPercentage;
        this.progressiveTaxPercentage = null;
        this.taxationType = false;

        city.citizens.forEach(citizen -> citizen.taxes = (short) Math.round(citizen.salary * taxPercentage / 100));
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

        city.citizens.forEach(citizen -> {
            for (short upper : progressiveTaxPercentage.keySet()) {
                if(citizen.salary < upper) {
                    citizen.taxes = (short) Math.round(citizen.salary * progressiveTaxPercentage.get(upper) / 100);
                    break;
                }
            }
        });
    }

    public void recalculateTax(Citizen citizen) {
        if (taxationType) {
            for (short upper : progressiveTaxPercentage.keySet()) {
                if(citizen.salary < upper) {
                    citizen.taxes = (short) Math.round(citizen.salary * progressiveTaxPercentage.get(upper) / 100);
                    break;
                }
            }
        } else {
            citizen.taxes = (short) Math.round(citizen.salary * fixedTaxPercentage / 100);
        }

        citizen.isSalaryChanged = false;
    }

    public void recalculateTax(Building building) {
        if (taxationType) {
            for (short upper : progressiveTaxPercentage.keySet()) {
                if (building instanceof WorldTradeCenter) {
                    if(((WorldTradeCenter) building).currentProfit < upper) {
                        ((WorldTradeCenter) building).taxes = Math.round(((WorldTradeCenter) building).currentProfit* progressiveTaxPercentage.get(upper) / 100);
                        break;
                    }
                } else if (building instanceof GroceryShop) {
                    if(((GroceryShop) building).currentProfit < upper) {
                        ((GroceryShop) building).taxes = Math.round(((GroceryShop) building).currentProfit* progressiveTaxPercentage.get(upper) / 100);
                        break;
                    }
                } else if (building instanceof Bar) {
                    if(((Bar) building).currentProfit < upper) {
                        ((Bar) building).taxes = Math.round(((Bar) building).currentProfit* progressiveTaxPercentage.get(upper) / 100);
                        break;
                    }
                } else if (building instanceof IronPlant) {
                    if(((IronPlant) building).currentProfit < upper) {
                        ((IronPlant) building).taxes = Math.round(((IronPlant) building).currentProfit* progressiveTaxPercentage.get(upper) / 100);
                        break;
                    }
                } else if (building instanceof OilPlant) {
                    if(((OilPlant) building).currentProfit < upper) {
                        ((OilPlant) building).taxes = Math.round(((OilPlant) building).currentProfit* progressiveTaxPercentage.get(upper) / 100);
                        break;
                    }
                }
            }
        } else {
            if (building instanceof WorldTradeCenter) {
                ((WorldTradeCenter) building).taxes = Math.round(((WorldTradeCenter) building).currentProfit* fixedTaxPercentage / 100);
            } else if (building instanceof GroceryShop) {
                ((GroceryShop) building).taxes = Math.round(((GroceryShop) building).currentProfit* fixedTaxPercentage / 100);
            } else if (building instanceof Bar) {
                ((Bar) building).taxes = Math.round(((Bar) building).currentProfit* fixedTaxPercentage / 100);
            } else if (building instanceof IronPlant) {
                ((IronPlant) building).taxes = Math.round(((IronPlant) building).currentProfit* fixedTaxPercentage / 100);
            } else if (building instanceof OilPlant) {
                ((OilPlant) building).taxes = Math.round(((OilPlant) building).currentProfit* fixedTaxPercentage / 100);
            }
        }
    }

    public void payTaxes(short taxes) {

    }
}
