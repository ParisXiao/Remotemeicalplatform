package com.gxey.remotemedicalplatform.activity;

import android.view.View;
import android.widget.ImageView;

import com.gxey.remotemedicalplatform.R;

import butterknife.BindView;

/**
 * Created by xusongsong on 2016/12/27.
 * 使用帮助
 */

public class HelpActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView back;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_help;
    }

    @Override
    protected void initView() {
        back.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;

        }

    }
}
