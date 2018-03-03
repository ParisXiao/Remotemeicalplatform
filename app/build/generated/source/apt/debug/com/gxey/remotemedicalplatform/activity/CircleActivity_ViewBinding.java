// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CircleActivity_ViewBinding<T extends CircleActivity> implements Unbinder {
  protected T target;

  @UiThread
  public CircleActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
    target.new_cir = Utils.findRequiredViewAsType(source, R.id.new_cir, "field 'new_cir'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.back = null;
    target.new_cir = null;

    this.target = null;
  }
}
