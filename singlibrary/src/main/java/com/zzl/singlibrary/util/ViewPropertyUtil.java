package com.zzl.singlibrary.util;

import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by zhenglin.zhu on 2020/12/2.
    用于属性动画，且在非UI线程执行，即使主线程堵塞，也不影响动画效果的流畅
    使用：执行动画前，需要先调用createViewPropertyAnimatorRT和setViewPropertyAnimatorRT方法
    demo：createViewPropertyAnimatorRT(view.animate())
    1，动画过程不能使用任何监听器。
    2，View支持硬件加速(Android 4.0 之后默认支持),注意：这个是支持，而不是开启。也就是4.0以后该条件满足
       <url></>https://www.sohu.com/a/424467119_611601
     */
public class ViewPropertyUtil {
    private static final String TAG = ViewPropertyUtil.class.getSimpleName();
    public static Object createViewPropertyAnimatorRT(View view) {

        try{

            final Class<?> animRtCalzz = Class.forName( "android.view.ViewPropertyAnimatorRT");

            final Constructor<?> animRtConstructor = animRtCalzz.getDeclaredConstructor(View.class);

            animRtConstructor.setAccessible(true);

            return animRtConstructor.newInstance(view);

        } catch(Exception e) {
            e.printStackTrace();
            Log.d(TAG, "createViewPropertyAnimatorRT ");
        }

        return null;

    }
    public static void setViewPropertyAnimatorRT(ViewPropertyAnimator animator, Object rt){

        try{

            Class<?> animClazz = Class.forName("android.view.ViewPropertyAnimator");

            final Field animRtField = animClazz.getDeclaredField( "mRTBackend");

            animRtField.setAccessible( true);

            animRtField.set(animator, rt);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
