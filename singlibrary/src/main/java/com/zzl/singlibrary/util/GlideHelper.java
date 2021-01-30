package com.zzl.singlibrary.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.ByteArrayOutputStream;
import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by zhenglin.zhu on 2020/11/25.
 */
public class GlideHelper {

    private static GlideHelper sGlideHelper;
    private RequestOptions options;

    public static GlideHelper getInstance() {
        if (sGlideHelper == null) {
            synchronized (GlideHelper.class) {
                if (sGlideHelper == null) {
                    sGlideHelper = new GlideHelper();
                }
            }
        }
        return sGlideHelper;
    }

    @SuppressLint("CheckResult")
    public void loadImageView(ImageView imageView, final String url, int defautId){
        initRequestOptions();
        options .placeholder(defautId)
                .error(defautId);
        Glide.with(imageView.getContext()).load(url).apply(options).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("============", "此处为加载图片错误时的操作，返回true代表用户自己处理，返回false代表交给Glide处理GlideException" + e + "=model=" + model + "=target=" + target + "=isFirstResource=" + isFirstResource);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.d("============", "此处为资源准备好时调用的方法，返回true表示时间将会被拦截，不再继续传递下去；返回false表示事件会传递下去，一般为false");
                return false;
            }
        }).apply(options).into(imageView);
    }

    @SuppressLint("CheckResult")
    public void loadImageView(ImageView imageView, String url, int defautId, int maxImageHight, int maxImageWidth){
        initRequestOptions();
        options.override(maxImageWidth,maxImageHight)
                .placeholder(defautId)
                .error(defautId);
        Glide.with(imageView.getContext()).load(url).apply(options).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("============", "此处为加载图片错误时的操作，返回true代表用户自己处理，返回false代表交给Glide处理GlideException" + e + "=model=" + model + "=target=" + target + "=isFirstResource=" + isFirstResource);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.d("============", "此处为资源准备好时调用的方法，返回true表示时间将会被拦截，不再继续传递下去；返回false表示事件会传递下去，一般为false");
                return false;
            }
        }).apply(options).into(imageView);
    }

    /**
     * @param imageView
     * @param url
     */
    @SuppressLint("CheckResult")
    public void loadImageView(ImageView imageView, String url){
        initRequestOptions();
        Glide.with(imageView.getContext()).load(url).apply(options).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("============", "此处为加载图片错误时的操作，返回true代表用户自己处理，返回false代表交给Glide处理GlideException" + e + "=model=" + model + "=target=" + target + "=isFirstResource=" + isFirstResource);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.d("============", "此处为资源准备好时调用的方法，返回true表示时间将会被拦截，不再继续传递下去；返回false表示事件会传递下去，一般为false");
                return false;
            }
        }).apply(options).into(imageView);
    }

    /**通过uri加载图片
     * @param imageView
     * @param uri
     * @param defautId
     */
    @SuppressLint("CheckResult")
    public void loadImageView(ImageView imageView, Uri uri, int defautId){
        initRequestOptions();
        options.placeholder(defautId).error(defautId);
        Glide.with(imageView.getContext()).load(uri).apply(options).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("============", "此处为加载图片错误时的操作，返回true代表用户自己处理，返回false代表交给Glide处理GlideException" + e + "=model=" + model + "=target=" + target + "=isFirstResource=" + isFirstResource);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.d("============", "此处为资源准备好时调用的方法，返回true表示时间将会被拦截，不再继续传递下去；返回false表示事件会传递下去，一般为false");
                return false;
            }
        }).apply(options).into(imageView);
    }

    /**
     * 获取Glide加载后的bitmap
     * @param imageView
     * @param url
     */
    public void loadBitmap(ImageView imageView, final String url){
        Glide.with(imageView.getContext()).asBitmap().load(url).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                showPic(resource);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                resource.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
//                IconCacheUtil.writeCache(url,byteArray);
            }

        });
    }

    /**通过file 加载图片
     * @param imageView
     * @param file
     * @param defautId
     */
    @SuppressLint("CheckResult")
    public void loadImageView(ImageView imageView, File file, int defautId){
        initRequestOptions();
        options.placeholder(defautId).error(defautId);
        Glide.with(imageView.getContext()).load(file).apply(options).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("============", "此处为加载图片错误时的操作，返回true代表用户自己处理，返回false代表交给Glide处理GlideException" + e + "=model=" + model + "=target=" + target + "=isFirstResource=" + isFirstResource);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.d("============", "此处为资源准备好时调用的方法，返回true表示时间将会被拦截，不再继续传递下去；返回false表示事件会传递下去，一般为false");
                return false;
            }
        }).apply(options).into(imageView);
    }

    /** 加载圆角角度
     * @param imageView
     * @param url
     * @param defautId
     * @param roundRadious 圆角的角度
     */
    @SuppressLint("CheckResult")
    public void loadRoundImageView(ImageView imageView, String url, int defautId,int roundRadious){
        initRequestOptions();
        options.placeholder(defautId).error(defautId).transform(new RoundedCorners(roundRadious));
        Glide.with(imageView.getContext()).load(url).apply(options).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("============", "此处为加载图片错误时的操作，返回true代表用户自己处理，返回false代表交给Glide处理GlideException" + e + "=model=" + model + "=target=" + target + "=isFirstResource=" + isFirstResource);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.d("============", "此处为资源准备好时调用的方法，返回true表示时间将会被拦截，不再继续传递下去；返回false表示事件会传递下去，一般为false");
                return false;
            }
        }).apply(options).into(imageView);
    }

    /** 加载圆形图片
     * @param imageView
     * @param url
     * @param defautId
     */
    @SuppressLint("CheckResult")
    public void loadCircleImageView(ImageView imageView, String url, int defautId){
        initRequestOptions();
        options.placeholder(defautId).error(defautId).transform(new CircleCrop());
        Glide.with(imageView.getContext()).load(url).apply(options).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("============", "此处为加载图片错误时的操作，返回true代表用户自己处理，返回false代表交给Glide处理GlideException" + e + "=model=" + model + "=target=" + target + "=isFirstResource=" + isFirstResource);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.d("============", "此处为资源准备好时调用的方法，返回true表示时间将会被拦截，不再继续传递下去；返回false表示事件会传递下去，一般为false");
                return false;
            }
        }).apply(options).into(imageView);
    }

    /** 加载图片，带动画效果
     * @param imageView
     * @param url
     * @param defautId
     */
    @SuppressLint("CheckResult")
    public void loadImageWithCrossFade(ImageView imageView, String url, int defautId){
        initRequestOptions();
        options.placeholder(defautId).error(defautId);
        Glide.with(imageView.getContext()).load(url).apply(options).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.d("============", "此处为加载图片错误时的操作，返回true代表用户自己处理，返回false代表交给Glide处理GlideException" + e + "=model=" + model + "=target=" + target + "=isFirstResource=" + isFirstResource);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.d("============", "此处为资源准备好时调用的方法，返回true表示时间将会被拦截，不再继续传递下去；返回false表示事件会传递下去，一般为false");
                return false;
            }
        }).transition(withCrossFade()).apply(options).into(imageView);
    }



    @SuppressLint("CheckResult")
    private void initRequestOptions() {
        if (options == null) {
            options = new RequestOptions();
            //磁盘缓存原数据
            options.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    //ImageView居中显示
                    .centerCrop();
        }
    }


    /** 清除glide图片内存缓存
     * @param context
     */
    public void clearCache(Context context) {
        clearMemory(context);
    }

    /**
     * 清除内存缓存
     * @param context
     */
    private void clearMemory(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除磁盘缓存
     */
    public void clearImageDiskCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Glide磁盘缓存大小
     * @return CacheSize 单位MB
     */
    public double getCacheSize(Context context) {
        try {
            return getFormatSize(getFolderSize(new File(context.getCacheDir() + "/" + ExternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 格式化单位
     * @param size size
     * @return size 单位MB
     */
    private static double getFormatSize(double size) {
        double kiloByte = size / 1024;
        double megaByte = kiloByte / 1024;
        return megaByte;
    }


    /**
     * 获取指定文件夹内所有文件大小的和
     * @param file file
     * @return size
     * @throws Exception
     */
    private long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
}
