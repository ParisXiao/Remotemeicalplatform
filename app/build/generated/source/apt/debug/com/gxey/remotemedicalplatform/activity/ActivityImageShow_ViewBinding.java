// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;
import uk.co.senab.photoview.PhotoView;

public class ActivityImageShow_ViewBinding<T extends ActivityImageShow> implements Unbinder {
  protected T target;

  @UiThread
  public ActivityImageShow_ViewBinding(T target, View source) {
    this.target = target;

    target.mPVSee = Utils.findRequiredViewAsType(source, R.id.pv_see, "field 'mPVSee'", PhotoView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mPVSee = null;

    this.target = null;
  }
}
