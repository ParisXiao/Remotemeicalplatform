// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FenZhenAdapter$FooterViewHolder_ViewBinding<T extends FenZhenAdapter.FooterViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public FenZhenAdapter$FooterViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mPbLoad = Utils.findRequiredViewAsType(source, R.id.pbLoad, "field 'mPbLoad'", ProgressBar.class);
    target.mTvLoadText = Utils.findRequiredViewAsType(source, R.id.tvLoadText, "field 'mTvLoadText'", TextView.class);
    target.mLoadLayout = Utils.findRequiredViewAsType(source, R.id.loadLayout, "field 'mLoadLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mPbLoad = null;
    target.mTvLoadText = null;
    target.mLoadLayout = null;

    this.target = null;
  }
}
