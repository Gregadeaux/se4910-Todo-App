package com.msoe.deaux.se4910_lab2;

import butterknife.InjectView;
import butterknife.Views;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TodoFragment extends Fragment {

	@InjectView(R.id.fragment_todo_text) TextView textView;
	private String text;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_todo, container, false);
		text = this.getArguments().getString("todo");
		
		// Butterknife 3.0 initialization
		Views.inject(this, v);
		textView.setText(text);
		
        return v;
    }

}
