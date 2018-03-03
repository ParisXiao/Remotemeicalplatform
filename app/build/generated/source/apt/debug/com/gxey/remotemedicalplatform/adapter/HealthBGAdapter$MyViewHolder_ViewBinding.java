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

public class HealthBGAdapter$MyViewHolder_ViewBinding<T extends HealthBGAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public HealthBGAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.itemBaogaoDanhaoText = Utils.findRequiredViewAsType(source, R.id.item_baogao_danhao_text, "field 'itemBaogaoDanhaoText'", TextView.class);
    target.itemBaogaoDanhao = Utils.findRequiredViewAsType(source, R.id.item_baogao_danhao, "field 'itemBaogaoDanhao'", TextView.class);
    target.itemBaogaoChakan = Utils.findRequiredViewAsType(source, R.id.item_baogao_chakan, "field 'itemBaogaoChakan'", TextView.class);
    target.itemBaogaoHzname = Utils.findRequiredViewAsType(source, R.id.item_baogao_hzname, "field 'itemBaogaoHzname'", TextView.class);
    target.itemBaogaoYsname = Utils.findRequiredViewAsType(source, R.id.item_baogao_ysname, "field 'itemBaogaoYsname'", TextView.class);
    target.itemBaogaoZuzhi = Utils.findRequiredViewAsType(source, R.id.item_baogao_zuzhi, "field 'itemBaogaoZuzhi'", TextView.class);
    target.itemBaogaoTime = Utils.findRequiredViewAsType(source, R.id.item_baogao_time, "field 'itemBaogaoTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.itemBaogaoDanhaoText = null;
    target.itemBaogaoDanhao = null;
    target.itemBaogaoChakan = null;
    target.itemBaogaoHzname = null;
    target.itemBaogaoYsname = null;
    target.itemBaogaoZuzhi = null;
    target.itemBaogaoTime = null;

    this.target = null;
  }
}
