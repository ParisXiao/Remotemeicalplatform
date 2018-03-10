package com.gxey.remotemedicalplatform.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.bean.HealthBGBean;
import com.gxey.remotemedicalplatform.newconfig.UserConfig;
import com.gxey.remotemedicalplatform.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 申诉记录适配器
 * Created by Administrator on 2017/12/12 0012.
 */

public class HealthBGAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


    private LayoutInflater mInflater;
    private Context context;
    private List<HealthBGBean> healthBGBeen = new ArrayList<>();
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE = 2;

    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = 0;


    public HealthBGAdapter(Context context, List<HealthBGBean> healthBGBeen) {
        this.context = context;
        this.healthBGBeen = healthBGBeen;
        this.mInflater = LayoutInflater.from(context);


    }

    @Override
    public int getItemViewType(int position) {

        if (position + 1 == getItemCount()) {
            //最后一个item设置为footerView
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View itemView = mInflater.inflate(R.layout.item_health_baogao, parent, false);

            return new MyViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            View itemView = mInflater.inflate(R.layout.recycleview_footview, parent, false);

            return new FooterViewHolder(itemView);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).itemBaogaoDanhao.setText(healthBGBeen.get(position).getPhysicalexaminationid());
            ((MyViewHolder) holder).itemBaogaoHzname.setText(PreferenceUtils.getInstance(context).getString(UserConfig.RealName));
            ((MyViewHolder) holder).itemBaogaoYsname.setText(healthBGBeen.get(position).getMeasuringdoctorname());
            ((MyViewHolder) holder).itemBaogaoZuzhi.setText(healthBGBeen.get(position).getOrganizationname());
            ((MyViewHolder) holder).itemBaogaoTime.setText(healthBGBeen.get(position).getMeasurementtime());

            ((MyViewHolder) holder).itemBaogaoChakan.setTag(position);

        } else if (holder instanceof FooterViewHolder) {


            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;


            switch (mLoadMoreStatus) {
                case PULLUP_LOAD_MORE:
                    footerViewHolder.mLoadLayout.setVisibility(View.VISIBLE);
                    footerViewHolder.mTvLoadText.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footerViewHolder.mLoadLayout.setVisibility(View.VISIBLE);
                    footerViewHolder.mTvLoadText.setText("正在加载更多...");
                    break;
                case NO_LOAD_MORE:
                    //隐藏加载更多
                    footerViewHolder.mLoadLayout.setVisibility(View.GONE);
                    break;

            }

        }
    }

    @Override
    public int getItemCount() {
        return healthBGBeen.size() + 1;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pbLoad)
        ProgressBar mPbLoad;
        @BindView(R.id.tvLoadText)
        TextView mTvLoadText;
        @BindView(R.id.loadLayout)
        LinearLayout mLoadLayout;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void AddHeaderItem(List<HealthBGBean> items) {
        healthBGBeen.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<HealthBGBean> items) {
        healthBGBeen.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * 更新加载更多状态
     *
     * @param status
     */
    public void changeMoreStatus(int status) {
        mLoadMoreStatus = status;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_baogao_danhao_text)
        TextView itemBaogaoDanhaoText;
        @BindView(R.id.item_baogao_danhao)
        TextView itemBaogaoDanhao;
        @BindView(R.id.item_baogao_chakan)
        TextView itemBaogaoChakan;
        @BindView(R.id.item_baogao_hzname)
        TextView itemBaogaoHzname;
        @BindView(R.id.item_baogao_ysname)
        TextView itemBaogaoYsname;
        @BindView(R.id.item_baogao_zuzhi)
        TextView itemBaogaoZuzhi;
        @BindView(R.id.item_baogao_time)
        TextView itemBaogaoTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemBaogaoChakan.setOnClickListener(HealthBGAdapter.this);
        }

    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * item里面有多个控件可以点击
     */
    public enum ViewName {
        ITEM,
        Button
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, ViewName viewName, int position);
    }

    @Override
    public void onClick(View v) {
        //注意这里使用getTag方法获取数据
        int position = (int) v.getTag();
        if (mOnItemClickListener != null) {
            switch (v.getId()) {
                case R.id.item_baogao_chakan:
                    mOnItemClickListener.onClick(v, ViewName.Button, position);
                    break;
//                default:
//                    mOnItemClickListener.onClick(v, ViewName.ITEM, position);
//                    break;
            }
        }
    }
}
