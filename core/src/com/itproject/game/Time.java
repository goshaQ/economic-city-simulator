package com.itproject.game;

public class Time {

	private byte day;
	private byte month;
	private short year;
	
	private byte[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public Time() {
		this.day = 1;
		this.month = 1;
		this.year = 2000;
	}
	
	public Time(byte day, byte month, short year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public void nextDay(){
		switch(month) {
		case 1:
			if(day < days[0]) {
				day++;
			} else {
				month++;
				day = 1;
			}
			break;
		case 2:
			if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
				days[1] = 29;
			} else {
				days[1] = 28;
			}
			if(day < days[1]) {
				day++;
			} else {
				month++;
				day = 1;
			}
			break;
		case 3:
			if(day < days[2]) {
				day++;
			} else {
				month++;
				day = 1;
			}
			break;
		case 4:
			if(day < days[3]) {
				day++;
			} else {
				month++;
				day = 1;
			}
			break;
		case 5:
			if(day < days[4]) {
				day++;
			} else {
				month++;
				day = 1;
			}
			break;
		case 6:
			if(day < days[5]) {
				day++;
			} else {
				month++;
				day = 1;
			}
			break;
		case 7:
			if(day < days[6]) {
				day++;
			} else {
				month++;
				day = 1;
			}
			break;
		case 8:
			if(day < days[7]) {
				day++;
			} else {
				month++;
				day = 1;
			}
			break;
		case 9:
			if(day < days[8]) {
				day++;
			} else {
				month++;
				day = 1;
			}
			break;
		case 10:
			if(day < days[9]) {
				day++;
			} else {
				month++;
				day = 1;
			}
			break;
		case 11:
			if(day < days[10]) {
				day++;
			} else {
				month++;
				day = 1;
			}
			break;
		case 12:
			if(day < days[11]) {
				day++;
			} else {
				month = 1;
				day = 1;
				year++;
			}
			break;
		}
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
	
	public Time getTime() {
		return this;
	}
	
	public String toString() {
		return "Day: " + day + " Month: " + month + " Year: " + year + "\n";
	}
}
