package com.msoe.deaux.se4910_lab2.models;

import java.io.Serializable;

import com.msoe.deaux.se4910_lab2.adapters.SerializableTime;



public class Todo implements Serializable{

	private static final long serialVersionUID = -6745481096921359029L;
	private String text;
	private SerializableTime date;
	private SerializableTime time;
	private Priority priority;
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	public SerializableTime getDate() {
		return date;
	}

	public void setDate(SerializableTime date) {
		this.date = date;
	}

	public SerializableTime getTime() {
		return time;
	}

	public void setTime(SerializableTime time) {
		this.time = time;
	}

	public Priority getPriority() {
		if(priority == null) {
			priority = Priority.NONE;
		}
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public enum Priority {
		NONE,
		HIGH,
		MEDIUM,
		LOW
	}
}
