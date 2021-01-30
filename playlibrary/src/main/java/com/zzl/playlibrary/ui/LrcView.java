
package com.zzl.playlibrary.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

import com.zzl.baselibrary.utils.TimeUtils;
import com.zzl.playlibrary.mode.LyricModel;

/**
 * karaoke歌词
 */
public class LrcView extends View {
    private static final String TAG = LrcView.class.getSimpleName();
    /* ######################### 外部可设置变量 ######################### */
    /**
     * 歌词集合，包含所有行的歌词
     */
    private LyricModel lrcModel;

    /***
     * 当前播放时间
     */
    private int lrcTime;

    /**
     * 对齐方式
     */
    private int gravity;

    /**
     * 歌词展示行
     */
    private int maxLine;

    /**
     * 高亮的歌词文字大小
     */
    private int highlightLrcFontSize;

    /**
     * 歌词字体大小默认值
     **/
    private int lrcFontSize;

    /**
     * 时间字体大小默认值
     **/
    private int startTimeFontSize;

    /**
     * 两行歌词之间的间距
     **/
    private int lineSpace;

    /**
     * 歌词距离顶部的距离
     **/
    private int lrcPaddingTop;

    /**
     * 倒计时圆点之间的距离
     */
    private int countDownSpace;

    /**
     * 倒计时圆点的半径大小
     */
    private int countDownRadius;

    /**
     * 倒计时圆点的颜色
     */
    private int countDownColor;

    /**
     * 当前高亮歌词的字体颜色为黄色
     */
    private int highlightRowColor;

    /**
     * 不高亮歌词的字体颜色
     */
    private int normalRowColor;

    /**
     * 当前行被唱过的歌词变色
     */
    private int highlightFontColor;

    /***
     * 当前高亮歌词的行数 从0开始计算
     */
    private int highlightRow;

    /**
     * 倒计时时间（即歌词开始演唱时间），第一次加载和滑动后重播都要设置一次
     */
    private int countDownTime;

    /**
     * 倒计时最大计算个数（1秒一个）
     */
    private int countDownMaxNumber = 5;

    /**
     * 是否显示倒计时
     */
    private boolean showCountDown = true;

    /**
     * 是否支持触摸调节 （UGC作品展示为false）
     */
    private boolean touchEnable = true;

    /**
     * 是否显示开始拖追歌词的第一句开始的时间线
     */
    private boolean isShowStartLineOnDrag = true;

    /**
     * 是否正在播放，外部播放状态改变时，需要设置此属性
     */
    private boolean isPlaying = false;

    /**
     * 歌词演唱开始时间文字距离左边的距离
     */
    private int startTimeLeftPadding;

    /**
     * 歌词未位增加一点区域
     */
    private float endSpace;

    /**
     * 歌词拖拽时的提示图标
     */
    private Bitmap dragBitmap;

    /**
     * 歌词拖拽时的提示图标宽度
     */
    private int dragBitmapWidth;

    /**
     * 歌词拖拽时的提示图标高度
     */
    private int dragBitmapHeight;

    /**
     * 歌词监听
     */
    private ILrcViewListener lrcListener;

    /**
     * 歌词字体样式
     */
    private Typeface lrcNormalTypeface;

    /**
     * 歌词字体样式
     */
    private Typeface lrcHighlightTypeface;

    /* ######################### 内部不可外设变量 ######################### */
    /**
     * 画笔
     */
    private Paint mLrcPint;

    /**
     * 当前倒计时圆点个数
     */
    private int mCountDownNumber = 0;

    /**
     * 倒计时圆点是否已显示完毕
     */
    private boolean mHasCountDownFinish = false;

    /**
     * 滑动结束时，歌词距离向上或者向下偏移量，通过动画执行
     */
    private ValueAnimator mLrcOffsetAnim;

    /**
     * 滑动时，歌词距离向上或者向下偏移量
     */
    private float mLrcOffset;

    /**
     * 记录触摸时间
     */
    private long mTouchTime;

