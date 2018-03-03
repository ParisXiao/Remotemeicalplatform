// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EvaluationActivity_ViewBinding<T extends EvaluationActivity> implements Unbinder {
  protected T target;

  @UiThread
  public EvaluationActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mRatingBar = Utils.findRequiredViewAsType(source, R.id.ratingBar, "field 'mRatingBar'", RatingBar.class);
    target.mEdiText = Utils.findRequiredViewAsType(source, R.id.medi_content, "field 'mEdiText'", EditText.class);
    target.ok_eva = Utils.findRequiredViewAsType(source, R.id.ok_eva, "field 'ok_eva'", TextView.class);
    target.back = Utils.findRequiredViewAsType(source, R.id.back, "field 'back'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mRatingBar = null;
    target.mEdiText = null;
    target.ok_eva = null;
    target.back = null;

    this.target = null;
  }
}
