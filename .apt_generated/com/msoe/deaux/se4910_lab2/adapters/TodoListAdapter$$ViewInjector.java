// Generated code from Butter Knife. Do not modify!
package com.msoe.deaux.se4910_lab2.adapters;

import android.view.View;
import butterknife.Views.Finder;

public class TodoListAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.msoe.deaux.se4910_lab2.adapters.TodoListAdapter target, Object source) {
    View view;
    view = finder.findById(source, 2131296273);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296273' for field 'deleteButton' was not found. If this field binding is optional add '@Optional'.");
    }
    target.deleteButton = (android.widget.ImageButton) view;
    view = finder.findById(source, 2131296270);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296270' for field 'container' was not found. If this field binding is optional add '@Optional'.");
    }
    target.container = (android.view.View) view;
    view = finder.findById(source, 2131296271);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296271' for field 'todoString' was not found. If this field binding is optional add '@Optional'.");
    }
    target.todoString = (android.widget.TextView) view;
    view = finder.findById(source, 2131296272);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296272' for field 'dateString' was not found. If this field binding is optional add '@Optional'.");
    }
    target.dateString = (android.widget.TextView) view;
  }

  public static void reset(com.msoe.deaux.se4910_lab2.adapters.TodoListAdapter target) {
    target.deleteButton = null;
    target.container = null;
    target.todoString = null;
    target.dateString = null;
  }
}