    /**
     * 记录最近按下的播放 （暂停状态，短时间按下，恢复播放）
     */
    private boolean mLatePlayState;

    /**
     * 记录触摸按下前的时候高亮行
     */
    private int mOnTouchDownHighlightRow;

    /**
     * 记录触摸最后x坐标的位置
     */
    private float mLastMotionX;

    /**
     * 记录触摸最后y坐标的位置,如果滑动后，滑动距离超过一句歌词的高度，则更新为当前滑动的距离
     *
     */
    private float mLastMotionY;

    /**
     * 是否正在拖拽歌词
     */
    private boolean mDraggingLrc = false;

    /**
     * 歌词总行数
     */
    private int mLrcTotal;

    /**
     * 滑动惯性计算比较
     */
    private VelocityTracker mVelocityTracker;

    /**
     * 滑动惯性计算
     */
    private Scroller mScroller;

    /**
     * 开始惯性滚动
     */
    boolean mStartScroll = false;

    /**
     *  最小的速度。小米1上是 75像素/s
     */
    private int mMinimumVelocity;

    /**
     *  最大的速度。小米1上是 6000像素/s
     */
    private int mMaximumVelocity;

    /**
     *  起始时间文字区域
     */
    private Rect mTimeRect;

    /**
     * 控件宽
     */
    private int mViewWidth;

    /**
     * 控件高
     */
    private int mViewHeight;

    /**
     * 构造
     * @param context Context
     */
    public LrcView(Context context) {
        this(context, null);
    }

