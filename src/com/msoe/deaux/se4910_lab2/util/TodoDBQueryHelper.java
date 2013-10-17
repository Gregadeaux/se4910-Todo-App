package com.msoe.deaux.se4910_lab2.util;

import java.util.LinkedList;
import java.util.List;

import com.msoe.deaux.se4910_lab2.models.Todo;
import com.msoe.deaux.se4910_lab2.models.Todo.Priority;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TodoDBQueryHelper {
	
	private static String SELECT_ALL_FROM_TODO = "SELECT * FROM todos ORDER BY rowid ASC;";
	private static String GET_LAST_ID_FROM_TODO = "SELECT rowid FROM todos ORDER BY rowid DESC LIMIT 1;";
	private static String INSERT_INTO_TODO = "INSERT INTO todos (todo, date, time, priority) VALUES (?, ?, ?, ?)";
	private static String UPDATE_TODO_WITH_ID =
			"UPDATE todos " +
			"SET todo = ?, " +
			"date = ?, " +
			"time = ?, " +
			"priority = ? " +
			"WHERE rowid = ?;";

	private SQLiteDatabase writeDb;
	private SQLiteDatabase readDb;
	
	private static TodoDBQueryHelper helper;
	
	//SINGLETON
	public static TodoDBQueryHelper getTodoQueryHelper() {
		if(helper == null) {
			helper = new TodoDBQueryHelper();
		}
		return helper;
	}
	
	private TodoDBQueryHelper() {}
	
	public void init(Context context) {
		TodoDBOpenHelper openHelper = new TodoDBOpenHelper(context);
		writeDb = openHelper.getWritableDatabase();
		readDb = openHelper.getReadableDatabase();
	}

	public List<Todo> selectAll() {
		List<Todo> todo = new LinkedList<Todo>();
		
		Cursor c = readDb.rawQuery(SELECT_ALL_FROM_TODO, null);
		System.out.println("SELECT ALL " + c.getCount());
		if(c != null) {
			c.moveToFirst();
			Todo temp;
			while(!c.isAfterLast()) {
				temp = new Todo();
				
				// Text
				temp.setText(c.getString(2));
				
				// Date
				SerializableTime date = new SerializableTime();
				date.set(c.getInt(3));
				temp.setDate(date);
				
				// Time
				SerializableTime time = new SerializableTime();
				time.set(c.getInt(4));
				temp.setTime(time);
				
				// Priority
				temp.setPriority(Priority.valueOf(c.getString(5).toUpperCase()));
				
				// RowId
				temp.setRowid(c.getInt(1));
				
				todo.add(temp);
				c.moveToNext();
			}
		}
		
		return todo;
	}
	
	public void addTodo(Todo todo) {
		// Adds the Todo to the database
		String[] changes = {
							todo.getText(), 
						    (todo.getTime() == null ? "null" : Long.toString(todo.getTime().toMillis(false))), 
						    (todo.getDate() == null ? "null" : Long.toString(todo.getDate().toMillis(false))), 
						    todo.getPriority().toString()
						   };
		writeDb.rawQuery(INSERT_INTO_TODO, changes);
		
		// Gets the rowid of the new todo, and assigns it to the todo
		Cursor c = readDb.rawQuery(GET_LAST_ID_FROM_TODO, null);
		c.moveToFirst();
		todo.setRowid(c.getInt(0));
	}
	
	public void deleteTodo(Todo todo) {
		String[] changes = {Integer.toString(todo.getRowid())};
		writeDb.delete("todos", "rowid = ?", changes);
	}
	
	public void updateTodo(Todo todo) {
		String[] changes = {
				todo.getText(), 
			    (todo.getTime() == null ? "null" : Long.toString(todo.getTime().toMillis(false))), 
			    (todo.getDate() == null ? "null" : Long.toString(todo.getDate().toMillis(false))), 
			    todo.getPriority().toString(),
			    Integer.toString(todo.getRowid())
			   };
		Cursor c = writeDb.rawQuery(UPDATE_TODO_WITH_ID, changes);
		System.out.println("UPDATE TODO " + c.getCount());
	}
	
	public void close() {
		writeDb.close();
		readDb.close();
	}
}
