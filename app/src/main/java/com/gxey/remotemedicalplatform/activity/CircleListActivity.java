package com.gxey.remotemedicalplatform.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;

import butterknife.BindView;

/**
 * Created by xusongsong on 2017/1/5.
 */

public class CircleListActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.new_cir)
    LinearLayout new_cir;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_circle_list;
    }

    @Override
    protected void initView() {
        back.setOnClickListener(this);
        new_cir.setOnClickListener(this);
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
            case R.id.new_cir:
                Intent intent= new Intent(CircleListActivity.this, CircleTopicactivity.class);
                startActivity(intent);
            break;

        }
    }
}
