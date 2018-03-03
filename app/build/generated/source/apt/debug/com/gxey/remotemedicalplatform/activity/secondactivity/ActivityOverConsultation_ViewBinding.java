// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.opengl.GLSurfaceView;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ActivityOverConsultation_ViewBinding<T extends ActivityOverConsultation> implements Unbinder {
  protected T target;

  @UiThread
  public ActivityOverConsultation_ViewBinding(T target, View source) {
    this.target = target;

    target.glviewCall = Utils.findRequiredViewAsType(source, R.id.glview_call, "field 'glviewCall'", GLSurfaceView.class);
    target.GLTextLeft = Utils.findRequiredViewAsType(source, R.id.GL_text_left, "field 'GLTextLeft'", TextView.class);
    target.GLTextMid = Utils.findRequiredViewAsType(source, R.id.GL_text_mid, "field 'GLTextMid'", TextView.class);
    target.GLTextRight = Utils.findRequiredViewAsType(source, R.id.GL_text_right, "field 'GLTextRight'", TextView.class);
    target.GLEditSay = Utils.findRequiredViewAsType(source, R.id.GL_edit_say, "field 'GLEditSay'", EditText.class);
    target.GLBtnSend = Utils.findRequiredViewAsType(source, R.id.GL_btn_send, "field 'GLBtnSend'", ImageView.class);
    target.GLBtnLiaotian = Utils.findRequiredViewAsType(source, R.id.GL_btn_liaotian, "field 'GLBtnLiaotian'", ImageView.class);
    target.reLiaotian = Utils.findRequiredViewAsType(source, R.id.re_liaotian, "field 'reLiaotian'", RelativeLayout.class);
    target.GLBtnSendimg = Utils.findRequiredViewAsType(source, R.id.GL_btn_sendimg, "field 'GLBtnSendimg'", ImageView.class);
    target.GLBtnGetimg = Utils.findRequiredViewAsType(source, R.id.GL_btn_getimg, "field 'GLBtnGetimg'", ImageView.class);
    target.GLBtnOver = Utils.findRequiredViewAsType(source, R.id.GL_btn_over, "field 'GLBtnOver'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.glviewCall = null;
    target.GLTextLeft = null;
    target.GLTextMid = null;
    target.GLTextRight = null;
    target.GLEditSay = null;
    target.GLBtnSend = null;
    target.GLBtnLiaotian = null;
    target.reLiaotian = null;
    target.GLBtnSendimg = null;
    target.GLBtnGetimg = null;
    target.GLBtnOver = null;

    this.target = null;
  }
}
