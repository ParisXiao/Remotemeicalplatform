// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MedicalApplicationActivity_ViewBinding<T extends MedicalApplicationActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MedicalApplicationActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
    target.apply = Utils.findRequiredViewAsType(source, R.id.apply, "field 'apply'", TextView.class);
    target.mLV = Utils.findRequiredViewAsType(source, R.id.medical_lv, "field 'mLV'", ListView.class);
    target.mSpinner = Utils.findRequiredViewAsType(source, R.id.spinner2, "field 'mSpinner'", Spinner.class);
    target.mETSearch = Utils.findRequiredViewAsType(source, R.id.et_search, "field 'mETSearch'", EditText.class);
    target.mTVSearch = Utils.findRequiredViewAsType(source, R.id.tv_search, "field 'mTVSearch'", TextView.class);
    target.mtVDoctorOnLine = Utils.findRequiredViewAsType(source, R.id.tv_doctor_on_line, "field 'mtVDoctorOnLine'", TextView.class);
    target.mtVDoctorNOLine = Utils.findRequiredViewAsType(source, R.id.tv_doctor_no_line, "field 'mtVDoctorNOLine'", TextView.class);
    target.mtVDoctorALl = Utils.findRequiredViewAsType(source, R.id.tv_doctor_all, "field 'mtVDoctorALl'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.back = null;
    target.apply = null;
    target.mLV = null;
    target.mSpinner = null;
    target.mETSearch = null;
    target.mTVSearch = null;
    target.mtVDoctorOnLine = null;
    target.mtVDoctorNOLine = null;
    target.mtVDoctorALl = null;

    this.target = null;
  }
}
