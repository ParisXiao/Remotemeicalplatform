// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.widget.EmptyLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ActivityYiChuan_ViewBinding<T extends ActivityYiChuan> implements Unbinder {
  protected T target;

  @UiThread
  public ActivityYiChuan_ViewBinding(T target, View source) {
    this.target = target;

    target.toolbarMid = Utils.findRequiredViewAsType(source, R.id.toolbar_mid, "field 'toolbarMid'", TextView.class);
    target.toolbarLeftBtn = Utils.findRequiredViewAsType(source, R.id.toolbar_left_btn, "field 'toolbarLeftBtn'", ImageButton.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recyclerViewYichuan = Utils.findRequiredViewAsType(source, R.id.recyclerView_yichuan, "field 'recyclerViewYichuan'", RecyclerView.class);
    target.emptyLayoutYichuan = Utils.findRequiredViewAsType(source, R.id.emptyLayout_yichuan, "field 'emptyLayoutYichuan'", EmptyLayout.class);
    target.swipeYichuan = Utils.findRequiredViewAsType(source, R.id.swipe_yichuan, "field 'swipeYichuan'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbarMid = null;
    target.toolbarLeftBtn = null;
    target.toolbar = null;
    target.recyclerViewYichuan = null;
    target.emptyLayoutYichuan = null;
    target.swipeYichuan = null;

    this.target = null;
  }
}
