package com.zzl.baselibrary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzl.baselibrary.BaseViewHolder;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

/**
 * Created by zhenglin.zhu on 2020/11/24.
 * 1，有xml布局--databinding。databinding和UI绑定，需要拿到UI的都需要拿到databinding
 * 2，数据model，不同数据mode不同，所以用一个泛型定义
 */
public abstract class AbsBaseRecyclerAdapter<M,DB extends ViewDataBinding> extends ListAdapter<M,BaseViewHolder<DB>> {
    protected DB mDB;
    protected M mode;
    private OnItemClickListerner mItemClickListerner;

    protected AbsBaseRecyclerAdapter(@NonNull DiffUtil.ItemCallback diffCallback) {
        super(diffCallback);
    }

    /**
     * @param parent RecyclerView
     * @param viewType 默认0
     * @return viweholder
     */
    @NonNull
    @Override
    public BaseViewHolder<DB> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        mDB = DataBindingUtil.bind(view);
        BaseViewHolder<DB> holder = creatMyViewHolder(view, mDB);
        return holder;
    }

    protected abstract BaseViewHolder<DB> creatMyViewHolder(View view, DB DB);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<DB> holder, int position) {
        holder.getDataBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListerner!=null){
                    mItemClickListerner.itemClick(holder,getItem(position),position);
                }
            }
        });
        updateItemView(holder,getItem(position),position);
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder<DB> holder) {
        super.onViewRecycled(holder);

    }

    public void setOnItemClickListerner(OnItemClickListerner listerner){
        mItemClickListerner = listerner;
    }

    public interface OnItemClickListerner<M>{
        void itemClick(BaseViewHolder holder,M mode,int position);
    }

    protected abstract int getLayoutId();
    protected abstract void updateItemView(BaseViewHolder<DB> holder, M mode, int position);

}
