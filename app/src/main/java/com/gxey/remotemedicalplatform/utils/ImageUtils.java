package com.gxey.remotemedicalplatform.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.gxey.remotemedicalplatform.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by simple on 16/12/16.
 */

public class ImageUtils {

    public static void load(Context context, String url, ImageView imageView) {
        Picasso.with(context)
                .load(url)
                .into(imageView);
    }

    public static void loadHeadImg(Context context, String url, ImageView imageView) {
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.touxiang)
                .error(R.drawable.touxiang)
                .into(imageView);
    }

    /**
     * 按比例缩小图片的像素以达到压缩的目的
     *
     * @param imgPath
     * @author JPH
     * @date 2014-12-5下午11:30:59
     */
    public static String compressImageByPixel(String imgPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;// 只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, newOpts);
        newOpts.inJustDecodeBounds = false;
        int width = newOpts.outWidth;
        int height = newOpts.outHeight;
        float maxSize = 1000f;// 默认1000px
        int be = 1;
        if (width > height && width > maxSize) {// 缩放比,用高或者宽其中较大的一个数据进行计算
            be = (int) (newOpts.outWidth / maxSize);
        } else if (width < height && height > maxSize) {
            be = (int) (newOpts.outHeight / maxSize);
        }
        be++;
        newOpts.inSampleSize = be;// 设置采样率
        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;// 该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;// 。当系统内存不够时候图片自动被回收
        bitmap = BitmapFactory.decodeFile(imgPath, newOpts);
        String path = JFileHelper.getSDCardPath() + "/temp.jpg";
        return compressImageByQuality(bitmap, path);// 压缩好比例大小后再进行质量压缩
    }


    /**
     * 多线程压缩图片的质量
     *
     * @param bitmap  内存中的图片
     * @param imgPath 图片的保存路径
     * @author JPH
     * @date 2014-12-5下午11:30:43
     */
    public static String compressImageByQuality(final Bitmap bitmap,
                                                final String imgPath) {

        // TODO Auto-generated method stub
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 质量压缩方法，把压缩后的数据存放到baos中
        // (100表示不压缩，0表示压缩到最小)
        while (baos.toByteArray().length / 1024 > 100) {// 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即让下一次的写入覆盖之前的内容
            options -= 10;// 图片质量每次减少10
            if (options < 0)
                options = 0;// 如果图片质量小于10，则将图片的质量压缩到最小值
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 将压缩后的图片保存到baos中
            if (options == 0)
                break;// 如果图片的质量已降到最低则，不再进行压缩
        }
        try {
            FileOutputStream fos = new FileOutputStream(new File(imgPath));// 将压缩后的图片保存的本地上指定路径中
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgPath;

    }


    public static void setImageBitmapUrl(Context context, RoundedImageView iv, String url) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url).resize(iv.getLayoutParams().width, iv.getLayoutParams().height).into(iv);
        }

    }

    public static void setImageBitmapUrl(Context context, ImageView iv, String url) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url).resize(iv.getLayoutParams().width, iv.getLayoutParams().height).into(iv);
        }
    }

}
