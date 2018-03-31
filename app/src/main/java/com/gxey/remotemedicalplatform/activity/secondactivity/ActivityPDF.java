package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.utils.MyStrUtil;
import com.gxey.remotemedicalplatform.utils.ToastUtils;
import com.gxey.remotemedicalplatform.widget.EmptyLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.gxey.remotemedicalplatform.R.id.toolbar_left_btn;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class ActivityPDF extends BaseActivity implements View.OnClickListener, OnPageChangeListener {
    @BindView(R.id.pdfView)
    PDFView pdfView;
    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(R.id.toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.emptyLayout)
    EmptyLayout emptyLayout;
    String url;
    @BindView(R.id.text)
    TextView text;
    String path;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pdf;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarMid.setText("健康报告详情");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    protected void initData() {
        path = Environment.getExternalStorageDirectory()+"";
        url = getIntent().getStringExtra("PDF");
        emptyLayout.setOnButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新加载数据
                getPDF();
            }
        });
        emptyLayout.showLoading(this);
        if (!MyStrUtil.isEmpty(url)) {
            getPDF();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showPDF();
                    emptyLayout.showSuccess();
                }
            }, 2000);
        }else {
            emptyLayout.showError();
        }


    }

    private void getPDF() {

        Request request = new Request.Builder().url(url).build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        emptyLayout.showError();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] bytes = new byte[2048];
                int len = 0;
                FileOutputStream o = null;
                try {


                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    final File file = new File(path, "Health.pdf");
                    o = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(bytes)) != -1) {
                        o.write(bytes, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        if (progress==100){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.s(ActivityPDF.this,"下载完成");
                                }
                            });
                        }
                    }
                    o.flush();


                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.s(ActivityPDF.this,"下载失败");
                            emptyLayout.showError();
                        }
                    });
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (Exception e) {

                    }
                    try {
                        if (o != null)
                            o.close();
                    } catch (Exception e) {

                    }


                }


            }
        });

    }

    private void showPDF() {

        File file = new File(path, "Health.pdf");
        pdfView.fromFile(file)
                //                .pages(0, 0, 0, 0, 0, 0) // 默认全部显示，pages属性可以过滤性显示
                .defaultPage(0)//默认展示第一页
                .onPageChange(this)//监听页面切换
                .load();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case toolbar_left_btn:
                finish();
                break;
        }
    }

    @Override
    public void onPageChanged(int i, int i1) {
        text.setText((i+1) + "/" + i1);
    }
}
