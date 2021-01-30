
package com.zzl.baselibrary.utils;

public class TimeUtils {

    /**
     * 小于10的数字前面补0
     *
     * @param number 数字
     */
    public static String int2Str(int number) {
        return number < 10 && number >= 0 ? "0" + number : String.valueOf(number);
    }

    /**
     * 将毫秒换算成00:00格式
     *
     * @param millisecond 毫秒
     */
    public static String getStrTime(int millisecond) {
        // 总时长
        int time = millisecond / 1000;
        int min = time / 60;
        int sec = time % 60;
        // 录制总时间
        String strTime =  TimeUtils.int2Str(min) + ":" + TimeUtils.int2Str(sec);
        return strTime;
    }

    /**
     * 将毫秒换算秒
     *
     * @param millisecond 毫秒
     */
    public static int parseMsToSec(int millisecond) {
        // 总时长
        int time = millisecond / 1000;
        int sec = time % 60;
        // 录制总时间
        return sec;
    }

    /**
     * 将毫秒换算秒
     *
     * @param second 毫秒
     */
    public static boolean secondIs(int second) {
        // 录制总时间
        return (second != 0 && second % 2 == 0);
    }
}
