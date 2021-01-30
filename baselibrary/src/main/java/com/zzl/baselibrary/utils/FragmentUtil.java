package com.zzl.baselibrary.utils;


import android.util.SparseArray;

import com.zzl.baselibrary.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by zhenglin.zhu on 2020/12/2.
 */
public class FragmentUtil {
    private static SparseArray<Fragment> fragments = new SparseArray<>();
    //切换fragment
    public static synchronized void switchFragment(AppCompatActivity activity, int index) {
        if (fragments.size() <= index) {
            return;
        }
        Fragment to = fragments.get(index);
        if (to == null) {
            return;
        }
        if (getCurrentFragment()!=null && getCurrentFragment() == to) {
            return;
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (getCurrentFragment() == null || !getCurrentFragment().isAdded()) {
            if (!to.isAdded()) {
                transaction.add(index, to).commitAllowingStateLoss();
            } else {
                transaction.show(to).commitAllowingStateLoss();
            }
        } else {
            if (!to.isAdded()) {
                transaction.hide(getCurrentFragment()).add(index, to).commitAllowingStateLoss();
            } else {
                transaction.hide(getCurrentFragment()).show(to).commitAllowingStateLoss();
            }
        }
    }

    //切换fragment
    public static synchronized void switchFragment(AppCompatActivity activity, Fragment fragment) {
        if (getCurrentFragment()!=null && getCurrentFragment() == fragment) {
            return;
        }
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (getCurrentFragment() == null || !getCurrentFragment().isAdded()) {
            if (!fragment.isAdded()) {
                fragments.put(fragments.size(),fragment);
                transaction.add(R.id.frame_layout, fragment).commitAllowingStateLoss();
            } else {
                transaction.show(fragment).commitAllowingStateLoss();
            }
        } else {
            if (!fragment.isAdded()) {
                fragments.put(fragments.size(),fragment);
                transaction.hide(getCurrentFragment()).add(R.id.frame_layout, fragment).commitAllowingStateLoss();
            } else {
                transaction.hide(getCurrentFragment()).show(fragment).commitAllowingStateLoss();
            }
        }
    }

    public static void clearAll(){
        fragments.clear();
    }
    public static void clearFragment(int index){
        fragments.remove(index);
    }

    public static void clearFragment(Fragment fragment){
        int i = fragments.indexOfValue(fragment);
        if (i!=-1){
            fragments.remove(i);
        }
    }
    public static Fragment getCurrentFragment(){
        if (fragments.size()>0){
            return fragments.get(fragments.size()-1);
        }
        return null;
    }
}
