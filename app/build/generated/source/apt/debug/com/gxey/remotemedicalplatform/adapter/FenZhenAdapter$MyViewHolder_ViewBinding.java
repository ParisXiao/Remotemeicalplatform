// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FenZhenAdapter$MyViewHolder_ViewBinding<T extends FenZhenAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public FenZhenAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.itemFenzhenZhuanchu = Utils.findRequiredViewAsType(source, R.id.item_fenzhen_zhuanchu, "field 'itemFenzhenZhuanchu'", TextView.class);
    target.itemFenzhenTime = Utils.findRequiredViewAsType(source, R.id.item_fenzhen_time, "field 'itemFenzhenTime'", TextView.class);
    target.itemFenzhenZhuanru = Utils.findRequiredViewAsType(source, R.id.item_fenzhen_zhuanru, "field 'itemFenzhenZhuanru'", TextView.class);
    target.itemFenzhenYiyuan = Utils.findRequiredViewAsType(source, R.id.item_fenzhen_yiyuan, "field 'itemFenzhenYiyuan'", TextView.class);
    target.itemFenzhenWaike = Utils.findRequiredViewAsType(source, R.id.item_fenzhen_waike, "field 'itemFenzhenWaike'", TextView.class);
    target.itemFenzhenYisheng = Utils.findRequiredViewAsType(source, R.id.item_fenzhen_yisheng, "field 'itemFenzhenYisheng'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.itemFenzhenZhuanchu = null;
    target.itemFenzhenTime = null;
    target.itemFenzhenZhuanru = null;
    target.itemFenzhenYiyuan = null;
    target.itemFenzhenWaike = null;
    target.itemFenzhenYisheng = null;

    this.target = null;
  }
}
