// Generated code from Butter Knife. Do not modify!
package com.msoe.deaux.se4910_lab2.fragments;

import android.view.View;
import butterknife.Views.Finder;

public class ListFragment$$ViewInjector {
  public static void inject(Finder finder, final com.msoe.deaux.se4910_lab2.fragments.ListFragment target, Object source) {
    View view;
    view = finder.findById(source, 2131296259);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296259' for field 'editText' was not found. If this field binding is optional add '@Optional'.");
    }
    target.editText = (android.widget.EditText) view;
    view = finder.findById(source, 2131296260);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296260' for field 'list' was not found. If this field binding is optional add '@Optional'.");
    }
    target.list = (android.widget.ListView) view;
    view = finder.findById(source, 2131296258);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296258' for method 'addTodo' was not found. If this method binding is optional add '@Optional'.");
    }
    view.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        target.addTodo();
      }
    });
  }

  public static void reset(com.msoe.deaux.se4910_lab2.fragments.ListFragment target) {
    target.editText = null;
    target.list = null;
  }
}
