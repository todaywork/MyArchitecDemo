package com.zzl.baselibrary.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zzl.baselibrary.viewmodel.AbsViewMode;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by zhenglin.zhu on 2020/11/19.
 * ui
 */

public abstract class BaseFragment<DB extends ViewDataBinding, VM extends AbsViewMode> extends Fragment {
    protected DB mDB;
    protected VM mVM;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDB = DataBindingUtil.inflate(inflater, getLayutId(), container, false);
        mDB.setLifecycleOwner(this);
        return mDB.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVM = new ViewModelProvider(this).get(getVMClass());
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract int getLayutId();

    protected abstract void initView();

    protected VM getViewMode() {
        return mVM;
    }

    protected DB getDataBinding() {
        return mDB;
    }

    /**
     * 拿到泛型中的Class类数据
     *
     * @return viewmode.class
     */
    protected Class<VM> getVMClass() {
        Class clazz;
        Type type = this.getClass().getGenericSuperclass();//拿到的是直接父类，且包括泛型数据
        //type打印：com.tlkg.base.fragment.BaseFragment<com.tlkg.karaoke.song.databinding.FragmentHotSongBinding, com.tlkg.karaoke.song.viewmodel.HotSongViewModel>
        if (type instanceof ParameterizedType) {
            clazz = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            clazz = AndroidViewModel.class;
        }
        return clazz;
    }
}
