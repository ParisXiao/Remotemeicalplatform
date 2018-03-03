// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HealthManagementFragment_ViewBinding<T extends HealthManagementFragment> implements Unbinder {
  protected T target;

  @UiThread
  public HealthManagementFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.healthJkda = Utils.findRequiredViewAsType(source, R.id.health_jkda, "field 'healthJkda'", LinearLayout.class);
    target.healthYczl = Utils.findRequiredViewAsType(source, R.id.health_yczl, "field 'healthYczl'", LinearLayout.class);
    target.healthDzbl = Utils.findRequiredViewAsType(source, R.id.health_dzbl, "field 'healthDzbl'", LinearLayout.class);
    target.healthFzzz = Utils.findRequiredViewAsType(source, R.id.health_fzzz, "field 'healthFzzz'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.healthJkda = null;
    target.healthYczl = null;
    target.healthDzbl = null;
    target.healthFzzz = null;

    this.target = null;
  }
}
