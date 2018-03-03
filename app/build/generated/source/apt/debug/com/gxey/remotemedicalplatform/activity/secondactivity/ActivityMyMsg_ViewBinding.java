// Generated code from Butter Knife. Do not modify!
package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.widget.TranslucentActionBar;
import com.gxey.remotemedicalplatform.widget.TranslucentScrollView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ActivityMyMsg_ViewBinding<T extends ActivityMyMsg> implements Unbinder {
  protected T target;

  @UiThread
  public ActivityMyMsg_ViewBinding(T target, View source) {
    this.target = target;

    target.msgHead = Utils.findRequiredViewAsType(source, R.id.msg_head, "field 'msgHead'", ImageView.class);
    target.msgName = Utils.findRequiredViewAsType(source, R.id.msg_name, "field 'msgName'", TextView.class);
    target.headZoom = Utils.findRequiredViewAsType(source, R.id.head_zoom, "field 'headZoom'", RelativeLayout.class);
    target.msgRealname = Utils.findRequiredViewAsType(source, R.id.msg_realname, "field 'msgRealname'", TextView.class);
    target.msgXingbie = Utils.findRequiredViewAsType(source, R.id.msg_xingbie, "field 'msgXingbie'", TextView.class);
    target.msgBirthday = Utils.findRequiredViewAsType(source, R.id.msg_birthday, "field 'msgBirthday'", TextView.class);
    target.msgGuoji = Utils.findRequiredViewAsType(source, R.id.msg_guoji, "field 'msgGuoji'", TextView.class);
    target.msgJiguan = Utils.findRequiredViewAsType(source, R.id.msg_jiguan, "field 'msgJiguan'", TextView.class);
    target.msgAddress = Utils.findRequiredViewAsType(source, R.id.msg_address, "field 'msgAddress'", TextView.class);
    target.msgMinzu = Utils.findRequiredViewAsType(source, R.id.msg_minzu, "field 'msgMinzu'", TextView.class);
    target.msgHeight = Utils.findRequiredViewAsType(source, R.id.msg_height, "field 'msgHeight'", TextView.class);
    target.msgWeight = Utils.findRequiredViewAsType(source, R.id.msg_weight, "field 'msgWeight'", TextView.class);
    target.msgBuchang = Utils.findRequiredViewAsType(source, R.id.msg_buchang, "field 'msgBuchang'", TextView.class);
    target.msgXuexing = Utils.findRequiredViewAsType(source, R.id.msg_xuexing, "field 'msgXuexing'", TextView.class);
    target.msgRH = Utils.findRequiredViewAsType(source, R.id.msg_RH, "field 'msgRH'", TextView.class);
    target.msgSfNo = Utils.findRequiredViewAsType(source, R.id.msg_sfNo, "field 'msgSfNo'", TextView.class);
    target.msgPhone = Utils.findRequiredViewAsType(source, R.id.msg_phone, "field 'msgPhone'", TextView.class);
    target.msgWenhua = Utils.findRequiredViewAsType(source, R.id.msg_wenhua, "field 'msgWenhua'", TextView.class);
    target.msgChangzhu = Utils.findRequiredViewAsType(source, R.id.msg_changzhu, "field 'msgChangzhu'", TextView.class);
    target.msgMarry = Utils.findRequiredViewAsType(source, R.id.msg_marry, "field 'msgMarry'", TextView.class);
    target.msgWeixin = Utils.findRequiredViewAsType(source, R.id.msg_weixin, "field 'msgWeixin'", TextView.class);
    target.msgJinjiname = Utils.findRequiredViewAsType(source, R.id.msg_jinjiname, "field 'msgJinjiname'", TextView.class);
    target.msgJinjiphone = Utils.findRequiredViewAsType(source, R.id.msg_jinjiphone, "field 'msgJinjiphone'", TextView.class);
    target.msgCompany = Utils.findRequiredViewAsType(source, R.id.msg_company, "field 'msgCompany'", TextView.class);
    target.msgJob = Utils.findRequiredViewAsType(source, R.id.msg_job, "field 'msgJob'", TextView.class);
    target.msgJobfl = Utils.findRequiredViewAsType(source, R.id.msg_jobfl, "field 'msgJobfl'", TextView.class);
    target.msgBaolushi = Utils.findRequiredViewAsType(source, R.id.msg_baolushi, "field 'msgBaolushi'", TextView.class);
    target.msgJkkh = Utils.findRequiredViewAsType(source, R.id.msg_jkkh, "field 'msgJkkh'", TextView.class);
    target.msgSbkh = Utils.findRequiredViewAsType(source, R.id.msg_sbkh, "field 'msgSbkh'", TextView.class);
    target.msgYblx = Utils.findRequiredViewAsType(source, R.id.msg_yblx, "field 'msgYblx'", TextView.class);
    target.msgZffs = Utils.findRequiredViewAsType(source, R.id.msg_zffs, "field 'msgZffs'", TextView.class);
    target.transcrollview = Utils.findRequiredViewAsType(source, R.id.transcrollview, "field 'transcrollview'", TranslucentScrollView.class);
    target.actionbar = Utils.findRequiredViewAsType(source, R.id.actionbar, "field 'actionbar'", TranslucentActionBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.msgHead = null;
    target.msgName = null;
    target.headZoom = null;
    target.msgRealname = null;
    target.msgXingbie = null;
    target.msgBirthday = null;
    target.msgGuoji = null;
    target.msgJiguan = null;
    target.msgAddress = null;
    target.msgMinzu = null;
    target.msgHeight = null;
    target.msgWeight = null;
    target.msgBuchang = null;
    target.msgXuexing = null;
    target.msgRH = null;
    target.msgSfNo = null;
    target.msgPhone = null;
    target.msgWenhua = null;
    target.msgChangzhu = null;
    target.msgMarry = null;
    target.msgWeixin = null;
    target.msgJinjiname = null;
    target.msgJinjiphone = null;
    target.msgCompany = null;
    target.msgJob = null;
    target.msgJobfl = null;
    target.msgBaolushi = null;
    target.msgJkkh = null;
    target.msgSbkh = null;
    target.msgYblx = null;
    target.msgZffs = null;
    target.transcrollview = null;
    target.actionbar = null;

    this.target = null;
  }
}
