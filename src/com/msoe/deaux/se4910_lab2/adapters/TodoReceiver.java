package com.msoe.deaux.se4910_lab2.adapters;

import com.msoe.deaux.se4910_lab2.models.Todo;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TodoReceiver extends BroadcastReceiver {

	@SuppressLint("NewApi")
	@Override
	public void onReceive(Context context, Intent theIntent) {
		int uniqueId = 723956021;
		Todo todo = (Todo)theIntent.getSerializableExtra("todo");
		
		try {
			NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

			Notification n = new Notification.Builder(context)
	         .setContentTitle("TODO DUE")
	         .setContentText(todo.getText())
	         .setSmallIcon(android.R.drawable.ic_menu_crop)
	         .build();
			n.defaults = Notification.DEFAULT_ALL;
			n.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
			
			nm.notify(uniqueId, n);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
