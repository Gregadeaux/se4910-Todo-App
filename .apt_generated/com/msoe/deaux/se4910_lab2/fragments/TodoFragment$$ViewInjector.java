// Generated code from Butter Knife. Do not modify!
package com.msoe.deaux.se4910_lab2.fragments;

import android.view.View;
import butterknife.Views.Finder;

public class TodoFragment$$ViewInjector {
  public static void inject(Finder finder, final com.msoe.deaux.se4910_lab2.fragments.TodoFragment target, Object source) {
    View view;
    view = finder.findById(source, 2131230729);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230729' for field 'timeView' was not found. If this field binding is optional add '@Optional'.");
    }
    target.timeView = (android.widget.TextView) view;
    view = finder.findById(source, 2131230725);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230725' for field 'textView' was not found. If this field binding is optional add '@Optional'.");
    }
    target.textView = (android.widget.TextView) view;
    view = finder.findById(source, 2131230727);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230727' for field 'dateView' was not found. If this field binding is optional add '@Optional'.");
    }
    target.dateView = (android.widget.TextView) view;
    view = finder.findById(source, 2131230726);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230726' for method 'dateTimeClicked' was not found. If this method binding is optional add '@Optional'.");
    }
    view.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        target.dateTimeClicked((android.view.View) view);
      }
    });
    view = finder.findById(source, 2131230728);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230728' for method 'dateTimeClicked' was not found. If this method binding is optional add '@Optional'.");
    }
    view.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        target.dateTimeClicked((android.view.View) view);
      }
    });
  }

  public static void reset(com.msoe.deaux.se4910_lab2.fragments.TodoFragment target) {
    target.timeView = null;
    target.textView = null;
    target.dateView = null;
  }
}
