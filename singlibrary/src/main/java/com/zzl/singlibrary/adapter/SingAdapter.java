package com.zzl.singlibrary.adapter;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.zzl.baselibrary.BaseViewHolder;
import com.zzl.baselibrary.adapter.AbsBaseRecyclerAdapter;
import com.zzl.singlibrary.R;
import com.zzl.singlibrary.databinding.ItemSingLayoutBinding;
import com.zzl.singlibrary.mode.SingMode;
import com.zzl.singlibrary.util.CornerTransform;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by zhenglin.zhu on 2020/11/25.
 *
 */
public class SingAdapter extends AbsBaseRecyclerAdapter<SingMode.ListBean, ItemSingLayoutBinding> {
    private static final String TAG = SingAdapter.class.getSimpleName();
    public SingAdapter() {
        super(new MyDiffCallback());
    }

    @Override
    protected BaseViewHolder<ItemSingLayoutBinding> creatMyViewHolder(View view, ItemSingLayoutBinding DB) {
        return new SingViewHolder(view,DB);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_sing_layout;
    }

    @Override
    protected void updateItemView(BaseViewHolder<ItemSingLayoutBinding> holder, SingMode.ListBean mode, int position) {
        Log.d(TAG, "updateItemView "+mode.name+" ,url="+mode.category.image);
        holder.getDataBinding().songName.setText(mode.name);
        holder.getDataBinding().singerName.setText(mode.category.name);

        CornerTransform transformation = new CornerTransform(holder.itemView.getContext(),10);
        transformation.setExceptCorner(false,false,true,true);
        Glide.with(holder.itemView.getContext()).load(mode.category.image)
                .apply(RequestOptions.bitmapTransform(transformation))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.d("============", "此处为加载图片错误时的操作，返回true代表用户自己处理，返回false代表交给Glide处理GlideException" + e + "=model=" + model + "=target=" + target + "=isFirstResource=" + isFirstResource);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.d("============", "此处为资源准备好时调用的方法，返回true表示事件将会被拦截，不再继续传递下去；返回false表示事件会传递下去，一般为false");
                        return false;
                    }
                })
                .into(holder.getDataBinding().potrait);
    }

    public static class MyDiffCallback extends DiffUtil.ItemCallback<SingMode.ListBean> {
        //判断新老数据是否相同
        @Override
        public boolean areItemsTheSame(@NonNull SingMode.ListBean oldItem, @NonNull SingMode.ListBean newItem) {
            return oldItem.category==newItem.category;
        }

        @Override
        public boolean areContentsTheSame(@NonNull SingMode.ListBean oldItem, @NonNull SingMode.ListBean newItem) {
            return false;
        }
    }
    public class SingViewHolder extends BaseViewHolder<ItemSingLayoutBinding> {

        public SingViewHolder(@NonNull View itemView, ItemSingLayoutBinding itemSingLayoutBinding) {
            super(itemView, itemSingLayoutBinding);
        }
    }
}
