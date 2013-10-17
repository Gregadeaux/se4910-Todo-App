package com.msoe.deaux.se4910_lab2.fragments;

import java.util.Calendar;

import com.msoe.deaux.se4910_lab2.R;
import com.msoe.deaux.se4910_lab2.models.Todo;
import com.msoe.deaux.se4910_lab2.util.SerializableTime;
import com.msoe.deaux.se4910_lab2.util.TodoReceiver;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class TodoFragment extends Fragment {

	@InjectView(R.id.fragment_todo_text) TextView textView;
	@InjectView(R.id.fragment_todo_edit_text) EditText editText;
	@InjectView(R.id.fragment_todo_date_text) TextView dateView;
	@InjectView(R.id.fragment_todo_time_text) TextView timeView;
	@InjectView(R.id.fragment_todo_priority) Spinner priority;
	@InjectView(R.id.fragment_todo_edit_button) ImageButton editButton; 
	private Todo todo;
	private boolean hasDate;
	private boolean hasTime;
	
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_todo, container, false);
		todo = (Todo) this.getArguments().getSerializable("todo");
		
		hasDate = false;
		hasTime = false;
		
		// Butterknife 3.0 initialization
		Views.inject(this, v);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.priority_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		priority.setAdapter(adapter);
		priority.setSelection(todo.getPriority().ordinal());
		priority.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				todo.setPriority(Todo.Priority.valueOf(((String) parent.getItemAtPosition(pos)).toUpperCase()));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				todo.setPriority(Todo.Priority.NONE);
			}
		});
		
		textView.setText(todo.getText());
		if(todo.getDate() != null) {
			dateView.setText(DateFormat.format("MMMM dd, yy", todo.getDate().toMillis(false)));
			timeView.setText(DateFormat.format("hh:mm", todo.getDate().toMillis(false)));
		}
		
		if(todo.getTime() != null) {
			timeView.setText(DateFormat.format("hh:mm", todo.getTime().toMillis(false)));
		}
		
        return v;
    }
	@OnClick({R.id.fragment_todo_edit_button})
	public void editButtonClicked() {
		switch(textView.getVisibility()) {
		case View.VISIBLE:
			textView.setVisibility(View.INVISIBLE);
			editText.setVisibility(View.VISIBLE);
			editButton.setImageResource(android.R.drawable.checkbox_on_background);
			
			editText.setText(todo.getText());
			break;
		case View.INVISIBLE:
			editText.setVisibility(View.INVISIBLE);
			textView.setVisibility(View.VISIBLE);
			editButton.setImageResource(android.R.drawable.ic_menu_edit);
			
			todo.setText(editText.getText().toString());
			textView.setText(todo.getText());
			break;
		}
	}
	
	@OnClick({R.id.fragment_todo_date_button, R.id.fragment_todo_time_button})
	public void dateTimeClicked(View v) {
		final Calendar c;
		
		switch(v.getId()) {
			case R.id.fragment_todo_date_button:
				c = Calendar.getInstance();
				new DatePickerDialog(this.getActivity(), new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						SerializableTime time = new SerializableTime();
						time.set(0, 0, 12, dayOfMonth, monthOfYear, year);
						todo.setDate(time);
						dateView.setText(DateFormat.format("MMMM dd, yy", time.toMillis(false)));
						timeView.setText(DateFormat.format("hh:mm aa", time.toMillis(false)));
						hasDate = true;
						if (hasTime && hasDate) setAlarm();
					}
					
				}, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
				break;
			case R.id.fragment_todo_time_button:
				c = Calendar.getInstance();
				new TimePickerDialog(this.getActivity(), new OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay,
							int minute) {
						SerializableTime time = new SerializableTime();
						time.set(0, minute, hourOfDay, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH), c.get(Calendar.YEAR));
						todo.setTime(time);
						timeView.setText(DateFormat.format("hh:mm aa",time.toMillis(false)));
						hasTime = true;
						if (hasTime && hasDate) setAlarm();
					}
					
				}, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), false).show();
				break;
		}
	}
	
	private void setAlarm() {
		int uniqueId = 723956022;
		AlarmManager am = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(getActivity(), TodoReceiver.class);
		intent.putExtra("todo", todo);

		Time date = todo.getDate();
		Time time = todo.getTime();
		
		Time datetime = new Time();
		datetime.set(time.second, time.minute, time.hour, date.monthDay, date.month, date.year);
		
		PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), uniqueId,  intent, PendingIntent.FLAG_CANCEL_CURRENT);

		am.set(AlarmManager.RTC_WAKEUP, datetime.toMillis(false), pendingIntent);
	}

}
