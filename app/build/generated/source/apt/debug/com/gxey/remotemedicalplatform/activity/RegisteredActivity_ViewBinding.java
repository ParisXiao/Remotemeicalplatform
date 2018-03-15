// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisteredActivity_ViewBinding<T extends RegisteredActivity> implements Unbinder {
  protected T target;

  private View view2131624123;

  @UiThread
  public RegisteredActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mTVTitleName = Utils.findRequiredViewAsType(source, R.id.tv_title_name, "field 'mTVTitleName'", TextView.class);
    target.reUsername = Utils.findRequiredViewAsType(source, R.id.re_username, "field 'reUsername'", TextView.class);
    target.reEdUsername = Utils.findRequiredViewAsType(source, R.id.re_ed_username, "field 'reEdUsername'", EditText.class);
    target.reLine1 = Utils.findRequiredViewAsType(source, R.id.re_line1, "field 'reLine1'", TextView.class);
    target.reName = Utils.findRequiredViewAsType(source, R.id.re_name, "field 'reName'", TextView.class);
    target.reEdName = Utils.findRequiredViewAsType(source, R.id.re_ed_name, "field 'reEdName'", EditText.class);
    target.reLine2 = Utils.findRequiredViewAsType(source, R.id.re_line2, "field 'reLine2'", TextView.class);
    target.reSix = Utils.findRequiredViewAsType(source, R.id.re_six, "field 'reSix'", TextView.class);
    target.reRadioMan = Utils.findRequiredViewAsType(source, R.id.re_radio_man, "field 'reRadioMan'", RadioButton.class);
    target.reRadioWuman = Utils.findRequiredViewAsType(source, R.id.re_radio_wuman, "field 'reRadioWuman'", RadioButton.class);
    target.rePihon = Utils.findRequiredViewAsType(source, R.id.re_pihon, "field 'rePihon'", TextView.class);
    target.reEdPihon = Utils.findRequiredViewAsType(source, R.id.re_ed_pihon, "field 'reEdPihon'", EditText.class);
    target.rePhionValidation = Utils.findRequiredViewAsType(source, R.id.re_phion_validation, "field 'rePhionValidation'", TextView.class);
    target.reLine3 = Utils.findRequiredViewAsType(source, R.id.re_line3, "field 'reLine3'", TextView.class);
    target.reValidation = Utils.findRequiredViewAsType(source, R.id.re_validation, "field 'reValidation'", TextView.class);
    target.reEdValidation = Utils.findRequiredViewAsType(source, R.id.re_ed_validation, "field 'reEdValidation'", EditText.class);
    target.reNumber = Utils.findRequiredViewAsType(source, R.id.re_number, "field 'reNumber'", TextView.class);
    target.reNumber2 = Utils.findRequiredViewAsType(source, R.id.re_number2, "field 'reNumber2'", TextView.class);
    target.reNumber3 = Utils.findRequiredViewAsType(source, R.id.re_number3, "field 'reNumber3'", TextView.class);
    target.reLine4 = Utils.findRequiredViewAsType(source, R.id.re_line4, "field 'reLine4'", TextView.class);
    target.reRadioOk = Utils.findRequiredViewAsType(source, R.id.re_radio_ok, "field 'reRadioOk'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.re_tv_registered, "field 'reTvRegistered' and method 'onClick'");
    target.reTvRegistered = Utils.castView(view, R.id.re_tv_registered, "field 'reTvRegistered'", TextView.class);
    view2131624123 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.agreement = Utils.findRequiredViewAsType(source, R.id.agreement, "field 'agreement'", TextView.class);
    target.ll_re_number2 = Utils.findRequiredViewAsType(source, R.id.ll_re_number2, "field 'll_re_number2'", LinearLayout.class);
    target.mRGSix = Utils.findRequiredViewAsType(source, R.id.rg_six, "field 'mRGSix'", RadioGroup.class);
    target.pass = Utils.findRequiredViewAsType(source, R.id.re_ed_pass, "field 'pass'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mTVTitleName = null;
    target.reUsername = null;
    target.reEdUsername = null;
    target.reLine1 = null;
    target.reName = null;
    target.reEdName = null;
    target.reLine2 = null;
    target.reSix = null;
    target.reRadioMan = null;
    target.reRadioWuman = null;
    target.rePihon = null;
    target.reEdPihon = null;
    target.rePhionValidation = null;
    target.reLine3 = null;
    target.reValidation = null;
    target.reEdValidation = null;
    target.reNumber = null;
    target.reNumber2 = null;
    target.reNumber3 = null;
    target.reLine4 = null;
    target.reRadioOk = null;
    target.reTvRegistered = null;
    target.agreement = null;
    target.ll_re_number2 = null;
    target.mRGSix = null;
    target.pass = null;

    view2131624123.setOnClickListener(null);
    view2131624123 = null;

    this.target = null;
  }
}
