// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyFragment_ViewBinding<T extends MyFragment> implements Unbinder {
  protected T target;

  @UiThread
  public MyFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.imgHead = Utils.findRequiredViewAsType(source, R.id.img_head, "field 'imgHead'", ImageView.class);
    target.textZcdl = Utils.findRequiredViewAsType(source, R.id.text_zcdl, "field 'textZcdl'", TextView.class);
    target.imgDingdan = Utils.findRequiredViewAsType(source, R.id.img_dingdan, "field 'imgDingdan'", ImageView.class);
    target.reDingdan = Utils.findRequiredViewAsType(source, R.id.re_dingdan, "field 'reDingdan'", RelativeLayout.class);
    target.imgQuanzi = Utils.findRequiredViewAsType(source, R.id.img_quanzi, "field 'imgQuanzi'", ImageView.class);
    target.reQuanzi = Utils.findRequiredViewAsType(source, R.id.re_quanzi, "field 'reQuanzi'", RelativeLayout.class);
    target.imgZiliao = Utils.findRequiredViewAsType(source, R.id.img_ziliao, "field 'imgZiliao'", ImageView.class);
    target.reZiliao = Utils.findRequiredViewAsType(source, R.id.re_ziliao, "field 'reZiliao'", RelativeLayout.class);
    target.imgGuanyu = Utils.findRequiredViewAsType(source, R.id.img_guanyu, "field 'imgGuanyu'", ImageView.class);
    target.reGuanyu = Utils.findRequiredViewAsType(source, R.id.re_guanyu, "field 'reGuanyu'", RelativeLayout.class);
    target.imgBangzhu = Utils.findRequiredViewAsType(source, R.id.img_bangzhu, "field 'imgBangzhu'", ImageView.class);
    target.reBangzhu = Utils.findRequiredViewAsType(source, R.id.re_bangzhu, "field 'reBangzhu'", RelativeLayout.class);
    target.imgFankui = Utils.findRequiredViewAsType(source, R.id.img_fankui, "field 'imgFankui'", ImageView.class);
    target.reFankui = Utils.findRequiredViewAsType(source, R.id.re_fankui, "field 'reFankui'", RelativeLayout.class);
    target.btnLogout = Utils.findRequiredViewAsType(source, R.id.btn_logout, "field 'btnLogout'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imgHead = null;
    target.textZcdl = null;
    target.imgDingdan = null;
    target.reDingdan = null;
    target.imgQuanzi = null;
    target.reQuanzi = null;
    target.imgZiliao = null;
    target.reZiliao = null;
    target.imgGuanyu = null;
    target.reGuanyu = null;
    target.imgBangzhu = null;
    target.reBangzhu = null;
    target.imgFankui = null;
    target.reFankui = null;
    target.btnLogout = null;

    this.target = null;
  }
}
