package com.itproject.game;

public class Interval {
    private byte day;
    private byte month;
    private short year;

    private byte[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Interval() {
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public Interval(byte day, byte month, short year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void concatenateWith(Interval anotherInterval) {
        this.year += anotherInterval.getYear();

        if (this.getMonth() + anotherInterval.getMonth() >= 12) {
            this.year++;
            this.month += (byte) (anotherInterval.getMonth() - 12);
        } else {
            this.month += anotherInterval.getMonth();
        }

        if (this.getDay() + anotherInterval.getDay() >= days[this.month]) {
            if (this.month >= 11) {
                this.year++;
                this.month = 0;
            } else {
                this.day += (byte) (anotherInterval.getDay() - days[this.month]);
                this.month++;
            }
        } else {
            this.day += anotherInterval.getDay();
        }
    }

    public Interval subtractInterval(Interval anotherInterval) {
        short year;
        byte month;
        byte day;

        year = (short) (this.year - anotherInterval.getYear());

        if (this.month >= anotherInterval.getMonth()) {
            month = (byte) (this.month - anotherInterval.getMonth());
        } else {
            year--;
            month = (byte) (days.length - anotherInterval.getMonth() + this.month);
        }

        if (this.day >= anotherInterval.getDay()) {
            day = (byte) (this.day - anotherInterval.getDay());
        } else {
            day = (byte) (days[month] - anotherInterval.getDay() + this.day);
            if (month == 0) {
                year--;
                month = (byte) (days.length - 1);
            } else {
                month--;
            }
        }

        return new Interval(day, month, year);
    }

    public short getYear() {
        return year;
    }

    public byte getMonth() {
        return month;
    }

    public byte getDay() {
        return day;
    }

    public String toString() {
        return "Day: " + day + " Month: " + month + " Year: " + year + "\n";
    }
}
