package com.zzl.singlibrary;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zzl.baselibrary.BaseViewHolder;
import com.zzl.baselibrary.adapter.AbsBaseRecyclerAdapter;
import com.zzl.baselibrary.constant.Constant;
import com.zzl.baselibrary.ui.BaseFragment;
import com.zzl.baselibrary.ui.RecyclerViewDivider;
import com.zzl.baselibrary.utils.FragmentUtil;
import com.zzl.singlibrary.adapter.SingAdapter;
import com.zzl.singlibrary.databinding.FragmentSingBinding;
import com.zzl.singlibrary.mode.SingMode;
import com.zzl.singlibrary.viewmode.SingViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zhenglin.zhu on 2020/11/19.
 * databindg的实现类，viewmode：观察数据变化
 */
@Route(path = Constant.fragment_sing_name)
public class SingFragment extends BaseFragment<FragmentSingBinding, SingViewModel> {
    private static final String TAG = SingFragment.class.getSimpleName();
    private SingAdapter mSingAdapter;

    @Override
    protected int getLayutId() {
        return R.layout.fragment_sing;
    }

    @Override
    protected void initView() {
        getDataBinding().tv.setText("sing fragment");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mSingAdapter = new SingAdapter();
        getDataBinding().recyclerView.setLayoutManager(linearLayoutManager);
        getDataBinding().recyclerView.addItemDecoration(new RecyclerViewDivider(getContext(),RecyclerView.HORIZONTAL,R.drawable.divider_shape,R.color.divider_color));
        getDataBinding().recyclerView.setAdapter(mSingAdapter);
        getViewMode().getData().mLiveData.observe(this, this::updateViewData);
        mSingAdapter.setOnItemClickListerner(new AbsBaseRecyclerAdapter.OnItemClickListerner() {
            @Override
            public void itemClick(BaseViewHolder  holder, Object mode, int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("song",(SingMode.ListBean)mode);
                Fragment fragment = (Fragment) ARouter.getInstance()
                        .build(Constant.fragment_play_name)
                        .withBundle("bundle", bundle)
                        .navigation();

                Log.d(TAG, "itemClick fragment="+fragment);
                FragmentUtil.switchFragment((AppCompatActivity) getActivity(),fragment);
                Toast.makeText(SingFragment.this.getContext(), "position="+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {
        getViewMode().requestData(20727, 0, 7, 100, 0);
    }

    private void updateViewData(SingMode singMode) {
        mSingAdapter.submitList(singMode.c.list);
        Log.d(TAG, "onChanged singMode= "+singMode.c.list.size());
    }


}
