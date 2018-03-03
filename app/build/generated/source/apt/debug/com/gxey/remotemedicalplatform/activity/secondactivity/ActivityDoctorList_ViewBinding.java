// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.widget.EmptyLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ActivityDoctorList_ViewBinding<T extends ActivityDoctorList> implements Unbinder {
  protected T target;

  @UiThread
  public ActivityDoctorList_ViewBinding(T target, View source) {
    this.target = target;

    target.toolbarMid = Utils.findRequiredViewAsType(source, R.id.toolbar_mid, "field 'toolbarMid'", TextView.class);
    target.toolbarLeftBtn = Utils.findRequiredViewAsType(source, R.id.toolbar_left_btn, "field 'toolbarLeftBtn'", ImageButton.class);
    target.toolbarRight = Utils.findRequiredViewAsType(source, R.id.toolbar_right, "field 'toolbarRight'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.sousuoDoctor = Utils.findRequiredViewAsType(source, R.id.sousuo_doctor, "field 'sousuoDoctor'", EditText.class);
    target.textAll = Utils.findRequiredViewAsType(source, R.id.text_all, "field 'textAll'", TextView.class);
    target.textOnline = Utils.findRequiredViewAsType(source, R.id.text_online, "field 'textOnline'", TextView.class);
    target.textOutline = Utils.findRequiredViewAsType(source, R.id.text_outline, "field 'textOutline'", TextView.class);
    target.recyclerViewDoctor = Utils.findRequiredViewAsType(source, R.id.recyclerView_doctor, "field 'recyclerViewDoctor'", RecyclerView.class);
    target.layRefreshDoctor = Utils.findRequiredViewAsType(source, R.id.lay_refresh_doctor, "field 'layRefreshDoctor'", SwipeRefreshLayout.class);
    target.emptyLayoutDoctor = Utils.findRequiredViewAsType(source, R.id.emptyLayout_doctor, "field 'emptyLayoutDoctor'", EmptyLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbarMid = null;
    target.toolbarLeftBtn = null;
    target.toolbarRight = null;
    target.toolbar = null;
    target.sousuoDoctor = null;
    target.textAll = null;
    target.textOnline = null;
    target.textOutline = null;
    target.recyclerViewDoctor = null;
    target.layRefreshDoctor = null;
    target.emptyLayoutDoctor = null;

    this.target = null;
  }
}
