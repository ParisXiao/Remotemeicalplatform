package com.gxey.remotemedicalplatform.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.bean.DoctorBean;
import com.gxey.remotemedicalplatform.inter.CustomBitmapLoadCallBack;
import com.gxey.remotemedicalplatform.utils.MyStrUtil;

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

public class DoctorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private LayoutInflater mInflater;
    private Context context;
    private List<DoctorBean> doctorBeen = new ArrayList<>();
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


    public DoctorAdapter(Context context, List<DoctorBean> doctorBeen) {
        this.context = context;
        this.doctorBeen = doctorBeen;
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
            View itemView = mInflater.inflate(R.layout.item_doctorlist, parent, false);

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

            if (!MyStrUtil.isEmpty(doctorBeen.get(position).getDoctor_iocn())) {
                ImageOptions imageOptions = new ImageOptions.Builder()
//                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
//                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                        .setCrop(true)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                        .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
//                   .setLoadingDrawableId(R.mipmap.ic_launcher)
//                  .setFailureDrawableId(R.mipmap.ic_launcher)
                        .build();
                x.image().bind(((MyViewHolder) holder).doctor_icon, doctorBeen.get(position).getDoctor_iocn().toString(), imageOptions, new CustomBitmapLoadCallBack(((MyViewHolder) holder).doctor_icon));
//
            } else {
                ((MyViewHolder) holder).doctor_icon.setImageResource(R.drawable.touxiang);
            }
            ((MyViewHolder) holder).doctor_name.setText(doctorBeen.get(position).getDoctor_name());
            ((MyViewHolder) holder).doctor_content.setText(doctorBeen.get(position).getDoctor_content());
            ((MyViewHolder) holder).doctor_keshi.setText(doctorBeen.get(position).getDoctor_keshi());
            ((MyViewHolder) holder).doctor_pdrs.setText(doctorBeen.get(position).getDoctor_renshu());
            if (!MyStrUtil.isEmpty(doctorBeen.get(position).getDoctor_labels())){
               List<String> s= Arrays.asList(doctorBeen.get(position).getDoctor_labels().split(","));
                ArrayList<String> labels=new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    labels.add(s.get(i));
                }
                ((MyViewHolder) holder).doctor_labels.setLabels(labels);
            }
            ((MyViewHolder) holder).itemView.setTag(position);
            ((MyViewHolder) holder).doctor_ljsq.setTag(position);

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
        return doctorBeen.size()+1;
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

    public void AddHeaderItem(List<DoctorBean> items) {
        doctorBeen.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<DoctorBean> items) {
        doctorBeen.addAll(items);
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
        ImageView doctor_icon;
        TextView doctor_name;
        TextView doctor_keshi;
        TextView doctor_content;
        TextView doctor_pdrs;
        Button doctor_ljsq;
        LabelsView doctor_labels;

        public MyViewHolder(View itemView) {
            super(itemView);
            //将全局的监听赋值给接口
            doctor_icon = (ImageView) itemView.findViewById(R.id.doctor_head);
            doctor_name = (TextView) itemView.findViewById(R.id.doctor_name);
            doctor_keshi = (TextView) itemView.findViewById(R.id.doctor_keshi);
            doctor_content = (TextView) itemView.findViewById(R.id.doctor_content);
            doctor_pdrs = (TextView) itemView.findViewById(R.id.doctor_pdrs);
            doctor_ljsq = (Button) itemView.findViewById(R.id.doctor_ljsq);
            doctor_labels = (LabelsView) itemView.findViewById(R.id.doctor_labels);
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
                case R.id.doctor_ljsq:
                    mOnItemClickListener.onClick(v, ViewName.Button, position);
                    break;
                default:
                    mOnItemClickListener.onClick(v, ViewName.ITEM, position);
                    break;
            }
        }
    }
}
