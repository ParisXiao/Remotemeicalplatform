// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.donkingliang.labels.LabelsView;
import com.gxey.remotemedicalplatform.R;
import com.makeramen.roundedimageview.RoundedImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class YiZhuAdapter$MyViewHolder_ViewBinding<T extends YiZhuAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public YiZhuAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.yizhuHead = Utils.findRequiredViewAsType(source, R.id.yizhu_head, "field 'yizhuHead'", RoundedImageView.class);
    target.yizhuName = Utils.findRequiredViewAsType(source, R.id.yizhu_name, "field 'yizhuName'", TextView.class);
    target.yizhuLabels = Utils.findRequiredViewAsType(source, R.id.yizhu_labels, "field 'yizhuLabels'", LabelsView.class);
    target.yizhuNo = Utils.findRequiredViewAsType(source, R.id.yizhu_No, "field 'yizhuNo'", TextView.class);
    target.yizhuContent = Utils.findRequiredViewAsType(source, R.id.yizhu_content, "field 'yizhuContent'", TextView.class);
    target.yizhuTime = Utils.findRequiredViewAsType(source, R.id.yizhu_time, "field 'yizhuTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.yizhuHead = null;
    target.yizhuName = null;
    target.yizhuLabels = null;
    target.yizhuNo = null;
    target.yizhuContent = null;
    target.yizhuTime = null;

    this.target = null;
  }
}
