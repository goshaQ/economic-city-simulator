package com.itproject.game;

import com.itproject.game.buildings.*;

public class Citizen {
	 public enum Occupation {IRONPLANTWORKER, OILPLANTWORKER, TRADER, SELLER, BANKER, CLERK, DOCTOR, WATERSTATIONWORKER, POWERSTATIONWORKER, UNEMPLOYED}

	    public Occupation occupation;
	    public final Worldview.WorldviewType worldview;

	    private final Interval week = new Interval((byte)7, (byte)0, (short)0);

	    private short daysWithoutUpdate;
	    public Interval age;
	    public byte happinessLevel;
	    public float biasedDeathProbability;

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
	        this.biasedDeathProbability = 0f;
	        this.happinessLevel = 70;
	        this.moneySavings = moneyFromParent;
	        this.occupation = Occupation.UNEMPLOYED;
	        this.ageOfLastProcreation = new Interval((byte)0, (byte)0, (short)0);
	    }

	    public Citizen(Worldview.WorldviewType worldview, Interval age, short moneyFromParent) {
	        this.worldview = worldview;

	        this.age = age;
	        this.daysWithoutUpdate = 0;
	        this.biasedDeathProbability = 0f;
	        this.happinessLevel = 70;
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
	                if (house != null) {
	                    if (!((monthlyExpenses = house.payUtility(this)) > 0)) {
	                        house.moveOut(this);
	                    }
	                }

	                //go to the grocery shop
	                int productCost = 0;

	                for (Building building : City.buildings) {
	                    if (building instanceof GroceryShop) {
	                        productCost = ((GroceryShop) building).visitGroceryShop(this);
	                        if (productCost > 0) {
	                            break;
	                        }
	                    }
	                }

	                if (productCost > 0) {
	                    if (biasedDeathProbability >= 0.5) {
	                        biasedDeathProbability -= 0.5f;
	                    }

	                    monthlyExpenses += productCost;
	                } else {
	                    biasedDeathProbability += 0.5f;
	                    happinessLevel -= 12;
	                }

	                // TODO spend money
	                int throwDie = City.BPRNG.nextByte(City.expensesStatistics, (short)10);
	                switch (throwDie) {
	                    // go to park
	                    case 0:
	                        for (Building building : City.buildings) {
	                            if (building instanceof Park) {
	                                ((Park) building).visitPark(this);
	                            }
	                        }
	                        break;
	                    // go to bar
	                    case 1:
	                        int beerCost = 0;

	                        for (Building building : City.buildings) {
	                            if (building instanceof GroceryShop) {
	                                beerCost = ((GroceryShop) building).visitGroceryShop(this);
	                                if (productCost > 0) {
	                                    break;
	                                }
	                            }
	                        }

	                        if (beerCost > 0) {
	                            monthlyExpenses += beerCost;
	                            happinessLevel += 4;
	                        }
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
	                        }

	                        if (purchasePrice > 0) {
	                            monthlyExpenses += purchasePrice;
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
	                        }

	                        if (treatmentBill > 0) {
	                            biasedDeathProbability -= 0.005f;
	                            monthlyExpenses += treatmentBill;
	                        } else {
	                            biasedDeathProbability += 0.01f;
	                            happinessLevel -= 6;
	                        }

	                        break;
	                }

	                //System.out.println("Total monthly expenses: " + monthlyExpenses + " Total money savings: " + moneySavings + " My salary: " + salary);
	                if ((moneySavings -= monthlyExpenses) >= 0) {
	                } else {
	                    //TODO became a homeless
	                    moneySavings = 0;
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

	        float deathProbability;
	        if ((deathProbability = City.deathStatistics[age.getYear()] + biasedDeathProbability) >= 0.999f) {
	            deathProbability = 0.999f;
	        }

	        float[] deathStatistics = new float[2];
	        deathStatistics[0] = 1 - deathProbability;
	        deathStatistics[1] = deathProbability;

	        throwDie = City.BPRNG.nextByte(deathStatistics, (short)1000);
	        if (throwDie == 0) {
	            return true;
	        } else {
	            return false;
	        }
	    }

}
