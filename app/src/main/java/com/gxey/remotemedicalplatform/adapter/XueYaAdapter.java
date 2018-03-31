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
import com.gxey.remotemedicalplatform.bean.XueYaBean;
import com.gxey.remotemedicalplatform.utils.TimeUtils;
import com.gxey.remotemedicalplatform.widget.ChartView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 申诉记录适配器
 * Created by Administrator on 2017/12/12 0012.
 */

public class XueYaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {



    private LayoutInflater mInflater;
    private Context context;
    private List<XueYaBean> list = new ArrayList<>();
    private List<XueYaBean> listLast = new ArrayList<>();
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ChartsSystolic = 2;
    private static final int TYPE_ChartsDiastolic = 3;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE = 2;

    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = 0;


    public XueYaAdapter(Context context, List<XueYaBean> list) {
        this.context = context;
        this.list = list;
        this.mInflater = LayoutInflater.from(context);


    }

    @Override
    public int getItemViewType(int position) {

        if (position + 1 == getItemCount()) {
            //最后一个item设置为footerView
            return TYPE_FOOTER;
        } else if (position == 0) {
            return TYPE_ChartsSystolic;
        } else if (position == 1) {
            return TYPE_ChartsDiastolic;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View itemView = mInflater.inflate(R.layout.item_xueya, parent, false);

            return new MyViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            View itemView = mInflater.inflate(R.layout.recycleview_footview, parent, false);

            return new FooterViewHolder(itemView);
        } else if (viewType == TYPE_ChartsSystolic) {
            View itemView = mInflater.inflate(R.layout.recycleview_charts, parent, false);

            return new ChartsView1Holder(itemView);
        } else if (viewType == TYPE_ChartsDiastolic) {
            View itemView = mInflater.inflate(R.layout.recycleview_charts, parent, false);

            return new ChartsView2Holder(itemView);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).itemSsName.setText(list.get(position-1).getSystolic());
            ((MyViewHolder) holder).itemSzName.setText(list.get(position-1).getDiastolic());
            ((MyViewHolder) holder).itemSsJg.setText(list.get(position-1).getSystolicRemark());
            ((MyViewHolder) holder).itemSzJg.setText(list.get(position-1).getDiastolicRemark());
            ((MyViewHolder) holder).itemYscl.setText(list.get(position-1).getDeviceID());
            ((MyViewHolder) holder).itemClTime.setText(list.get(position-1).getAddtime());
            ((MyViewHolder) holder).itemClBz.setText(list.get(position-1).getRemark());

            ((MyViewHolder) holder).itemView.setTag(position-1);

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

        } else if (holder instanceof ChartsView1Holder) {
            if (list.size() > 7) {
                for (int i = 0; i < 7; i++) {
                    listLast.add(i, list.get(list.size() - i - 1));
                }

            } else {
                for (int i = 0; i < list.size(); i++) {
                    listLast.add(list.get(i));
                }
            }
            //x轴坐标对应的数据
            List<String> xValue = new ArrayList<>();
            //y轴坐标对应的数据
            List<Float> yValue = new ArrayList<>();
            //折线对应的数据
            Map<String, Float> value = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                xValue.add(TimeUtils.MyDateMD(list.get(i).getAddtime()));
                value.put(TimeUtils.MyDateMD(list.get(i).getAddtime()), Float.valueOf(list.get(i).getSystolic()));//60--240
            }

            for (int i = 0; i < 20; i++) {
                yValue.add((float) (i * 10));
            }

            ((ChartsView1Holder) holder).chart.setValue(0,value, xValue, yValue);
            ((ChartsView1Holder) holder).chartName.setText("收缩压图");


        } else if (holder instanceof ChartsView2Holder) {
            if (list.size() > 7) {
                for (int i = 0; i < 7; i++) {
                    listLast.add(i, list.get(list.size() - i - 1));
                }

            } else {
                for (int i = 0; i < list.size(); i++) {
                    listLast.add(list.get(i));
                }
            }
            //x轴坐标对应的数据
            List<String> xValue = new ArrayList<>();
            //y轴坐标对应的数据
            List<Float> yValue = new ArrayList<>();
            //折线对应的数据
            Map<String, Float> value = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                xValue.add(TimeUtils.MyDateMD(list.get(i).getAddtime()));
                value.put(TimeUtils.MyDateMD(list.get(i).getAddtime()), Float.valueOf(list.get(i).getDiastolic()));//60--240
            }

            for (int i = 0; i < 20; i++) {
                yValue.add((float) (i *5));
            }

            ((ChartsView2Holder) holder).chart.setValue(0,value, xValue, yValue);
            ((ChartsView2Holder) holder).chartName.setText("舒张压图");

        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 2;
    }

    public class ChartsView1Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.chartview_name)
        TextView chartName;
        @BindView(R.id.chartview)
        ChartView chart;

        public ChartsView1Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ChartsView2Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.chartview)
        ChartView chart;
        @BindView(R.id.chartview_name)
        TextView chartName;
        public ChartsView2Holder(View itemView) {
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

    public void AddHeaderItem(List<XueYaBean> items) {
        list.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<XueYaBean> items) {
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
        @BindView(R.id.item_ss_name)
        TextView itemSsName;
        @BindView(R.id.item_sz_name)
        TextView itemSzName;
        @BindView(R.id.item_ss_jg)
        TextView itemSsJg;
        @BindView(R.id.item_sz_jg)
        TextView itemSzJg;
        @BindView(R.id.item_ys_text)
        TextView itemYsText;
        @BindView(R.id.item_yscl)
        TextView itemYscl;
        @BindView(R.id.item_cl_time)
        TextView itemClTime;
        @BindView(R.id.item_cl_bz)
        TextView itemClBz;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
//                case R.id.item_baogao_chakan:
//                    mOnItemClickListener.onClick(v, ViewName.Button, position);
//                    break;
                default:
                    mOnItemClickListener.onClick(v, ViewName.ITEM, position);
                    break;
            }
        }
    }
}
