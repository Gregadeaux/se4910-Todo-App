package com.msoe.deaux.se4910_lab2.models;

import java.io.Serializable;

import android.text.format.Time;


public class Todo implements Serializable{

	private static final long serialVersionUID = -6745481096921359029L;
	private String text;
	private Time date;
	private Time time;
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	public Time getDate() {
		return date;
	}

	public void setDate(Time date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
