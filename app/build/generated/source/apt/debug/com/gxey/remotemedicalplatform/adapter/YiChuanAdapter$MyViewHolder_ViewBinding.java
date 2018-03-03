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

public class YiChuanAdapter$MyViewHolder_ViewBinding<T extends YiChuanAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public YiChuanAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.itemYichuanYcname = Utils.findRequiredViewAsType(source, R.id.item_yichuan_ycname, "field 'itemYichuanYcname'", TextView.class);
    target.itemYichuanTime = Utils.findRequiredViewAsType(source, R.id.item_yichuan_time, "field 'itemYichuanTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.itemYichuanYcname = null;
    target.itemYichuanTime = null;

    this.target = null;
  }
}
