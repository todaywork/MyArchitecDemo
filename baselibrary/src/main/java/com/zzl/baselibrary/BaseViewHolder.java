package com.zzl.baselibrary;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zhenglin.zhu on 2020/11/25.
 * 持有Databinding
 */
public class BaseViewHolder<DB extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private DB mDb;

    public BaseViewHolder(@NonNull View itemView, DB db) {
        super(itemView);
        mDb = db;
    }
    public DB getDataBinding(){
        return mDb;
    }
}
