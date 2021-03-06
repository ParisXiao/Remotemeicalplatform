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
import com.gxey.remotemedicalplatform.bean.XinDianTuBean;
import com.gxey.remotemedicalplatform.widget.ChartView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 申诉记录适配器
 * Created by Administrator on 2017/12/12 0012.
 */

public class XinDianTuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


    private LayoutInflater mInflater;
    private Context context;
    private List<XinDianTuBean> list = new ArrayList<>();
    private List<XinDianTuBean> listLast = new ArrayList<>();
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_Charts = 2;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE = 2;

    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = 0;


    public XinDianTuAdapter(Context context, List<XinDianTuBean> list) {
        this.context = context;
        this.list = list;
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
            View itemView = mInflater.inflate(R.layout.item_xindiantu, parent, false);

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
            if (list.get(position).getIsException().equals("1")){
                ((MyViewHolder) holder).itemSfyc.setText("异常");
                ((MyViewHolder) holder).itemSfyc.setTextColor(context.getResources().getColor(R.color.red));
            }else {
                ((MyViewHolder) holder).itemSfyc.setText("正常");
            }
            ((MyViewHolder) holder).itemSbInfo.setText(list.get(position).getDeviceID());
            ((MyViewHolder) holder).itemCsTime.setText(list.get(position).getAddtime());
            ((MyViewHolder) holder).itemStartTime.setText(list.get(position).getStartTime());
            ((MyViewHolder) holder).itemEndTime.setText(list.get(position).getEndTime());
            if (list.get(position).getExceptionStartTime().equals("1900-01-01 00:00:00")){
                ((MyViewHolder) holder).itemYcstartTime.setText("");
                ((MyViewHolder) holder).itemYcendTime.setText("");
            }else {
                ((MyViewHolder) holder).itemYcstartTime.setText(list.get(position).getExceptionStartTime());
                ((MyViewHolder) holder).itemYcendTime.setText(list.get(position).getExceptionEndTime());
            }
            ((MyViewHolder) holder).itemBjInfo.setText(list.get(position).getAlertInfo());
            ((MyViewHolder) holder).itemClick.setTag(position);
            ((MyViewHolder) holder).itemView.setTag(position);

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
        return list.size() + 1;
    }

    public class ChartsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chartview)
        ChartView chart;
        @BindView(R.id.chartview_name)
        TextView chartName;

        public ChartsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
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

    public void AddHeaderItem(List<XinDianTuBean> items) {
        list.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<XinDianTuBean> items) {
        list.addAll(items);
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

        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_click)
        TextView itemClick;
        @BindView(R.id.item_name_text)
        TextView itemNameText;
        @BindView(R.id.item_sfyc)
        TextView itemSfyc;
        @BindView(R.id.item_ys_text)
        TextView itemYsText;
        @BindView(R.id.item_start_time)
        TextView itemStartTime;
        @BindView(R.id.item_time_text)
        TextView itemTimeText;
        @BindView(R.id.item_end_time)
        TextView itemEndTime;
        @BindView(R.id.item_ycstart_time)
        TextView itemYcstartTime;
        @BindView(R.id.item_ycend_time)
        TextView itemYcendTime;
        @BindView(R.id.item_bj_info)
        TextView itemBjInfo;
        @BindView(R.id.item_sb_info)
        TextView itemSbInfo;
        @BindView(R.id.item_cs_time)
        TextView itemCsTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemClick.setOnClickListener(XinDianTuAdapter.this);

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
                case R.id.item_click:
                    mOnItemClickListener.onClick(v, ViewName.Button, position);
                    break;
                default:
                    mOnItemClickListener.onClick(v, ViewName.ITEM, position);
                    break;
            }
        }
    }
}
