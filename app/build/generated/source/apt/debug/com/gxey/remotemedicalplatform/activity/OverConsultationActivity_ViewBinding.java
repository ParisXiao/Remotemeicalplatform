// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OverConsultationActivity_ViewBinding<T extends OverConsultationActivity> implements Unbinder {
  protected T target;

  @UiThread
  public OverConsultationActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mETContent = Utils.findRequiredViewAsType(source, R.id.editText2, "field 'mETContent'", EditText.class);
    target.mTVSend = Utils.findRequiredViewAsType(source, R.id.tv_sned, "field 'mTVSend'", TextView.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
    target.mLVMesage = Utils.findRequiredViewAsType(source, R.id.lv_message, "field 'mLVMesage'", ListView.class);
    target.mTVCancleDoctor = Utils.findRequiredViewAsType(source, R.id.oc_tv8, "field 'mTVCancleDoctor'", TextView.class);
    target.mmRLWait = Utils.findRequiredViewAsType(source, R.id.rl_wait, "field 'mmRLWait'", RelativeLayout.class);
    target.ivSendImage = Utils.findRequiredViewAsType(source, R.id.iv_send_img, "field 'ivSendImage'", ImageView.class);
    target.ivScreenshot = Utils.findRequiredViewAsType(source, R.id.iv_screenshot, "field 'ivScreenshot'", ImageView.class);
    target.mTVWaitNumber = Utils.findRequiredViewAsType(source, R.id.oc_tv4, "field 'mTVWaitNumber'", TextView.class);
    target.mTVWaitPersion = Utils.findRequiredViewAsType(source, R.id.oc_tv6, "field 'mTVWaitPersion'", TextView.class);
    target.mIVDoctorHead = Utils.findRequiredViewAsType(source, R.id.oc_img, "field 'mIVDoctorHead'", ImageView.class);
    target.mTVDoctorNmae = Utils.findRequiredViewAsType(source, R.id.oc_tv9, "field 'mTVDoctorNmae'", TextView.class);
    target.mTVPrescription = Utils.findRequiredViewAsType(source, R.id.tv_prescription, "field 'mTVPrescription'", TextView.class);
    target.mTVDoctorName = Utils.findRequiredViewAsType(source, R.id.tv_dotor_name, "field 'mTVDoctorName'", TextView.class);
    target.mIVPrescription = Utils.findRequiredViewAsType(source, R.id.oc_tv11, "field 'mIVPrescription'", ImageView.class);
    target.mIVDoc = Utils.findRequiredViewAsType(source, R.id.imageView4, "field 'mIVDoc'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mETContent = null;
    target.mTVSend = null;
    target.back = null;
    target.mLVMesage = null;
    target.mTVCancleDoctor = null;
    target.mmRLWait = null;
    target.ivSendImage = null;
    target.ivScreenshot = null;
    target.mTVWaitNumber = null;
    target.mTVWaitPersion = null;
    target.mIVDoctorHead = null;
    target.mTVDoctorNmae = null;
    target.mTVPrescription = null;
    target.mTVDoctorName = null;
    target.mIVPrescription = null;
    target.mIVDoc = null;

    this.target = null;
  }
}
