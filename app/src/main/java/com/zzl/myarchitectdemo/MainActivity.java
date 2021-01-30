package com.zzl.myarchitectdemo;

import android.app.Activity;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zzl.baselibrary.constant.Constant;
import com.zzl.baselibrary.databinding.ActivityMainBinding;
import com.zzl.baselibrary.ui.BaseActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * UI界面
 * 1, viewmode
 * 2, databinding
 */
public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewModel> {
    private SparseArray<Fragment> mSparseArray =new SparseArray<>();
    private Fragment mFragment;
    private Activity mActivity;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        getDB().navigationView.setOnNavigationItemSelectedListener(mItemSelectedListener);
        mFragment = (Fragment) ARouter.getInstance()
                .build(Constant.fragment_song_name)
                .withString("key1","1123")
                .navigation();
        Log.d(TAG,"fffff="+mFragment);
        mSparseArray.put(0,mFragment);
        switchFragment(mFragment);


    }
    BottomNavigationView.OnNavigationItemSelectedListener mItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.tv1:
                    Toast.makeText(MainActivity.this, "tv1", Toast.LENGTH_SHORT).show();
                    Fragment fragment = mSparseArray.get(0);
                    if (fragment!=null){
                        switchFragment(fragment);
                    }
                    item.setChecked(true);
                    break;
                case R.id.tv2:
                    Toast.makeText(MainActivity.this, "tv2", Toast.LENGTH_SHORT).show();
                    mFragment =mSparseArray.get(1);
                    Log.d(TAG, "onNavigationItemSelected i==="+" "+mSparseArray.size());
                    if (mFragment==null){
                        mFragment = (Fragment) ARouter.getInstance()
                                .build(Constant.fragment_sing_name)
                                .navigation();
                        mSparseArray.put(1,mFragment);
                    }
                    item.setChecked(true);
                    switchFragment(mFragment);
                    break;
                case R.id.tv3:
                    item.setChecked(true);
                    Toast.makeText(MainActivity.this, "tv3", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }

    };

    private void switchFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}