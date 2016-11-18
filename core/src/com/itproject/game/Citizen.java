package com.itproject.game;

import com.itproject.game.buildings.Building;
import com.itproject.game.buildings.Hospital;
import com.itproject.game.buildings.House;
import com.itproject.game.buildings.WorldTradeCenter;

public class Citizen {
    public enum Occupation {TRADER, SELLER, BANKER, CLERCK, DOCTOR, WATERSTATIONWORKER, POWERSTATIONWORKER, FIREMAN, UNEMPLOYED}

    public Occupation occupation;
    public final Worldview.WorldviewType worldview;

    private final Interval week = new Interval((byte)7, (byte)0, (short)0);

    private short daysWithoutUpdate;
    public Interval age;
    public byte happinessLevel;

    public House house;

    public boolean isSalaryChanged;
    public int moneySavings;
    public short salary;
    public short taxes;
    public short monthlyExpenses;

    public boolean isReadyToProcreate;
    public Interval ageOfLastProcreation;

    public Citizen(Worldview.WorldviewType worldview, short moneyFromParent) {
        this.worldview = worldview;

        this.age = new Interval((byte)0, (byte)0, (short)0);
        this.daysWithoutUpdate = 0;
        this.moneySavings = moneyFromParent;
        this.occupation = Occupation.UNEMPLOYED;
        this.ageOfLastProcreation = new Interval((byte)0, (byte)0, (short)0);
    }

    public Citizen(Worldview.WorldviewType worldview, Interval age, short moneyFromParent) {
        this.worldview = worldview;

        this.age = age;
        this.daysWithoutUpdate = 0;
        this.moneySavings = moneyFromParent;
        this.occupation = Occupation.UNEMPLOYED;
        this.ageOfLastProcreation = new Interval((byte)0, (byte)0, (short)0);
    }

    public void update() {
        if (daysWithoutUpdate >= week.getDay()) {
            // inc age
            age.concatenateWith(week);
            daysWithoutUpdate -= week.getDay();

            // check whether is ready to spend money
            if (City.time.getDay() <= week.getDay()) {
                if (!((monthlyExpenses = house.payUtility(this)) > 0)) {
                    house.moveOut(this);
                }

                System.out.println(monthlyExpenses);

                monthlyExpenses += (short) ((City.PRNG.nextFloat() * 0.06 + 0.24) * salary);

                // TODO spend money
                int throwDie = City.BPRNG.nextByte(City.expensesStatistics, (short)10);
                switch (throwDie) {
                    // go to park
                    case 0:
                        break;
                    // go to bar
                    case 1:
                        break;
                    // go to world trade center
                    case 2:
                        int purchasePrice = 0;

                        for (Building building : City.buildings) {
                            if (building instanceof WorldTradeCenter) {
                                purchasePrice = ((WorldTradeCenter) building).visitWTC(this);
                                if (purchasePrice > 0) {
                                    break;
                                }
                            }

                            if (purchasePrice > 0) {
                                monthlyExpenses += purchasePrice;
                            }
                        }
                        break;
                    // go to hospital
                    case 3:
                        int treatmentBill = 0;

                        for (Building building : City.buildings) {
                            if (building instanceof Hospital) {
                                treatmentBill = ((Hospital) building).visitHospital(this);
                                if (treatmentBill > 0) {
                                    break;
                                }
                            }

                            if (treatmentBill > 0) {
                                monthlyExpenses += treatmentBill;
                            } else {
                                happinessLevel -= 8;
                            }
                        }
                        break;
                }

                if ((moneySavings -= monthlyExpenses) >= 0) {
                } else {
                    //TODO became a homeless
                    monthlyExpenses = 0;
                }
            }

            // check whether is ready to procreate
            Interval interval = age.subtractInterval(ageOfLastProcreation);
            if (!isReadyToProcreate && age.getYear() >= 18 && ((interval.getYear() == 1 && interval.getMonth() >= 8) || interval.getYear() >= 2)) {
                isReadyToProcreate = true;
            }
        } else {
            daysWithoutUpdate++;
        }
    }

    public void getSalary() {
        if (isSalaryChanged) {
            City.budget.recalculateTax(this);
        }

        City.budget.payTaxes(taxes);
        moneySavings += salary - taxes;
    }

    boolean isAlive() {
        int throwDie;

        float[] deathStatistics = new float[2];
        deathStatistics[0] = 1 - City.deathStatistics[age.getYear()];
        deathStatistics[1] = City.deathStatistics[age.getYear()];

        throwDie = City.BPRNG.nextByte(deathStatistics, (short)1000);
        if (throwDie == 0) {
            return true;
        } else {
            return false;
        }
    }

}
