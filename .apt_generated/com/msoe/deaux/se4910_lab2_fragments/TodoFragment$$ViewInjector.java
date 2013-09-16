// Generated code from Butter Knife. Do not modify!
package com.msoe.deaux.se4910_lab2_fragments;

import android.view.View;
import butterknife.Views.Finder;

public class TodoFragment$$ViewInjector {
  public static void inject(Finder finder, final com.msoe.deaux.se4910_lab2_fragments.TodoFragment target, Object source) {
    View view;
    view = finder.findById(source, 2131230725);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131230725' for field 'textView' was not found. If this field binding is optional add '@Optional'.");
    }
    target.textView = (android.widget.TextView) view;
  }

  public static void reset(com.msoe.deaux.se4910_lab2_fragments.TodoFragment target) {
    target.textView = null;
  }
}
