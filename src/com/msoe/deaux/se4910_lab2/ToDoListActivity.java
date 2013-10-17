package com.msoe.deaux.se4910_lab2;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.joshdholtz.trajectory.Trajectory;
import com.joshdholtz.trajectory.Trajectory.Route;
import com.msoe.deaux.se4910_lab2.adapters.TodoListAdapter;
import com.msoe.deaux.se4910_lab2.fragments.ListFragment;
import com.msoe.deaux.se4910_lab2.fragments.TodoFragment;
import com.msoe.deaux.se4910_lab2.models.Todo;
import com.msoe.deaux.se4910_lab2.models.Todo.Priority;
import com.msoe.deaux.se4910_lab2.util.TodoDBQueryHelper;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class ToDoListActivity extends Activity {

	private List<Todo> todos;
	private TodoDBQueryHelper queryHelper;
	
    @SuppressWarnings("unchecked")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
		queryHelper = TodoDBQueryHelper.getTodoQueryHelper();
		queryHelper.init(this);
        
		todos = queryHelper.selectAll();
		
        this.registerRoutes();
        
        Bundle bundle = new Bundle();
        bundle.putSerializable("todos", (Serializable) todos);
        
        Trajectory.call("/todos", bundle);
    }
    
    private void registerRoutes() {
    	Trajectory.setRoute("/todos", routeTodoList);
    	Trajectory.setRoute("/todo", routeTodoDetail);
    }
    
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		  super.onSaveInstanceState(savedInstanceState);
		  savedInstanceState.putSerializable("todos", (Serializable) todos);
		  TodoDBQueryHelper.getTodoQueryHelper().close();
	}
    
	private void replaceFragment(Fragment fragment) {
		fragment.setRetainInstance(false);

		FragmentTransaction ft = this.getFragmentManager().beginTransaction();
		ft.replace(R.id.activity_todo_fragment_container, fragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	final Route routeTodoDetail = new Route() {
		@Override
		public void onRoute(String route, HashMap<String, String> routeParams,
				HashMap<String, String> queryParams) {
			Fragment fragment = new TodoFragment();
			fragment.setArguments(this.getExtras());
			replaceFragment(fragment);
		}
	};
	
	final Route routeTodoList = new Route() {
		@Override
		public void onRoute(String route, HashMap<String, String> routeParams,
				HashMap<String, String> queryParams) {
			Fragment fragment = new ListFragment();
			fragment.setArguments(this.getExtras());
			replaceFragment(fragment);
		}
	};
}
