// Generated code from Butter Knife. Do not modify!
package com.msoe.deaux.se4910_lab2_adapters;

import android.view.View;
import butterknife.Views.Finder;

public class TodoListAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.msoe.deaux.se4910_lab2_adapters.TodoListAdapter target, Object source) {
    View view;
    view = finder.findById(source, 2131230727);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230727' for field 'textView' was not found. If this field binding is optional add '@Optional'.");
    }
    target.textView = (android.widget.TextView) view;
    view = finder.findById(source, 2131230726);
    target.container = (android.view.View) view;
  }

  public static void reset(com.msoe.deaux.se4910_lab2_adapters.TodoListAdapter target) {
    target.textView = null;
    target.container = null;
  }
}
