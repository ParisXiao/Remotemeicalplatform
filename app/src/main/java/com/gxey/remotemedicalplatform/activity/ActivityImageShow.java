package com.gxey.remotemedicalplatform.activity;

import com.gxey.remotemedicalplatform.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by lanluo on 2017/1/11.
 */

public class ActivityImageShow extends BaseActivity {
    @BindView(R.id.pv_see)
    PhotoView mPVSee;
    String path;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_show;
    }

    @Override
    protected void initView() {
        path = getIntent().getStringExtra("path");
        Picasso.with(this).load(path).into(mPVSee);
    }

    @Override
    protected void initData() {

    }
}
