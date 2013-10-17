package com.msoe.deaux.se4910_lab2.adapters;

import java.util.LinkedList;
import java.util.List;

import com.msoe.deaux.se4910_lab2.R;
import com.msoe.deaux.se4910_lab2.models.Todo;

import butterknife.InjectView;
import butterknife.Views;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class TodoListAdapter extends ArrayAdapter<Todo> {

	private List<Todo> todos;
	private TodoListAdapterListener listener;

	@InjectView(R.id.list_item_todo_container) View container;
	@InjectView(R.id.list_item_todo_text) TextView todoString;
	@InjectView(R.id.list_item_todo_due) TextView dateString;
	@InjectView(R.id.list_item_todo_delete) ImageButton deleteButton;
	
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
		
		final Todo todo = todos.get(position);
		System.out.println(todo.getRowid());
		todoString.setText(todo.getText());
		
		CharSequence date = " ";
		CharSequence time = " ";
		
		if(todo.getTime() != null) {
			time = DateFormat.format("hh:mm aa", todo.getTime().toMillis(false));
		}else {
			if(todo.getDate() != null) {
				time = DateFormat.format("hh:mm aa", todo.getDate().toMillis(false));
			}
		}
		
		if(todo.getDate() != null) {
			date = DateFormat.format("MMM dd, yyyy", todo.getDate().toMillis(false));
		}
	
		Resources res = getContext().getResources();
		String text = String.format(res.getString(R.string.fragment_todo_string_formatted_due_date), time, date);
		CharSequence due = Html.fromHtml(text);
		
		dateString.setText(due);
		
		container.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(listener != null) {
					listener.todoClicked(position, todo);
				}
			}
		});
		
		deleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null) {
					System.out.println(todo.getRowid());
					listener.todoDeleted(position, todo);
				}
			}
		});
		
		return v;
	}
	
	public interface TodoListAdapterListener {
		public void todoClicked(int position, Todo todo);
		public void todoDeleted(int position, Todo todo);
	}
}
