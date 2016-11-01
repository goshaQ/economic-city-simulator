package com.itproject.game;

import java.util.List;

public class Citizen {
    enum Occupation {FIREMAN, UNEMPLOYED}

    public Occupation occupation;
    public final Worldview.WorldviewType worldview;
    private final City city;

    public int age;
    public byte happinessLevel;

    public boolean isSalaryChanged;
    public short salary;
    public short tax;

    public boolean isReadyToProcreate;
    public int ageOfLastProcreation;

    public Citizen(Worldview.WorldviewType worldview, City city) {
        this.worldview = worldview;
        this.city = city;
    }

    boolean isAlive() {
        int throwDie;

        float[] deathStatistics = new float[2];
        deathStatistics[0] = 1 - City.deathStatistics[age];
        deathStatistics[1] = City.deathStatistics[age];

        throwDie = city.BPRNG.nextByte(deathStatistics, (short)1000);
        if (throwDie == 0) {
            return true;
        } else {
            return false;
        }
    }

    void die() {
        city.citizens.remove(this);
    }
}
