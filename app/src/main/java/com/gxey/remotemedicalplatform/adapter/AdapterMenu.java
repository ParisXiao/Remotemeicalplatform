package com.gxey.remotemedicalplatform.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.bean.BeanMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 申诉记录适配器
 * Created by Administrator on 2017/12/12 0012.
 */

public class AdapterMenu extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mInflater;
    private Context context;
    private MyItemClickListener mItemClickListener;
    private List<BeanMenu> beanMenus = new ArrayList<>();


    public AdapterMenu(Context context, List<BeanMenu> beanMenus) {
        this.context = context;
        this.beanMenus = beanMenus;
        this.mInflater = LayoutInflater.from(context);


    }

//    @Override
//    public int getItemViewType(int position) {
//
//        if (position % 2 == 0) {
//            return 1;
//        } else if (position % 3 == 0) {
//            return 2;
//        } else {
//            return 3;
//        }
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_menu, parent, false);
        MyViewHolder holder = new MyViewHolder(view, mItemClickListener);
        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder) holder).menuImg.setImageResource(beanMenus.get(position).getMeunIocn());
        ((MyViewHolder) holder).menuText.setText(beanMenus.get(position).getMeunName());


    }

    @Override
    public int getItemCount() {
        return beanMenus.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private MyItemClickListener mListener;
        ImageView menuImg;
        TextView menuText;

        public MyViewHolder(View itemView,MyItemClickListener myItemClickListener) {
            super(itemView);
            //将全局的监听赋值给接口
            this.mListener = myItemClickListener;
            itemView.setOnClickListener(this);
            menuImg = (ImageView) itemView.findViewById(R.id.menu_img);
            menuText = (TextView) itemView.findViewById(R.id.menu_text);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }

        }
    }
    /**
     * 创建一个回调接口
     */
    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 在activity里面adapter就是调用的这个方法,将点击事件监听传递过来,并赋值给全局的监听
     *
     * @param myItemClickListener
     */
    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }
}
