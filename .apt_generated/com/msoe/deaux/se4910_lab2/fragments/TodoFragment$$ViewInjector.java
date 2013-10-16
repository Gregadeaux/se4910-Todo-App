// Generated code from Butter Knife. Do not modify!
package com.msoe.deaux.se4910_lab2.fragments;

import android.view.View;
import butterknife.Views.Finder;

public class TodoFragment$$ViewInjector {
  public static void inject(Finder finder, final com.msoe.deaux.se4910_lab2.fragments.TodoFragment target, Object source) {
    View view;
    view = finder.findById(source, 2131296261);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296261' for field 'textView' was not found. If this field binding is optional add '@Optional'.");
    }
    target.textView = (android.widget.TextView) view;
    view = finder.findById(source, 2131296263);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296263' for field 'editButton' was not found. If this field binding is optional add '@Optional'.");
    }
    target.editButton = (android.widget.ImageButton) view;
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296263' for method 'editButtonClicked' was not found. If this method binding is optional add '@Optional'.");
    }
    view.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        target.editButtonClicked();
      }
    });
    view = finder.findById(source, 2131296267);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296267' for field 'dateView' was not found. If this field binding is optional add '@Optional'.");
    }
    target.dateView = (android.widget.TextView) view;
    view = finder.findById(source, 2131296269);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296269' for field 'timeView' was not found. If this field binding is optional add '@Optional'.");
    }
    target.timeView = (android.widget.TextView) view;
    view = finder.findById(source, 2131296262);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296262' for field 'editText' was not found. If this field binding is optional add '@Optional'.");
    }
    target.editText = (android.widget.EditText) view;
    view = finder.findById(source, 2131296265);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296265' for field 'priority' was not found. If this field binding is optional add '@Optional'.");
    }
    target.priority = (android.widget.Spinner) view;
    view = finder.findById(source, 2131296266);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296266' for method 'dateTimeClicked' was not found. If this method binding is optional add '@Optional'.");
    }
    view.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        target.dateTimeClicked((android.view.View) view);
      }
    });
    view = finder.findById(source, 2131296268);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131296268' for method 'dateTimeClicked' was not found. If this method binding is optional add '@Optional'.");
    }
    view.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        target.dateTimeClicked((android.view.View) view);
      }
    });
  }

  public static void reset(com.msoe.deaux.se4910_lab2.fragments.TodoFragment target) {
    target.textView = null;
    target.editButton = null;
    target.dateView = null;
    target.timeView = null;
    target.editText = null;
    target.priority = null;
  }
}
