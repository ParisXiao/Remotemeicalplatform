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

public class ActivityFZDetails_ViewBinding<T extends ActivityFZDetails> implements Unbinder {
  protected T target;

  @UiThread
  public ActivityFZDetails_ViewBinding(T target, View source) {
    this.target = target;

    target.toolbarMid = Utils.findRequiredViewAsType(source, R.id.toolbar_mid, "field 'toolbarMid'", TextView.class);
    target.toolbarLeftBtn = Utils.findRequiredViewAsType(source, R.id.toolbar_left_btn, "field 'toolbarLeftBtn'", ImageButton.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.zzSqdh = Utils.findRequiredViewAsType(source, R.id.zz_sqdh, "field 'zzSqdh'", TextView.class);
    target.zzChuzhen = Utils.findRequiredViewAsType(source, R.id.zz_chuzhen, "field 'zzChuzhen'", TextView.class);
    target.zzZhenzhuang = Utils.findRequiredViewAsType(source, R.id.zz_zhenzhuang, "field 'zzZhenzhuang'", TextView.class);
    target.zzZhuanchu = Utils.findRequiredViewAsType(source, R.id.zz_zhuanchu, "field 'zzZhuanchu'", TextView.class);
    target.zzZhuanru = Utils.findRequiredViewAsType(source, R.id.zz_zhuanru, "field 'zzZhuanru'", TextView.class);
    target.zzKeshi = Utils.findRequiredViewAsType(source, R.id.zz_keshi, "field 'zzKeshi'", TextView.class);
    target.zzJztime = Utils.findRequiredViewAsType(source, R.id.zz_jztime, "field 'zzJztime'", TextView.class);
    target.zzYisheng = Utils.findRequiredViewAsType(source, R.id.zz_yisheng, "field 'zzYisheng'", TextView.class);
    target.zzShenhe = Utils.findRequiredViewAsType(source, R.id.zz_shenhe, "field 'zzShenhe'", TextView.class);
    target.zzShijian = Utils.findRequiredViewAsType(source, R.id.zz_shijian, "field 'zzShijian'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbarMid = null;
    target.toolbarLeftBtn = null;
    target.toolbar = null;
    target.zzSqdh = null;
    target.zzChuzhen = null;
    target.zzZhenzhuang = null;
    target.zzZhuanchu = null;
    target.zzZhuanru = null;
    target.zzKeshi = null;
    target.zzJztime = null;
    target.zzYisheng = null;
    target.zzShenhe = null;
    target.zzShijian = null;

    this.target = null;
  }
}
