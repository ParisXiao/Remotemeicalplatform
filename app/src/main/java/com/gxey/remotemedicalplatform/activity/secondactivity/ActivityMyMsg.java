package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.inter.ActionBarClickListener;
import com.gxey.remotemedicalplatform.newconfig.UserConfig;
import com.gxey.remotemedicalplatform.utils.ImageUtils;
import com.gxey.remotemedicalplatform.utils.PreferenceUtils;
import com.gxey.remotemedicalplatform.widget.TranslucentActionBar;
import com.gxey.remotemedicalplatform.widget.TranslucentScrollView;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-02-28.
 */

public class ActivityMyMsg extends BaseActivity implements ActionBarClickListener, TranslucentScrollView.TranslucentChangedListener{
    @BindView(R.id.msg_head)
    ImageView msgHead;
    @BindView(R.id.msg_name)
    TextView msgName;
    @BindView(R.id.head_zoom)
    RelativeLayout headZoom;
    @BindView(R.id.msg_realname)
    TextView msgRealname;
    @BindView(R.id.msg_xingbie)
    TextView msgXingbie;
    @BindView(R.id.msg_birthday)
    TextView msgBirthday;
    @BindView(R.id.msg_guoji)
    TextView msgGuoji;
    @BindView(R.id.msg_jiguan)
    TextView msgJiguan;
    @BindView(R.id.msg_address)
    TextView msgAddress;
    @BindView(R.id.msg_minzu)
    TextView msgMinzu;
    @BindView(R.id.msg_height)
    TextView msgHeight;
    @BindView(R.id.msg_weight)
    TextView msgWeight;
    @BindView(R.id.msg_buchang)
    TextView msgBuchang;
    @BindView(R.id.msg_xuexing)
    TextView msgXuexing;
    @BindView(R.id.msg_RH)
    TextView msgRH;
    @BindView(R.id.msg_sfNo)
    TextView msgSfNo;
    @BindView(R.id.msg_phone)
    TextView msgPhone;
    @BindView(R.id.msg_wenhua)
    TextView msgWenhua;
    @BindView(R.id.msg_changzhu)
    TextView msgChangzhu;
    @BindView(R.id.msg_marry)
    TextView msgMarry;
    @BindView(R.id.msg_weixin)
    TextView msgWeixin;
    @BindView(R.id.msg_jinjiname)
    TextView msgJinjiname;
    @BindView(R.id.msg_jinjiphone)
    TextView msgJinjiphone;
    @BindView(R.id.msg_company)
    TextView msgCompany;
    @BindView(R.id.msg_job)
    TextView msgJob;
    @BindView(R.id.msg_jobfl)
    TextView msgJobfl;
    @BindView(R.id.msg_baolushi)
    TextView msgBaolushi;
    @BindView(R.id.msg_jkkh)
    TextView msgJkkh;
    @BindView(R.id.msg_sbkh)
    TextView msgSbkh;
    @BindView(R.id.msg_yblx)
    TextView msgYblx;
    @BindView(R.id.msg_zffs)
    TextView msgZffs;
    @BindView(R.id.transcrollview)
    TranslucentScrollView transcrollview;
    @BindView(R.id.actionbar)
    TranslucentActionBar actionbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mymessage;
    }

    @Override
    protected void initView() {
        actionbar.setData("基本信息",1, null, 0, null, null);
        //开启渐变
        actionbar.setNeedTranslucent();
        //设置状态栏高度
        actionbar.setStatusBarHeight(getStatusBarHeight());

        //设置透明度变化监听
        transcrollview.setTranslucentChangedListener(this);
        //关联需要渐变的视图
        transcrollview.setTransView(actionbar);
        //设置ActionBar键渐变颜色
        transcrollview.setTransColor(getResources().getColor(R.color.background_green));
        //关联伸缩的视图
        transcrollview.setPullZoomView(headZoom);
    }

    @Override
    protected void initData() {
        ImageUtils.loadHeadImg(this, PreferenceUtils.getInstance(this).getString(UserConfig.HeadImg),msgHead);
        msgName.setText(PreferenceUtils.getInstance(this).getString(UserConfig.UserName));
        msgRealname.setText(PreferenceUtils.getInstance(this).getString(UserConfig.RealName));
        if (PreferenceUtils.getInstance(this).getString(UserConfig.Sex).equals("M")){
            msgXingbie.setText("男");
        }else {
            msgXingbie.setText("女");
        }

        msgAddress.setText(PreferenceUtils.getInstance(this).getString(UserConfig.Address));
        msgSfNo.setText(PreferenceUtils.getInstance(this).getString(UserConfig.SFZH));
        msgPhone.setText(PreferenceUtils.getInstance(this).getString(UserConfig.Phone));
        msgBirthday.setText("");
        msgGuoji.setText("");
        msgJiguan.setText("");
        msgMinzu.setText("");
        msgHeight.setText("");
        msgWeight.setText("");
        msgBuchang.setText("");
        msgXuexing.setText("");
        msgRH.setText("");
        msgWenhua.setText("");
        msgChangzhu.setText("");
        msgMarry.setText("");
        msgWeixin.setText("");
        msgJinjiname.setText("");
        msgJinjiphone.setText("");
        msgCompany.setText("");
        msgJob.setText("");
        msgJobfl.setText("");
        msgBaolushi.setText("");
        msgJkkh.setText("");
        msgSbkh.setText("");
        msgYblx.setText("");
        msgZffs.setText("");
    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onTranslucentChanged(int transAlpha) {
        actionbar.tvActionbarTitle.setVisibility(transAlpha > 48 ? View.VISIBLE : View.GONE);
    }
}
