package com.gxey.remotemedicalplatform.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.bean.YiZhuBean;
import com.gxey.remotemedicalplatform.inter.CustomBitmapLoadCallBack;
import com.gxey.remotemedicalplatform.utils.ImageUtils;
import com.gxey.remotemedicalplatform.utils.MyStrUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 申诉记录适配器
 * Created by Administrator on 2017/12/12 0012.
 */

public class YiZhuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private LayoutInflater mInflater;
    private Context context;
    private List<YiZhuBean> yiZhuBeen = new ArrayList<>();
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


    public YiZhuAdapter(Context context, List<YiZhuBean> yiZhuBeen) {
        this.context = context;
        this.yiZhuBeen = yiZhuBeen;
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
            View itemView = mInflater.inflate(R.layout.item_yizhu, parent, false);

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

            if (!MyStrUtil.isEmpty(yiZhuBeen.get(position).getHeadimg())) {

                ImageUtils.loadHeadImg(context,yiZhuBeen.get(position).getHeadimg(),((MyViewHolder) holder).yizhuHead);
            } else {
                ((MyViewHolder) holder).yizhuHead.setImageResource(R.drawable.touxiang);
            }
            ((MyViewHolder) holder).yizhuName.setText(yiZhuBeen.get(position).getDoctorname());
            ((MyViewHolder) holder).yizhuContent.setText(yiZhuBeen.get(position).getContent());
            ((MyViewHolder) holder).yizhuNo.setText(yiZhuBeen.get(position).getNumber());
            ((MyViewHolder) holder).yizhuTime.setText(yiZhuBeen.get(position).getOpentoldtime());
            if (!MyStrUtil.isEmpty(yiZhuBeen.get(position).getPositionname())) {
                List<String> s = Arrays.asList(yiZhuBeen.get(position).getPositionname().split(","));
                ArrayList<String> labels = new ArrayList<>();
                if (s.size()>2){
                    for (int i = 0; i < 2; i++) {
                        labels.add(s.get(i));
                    }
                }else {
                    for (int i = 0; i < s.size(); i++) {
                        labels.add(s.get(i));
                    }
                }

                ((MyViewHolder) holder).yizhuLabels.setLabels(labels);
            }
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
        return yiZhuBeen.size() + 1;
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

    public void AddHeaderItem(List<YiZhuBean> items) {
        yiZhuBeen.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<YiZhuBean> items) {
        yiZhuBeen.addAll(items);
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
        @BindView(R.id.yizhu_head)
        RoundedImageView yizhuHead;
        @BindView(R.id.yizhu_name)
        TextView yizhuName;
        @BindView(R.id.yizhu_labels)
        LabelsView yizhuLabels;
        @BindView(R.id.yizhu_No)
        TextView yizhuNo;
        @BindView(R.id.yizhu_content)
        TextView yizhuContent;
        @BindView(R.id.yizhu_time)
        TextView yizhuTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(YiZhuAdapter.this );
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
                default:
                    mOnItemClickListener.onClick(v, ViewName.ITEM, position);
                    break;
            }
        }
    }
}
