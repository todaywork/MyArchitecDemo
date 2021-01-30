package com.zzl.mylibrary.adapter;

import android.util.Log;
import android.view.View;

import com.zzl.baselibrary.BaseViewHolder;
import com.zzl.baselibrary.adapter.AbsBaseRecyclerAdapter;
import com.zzl.mylibrary.R;
import com.zzl.mylibrary.databinding.ItemSongLayoutBinding;
import com.zzl.mylibrary.mode.SongMode;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by zhenglin.zhu on 2020/11/26
 *
 */
public class SongAdapter extends AbsBaseRecyclerAdapter<SongMode, ItemSongLayoutBinding> {
    private static final String TAG = SongAdapter.class.getSimpleName();
    public SongAdapter() {
        super(new MySongDiffCallback());
    }

    @Override
    protected BaseViewHolder<ItemSongLayoutBinding> creatMyViewHolder(View view, ItemSongLayoutBinding DB) {
        return new SongViewHolder(view,DB);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_song_layout;
    }

    @Override
    protected void updateItemView(BaseViewHolder<ItemSongLayoutBinding> holder, SongMode mode, int position) {
        holder.getDataBinding().title.setText("123");
        Log.d(TAG, "updateItemView position="+position);
    }


    public static class MySongDiffCallback extends DiffUtil.ItemCallback<SongMode>{


        @Override
        public boolean areItemsTheSame(@NonNull SongMode oldItem, @NonNull SongMode newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull SongMode oldItem, @NonNull SongMode newItem) {
            return false;
        }
    }
    public class SongViewHolder extends BaseViewHolder<ItemSongLayoutBinding>{

        public SongViewHolder(@NonNull View itemView, ItemSongLayoutBinding itemSongLayoutBinding) {
            super(itemView, itemSongLayoutBinding);
        }
    }
}
