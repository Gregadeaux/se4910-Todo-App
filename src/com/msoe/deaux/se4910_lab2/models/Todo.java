package com.msoe.deaux.se4910_lab2.models;

import java.io.Serializable;

import com.msoe.deaux.se4910_lab2.util.SerializableTime;
import com.msoe.deaux.se4910_lab2.util.TodoDBQueryHelper;



public class Todo implements Serializable{

	private static final long serialVersionUID = -6745481096921359029L;
	private int rowid;
	private String text;
	private SerializableTime date;
	private SerializableTime time;
	private Priority priority;
	
	public Todo() {
		rowid = -1;
	}
	
	public void setText(String text) {
		this.text = text;
		update();
	}
	
	public String getText() {
		return text;
	}

	public SerializableTime getDate() {
		return date;
	}

	public void setDate(SerializableTime date) {
		this.date = date;
		update();
	}

	public SerializableTime getTime() {
		return time;
	}

	public void setTime(SerializableTime time) {
		this.time = time;
		update();
	}

	public Priority getPriority() {
		if(priority == null) {
			priority = Priority.NONE;
		}
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
		update();
	}

	public int getRowid() {
		return rowid;
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}
	
	private void update() {
		if(rowid != -1) {
			//TodoDBQueryHelper.getTodoQueryHelper().updateTodo(this);
		}
	}

	public enum Priority {
		NONE,
		LOW,
		MEDIUM,
		HIGH
	}
}