    /**
     * 构造
     * @param context Context
     * @param attr AttributeSet （无xml预设）
     */
    public LrcView(Context context, AttributeSet attr) {
        super(context, attr);
        mLrcPint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 默认值设置，根据实际情况，需要重设置一遍
        setNormalTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL));
        setHighlightTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
        setHighlightFontColor(0xFFFF5265);
        setHighlightRowColor(Color.WHITE);
        setNormalRowColor(0x99FFFFFF);
        setCountDownColor(Color.WHITE);
        setHighlightLrcFontSize(dp2px(20));
        setLrcFontSize(dp2px(18));
        setLineSpace(dp2px(20));
        setLrcPaddingTop(dp2px(0));
        setCountDownSpace(dp2px(6));
        setCountDownRadius(dp2px(8));
        setStartTimeFontSize(dp2px(12));
        setStartTimeLeftPadding(dp2px(8));
        setCountDownMaxNumber(5);
        setGravity(Gravity.CENTER);
        setMaxLine(Integer.MAX_VALUE);

    }

    /**
     * 对预设值进行dp转px，保持不同机型缩放比例
     */
    private int dp2px(int dp) {
        return (int) (this.getContext().getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    /**
     * 数据全部重置，从头开始演唱
     * 设置歌词/重唱时会调用
     */
    public void reStart() {
        highlightRow = 0;
        lrcTime = 0;
        mCountDownNumber = 0;
        countDownTime = 0;
        invalidate();
    }

    /**
     * 设置歌词行集合
     * 数据全部重置
     *
     * @param lrcModel 歌词实体类
     */
    public void setLrc(LyricModel lrcModel) {
        this.lrcModel = lrcModel;
        this.mLrcTotal = lrcModel.getLyricData().getLyricList().size();
        reStart();
    }

    /**
     * 设置歌词的字体
     */
    public void setNormalTypeface(Typeface typeface) {
        this.lrcNormalTypeface = typeface;
    }

    /**
     * 设置歌词的字体
     */
    public void setHighlightTypeface(Typeface typeface) {
        this.lrcHighlightTypeface = typeface;
    }

    /**
     * 歌词位置展示模式
     */
    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    /**
     * 歌词显示最大行
     */
    public void setMaxLine(int maxLine) {
        this.maxLine = maxLine;
    }

    /**
     * 高亮歌词的字体大小
     */
    public void setHighlightLrcFontSize(int highlightLrcFontSize) {
        this.highlightLrcFontSize = highlightLrcFontSize;
    }

    /**
     * 正常歌词的字体大小
     */
    public void setLrcFontSize(int lrcFontSize) {
        this.lrcFontSize = lrcFontSize;
        // 歌词结尾多渲染一段
        this.endSpace = lrcFontSize * 0.5f;
    }

    /**
     * 拖追显示的起始时间字体大小
     */
    public void setStartTimeFontSize(int timeFontSize) {
        this.startTimeFontSize = timeFontSize;
    }

    /**
     * 拖追显示的起始时间距离左边的距离
     */
    public void setStartTimeLeftPadding(int leftPadding) {
        this.startTimeLeftPadding = leftPadding;
    }

    /**
     * 歌词的行间距
     */
    public void setLineSpace(int lineSpace) {
        this.lineSpace = lineSpace;
    }

    /**
     * 设置歌词距离顶部的距离，顶部会显示倒计时，需要一定的PaddingTop
     */
    public void setLrcPaddingTop(int lrcPaddingTop) {
        this.lrcPaddingTop = lrcPaddingTop;
    }

    /**
     * 设置高亮歌词字体颜色
     */
    public void setHighlightRowColor(int highlightRowColor) {
        this.highlightRowColor = highlightRowColor;
    }

    /**
     * 设置正常歌词的颜色
     */
    public void setNormalRowColor(int normalRowColor) {
        this.normalRowColor = normalRowColor;
    }

    /**
     * 设置被演唱的歌词高亮字的颜色
     */
    public void setHighlightFontColor(int highlightFontColor) {
        this.highlightFontColor = highlightFontColor;
    }

    /**
     * 设置是否展示倒计时
     */
    public void setShowCountDown(boolean showCountDown) {
        this.showCountDown = showCountDown;
    }

    /**
     * 设置倒计时最大个数 (默认时5个)
     */
    public void setCountDownMaxNumber(int countDownMaxNumber) {
        this.countDownMaxNumber = countDownMaxNumber;
    }

    /**
     * 设置倒计时圆点间距
     */
    public void setCountDownSpace(int countDownSpace) {
        this.countDownSpace = countDownSpace;
    }

    /**
     * 设置倒计时圆点半径
     */
    public void setCountDownRadius(int countDownRadius) {
        this.countDownRadius = countDownRadius;
    }

    /**
     * 设置倒计时圆点颜色
     */
    public void setCountDownColor(int countDownColor) {
        this.countDownColor = countDownColor;
    }

    /**
     * 设置倒计时结束时间（即歌词开始演唱时间），第一次加载和滑动后重播都要设置一次
     */
    public void setCountDownTime(int countDownTime) {
        this.countDownTime = countDownTime;
    }

    /**
     * 设置是否支持触摸调节 （UGC作品展示为false）
     */
    public void setTouchable(boolean touchEnable) {
        this.touchEnable = touchEnable;
    }

    /**
     * 设置是否正在播放，外部播放状态改变时，需要设置此属性
     */
    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    /**
     * 设置是否显示开始拖追歌词的第一句开始的时间线
     */
    public void setShowStartLineOnDrag(boolean show) {
        isShowStartLineOnDrag = show;
    }

    /**
     * 设置歌词监听器
     */
    public void setListener(ILrcViewListener lrcListener) {
        this.lrcListener = lrcListener;
    }

    /**
     * 设置第几行歌词高亮
     *
     * @param bitmap 设置拖拽的bitmap
     * @param width  拖拽的bitmap宽度
     * @param height 拖拽的bitmap高度
     */
    public void setDragBitmap(Bitmap bitmap, int width, int height) {
        this.dragBitmap = bitmap;
        this.dragBitmapWidth = width;
        this.dragBitmapHeight = height;
    }

    /**
     * 设置歌词当前播放时间，歌词的位置更新是靠此方法来更新的
     */
    public void seekLrcToTime(int time) {
        this.lrcTime = time;
        updateLrcTime();
    }

    /**
     * 获取歌词距离顶部的布局
     */
    private int getLrcPaddingTop() {
        return lrcPaddingTop;
    }

    /**
     * 获取歌词每行占的高度
     * @return 返回歌词一行的高度
     */
    private int getLineHeight() {
        return lineSpace + lrcFontSize;
    }

    /**
     * 获取当前歌词播放时间
     * @return 返回歌词当前进行的时间
     */
    public int getLrcTime() {
        return lrcTime;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = this.getMeasuredWidth();
        mViewHeight = this.getMeasuredHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!touchEnable) {
            return super.onTouchEvent(event);
        }
        if (lrcModel == null || mLrcTotal == 0) {
            return super.onTouchEvent(event);
        }
        if (mVelocityTracker == null) {
            mScroller = new Scroller(getContext());
            mMinimumVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
            mMaximumVelocity = ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity();
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        return touchEvent(event);
    }

    private boolean touchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mLastMotionY = event.getY();
                mLastMotionX = event.getX();
                mOnTouchDownHighlightRow = highlightRow;
                mTouchTime = System.currentTimeMillis();
                mLatePlayState = isPlaying;
                mDraggingLrc = false;
                pause();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                scrollLrc(event.getY());
                break;
            case MotionEvent.ACTION_UP:
                if (mDraggingLrc) {
                    mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                    float initialVelocity = (int) mVelocityTracker.getYVelocity();
                    // 是否执行惯性滚动
                    if (Math.abs(initialVelocity) > mMinimumVelocity) {
                        int min = getLineHeight() * highlightRow;
                        int max = getLineHeight() * (mLrcTotal - highlightRow);
                        mStartScroll = true;
                        // 继续滚动
                        mScroller.fling(0, (int) mLastMotionY, 0, (int) initialVelocity, 0, 0, -max, min);
                    } else {
                        // 停止滚动
                        scrollOrDragEnd();
                        return true;
                    }
                } else if (isClicked(event) && System.currentTimeMillis() - mTouchTime < 100 && !mLatePlayState) {
                    // 没有滚动
                    Log.d("zzl", "touchEvent not scroll");
                    scrollOrDragEnd();
                    return true;
                }
                invalidate();
                break;
            default:
                Log.d(TAG,"onTouchEvent - default");
                break;

        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (!mStartScroll) {
            return;
        }
        boolean scrollEnd = false;
        if (mScroller.computeScrollOffset()) {
            if (highlightRow == 0 || highlightRow == mLrcTotal - 1) {
                scrollEnd = true;
            } else {
                Log.d("zzl", "computeScroll=== "+mScroller.getCurrY());
                scrollLrc(mScroller.getCurrY());
            }
        } else if (mScroller.isFinished()) {
            scrollEnd = true;
        }
        if (scrollEnd) {
            mStartScroll = false;
            if (mDraggingLrc) {
                scrollOrDragEnd();
            }
        }
    }

    /**
     * 滚动结束
     */
    private void scrollOrDragEnd() {
        // 恢复播放
        seekCurHighlightRow();
        resume();
        if (mLrcOffset != 0) {
            // 矫正歌词偏移位置
            startAnimLrcSeek(mLrcOffset, 0, 400);
        }
        mDraggingLrc = false;
        invalidate();
    }

    /**
     * 滚动歌词
     */
    private void scrollLrc(float y) {
        // 手指滑动的距离
        float offsetY = y - mLastMotionY;
        if (offsetY < 0 && mOnTouchDownHighlightRow == mLrcTotal - 1 || offsetY > 0 && mOnTouchDownHighlightRow == 0) {
            mLrcOffset = 0;
            return;
        }
        // 从字的中心偏移半个字+间距=高亮
        if (Math.abs(offsetY) >= getLineHeight() * 0.5f) {
            if (mOnTouchDownHighlightRow == highlightRow) {
                highlightRow = mOnTouchDownHighlightRow - (offsetY > 0 ? 1 : -1);
            }
        } else {
            highlightRow = mOnTouchDownHighlightRow;
        }
        Log.d("zzl", "offsetY="+Math.abs(offsetY)+" ,getLineHeight()/2=="+(getLineHeight()*0.5));
        // 超过一句就重新计算
        if (Math.abs(offsetY) >= getLineHeight() && mOnTouchDownHighlightRow != highlightRow) {
            mLrcOffset = 0;
            mLastMotionY = y;
            mOnTouchDownHighlightRow = highlightRow;
        } else {
            mLrcOffset = offsetY + getLineHeight() * (highlightRow - mOnTouchDownHighlightRow);
        }
        // 歌词偏移了，进行了拖拽
        if (Math.abs(offsetY) > highlightLrcFontSize * 0.5f) {
            if (!mDraggingLrc) {
                mDraggingLrc = true;
                if (lrcListener != null) {
                    lrcListener.overSkip();
                }
            }
        }
        highlightRow = Math.max(0, highlightRow);
        highlightRow = Math.min(mLrcTotal - 1, highlightRow);
        Log.d("zzl", "computeScroll highlightRow="+highlightRow+" ,mOnTouchDownHighlightRow="+mOnTouchDownHighlightRow);
        invalidate();
    }

    /**
     * 由歌词触摸按下触发
     */
    private void pause() {
        if (!isPlaying) {
            return;
        }
        if (lrcListener != null) {
            lrcListener.touchPause();
        }
    }

    /**
     * 由歌词触摸抬起触发
     */
    private void resume() {
        if (isPlaying) {
            return;
        }
        if (lrcListener != null) {
            lrcListener.touchResume();
        }
    }

    private boolean isClicked(MotionEvent event) {
        float mUpMotionY = event.getY();
        float mUpMotionX = event.getX();
        return (Math.pow(mLastMotionX - mUpMotionX, 2) + Math.pow(mLastMotionY - mUpMotionY, 2) < 50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (lrcModel == null || mLrcTotal == 0) {
            return;
        }
        drawLrc(canvas);
        drawCountDown(canvas);
        drawLrcStartTimeLine(canvas);
    }

    // 绘制歌词开始的时间线
    private void drawLrcStartTimeLine(Canvas canvas) {
        // 如果用户没拖拽歌词就不画提示线
        if (!mDraggingLrc || !isShowStartLineOnDrag) {
            return;
        }

        // 绘制歌词
        mLrcPint.setTextAlign(Paint.Align.LEFT);
        mLrcPint.setTextSize(startTimeFontSize);
        mLrcPint.setColor(Color.WHITE);
        String time = TimeUtils.getStrTime(lrcModel.getLyricData().getLyricList().get(highlightRow).getStartTime());
        if (mTimeRect == null) {
            mTimeRect = new Rect();
            mLrcPint.getTextBounds(time, 0, time.length(), mTimeRect);
        }
        // 第一行高亮歌词的中心点
        float firstRowCenterY = lrcFontSize * 1.0f / 2 + lrcPaddingTop + (showCountDown ? lineSpace : 0);
        /* 中心线偏移 */
        int offCenterY = 2;
        // 画起始时间文字
        canvas.drawText(time,
            startTimeLeftPadding,
            firstRowCenterY + mTimeRect.height() * 1.0f / 2 + offCenterY,
            mLrcPint);
        // 画拖拽三角形图片
        if (dragBitmap != null) {
            canvas.drawBitmap(dragBitmap,
                mTimeRect.width() + startTimeLeftPadding * 2,
                firstRowCenterY - dragBitmapHeight * 1.0f / 2,
                mLrcPint);
        }
        // 画中心线
        mLrcPint.setColor(normalRowColor);
        mLrcPint.setStrokeCap(Paint.Cap.ROUND);
        mLrcPint.setStrokeWidth(2);
        canvas.drawLine(mTimeRect.width() + startTimeLeftPadding * 2
            + dragBitmapWidth, firstRowCenterY + offCenterY, mViewWidth, firstRowCenterY + offCenterY, mLrcPint);
        mLrcPint.setStrokeWidth(0);
    }

    // 画倒计时圆点
    private void drawCountDown(Canvas canvas) {
        if (!isPlaying || mCountDownNumber <= 0 || mCountDownNumber > countDownMaxNumber) {
            return;
        }
        if (mCountDownNumber == countDownMaxNumber && lrcListener != null) {
            lrcListener.countDownStart();
        }
        if (!showCountDown) {
            return;
        }
        mLrcPint.setColor(countDownColor);
        int countDownWidth = (countDownSpace + countDownRadius * 2) * countDownMaxNumber - countDownSpace;
        for (int j = 0; j < mCountDownNumber; j++) {
            int cx = (mViewWidth - countDownWidth) / 2 + (countDownSpace + countDownRadius * 2) * j + countDownRadius;
            canvas.drawCircle(cx, countDownRadius, countDownRadius, mLrcPint);
        }
    }

    // 画歌词
    private void drawLrc(Canvas canvas) {
        int playRow = Math.max(0, highlightRow);
        // 每行的高度
        int lineHeight = getLineHeight();
        // 计算歌词整体Y轴偏移
        float lineY = mLrcOffset - highlightRow * lineHeight + getLrcPaddingTop();
        // 高亮歌词逐步放大效果
        float zoom = Math.abs(lineHeight - mLrcOffset) / lineHeight;
        int highlightLrcFontSize = lrcFontSize + (int) ((this.highlightLrcFontSize - lrcFontSize) * zoom + 0.5f);
        highlightLrcFontSize = Math.min(this.highlightLrcFontSize, highlightLrcFontSize);
        // 绘制每行歌词
        for (int i = 0; i < mLrcTotal; i++) {
            // 大于最多行则不再绘制
            if ((i - highlightRow) >= maxLine) {
                return;
            }

            if (i == 0 && !showCountDown) {
                // 如果不显示倒计时，第一行上面不加间隔
                lineY += lineHeight - lineSpace;
            } else {
                lineY += lineHeight;
            }
            // 歌词偏移小于顶部 才进行绘制
            if (lineY <= getLrcPaddingTop() && !mDraggingLrc) {
                continue;
            }
            LyricModel.LyricDataBean.LyricListBean lrcBean = lrcModel.getLyricData().getLyricList().get(i);
            mLrcPint.setTextAlign(Paint.Align.CENTER);
            mLrcPint.setTextSize(i == playRow ? highlightLrcFontSize : lrcFontSize);
            float lrcWidth = 1;
            // 第一次遍历计算歌词宽度
            for (LyricModel.LyricDataBean.LyricListBean.BlockListBean blockListBean : lrcBean.getBlockList()) {
                if (blockListBean.getColorSchemes().getStatus() == 255) {
                    continue;
                }
                StringBuffer words = new StringBuffer("");
                for (String s : blockListBean.getWordCtxList()) {
                    words.append(s);
                }
                if (words.length() > 0) {
                    int len = words.length();
                    float[] widths = new float[len];
                    mLrcPint.getTextWidths(words.toString(), widths);
                    for (int j = 0; j < len; j++) {
                        blockListBean.getWordWidthList().set(j, (int) widths[j]);
                        lrcWidth += (int) widths[j];
                    }
                    lrcWidth += endSpace;
                }
            }
            float startX = 0;
            switch (gravity) {
                case Gravity.LEFT:
                    // 靠左
                    startX = getPaddingLeft();
                    break;
                case Gravity.RIGHT:
                    // 靠右
                    startX = mViewWidth - lrcWidth - getPaddingRight();
                    break;
                case Gravity.CENTER:
                default:
                    // 居中
                    startX = (mViewWidth - lrcWidth) / 2;
                    break;
            }
            float lrcPosX = lrcTime - lrcBean.getStartTime();
            float cx = 0;
            for (int j = 0; j < lrcBean.getBlockList().size(); j++) {
                LyricModel.LyricDataBean.LyricListBean.BlockListBean block = lrcBean.getBlockList().get(j);
                if (block.getColorSchemes().getStatus() == 255) {
                    continue;
                }

                if (i == highlightRow) {
                    mLrcPint.setColor(highlightRowColor);
                    mLrcPint.setTypeface(lrcHighlightTypeface);
                } else {
                    mLrcPint.setColor(normalRowColor);
                    mLrcPint.setTypeface(lrcNormalTypeface);
                }

                for (int k = 0; k < block.getWordCtxList().size(); k++) {
                    mLrcPint.setTextSize(i == playRow ? highlightLrcFontSize : lrcFontSize);
                    float wordWidth = block.getWordWidthList().get(k);
                    float halfWordWidth = wordWidth / 2;
                    cx += halfWordWidth;
                    float currentX = 0;
                    // 拖动时候不显示 没播放也不显示染红
                    if (isPlaying && !mDraggingLrc && i == playRow && block.getColorSchemes().getStatus() == 0
                        && block.getWordDurationList().size() != 0) {
                        int wordDuration = block.getWordDurationList().get(k);
                        if (lrcPosX <= 0) {
                            mLrcPint.setColor(highlightRowColor);
                        } else if (lrcPosX >= wordDuration) {
                            mLrcPint.setColor(highlightFontColor);
                            lrcPosX -= wordDuration;
                        } else {
                            currentX = lrcPosX / wordDuration;
                            mLrcPint.setColor(highlightRowColor);
                            lrcPosX -= wordDuration;
                        }
                    }
                    String word = block.getWordCtxList().get(k);
                    float wordX = startX + cx % lrcWidth;
                    float wordY = lineY + Math.max(0, ((int) (cx / lrcWidth)) * lineHeight);
                    // 绘制歌词
                    canvas.drawText(word, wordX, wordY, mLrcPint);
                    // 绘制歌渐变歌词
                    if (currentX > 0) {
                        mLrcPint.setColor(highlightFontColor);
                        mLrcPint.setTextSize(highlightLrcFontSize);
                        Paint.FontMetricsInt fm = mLrcPint.getFontMetricsInt();
                        canvas.save();
                        float clipX = wordX - halfWordWidth;
                        float clipY = lineY - highlightLrcFontSize + Math.max(0, (int) (cx / lrcWidth) * lineHeight);
                        canvas.clipRect(clipX,
                            clipY,
                            clipX + (currentX * wordWidth),
                            clipY + highlightLrcFontSize + fm.bottom);
                        canvas.drawText(word, startX + cx % lrcWidth, wordY, mLrcPint);
                        canvas.restore();
                    }
                    cx += halfWordWidth;
                }
                cx += endSpace;
                lineY += Math.max(0,
                    ((int) ((cx - block.getWordWidthList().get(block.getWordCtxList().size() - 1) * 1.0f / 2)
                        / lrcWidth)) * lineHeight);
            }
            mLrcPint.setTypeface(Typeface.DEFAULT);
            // 超过的不会绘制
            if (lineY > mViewHeight - lineHeight) {
                break;
            }
        }
    }

    /**
     * Pause->Play 暂停恢复需要重唱当前行，重新倒计时
     */
    public void seekCurHighlightRow() {
        // 若当前是第一句，播放时间还没到跳过前奏，第一行不需要执行重唱逻辑。
        if (highlightRow == 0 && getLrcTime() < (lrcModel.getLyricData().getLyricList().get(0).getStartTime()
            - countDownMaxNumber * 1000)) {
            return;
        }
        // 当前行进行重唱
        seekLrc(highlightRow, true);
    }

    /**
     * 播放的时候调用该方法滚动歌词，高亮正在播放的那句歌词
     */
    private void updateLrcTime() {
        if (lrcModel == null || mLrcTotal == 0) {
            return;
        }
        // 播放时间大于起唱时间（max*1000）5秒，则进行跳过前奏提示
        if (lrcListener != null && getLrcTime() >= (lrcModel.getLyricData().getLyricList().get(0).getStartTime()
            - countDownMaxNumber * 1000)) {
            // 跳过前奏
            lrcListener.overSkip();
        }

        // 歌词当前播放时间，还没超过起播时间，处于倒计时阶段
        if (getLrcTime() < countDownTime) {
            mCountDownNumber = (countDownTime - getLrcTime()) / 1000;
            mHasCountDownFinish = false;
            invalidate();
            return;
        }

        // 通知已经倒计时结束
        if (lrcListener != null && !mHasCountDownFinish) {
            mHasCountDownFinish = true;
            lrcListener.countDownFinish();
        }
        mCountDownNumber = 0;
        // 播放状态的更新不用for循环计算，固定在当前+1
        if (isPlaying) {
            LyricModel.LyricDataBean.LyricListBean curLrc = lrcModel.getLyricData().getLyricList().get(highlightRow);
            if (curLrc.getEndTime() > 0 && getLrcTime() < curLrc.getEndTime() || highlightRow == mLrcTotal - 1) {
                // 当前行 直接绘制
                invalidate();
            } else {
                // 下一行
                seekLrc(highlightRow + 1, false);
            }
        } else {
            // 暂停状态，seek位置不是+1 ，要循环计算，比如后处理进度调整拖拽
            for (int i = lrcModel.getLyricData().getLyricList().size() - 1; i >= 0; i--) {
                LyricModel.LyricDataBean.LyricListBean current = lrcModel.getLyricData().getLyricList().get(i);
                if (current.getEndTime() > 0 && (getLrcTime() >= current.getEndTime())) {
                    // 如果是快进更新时间
                    seekLrc(Math.min(i + 1, lrcModel.getLyricData().getLyricList().size() - 1), false);
                    return;
                }
            }
        }
    }

    /**
     * 设置第几行歌词高亮
     *
     * @param position 要高亮的歌词行数
     * @param touch    是否是外部触摸调整
     */
    public void seekLrc(int position, boolean touch) {
        if (lrcModel == null || position < 0 || position > lrcModel.getLyricData().getLyricList().size()) {
            return;
        }
        // 演唱自动更新歌词时，一次调整一行
        if (!touch && position > 0 && position == highlightRow + 1) {
            startAnimLrcSeek(getLineHeight(), 0, 400);
        }
        highlightRow = position;
        invalidate();
        if (lrcListener != null && touch) {
            lrcListener.onLrcSeek(position);
            // 触摸后会重播，需要重写设置开始时间
            int startTime = lrcModel.getLyricData().getLyricList().get(position).getStartTime();
            setCountDownTime(startTime);
        }
    }

    /**
     * 歌词滚动动画
     */
    private void startAnimLrcSeek(final float start, final float end, final int duration) {
        mLrcOffset = start;
        if (mLrcOffsetAnim != null && mLrcOffsetAnim.isRunning()) {
            mLrcOffsetAnim.cancel();
        }
        mLrcOffsetAnim = ValueAnimator.ofFloat(start, end);
        mLrcOffsetAnim.setDuration(duration);
        mLrcOffsetAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 获取改变后的值
                mLrcOffset = (float) animation.getAnimatedValue();
                invalidate();
                // 输出改变后的值
            }
        });
        // 启动动画
        mLrcOffsetAnim.start();
    }

    /**
     * 歌词监听器
     */
    public interface ILrcViewListener {
        /**
         * 当歌词被用户上下拖动的时候回调该方法
         */
        void onLrcSeek(int position);

        /**
         * 超过前奏
         */
        void overSkip();

        /**
         * 点击暂停
         */
        void touchPause();

        /**
         * 点击继续
         */
        void touchResume();

        /**
         * 倒计时结束
         */
        void countDownStart();

        /**
         * 倒计时结束
         */
        void countDownFinish();
    }

}
