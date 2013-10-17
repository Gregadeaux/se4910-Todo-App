package com.msoe.deaux.se4910_lab2.fragments;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.joshdholtz.trajectory.Trajectory;
import com.msoe.deaux.se4910_lab2.R;
import com.msoe.deaux.se4910_lab2.adapters.TodoListAdapter;
import com.msoe.deaux.se4910_lab2.adapters.TodoListAdapter.TodoListAdapterListener;
import com.msoe.deaux.se4910_lab2.models.Todo;
import com.msoe.deaux.se4910_lab2.models.Todo.Priority;
import com.msoe.deaux.se4910_lab2.util.TodoDBQueryHelper;

import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Views;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ListFragment extends Fragment implements TodoListAdapterListener{
	
	/**
	 * This project uses Butterknife 3.0
	 * Simplifies UI/Controller interactions
	 * Uses annotations to handle the tedious this.getActivity().findViewById(R.id.view)
	 * Instead it's just this:
	 * @InjectView(R.id.view) View view
	 * This initializes the view with the appropriate layout element.
	 */
	@InjectView(R.id.fragment_list_listview) ListView list;
	@InjectView(R.id.fragment_list_add) EditText editText; 
	
	private List<Todo> todos;
	private ArrayAdapter<Todo> todoAdapter;
	private TodoDBQueryHelper queryHelper;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRetainInstance(true);
		setHasOptionsMenu(true);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_list, container, false);
		// Butterknife 3.0 initialization
		Views.inject(this, v);
		queryHelper = TodoDBQueryHelper.getTodoQueryHelper();
		
		todos = (List<Todo>) this.getArguments().getSerializable("todos");
		
		todoAdapter = new TodoListAdapter(this.getActivity(), todos, this);
		list.setAdapter(todoAdapter);
		
        return v;
    }
	
	@OnClick(R.id.fragment_list_add_button)
	public void addTodo() {
		String text = editText.getText().toString().trim();
		if(!TextUtils.isEmpty(text)) {
			Todo newTodo = new Todo();
			newTodo.setText(text);
			todos.add(0, newTodo);
			todoAdapter.notifyDataSetChanged();
//			queryHelper.addTodo(newTodo);
		}
		
		editText.setText("");
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
    	System.out.println("HERE I AM");
        switch (item.getItemId()) {
            case R.id.menu_sort_by_priority:
                Collections.sort(todos, new Comparator<Todo>() {

					@Override
					public int compare(Todo left, Todo right) {
						System.out.println(left.getPriority().ordinal() - right.getPriority().ordinal());
						return left.getPriority().ordinal() - right.getPriority().ordinal();
					}
                	
                });
                todoAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    	inflater.inflate(R.menu.to_do_list, menu);
    	super.onCreateOptionsMenu(menu, inflater);
    }

	@Override
	public void onDestroy() {
		super.onDestroy();
		Views.reset(this);
	}

	@Override
	public void todoClicked(int position, Todo todo) {
		Bundle extras = new Bundle();
		extras.putInt("position", position);
		extras.putSerializable("todo", todo);
		
		Trajectory.call("/todo", extras);
	}

	@Override
	public void todoDeleted(int position, Todo todo) {
		System.out.println(todo.getRowid());
//		queryHelper.deleteTodo(todo);
		todos.remove(position);
		todoAdapter.notifyDataSetChanged();
		Toast.makeText(getActivity(), "Todo Deleted", Toast.LENGTH_SHORT).show();
	}
}
