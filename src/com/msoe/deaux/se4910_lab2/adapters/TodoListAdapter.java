package com.msoe.deaux.se4910_lab2.adapters;

import java.util.LinkedList;
import java.util.List;

import com.msoe.deaux.se4910_lab2.R;
import com.msoe.deaux.se4910_lab2.models.Todo;

import butterknife.InjectView;
import butterknife.Optional;
import butterknife.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TodoListAdapter extends ArrayAdapter<Todo> {

	private List<Todo> todos;
	private TodoListAdapterListener listener;

	@Optional @InjectView(R.id.list_item_todo_container) View container;
	@InjectView(R.id.list_item_todo_text) TextView textView;
	
	public TodoListAdapter(Context context) {
		this(context, new LinkedList<Todo>());
	}
	
	public TodoListAdapter(Context context, TodoListAdapterListener listener) {
		this(context, new LinkedList<Todo>(), listener);
	}
	
	public TodoListAdapter(Context context, List<Todo> objects) {
		this(context, objects, null);
	}
	
	public TodoListAdapter(Context context, List<Todo> objects, TodoListAdapterListener listener) {
		super(context, R.layout.list_item_todo, objects);
		
		this.todos = objects;
		this.listener = listener;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item_todo, null);
        }
		
		Views.inject(this, v);
		
		textView.setText(todos.get(position).getText());
		container.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(listener != null) {
					listener.todoClicked(position, todos.get(position));
				}
			}
		});
		
		return v;
	}
	
	public interface TodoListAdapterListener {
		public void todoClicked(int position, String todo);
	}
}
