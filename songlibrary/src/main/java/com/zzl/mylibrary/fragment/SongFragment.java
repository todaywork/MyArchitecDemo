package com.zzl.mylibrary.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zzl.baselibrary.constant.Constant;
import com.zzl.baselibrary.ui.BaseFragment;
import com.zzl.mylibrary.R;
import com.zzl.mylibrary.adapter.SongAdapter;
import com.zzl.mylibrary.databinding.FragmentSongBinding;
import com.zzl.mylibrary.mode.SongMode;
import com.zzl.mylibrary.viewmodel.SongViewModel;

import java.util.ArrayList;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zhenglin.zhu on 2020/11/20.
 * ui:databinding,viewmodel
 */
@Route(path = Constant.fragment_song_name)
public class SongFragment extends BaseFragment<FragmentSongBinding, SongViewModel> {
    @Override
    protected int getLayutId() {
        return R.layout.fragment_song;
    }

    @Override
    protected void initView() {
        getDataBinding().setLifecycleOwner(this);
        getDataBinding().tv.setText("song fragment");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        getDataBinding().recyclerView.setLayoutManager(linearLayoutManager);
        SongAdapter songAdapter = new SongAdapter();
        getDataBinding().recyclerView.setAdapter(songAdapter);
        getViewMode().getData().mLiveData.observe(this, new Observer() {
            @Override
            public void onChanged(Object songViewData) {
                SongMode songMode = (SongMode) songViewData;
                ArrayList<SongMode> list = new ArrayList<>();
                list.add(songMode);
                songAdapter.submitList(list);
            }
        });
    }

    @Override
    protected void initData() {
        getViewMode().requesData();
    }

}
