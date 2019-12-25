package com.mengk.viewmodellivedata.common.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;


public class GlideUtil {

    public static void loadLogo(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)
                .into(imageView);
    }


    /**
     * 加载圆角图片并采样压缩大图片 防止内存溢出
     *
     * @param context
     * @param imgUrl
     * @param imageView
     */
    public static void loadImg(Context context, String imgUrl, ImageView imageView) {
//        int width = (DisplayUtil.getScreenWidth(context) - (int) DisplayUtil.dip2px(12) * 3) / 2;
//        // 设置图片圆角角度
//        RoundedCorners roundedCorners = new RoundedCorners(DisplayUtil.dip2px(4));
//        // 通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
//        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners)
//                .placeholder(R.drawable.icon_default_info_ball)
//                .error(R.drawable.icon_default_info_ball)
//                .centerCrop()
//                .override(width, DisplayUtil.dip2px(100));
//        Glide.with(context).load(imgUrl)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .apply(options).into(imageView);
    }

    /**
     * 加载图片with回调
     *
     * @param context
     * @param imgUrl
     * @param imageView
     */
    public static void loadImgWithCallback(Context context, String imgUrl, ImageView imageView) {
//        int width = (DisplayUtil.getScreenWidth(context) - (int) DisplayUtil.dip2px(12) * 3) / 2;
//        // 设置图片圆角角度
//        RoundedCorners roundedCorners = new RoundedCorners(DisplayUtil.dip2px(4));
//        // 通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
//        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners)
//                .placeholder(R.drawable.icon_default_info_ball)
//                .error(R.drawable.icon_default_info_ball)
//                .centerCrop()
//                .override(width, DisplayUtil.dip2px(100));
//        Glide.with(context).load(imgUrl)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        ToastUtils.showToast("图片不存在");
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                })
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .apply(options).into(imageView);
    }

    /**
     *
     * @param context
     * @param imgUrl
     * @param imageView
     */
    public static void loadTeamLogo(Context context, String imgUrl, ImageView imageView) {
//        int width = (DisplayUtil.getScreenWidth(context) - (int) DisplayUtil.dip2px(12) * 3) / 2;
//        // 设置图片圆角角度
//        RoundedCorners roundedCorners = new RoundedCorners(DisplayUtil.dip2px(4));
//        // 通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
//        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners)
//                .placeholder(R.drawable.icon_default_match_event)
//                .error(R.drawable.icon_default_match_event)
//                .override(width, DisplayUtil.dip2px(100));
//        Glide.with(context).load(imgUrl)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .apply(options).into(imageView);

        //支持https加载
    }







}
