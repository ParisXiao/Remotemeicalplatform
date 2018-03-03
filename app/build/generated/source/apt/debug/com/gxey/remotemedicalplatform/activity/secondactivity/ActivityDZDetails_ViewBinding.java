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

public class ActivityDZDetails_ViewBinding<T extends ActivityDZDetails> implements Unbinder {
  protected T target;

  @UiThread
  public ActivityDZDetails_ViewBinding(T target, View source) {
    this.target = target;

    target.toolbarMid = Utils.findRequiredViewAsType(source, R.id.toolbar_mid, "field 'toolbarMid'", TextView.class);
    target.toolbarLeftBtn = Utils.findRequiredViewAsType(source, R.id.toolbar_left_btn, "field 'toolbarLeftBtn'", ImageButton.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.dzBlh = Utils.findRequiredViewAsType(source, R.id.dz_blh, "field 'dzBlh'", TextView.class);
    target.dzChuzhen = Utils.findRequiredViewAsType(source, R.id.dz_chuzhen, "field 'dzChuzhen'", TextView.class);
    target.dzZhenzhuang = Utils.findRequiredViewAsType(source, R.id.dz_zhenzhuang, "field 'dzZhenzhuang'", TextView.class);
    target.dzYisheng = Utils.findRequiredViewAsType(source, R.id.dz_yisheng, "field 'dzYisheng'", TextView.class);
    target.dzJiwangshi = Utils.findRequiredViewAsType(source, R.id.dz_jiwangshi, "field 'dzJiwangshi'", TextView.class);
    target.dzGerenshi = Utils.findRequiredViewAsType(source, R.id.dz_gerenshi, "field 'dzGerenshi'", TextView.class);
    target.dzJiazushi = Utils.findRequiredViewAsType(source, R.id.dz_jiazushi, "field 'dzJiazushi'", TextView.class);
    target.dzTigejc = Utils.findRequiredViewAsType(source, R.id.dz_tigejc, "field 'dzTigejc'", TextView.class);
    target.dzFuzhujc = Utils.findRequiredViewAsType(source, R.id.dz_fuzhujc, "field 'dzFuzhujc'", TextView.class);
    target.dzZhenduan = Utils.findRequiredViewAsType(source, R.id.dz_zhenduan, "field 'dzZhenduan'", TextView.class);
    target.dzTsbz = Utils.findRequiredViewAsType(source, R.id.dz_tsbz, "field 'dzTsbz'", TextView.class);
    target.dzClyj = Utils.findRequiredViewAsType(source, R.id.dz_clyj, "field 'dzClyj'", TextView.class);
    target.dzBz = Utils.findRequiredViewAsType(source, R.id.dz_bz, "field 'dzBz'", TextView.class);
    target.dzShijian = Utils.findRequiredViewAsType(source, R.id.dz_shijian, "field 'dzShijian'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbarMid = null;
    target.toolbarLeftBtn = null;
    target.toolbar = null;
    target.dzBlh = null;
    target.dzChuzhen = null;
    target.dzZhenzhuang = null;
    target.dzYisheng = null;
    target.dzJiwangshi = null;
    target.dzGerenshi = null;
    target.dzJiazushi = null;
    target.dzTigejc = null;
    target.dzFuzhujc = null;
    target.dzZhenduan = null;
    target.dzTsbz = null;
    target.dzClyj = null;
    target.dzBz = null;
    target.dzShijian = null;

    this.target = null;
  }
}
