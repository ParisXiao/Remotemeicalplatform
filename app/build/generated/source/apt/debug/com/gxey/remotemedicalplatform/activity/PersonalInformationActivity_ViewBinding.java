// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PersonalInformationActivity_ViewBinding<T extends PersonalInformationActivity> implements Unbinder {
  protected T target;

  @UiThread
  public PersonalInformationActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.changpass = Utils.findRequiredViewAsType(source, R.id.xg, "field 'changpass'", TextView.class);
    target.mendian = Utils.findRequiredViewAsType(source, R.id.mendian, "field 'mendian'", TextView.class);
    target.mendianid = Utils.findRequiredViewAsType(source, R.id.mendianid, "field 'mendianid'", TextView.class);
    target.ok = Utils.findRequiredViewAsType(source, R.id.re_tv_registered, "field 'ok'", TextView.class);
    target.yonghuming = Utils.findRequiredViewAsType(source, R.id.yongmuming, "field 'yonghuming'", EditText.class);
    target.huiyaun = Utils.findRequiredViewAsType(source, R.id.huiyaun, "field 'huiyaun'", EditText.class);
    target.nicheng = Utils.findRequiredViewAsType(source, R.id.nicheng, "field 'nicheng'", EditText.class);
    target.gerenjianjei = Utils.findRequiredViewAsType(source, R.id.gerenjianjei, "field 'gerenjianjei'", EditText.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
    target.mIV1 = Utils.findRequiredViewAsType(source, R.id.iv_1, "field 'mIV1'", ImageView.class);
    target.mIV2 = Utils.findRequiredViewAsType(source, R.id.iv_2, "field 'mIV2'", ImageView.class);
    target.mIV3 = Utils.findRequiredViewAsType(source, R.id.iv_3, "field 'mIV3'", ImageView.class);
    target.mRGSix = Utils.findRequiredViewAsType(source, R.id.rg_six, "field 'mRGSix'", RadioGroup.class);
    target.radio_man = Utils.findRequiredViewAsType(source, R.id.re_radio_man, "field 'radio_man'", RadioButton.class);
    target.radio_wuman = Utils.findRequiredViewAsType(source, R.id.re_radio_wuman, "field 'radio_wuman'", RadioButton.class);
    target.sfzh = Utils.findRequiredViewAsType(source, R.id.sfzh, "field 'sfzh'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.changpass = null;
    target.mendian = null;
    target.mendianid = null;
    target.ok = null;
    target.yonghuming = null;
    target.huiyaun = null;
    target.nicheng = null;
    target.gerenjianjei = null;
    target.back = null;
    target.mIV1 = null;
    target.mIV2 = null;
    target.mIV3 = null;
    target.mRGSix = null;
    target.radio_man = null;
    target.radio_wuman = null;
    target.sfzh = null;

    this.target = null;
  }
}
