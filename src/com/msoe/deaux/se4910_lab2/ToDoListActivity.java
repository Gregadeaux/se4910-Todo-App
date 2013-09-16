package com.msoe.deaux.se4910_lab2;

import java.util.HashMap;

import com.joshdholtz.trajectory.Trajectory;
import com.joshdholtz.trajectory.Trajectory.Route;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.Menu;

public class ToDoListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        
        this.registerRoutes();
        Trajectory.call("/todos");
    }
    
    private void registerRoutes() {
    	Trajectory.setRoute("/todos", routeTodoList);
    	Trajectory.setRoute("/todo", routeTodoDetail);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.to_do_list, menu);
        return true;
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
