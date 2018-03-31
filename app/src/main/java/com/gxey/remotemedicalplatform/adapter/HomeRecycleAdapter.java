package com.gxey.remotemedicalplatform.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.WebBannerbenActgvity;
import com.gxey.remotemedicalplatform.javaben.Bannerben;
import com.gxey.remotemedicalplatform.javaben.HomeNewsBen;
import com.gxey.remotemedicalplatform.utils.ImageUtils;
import com.gxey.remotemedicalplatform.widget.RollHeaderView;
import com.gxey.remotemedicalplatform.widget.SlideShowView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class HomeRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Bannerben> HBlist = new ArrayList<>();
    private List<Bannerben> ZClist = new ArrayList<>();
    private List<Bannerben> NEWlist = new ArrayList<>();
    private List<HomeNewsBen> ZCNewslist = new ArrayList<>();
    private List<HomeNewsBen> HealthNewslist = new ArrayList<>();
    private String[] sy_menu = new String[]{"视频问诊", "健康报告", "综合病历", "用药建议", "诊疗记录", "医嘱", "体检数据", "闪电购药"};
    private int[] sy_menu_img = new int[]{R.drawable.shipinwenzhen_sy, R.drawable.jiankangbaogao_sy, R.drawable.dianzibingli_sy
            , R.drawable.yongyaojianyi_sy, R.drawable.zhenliaojilu_sy, R.drawable.yizhu_sy, R.drawable.tijianshuju_sy, R.drawable.sandiangouyao_sy};

    public Type1ItemClickListener type1ItemClickListener;
    public Type2ItemClickListener type2ItemClickListener;
    public Type3ItemClickListener type3ItemClickListener;
    public Type4ItemClickListener type4ItemClickListener;
    public Type5ItemClickListener type5ItemClickListener;
