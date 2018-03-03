package com.gxey.remotemedicalplatform.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.adapter.CommonAdapter;
import com.gxey.remotemedicalplatform.adapter.ViewHolder;
import com.gxey.remotemedicalplatform.javaben.StoresBen;
import com.gxey.remotemedicalplatform.network.HttpClientHelper;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xusongsong on 2017/1/2.
 */

public class storesActivity extends BaseActivity {
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.back)
    ImageView back;
    public static final int RESULT_CODE = 1;
    private CommonAdapter<StoresBen> adapter;
    private List<StoresBen> list = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_stores;
    }

    @Override
    protected void initView() {

        showLoadDialog();




        HttpClientHelper.getInstance().stores(new HttpSubseiber.ResponseHandler<List<StoresBen>>() {
            @Override
            public void onSucceed(List<StoresBen> data) {

                dismisDialog();
                adapter.notifyDataSetChanged();
                list.addAll(data);
            }

            @Override
            public void onFail(String msg) {

            }
        });




        adapter = new CommonAdapter<StoresBen>(this,list,R.layout.item_stores) {
            @Override
            public void convert(ViewHolder helper, StoresBen item) {
                helper.setText(R.id.mendianid,item.getName());
            }
        };
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("ParentId",list.get(position).getId());
                Log.d("-----","======"+list.get(position).getId());
                setResult(RESULT_CODE, intent );
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initData() {

    }


}
