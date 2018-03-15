// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding<T extends HomeFragment> implements Unbinder {
  protected T target;

  @UiThread
  public HomeFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.sousuoSy = Utils.findRequiredViewAsType(source, R.id.sousuo_sy, "field 'sousuoSy'", EditText.class);
    target.recyclerViewSy = Utils.findRequiredViewAsType(source, R.id.recyclerView_sy, "field 'recyclerViewSy'", RecyclerView.class);
    target.layRefresh = Utils.findRequiredViewAsType(source, R.id.lay_refresh, "field 'layRefresh'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.sousuoSy = null;
    target.recyclerViewSy = null;
    target.layRefresh = null;

    this.target = null;
  }
}
