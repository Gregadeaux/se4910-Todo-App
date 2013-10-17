package com.msoe.deaux.se4910_lab2.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoDBOpenHelper extends SQLiteOpenHelper {
	static final String TODO_DATABASE_NAME = "todo";
	static final int TODO_DATABASE_VERSION = 1;
	static final String CREATE_TODO_TABLE = 
			"CREATE TABLE IF NOT EXISTS todos (" +
			"id INTEGER, " +
			"rowid INTEGER PRIMARY KEY AUTOINCREMENT ," +
			"todo STRING, " +
			"date INTEGER, " +
			"time INTEGER, " +
			"priority STRING);";
	static final String DROP_TODO_TABLE = "DROP TABLE IF EXISTS todos;";

	public TodoDBOpenHelper(Context context) {
		super(context, TODO_DATABASE_NAME, null, TODO_DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TODO_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_TODO_TABLE);
		db.execSQL(CREATE_TODO_TABLE);
	}

}
