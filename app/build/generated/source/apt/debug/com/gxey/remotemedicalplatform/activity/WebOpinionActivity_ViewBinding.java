// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WebOpinionActivity_ViewBinding<T extends WebOpinionActivity> implements Unbinder {
  protected T target;

  @UiThread
  public WebOpinionActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.wView = Utils.findRequiredViewAsType(source, R.id.wv, "field 'wView'", WebView.class);
    target.mTVTitle = Utils.findRequiredViewAsType(source, R.id.tv_title_name, "field 'mTVTitle'", TextView.class);
    target.ivBreak = Utils.findRequiredViewAsType(source, R.id.back, "field 'ivBreak'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.wView = null;
    target.mTVTitle = null;
    target.ivBreak = null;

    this.target = null;
  }
}
