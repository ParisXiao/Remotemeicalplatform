// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ActivityYiZhuDetails_ViewBinding<T extends ActivityYiZhuDetails> implements Unbinder {
  protected T target;

  @UiThread
  public ActivityYiZhuDetails_ViewBinding(T target, View source) {
    this.target = target;

    target.toolbarMid = Utils.findRequiredViewAsType(source, R.id.toolbar_mid, "field 'toolbarMid'", TextView.class);
    target.toolbarLeftBtn = Utils.findRequiredViewAsType(source, R.id.toolbar_left_btn, "field 'toolbarLeftBtn'", ImageButton.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.yizhudetailsContent = Utils.findRequiredViewAsType(source, R.id.yizhudetails_content, "field 'yizhudetailsContent'", TextView.class);
    target.yizhudetailsID = Utils.findRequiredViewAsType(source, R.id.yizhudetails_ID, "field 'yizhudetailsID'", TextView.class);
    target.yizhudetailsYisheng = Utils.findRequiredViewAsType(source, R.id.yizhudetails_yisheng, "field 'yizhudetailsYisheng'", TextView.class);
    target.yizhudetailsStarttime = Utils.findRequiredViewAsType(source, R.id.yizhudetails_starttime, "field 'yizhudetailsStarttime'", TextView.class);
    target.yizhudetailsQixiao = Utils.findRequiredViewAsType(source, R.id.yizhudetails_qixiao, "field 'yizhudetailsQixiao'", TextView.class);
    target.yizhudetailsPl = Utils.findRequiredViewAsType(source, R.id.yizhudetails_pl, "field 'yizhudetailsPl'", TextView.class);
    target.yizhudetailsJigou = Utils.findRequiredViewAsType(source, R.id.yizhudetails_jigou, "field 'yizhudetailsJigou'", TextView.class);
    target.yizhudetailsTime = Utils.findRequiredViewAsType(source, R.id.yizhudetails_time, "field 'yizhudetailsTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbarMid = null;
    target.toolbarLeftBtn = null;
    target.toolbar = null;
    target.yizhudetailsContent = null;
    target.yizhudetailsID = null;
    target.yizhudetailsYisheng = null;
    target.yizhudetailsStarttime = null;
    target.yizhudetailsQixiao = null;
    target.yizhudetailsPl = null;
    target.yizhudetailsJigou = null;
    target.yizhudetailsTime = null;

    this.target = null;
  }
}
