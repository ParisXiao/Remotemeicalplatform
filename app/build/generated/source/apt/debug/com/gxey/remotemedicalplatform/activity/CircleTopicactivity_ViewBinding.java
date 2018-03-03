// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CircleTopicactivity_ViewBinding<T extends CircleTopicactivity> implements Unbinder {
  protected T target;

  @UiThread
  public CircleTopicactivity_ViewBinding(T target, View source) {
    this.target = target;

    target.release = Utils.findRequiredViewAsType(source, R.id.release, "field 'release'", TextView.class);
    target.delete1 = Utils.findRequiredViewAsType(source, R.id.delete1, "field 'delete1'", TextView.class);
    target.delete2 = Utils.findRequiredViewAsType(source, R.id.delete2, "field 'delete2'", TextView.class);
    target.delete3 = Utils.findRequiredViewAsType(source, R.id.delete3, "field 'delete3'", TextView.class);
    target.mname = Utils.findRequiredViewAsType(source, R.id.name, "field 'mname'", EditText.class);
    target.mtitle = Utils.findRequiredViewAsType(source, R.id.title, "field 'mtitle'", EditText.class);
    target.mcontent = Utils.findRequiredViewAsType(source, R.id.content, "field 'mcontent'", EditText.class);
    target.mimg1 = Utils.findRequiredViewAsType(source, R.id.img1, "field 'mimg1'", ImageView.class);
    target.mimg2 = Utils.findRequiredViewAsType(source, R.id.img2, "field 'mimg2'", ImageView.class);
    target.mimg3 = Utils.findRequiredViewAsType(source, R.id.img3, "field 'mimg3'", ImageView.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.release = null;
    target.delete1 = null;
    target.delete2 = null;
    target.delete3 = null;
    target.mname = null;
    target.mtitle = null;
    target.mcontent = null;
    target.mimg1 = null;
    target.mimg2 = null;
    target.mimg3 = null;
    target.back = null;

    this.target = null;
  }
}
