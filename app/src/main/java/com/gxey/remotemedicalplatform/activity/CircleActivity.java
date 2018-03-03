package com.gxey.remotemedicalplatform.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xusongsong on 2016/12/27.
 * 圈子列表
 */

public class CircleActivity extends BaseActivity  implements View.OnClickListener{


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.new_cir)
    TextView new_cir;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_circle;
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
                Intent intent= new Intent(CircleActivity.this, CircleListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