//    private List<Cheese> results;

    //type
    public static final int TYPE_SLIDER = 0xff00;
    public static final int TYPE_TYPE1 = 0xff01;
    public static final int TYPE_TYPE2 = 0xff02;
    public static final int TYPE_TYPE3 = 0xff03;
    public static final int TYPE_TYPE4 = 0xff04;
    public static final int TYPE_TYPE5 = 0xff05;
    public static final int TYPE_TYPE6 = 0xff06;

    public HomeRecycleAdapter(Context context, List<Bannerben> HBlist, List<Bannerben> ZClist, List<Bannerben> NEWlist, List<HomeNewsBen> ZCNewslist, List<HomeNewsBen> healthNewslist) {
        this.context = context;
        this.HBlist = HBlist;
        this.ZClist = ZClist;
        this.NEWlist = NEWlist;
        this.ZCNewslist = ZCNewslist;
        this.HealthNewslist = healthNewslist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_SLIDER:
                return new HolderSlider(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_slider, parent, false));
            case TYPE_TYPE1:
                return new HolderType1(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false), type1ItemClickListener);
            case TYPE_TYPE2:
                return new HolderType2(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_type2, parent, false), type2ItemClickListener);
            case TYPE_TYPE3:
                return new HolderType3(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_type3, parent, false), type3ItemClickListener);
            case TYPE_TYPE4:
                return new HolderType4(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_type4, parent, false), type4ItemClickListener);
            case TYPE_TYPE5:
                return new HolderType5(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_type5, parent, false), type5ItemClickListener);
            case TYPE_TYPE6:
                return new HolderType6(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_null, parent, false));

            default:
                Log.d("error", "viewholder is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HolderSlider) {
            bindTypeSlider((HolderSlider) holder, position);
        } else if (holder instanceof HolderType1) {
            bindType1((HolderType1) holder, position);
        } else if (holder instanceof HolderType2) {
            bindType2((HolderType2) holder, position);
        } else if (holder instanceof HolderType3) {
            bindType3((HolderType3) holder, position);
        } else if (holder instanceof HolderType4) {
            bindType4((HolderType4) holder, position);
        } else if (holder instanceof HolderType5) {
            bindType5((HolderType5) holder, position);
        } else if (holder instanceof HolderType6) {
            bindType6((HolderType6) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return 19+ZCNewslist.size()+HealthNewslist.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_SLIDER;
        } else if (1 <= position && position <= 8) {
            return TYPE_TYPE1;
        } else if (position == 9) {
            return TYPE_TYPE6;
        } else if (position == 10) {
            return TYPE_TYPE2;
        } else if (position == 11) {
            return TYPE_TYPE6;
        } else if (position == 12) {
            return TYPE_TYPE3;
        } else if (position == 13) {
            return TYPE_TYPE6;
        } else if (14 <= position && position <= (14 + ZCNewslist.size()-1)) {
            return TYPE_TYPE4;
        } else if (position == (14 + ZCNewslist.size())) {
            return TYPE_TYPE6;
        } else if (position == (14 + ZCNewslist.size()) + 1) {
            return TYPE_TYPE2;
        } else if (position == (14 + ZCNewslist.size() ) + 2) {
            return TYPE_TYPE6;
        } else if (position == (14 + ZCNewslist.size() ) + 3) {
            return TYPE_TYPE3;
        } else if (position == (14 + ZCNewslist.size() ) + 4) {
            return TYPE_TYPE6;
        } else {
            return TYPE_TYPE5;
        }
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case TYPE_SLIDER:
                            return gridManager.getSpanCount();
                        case TYPE_TYPE1:
                            return 2;
                        case TYPE_TYPE2:
                            return gridManager.getSpanCount();
                        case TYPE_TYPE3:
                            return gridManager.getSpanCount();
                        case TYPE_TYPE4:
                            return gridManager.getSpanCount();
                        case TYPE_TYPE5:
                            return gridManager.getSpanCount();
                        case TYPE_TYPE6:
                            return gridManager.getSpanCount();
                        default:
                            return gridManager.getSpanCount();
                    }
                }
            });
        }
    }

    /////////////////////////////

    private void bindTypeSlider(HolderSlider holder, int position) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < HBlist.size(); i++) {
            list.add(HBlist.get(i).getImgUrl());
        }
        holder.slideShowView.setImgUrlData(list);
        holder.slideShowView.setOnHeaderViewClickListener(new RollHeaderView.HeaderViewClickListener() {
            @Override
            public void HeaderViewClick(int position) {
                Bannerben bannerben = HBlist.get(position);
                Intent intent = new Intent(context, WebBannerbenActgvity.class);
                intent.putExtra("url", bannerben.getLinkUrl());
                context.startActivity(intent);
            }
        });
    }

    private void bindType1(HolderType1 holder, int position) {
        for (int i = 0; i < sy_menu_img.length; i++) {
            if (position - 1 == i) {
                holder.menu_img.setImageResource(sy_menu_img[i]);
                holder.menu_text.setText(sy_menu[i]);
            }
        }
    }

    private void bindType2(HolderType2 holder, int position) {
        if (ZClist.size() > 0) {
            String img = ZClist.get(0).getImgUrl();
            if (position == 10) {
                ImageUtils.load(context, img, holder.item_img_type2);
                holder.item_img_type2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebBannerbenActgvity.class);
                        intent.putExtra("url", ZClist.get(0).getLinkUrl());
                        context.startActivity(intent);
                    }
                });

            }
        }
        if (NEWlist.size() > 0) {
            String img2 = NEWlist.get(0).getImgUrl();
            if (position == (14 + NEWlist.size() ) + 3) {
                ImageUtils.load(context, img2, holder.item_img_type2);
                holder.item_img_type2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebBannerbenActgvity.class);
                        intent.putExtra("url", NEWlist.get(0).getLinkUrl());
                        context.startActivity(intent);
                    }
                });
            }
        }
    }

    private void bindType3(HolderType3 holder, int position) {
        if (position == 12) {
            holder.item_type3_title.setText(R.string.zhengcejiedu);
        } else if (position ==  (14 + ZCNewslist.size() - 1) + 4) {
            holder.item_type3_title.setText(R.string.jiankangtoutaio);
        }
    }

    private void bindType4(HolderType4 holder, int position) {
            holder.item_type4_title.setText(ZCNewslist.get(position - 14).getTitle());
            holder.item_type4_content.setText(ZCNewslist.get(position - 14).getContent());
            String time=ZCNewslist.get(position - 14).getReleaseTime().substring(0,10);
            holder.item_type4_time.setText(time);
    }

    private void bindType5(HolderType5 holder, int position) {

            holder.item_type5_title.setText(HealthNewslist.get(position - (14 + ZCNewslist.size()+5)).getTitle());
            holder.item_type5_content.setText(HealthNewslist.get(position - (14 + ZCNewslist.size()+5)).getContent());
            ImageUtils.load(context,HealthNewslist.get(position - (14 + ZCNewslist.size()+5)).getImgUrl(), holder.item_type5_headimg);

    }

    private void bindType6(HolderType6 holder, int position) {
    }

