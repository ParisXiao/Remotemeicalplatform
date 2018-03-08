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
import com.gxey.remotemedicalplatform.bean.TiWenBean;
import com.gxey.remotemedicalplatform.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;

/**
 * 申诉记录适配器
 * Created by Administrator on 2017/12/12 0012.
 */

public class TiWenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {



    private LayoutInflater mInflater;
    private Context context;
    private List<TiWenBean> list = new ArrayList<>();
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


    public TiWenAdapter(Context context, List<TiWenBean> list) {
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
            return TYPE_Charts;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View itemView = mInflater.inflate(R.layout.item_tiwen, parent, false);

            return new MyViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            View itemView = mInflater.inflate(R.layout.recycleview_footview, parent, false);

            return new FooterViewHolder(itemView);
        } else if (viewType == TYPE_Charts) {
            View itemView = mInflater.inflate(R.layout.recycleview_charts, parent, false);

            return new ChartsViewHolder(itemView);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).itemClName.setText(list.get(position).getTemperature());
            ((MyViewHolder) holder).itemYscl.setText(list.get(position).getDeviceID());
            ((MyViewHolder) holder).itemClTime.setText(list.get(position).getAddtime());
            ((MyViewHolder) holder).itemClBz.setText(list.get(position).getRemark());

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

        }else if (holder instanceof ChartsViewHolder) {
            ChartsViewHolder chartsViewHolder= (ChartsViewHolder) holder;
            List<Column> columnList = new ArrayList<>(); //柱子列表
            List<SubcolumnValue> subcolumnValueList;     //子柱列表（即一个柱子，因为一个柱子可分为多个子柱）
            List<AxisValue> axisValues = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                subcolumnValueList = new ArrayList<>();
                subcolumnValueList.add(new SubcolumnValue(Float.valueOf(list.get(i).getTemperature()), ChartUtils.pickColor()));

                Column column = new Column(subcolumnValueList);
                column.setHasLabels(true);                    //设置列标签
//            column.setHasLabelsOnlyForSelected(true);       //只有当点击时才显示列标签

                columnList.add(column);

                //设置坐标值
                axisValues.add(new AxisValue(i).setLabel(TimeUtils.MyDateMD(list.get(i).getAddtime())));
            }
            ColumnChartData  mColumnChartData = new ColumnChartData(columnList);
             /*===== 坐标轴相关设置 =====*/
            Axis axisX = new Axis(axisValues); //将自定义x轴显示值传入构造函数
            Axis axisY = new Axis().setHasLines(true); //setHasLines是设置线条
            axisX.setName("日期");    //设置横轴名称
            axisY.setName("体温（℃）");    //设置竖轴名称
            mColumnChartData.setAxisXBottom(axisX); //设置横轴
            mColumnChartData.setAxisYLeft(axisY);   //设置竖轴

            //以上所有设置的数据、坐标配置都已存放到mColumnChartData中，接下来给mColumnChartView设置这些配置
            ((ChartsViewHolder) holder).chart.setColumnChartData(mColumnChartData);
             /*===== 设置竖轴最大值 =====*/
            //法一：
            Viewport v =   ((ChartsViewHolder) holder).chart.getMaximumViewport();
            v.top = 42;
            ((ChartsViewHolder) holder).chart.setCurrentViewport(v);
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public class ChartsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chart)
        lecho.lib.hellocharts.view.ColumnChartView chart;
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

    public void AddHeaderItem(List<TiWenBean> items) {
        list.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<TiWenBean> items) {
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
        @BindView(R.id.item_cl_name)
        TextView itemClName;
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
