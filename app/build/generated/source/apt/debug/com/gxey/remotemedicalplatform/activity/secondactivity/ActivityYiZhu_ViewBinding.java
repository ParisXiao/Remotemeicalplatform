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

public class ActivityYiZhu_ViewBinding<T extends ActivityYiZhu> implements Unbinder {
  protected T target;

  @UiThread
  public ActivityYiZhu_ViewBinding(T target, View source) {
    this.target = target;

    target.toolbarMid = Utils.findRequiredViewAsType(source, R.id.toolbar_mid, "field 'toolbarMid'", TextView.class);
    target.toolbarLeftBtn = Utils.findRequiredViewAsType(source, R.id.toolbar_left_btn, "field 'toolbarLeftBtn'", ImageButton.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recyclerViewYizhu = Utils.findRequiredViewAsType(source, R.id.recyclerView_yizhu, "field 'recyclerViewYizhu'", RecyclerView.class);
    target.emptyLayoutYizhu = Utils.findRequiredViewAsType(source, R.id.emptyLayout_yizhu, "field 'emptyLayoutYizhu'", EmptyLayout.class);
    target.swipeYizhu = Utils.findRequiredViewAsType(source, R.id.swipe_yizhu, "field 'swipeYizhu'", SwipeRefreshLayout.class);
    target.toolbarRight = Utils.findRequiredViewAsType(source, R.id.toolbar_right, "field 'toolbarRight'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbarMid = null;
    target.toolbarLeftBtn = null;
    target.toolbar = null;
    target.recyclerViewYizhu = null;
    target.emptyLayoutYizhu = null;
    target.swipeYizhu = null;
    target.toolbarRight = null;

    this.target = null;
  }
}
