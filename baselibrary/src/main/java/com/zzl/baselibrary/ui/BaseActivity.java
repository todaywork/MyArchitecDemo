package com.zzl.baselibrary.ui;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.zzl.baselibrary.viewmodel.AbsViewMode;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by zhenglin.zhu on 2020/11/20.
 * ui界面:Activity
 * 1：Viewdatabinding
 * 2：viewmodel （定义一个父类的viewMode）
 */
public abstract class BaseActivity<DB extends ViewDataBinding,VM extends AbsViewMode> extends AppCompatActivity {
    protected DB mDB;
    protected VM mVM;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDB = DataBindingUtil.setContentView(this, getLayoutId());
        mDB.setLifecycleOwner(this);
        mVM = new ViewModelProvider(this).get(getVMClass());
        initView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    protected DB getDB(){
        return mDB;
    }

    protected VM getVM(){
        return mVM;
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

    protected abstract void initView();

    protected abstract int getLayoutId();
}
