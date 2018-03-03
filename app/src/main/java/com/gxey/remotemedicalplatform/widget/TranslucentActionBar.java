package com.gxey.remotemedicalplatform.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.inter.ActionBarClickListener;


/**
 * 支持渐变的 actionBar
 * Created by 晖仔(Milo) on 2016/12/28.
 * email:303767416@qq.com
 */

public final class TranslucentActionBar extends LinearLayout {

    View vStatusbar;
    ImageView ivActionbarLeft;
    RelativeLayout layActionbarLeft;
    public TextView tvActionbarTitle;
    View vActionbarRight;
    TextView tvActionbarLeft;
    TextView tvActionbarRight;
    RelativeLayout layActionbarRight;
    LinearLayout layTransroot;

    public TranslucentActionBar(Context context) {
        this(context, null);
    }

    public TranslucentActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TranslucentActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        setOrientation(HORIZONTAL);

        View contentView = inflate(getContext(), R.layout.actionbar_trans, this);
        tvActionbarTitle = (TextView) contentView.findViewById(R.id.tv_actionbar_title);
        tvActionbarLeft = (TextView) contentView.findViewById(R.id.tv_actionbar_left);
        tvActionbarRight = (TextView) contentView.findViewById(R.id.tv_actionbar_right );
        vStatusbar = (View) contentView.findViewById(R.id.v_statusbar);
        ivActionbarLeft = (ImageView) contentView.findViewById(R.id.iv_actionbar_left);
        layActionbarLeft = (RelativeLayout) contentView.findViewById(R.id.lay_actionbar_left);
        vActionbarRight = (View) contentView.findViewById(R.id.v_actionbar_right);
        layActionbarRight = (RelativeLayout) contentView.findViewById(R.id.lay_actionbar_right);
        layTransroot = (LinearLayout) contentView.findViewById(R.id.lay_transroot);
    }

    /**
     * 设置状态栏高度
     *
     * @param statusBarHeight
     */
    public void setStatusBarHeight(int statusBarHeight) {
        ViewGroup.LayoutParams params = vStatusbar.getLayoutParams();
        params.height = statusBarHeight;
        vStatusbar.setLayoutParams(params);
    }

    /**
     * 设置是否需要渐变
     */
    public void setNeedTranslucent() {
        setNeedTranslucent(true, false);
    }

    /**
     * 设置是否需要渐变,并且隐藏标题
     *
     * @param translucent
     */
    public void setNeedTranslucent(boolean translucent, boolean titleInitVisibile) {
        if (translucent) {
            layTransroot.setBackgroundDrawable(null);
        }
        if (!titleInitVisibile) {
            tvActionbarTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 设置标题
     *
     * @param strTitle
     */
    public void setTitle(String strTitle) {
        if (!TextUtils.isEmpty(strTitle)) {
            tvActionbarTitle.setText(strTitle);
        } else {
            tvActionbarTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 设置数据
     *
     * @param strTitle
     * @param resIdLeft
     * @param strLeft
     * @param resIdRight
     * @param strRight
     * @param listener
     */
    public void setData(String strTitle, int resIdLeft, String strLeft, int resIdRight, String strRight, final ActionBarClickListener listener) {
        if (!TextUtils.isEmpty(strTitle)) {
            tvActionbarTitle.setText(strTitle);
        } else {
            tvActionbarTitle.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(strLeft)) {
            tvActionbarLeft.setText(strLeft);
            tvActionbarLeft.setVisibility(View.VISIBLE);
        } else {
            tvActionbarLeft.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(strRight)) {
            tvActionbarRight.setText(strRight);
            tvActionbarRight.setVisibility(View.VISIBLE);
        } else {
            tvActionbarRight.setVisibility(View.GONE);
        }

        if (resIdLeft == 0) {
            ivActionbarLeft.setVisibility(View.GONE);
        } else {
            ivActionbarLeft.setVisibility(View.VISIBLE);
        }

        if (resIdRight == 0) {
            tvActionbarRight.setVisibility(View.GONE);
        } else {
            tvActionbarRight.setBackgroundResource(resIdRight);
            tvActionbarRight.setVisibility(View.VISIBLE);
        }

        if (listener != null) {
            layActionbarLeft.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onLeftClick();
                }
            });
            layActionbarRight.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRightClick();
                }
            });
        }
    }

}
