// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ActivityDrugStore_ViewBinding<T extends ActivityDrugStore> implements Unbinder {
  protected T target;

  @UiThread
  public ActivityDrugStore_ViewBinding(T target, View source) {
    this.target = target;

    target.wView = Utils.findRequiredViewAsType(source, R.id.wv, "field 'wView'", WebView.class);
    target.mLLTitle = Utils.findRequiredViewAsType(source, R.id.ll_title, "field 'mLLTitle'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.wView = null;
    target.mLLTitle = null;

    this.target = null;
  }
}
