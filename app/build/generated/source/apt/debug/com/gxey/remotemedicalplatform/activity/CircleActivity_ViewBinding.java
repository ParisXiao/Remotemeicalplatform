// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CircleActivity_ViewBinding<T extends CircleActivity> implements Unbinder {
  protected T target;

  @UiThread
  public CircleActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.new_cir = Utils.findRequiredViewAsType(source, R.id.new_cir, "field 'new_cir'", TextView.class);
    target.toolbarMid = Utils.findRequiredViewAsType(source, R.id.toolbar_mid, "field 'toolbarMid'", TextView.class);
    target.toolbarLeftBtn = Utils.findRequiredViewAsType(source, R.id.toolbar_left_btn, "field 'toolbarLeftBtn'", ImageButton.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.img1 = Utils.findRequiredViewAsType(source, R.id.img1, "field 'img1'", ImageView.class);
    target.tv1 = Utils.findRequiredViewAsType(source, R.id.tv1, "field 'tv1'", TextView.class);
    target.tv2 = Utils.findRequiredViewAsType(source, R.id.tv2, "field 'tv2'", TextView.class);
    target.tv3 = Utils.findRequiredViewAsType(source, R.id.tv3, "field 'tv3'", TextView.class);
    target.img2 = Utils.findRequiredViewAsType(source, R.id.img2, "field 'img2'", ImageView.class);
    target.tv4 = Utils.findRequiredViewAsType(source, R.id.tv4, "field 'tv4'", TextView.class);
    target.tv5 = Utils.findRequiredViewAsType(source, R.id.tv5, "field 'tv5'", TextView.class);
    target.tv6 = Utils.findRequiredViewAsType(source, R.id.tv6, "field 'tv6'", TextView.class);
    target.webview = Utils.findRequiredViewAsType(source, R.id.webview, "field 'webview'", WebView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.new_cir = null;
    target.toolbarMid = null;
    target.toolbarLeftBtn = null;
    target.toolbar = null;
    target.img1 = null;
    target.tv1 = null;
    target.tv2 = null;
    target.tv3 = null;
    target.img2 = null;
    target.tv4 = null;
    target.tv5 = null;
    target.tv6 = null;
    target.webview = null;

    this.target = null;
  }
}
