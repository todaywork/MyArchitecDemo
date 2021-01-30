package com.zzl.singlibrary.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by zhenglin.zhu on 2020/11/25.
 */
public class GlideUtil {
    public static void loadImageWithCrossFade(ImageView iv, Object url) {
        Glide.with(iv.getContext())
                .load(url)
                .transition(withCrossFade())
                .into(iv);
    }

    public static void loadImage(ImageView iv, Object url) {
        Glide.with(iv.getContext())
                .load(url)
                .into(iv);
    }

    public static void loadImage(ImageView iv, Object url, Drawable errorDrawable) {
        Glide.with(iv.getContext())
                .load(url)
                .apply(RequestOptions.errorOf(errorDrawable).placeholder(errorDrawable))
                .into(iv);
    }

    public void loadImage(ImageView iv, Object url, int errorId) {
        Glide.with(iv.getContext())
                .load(url)
                .apply(RequestOptions.errorOf(errorId).placeholder(errorId))
                .into(iv);
    }


    public static void loadRoundImage(ImageView iv, Object url, int round) {
        Glide.with(iv.getContext())
                .load(url)
                .transform(new CenterCrop(), new RoundedCorners(round))
                .into(iv);
    }

    public static void loadCircleImage(ImageView iv, Drawable url) {
        Glide.with(iv.getContext())
                .load(url)
                .transform(new CircleCrop()).into(iv);
    }

    public static void loadCircleImage(ImageView iv, String url) {
        Glide.with(iv.getContext())
                .load(url)
                .transform(new CircleCrop()).into(iv);
    }
}