/////////////////////////////

    public class HolderSlider extends RecyclerView.ViewHolder {
        public RollHeaderView slideShowView;

        public HolderSlider(View itemView) {
            super(itemView);
            slideShowView = (RollHeaderView) itemView.findViewById(R.id.slideShowView);
        }
    }

    /**
     * type1
     */
    public class HolderType1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Type1ItemClickListener type1ItemClickListener;
        public ImageView menu_img;
        public TextView menu_text;

        public HolderType1(View itemView, Type1ItemClickListener type1ItemClickListener) {
            super(itemView);
            this.type1ItemClickListener = type1ItemClickListener;
            itemView.setOnClickListener(this);
            menu_img = (ImageView) itemView.findViewById(R.id.menu_img);
            menu_text = (TextView) itemView.findViewById(R.id.menu_text);
        }

        @Override
        public void onClick(View v) {
            if (type1ItemClickListener != null) {
                type1ItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    /**
     * 创建一个回调接口
     */
    public interface Type1ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setType1ItemClickListener(Type1ItemClickListener type1ItemClickListener) {
        this.type1ItemClickListener = type1ItemClickListener;
    }

    /**
     * Type2
     */
    public class HolderType2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView item_img_type2;
        public Type2ItemClickListener type2ItemClickListener;

        public HolderType2(View itemView, Type2ItemClickListener type2ItemClickListener) {
            super(itemView);
            this.type2ItemClickListener = type2ItemClickListener;
            itemView.setOnClickListener(this);
            item_img_type2 = (ImageView) itemView.findViewById(R.id.type2_img);
        }

        @Override
        public void onClick(View v) {
            if (type2ItemClickListener != null) {
                type2ItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    /**
     * 创建一个回调接口
     */
    public interface Type2ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setType2ItemClickListener(Type2ItemClickListener type2ItemClickListener) {
        this.type2ItemClickListener = type2ItemClickListener;
    }

    /**
     * Type3
     */
    public class HolderType3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Type3ItemClickListener type3ItemClickListener;
        public TextView item_type3_title;
        public TextView item_type3_gengduo;

        public HolderType3(View itemView, Type3ItemClickListener type3ItemClickListener) {
            super(itemView);
            this.type3ItemClickListener = type3ItemClickListener;

            item_type3_title = (TextView) itemView.findViewById(R.id.item_type3_title);
            item_type3_gengduo = (TextView) itemView.findViewById(R.id.item_type3_gengduo);
            item_type3_gengduo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (type3ItemClickListener != null) {
                type3ItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    /**
     * 创建一个回调接口
     */
    public interface Type3ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setType3ItemClickListener(Type3ItemClickListener type3ItemClickListener) {
        this.type3ItemClickListener = type3ItemClickListener;
    }

    /**
     * Type4
     */
    public class HolderType4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Type4ItemClickListener type4ItemClickListener;
        public TextView item_type4_title;
        public TextView item_type4_content;
//        public TextView item_type4_yuandu;
//        public TextView item_type4_dianzan;
        public TextView item_type4_time;

        public HolderType4(View itemView, Type4ItemClickListener type4ItemClickListener) {
            super(itemView);
            this.type4ItemClickListener = type4ItemClickListener;
            itemView.setOnClickListener(this);
            item_type4_title = (TextView) itemView.findViewById(R.id.item_type4_title);
            item_type4_content = (TextView) itemView.findViewById(R.id.item_type4_content);
//            item_type4_yuandu = (TextView) itemView.findViewById(R.id.item_type4_yuandu);
//            item_type4_dianzan = (TextView) itemView.findViewById(R.id.·item_type4_dianzan);
            item_type4_time = (TextView) itemView.findViewById(R.id.item_type4_time);
        }

        @Override
        public void onClick(View v) {
            if (type4ItemClickListener != null) {
                type4ItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    /**
     * 创建一个回调接口
     */
    public interface Type4ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setType4ItemClickListener(Type4ItemClickListener type4ItemClickListener) {
        this.type4ItemClickListener = type4ItemClickListener;
    }

    /**
     * Type5
     */
    public class HolderType5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Type5ItemClickListener type5ItemClickListener;
        public TextView item_type5_title;
        public TextView item_type5_content;
        public ImageView item_type5_headimg;

        public HolderType5(View itemView, Type5ItemClickListener type5ItemClickListener) {
            super(itemView);
            this.type5ItemClickListener = type5ItemClickListener;
            itemView.setOnClickListener(this);
            item_type5_title = (TextView) itemView.findViewById(R.id.item_type5_title);
            item_type5_content = (TextView) itemView.findViewById(R.id.item_type5_content);
            item_type5_headimg = (ImageView) itemView.findViewById(R.id.item_type5_img);
        }

        @Override
        public void onClick(View v) {
            if (type5ItemClickListener != null) {
                type5ItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    /**
     * 创建一个回调接口
     */
    public interface Type5ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setType5ItemClickListener(Type5ItemClickListener type5ItemClickListener) {
        this.type5ItemClickListener = type5ItemClickListener;
    }

    public class HolderType6 extends RecyclerView.ViewHolder {

        public HolderType6(View itemView) {
            super(itemView);
        }
    }

}
