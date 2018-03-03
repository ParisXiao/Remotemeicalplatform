// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BackPasswordActivity_ViewBinding<T extends BackPasswordActivity> implements Unbinder {
  protected T target;

  @UiThread
  public BackPasswordActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.user = Utils.findRequiredViewAsType(source, R.id.pass_ed_username, "field 'user'", EditText.class);
    target.pihon = Utils.findRequiredViewAsType(source, R.id.pass_ed_pihon, "field 'pihon'", EditText.class);
    target.validation = Utils.findRequiredViewAsType(source, R.id.pass_phion_validation, "field 'validation'", TextView.class);
    target.ed_validation = Utils.findRequiredViewAsType(source, R.id.pass_ed_validation, "field 'ed_validation'", EditText.class);
    target.new_pass = Utils.findRequiredViewAsType(source, R.id.new_ed_passworld, "field 'new_pass'", EditText.class);
    target.new_pass_ok = Utils.findRequiredViewAsType(source, R.id.new_ed_passworld_ok, "field 'new_pass_ok'", EditText.class);
    target.registered = Utils.findRequiredViewAsType(source, R.id.re_tv_registered, "field 'registered'", TextView.class);
    target.pass_number2 = Utils.findRequiredViewAsType(source, R.id.pass_number2, "field 'pass_number2'", TextView.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
    target.linear = Utils.findRequiredViewAsType(source, R.id.linear, "field 'linear'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.user = null;
    target.pihon = null;
    target.validation = null;
    target.ed_validation = null;
    target.new_pass = null;
    target.new_pass_ok = null;
    target.registered = null;
    target.pass_number2 = null;
    target.back = null;
    target.linear = null;

    this.target = null;
  }
}
