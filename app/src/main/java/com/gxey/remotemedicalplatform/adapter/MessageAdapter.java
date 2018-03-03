package com.gxey.remotemedicalplatform.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.ActivityImageShow;
import com.gxey.remotemedicalplatform.javaben.MessageEntity;
import com.gxey.remotemedicalplatform.utils.ImageUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * Created by lanluo on 2017/1/8.
 */

public class MessageAdapter extends BaseAdapter implements View.OnClickListener{

    private List<MessageEntity> list;
    private Context context;
    private LayoutInflater inflater;

    final int TYPE_SEND_TEXT   = 0;
    final int TYPE_SEND_IMAGE=1;
    final int TYPE_COLLECT_TExt  = 2;
    final int TYPE_COLLECT_IMAGE   = 3;


    public MessageAdapter(List<MessageEntity> list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        switch (getItemViewType(position)){
            case TYPE_SEND_TEXT:
                SendTextHolder sendTextHolder;
                if(convertView==null){
                    sendTextHolder = new SendTextHolder();
                    convertView=inflater.inflate(R.layout.item_send_text,null);
                    sendTextHolder.mHead = (RoundedImageView) convertView.findViewById(R.id.doc_img);
                    sendTextHolder.mTVContent = (TextView) convertView.findViewById(R.id.tv_content);
                    convertView.setTag(sendTextHolder);

                }else{
                  sendTextHolder = (SendTextHolder) convertView.getTag();
                }

                sendTextHolder.mTVContent.setText(list.get(position).getContent());
                ImageUtils.setImageBitmapUrl(context,sendTextHolder.mHead,list.get(position).getHeadImg());
                break;

            case TYPE_SEND_IMAGE:
                SendImageHolder sendImageHolder;
                if(convertView==null){
                    sendImageHolder = new SendImageHolder();
                    convertView=inflater.inflate(R.layout.item_send_image,null);
                    sendImageHolder.mHead = (RoundedImageView) convertView.findViewById(R.id.doc_img);
                    sendImageHolder.mIVContent = (ImageView) convertView.findViewById(R.id.iv_content);
                    convertView.setTag(sendImageHolder);

                }else{
                    sendImageHolder = (SendImageHolder) convertView.getTag();
                }
                ImageUtils.setImageBitmapUrl(context,sendImageHolder.mIVContent,list.get(position).getContent());
                sendImageHolder.mIVContent.setTag(list.get(position).getContent());
                sendImageHolder.mIVContent.setOnClickListener(this);

                ImageUtils.setImageBitmapUrl(context,sendImageHolder.mHead,list.get(position).getHeadImg());



                break;
            case TYPE_COLLECT_TExt:
                CollectText collectText;
                if(convertView==null){
                    collectText = new CollectText();
                    convertView=inflater.inflate(R.layout.item_fre_doctor,null);
                    collectText.mHead = (RoundedImageView) convertView.findViewById(R.id.doc_img);
                    collectText.mTVContent = (TextView) convertView.findViewById(R.id.tv_content);
                    convertView.setTag(collectText);
                }else{
                    collectText = (CollectText) convertView.getTag();
                }

                collectText.mTVContent.setText(list.get(position).getContent());
                ImageUtils.setImageBitmapUrl(context,collectText.mHead,list.get(position).getHeadImg());

                break;
            case TYPE_COLLECT_IMAGE:
                CpllectImage cpllectImage;
                if(convertView==null){
                    cpllectImage = new CpllectImage();
                    convertView=inflater.inflate(R.layout.item_fre_doctor_img,null);
                    cpllectImage.mHead = (RoundedImageView) convertView.findViewById(R.id.doc_img);
                    cpllectImage.mIVContent = (ImageView) convertView.findViewById(R.id.iv_content);
                    convertView.setTag(cpllectImage);

                }else{
                    cpllectImage = (CpllectImage) convertView.getTag();
                }
                ImageUtils.setImageBitmapUrl(context,cpllectImage.mIVContent,list.get(position).getContent());
                ImageUtils.setImageBitmapUrl(context,cpllectImage.mHead,list.get(position).getHeadImg());
                cpllectImage.mIVContent.setTag(list.get(position).getContent());
                cpllectImage.mIVContent.setOnClickListener(this);

                break;
        }
        return convertView;
    }

    public int getItemViewType(int position) {
       MessageEntity entity =  list.get(position);
        if(entity.getDirection()==0){
            //0收
            if(entity.getCenum()==1){
                //文本
                return TYPE_COLLECT_TExt;
            }else if(entity.getCenum()==2){
                //图片
                return TYPE_COLLECT_IMAGE;
            }else {
                //文本
                return TYPE_COLLECT_TExt;
            }

        }else{
            //1发
            if(entity.getCenum()==1){
                return TYPE_SEND_TEXT;
            }else if(entity.getCenum()==2){
                return TYPE_SEND_IMAGE;
            }else{
                return TYPE_SEND_TEXT;
            }

        }

    }


    public int getViewTypeCount() { // 总共需要展示的条目类型数量

        return 4;

    }

    @Override
    public void onClick(View v) {
        String path = (String) v.getTag();
        Intent intent = new Intent(context, ActivityImageShow.class);
        intent.putExtra("path",path);
        context.startActivity(intent);

    }

    class SendTextHolder{
         RoundedImageView mHead;
         TextView mTVContent;
    }


    class SendImageHolder{
        RoundedImageView mHead;
        ImageView mIVContent;


    }

    class CollectText{
         RoundedImageView mHead;
         TextView mTVContent;

    }
    class CpllectImage{
        RoundedImageView mHead;
        ImageView mIVContent;
    }

    }
