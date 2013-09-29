package com.msoe.deaux.se4910_lab2.fragments;

import java.util.Calendar;

import com.msoe.deaux.se4910_lab2.R;
import com.msoe.deaux.se4910_lab2.models.Todo;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class TodoFragment extends Fragment {

	@InjectView(R.id.fragment_todo_text) TextView textView;
	@InjectView(R.id.fragment_todo_date_text) TextView dateView;
	@InjectView(R.id.fragment_todo_time_text) TextView timeView;
	private Todo todo;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_todo, container, false);
		todo = (Todo) this.getArguments().getSerializable("todo");
		
		// Butterknife 3.0 initialization
		Views.inject(this, v);
		textView.setText(todo.getText());
		if(todo.getDate() != null) {
			dateView.setText(DateFormat.format("MMMM dd, yy", todo.getDate().toMillis(true)));
			timeView.setText(DateFormat.format("hh:mm", todo.getDate().toMillis(true)));
		}
		
		if(todo.getTime() != null) {
			timeView.setText(DateFormat.format("hh:mm", todo.getTime().toMillis(true)));
		}
		
        return v;
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
						Time time = new Time();
						time.set(0, 0, 12, dayOfMonth, monthOfYear, year);
						todo.setDate(time);
						dateView.setText(DateFormat.format("MMMM dd, yy", time.toMillis(true)));
						timeView.setText(DateFormat.format("hh:mm aa", time.toMillis(true)));
					}
					
				}, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
				break;
			case R.id.fragment_todo_time_button:
				c = Calendar.getInstance();
				new TimePickerDialog(this.getActivity(), new OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay,
							int minute) {
						Time time = new Time();
						time.set(0, minute, hourOfDay, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH), c.get(Calendar.YEAR));
						todo.setTime(time);
						timeView.setText(DateFormat.format("hh:mm aa",time.toMillis(true)));
					}
					
				}, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), false).show();
				break;
		}
	}

}
