package com.zzl.playlibrary.ui;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.zzl.playlibrary.R;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;


/**
 * 录音倒计时准备动画
 */
public class CircleProgressView extends View {
    /**
     * 控件背景画笔
     */
    private Paint mBgPaint;

    /**
     * 进度背景色画笔
     */
    private Paint mBackPaint;

    /**
     * 进度前景色画笔
     */
    private Paint mForePaint;

    /**
     * 进度文字画笔
     */
    private Paint mTextPaint;

    /**
     * 绘制区域
     */
    private RectF mRectF;

    /**
     * 当前进度
     */
    private int mCurProgress;

    /**
     * 进度后缀文本
     */
    private String mSuffixText = "";

    /**
     * 最后显示的文本
     */
    private String mFinishText;

    /**
     * 最后显示的文本的颜色
     */
    private int mFinishTextColor;

    /**
     * 当前进度
     */
    private int mCurAnimValue;

    /**
     * 最大进度度
     */
    private int mEndProgress = 0;

    /**
     * 最小进度度
     */
    private int mStartProgress = 0;

    /**
     * 文字基准线
     */
    private int mTextBaseline;

    /**
     * 文字区域
     */
    private Rect mTextRect;

    /**
     * 中心点x坐标
     */
    private int mCenterX;

    /**
     * 数值变化
     */
    private ValueAnimator mValueAnimator;

    /**
     * 动画结束监听器
     */
    private OnCountNumListener finishListener;

    /**
     * 是否时倒计时321动画，兼容华为的设置方法
     */
    private boolean is321 = false;

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ReadyTimeView);

        // 初始化背景画笔
        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setDither(true);
        mBgPaint.setColor(typedArray.getColor(R.styleable.ReadyTimeView_bgColor,
                context.getResources().getColor(R.color.trans_black_60)));

        // 初始化背景圆环画笔
        mBackPaint = new Paint();
        mBackPaint.setStyle(Paint.Style.STROKE);
        mBackPaint.setStrokeCap(Paint.Cap.ROUND);
        mBackPaint.setAntiAlias(true);
        mBackPaint.setDither(true);
        mBackPaint.setStrokeWidth(typedArray.getDimension(R.styleable.ReadyTimeView_backWidth, 10));
        mBackPaint.setColor(typedArray.getColor(R.styleable.ReadyTimeView_backColor, Color.LTGRAY));

        // 初始化进度圆环画笔
        mForePaint = new Paint();
        mForePaint.setStyle(Paint.Style.STROKE);
        mForePaint.setStrokeCap(Paint.Cap.ROUND);
        mForePaint.setAntiAlias(true);
        mForePaint.setDither(true);
        mForePaint.setStrokeWidth(typedArray.getDimension(R.styleable.ReadyTimeView_foreWidth, 10));
        mForePaint.setColor(typedArray.getColor(R.styleable.ReadyTimeView_foreColor, Color.RED));

        // 初始化进度文字画笔
        mTextPaint = new Paint();
        mFinishTextColor = typedArray.getColor(R.styleable.ReadyTimeView_finishTextColor, Color.RED);
        mTextPaint.setColor(typedArray.getColor(R.styleable.ReadyTimeView_textColor, Color.WHITE));
        mTextPaint.setTextSize(typedArray.getDimension(R.styleable.ReadyTimeView_textSize, 200));
        mTextPaint.setStrokeWidth(0);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.clearShadowLayer();

        // 初始化进度
        mCurProgress = typedArray.getInteger(R.styleable.ReadyTimeView_progress, 0);
        typedArray.recycle();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int viewHigh = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int mRectLength = (int) ((Math.min(viewWidth, viewHigh))
                - (Math.max(mBackPaint.getStrokeWidth(), mForePaint.getStrokeWidth())));
        int mRectL = getPaddingLeft() + (viewWidth - mRectLength) / 2;
        int mRectT = getPaddingTop() + (viewHigh - mRectLength) / 2;
        /* 绘制区域 */
        mRectF = new RectF(mRectL, mRectT, mRectL + mRectLength, mRectT + mRectLength);
        // 文字边框
        mTextRect = new Rect();
        // 绘制文字的边界矩形
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        // 文字的基准线 垂直居中
        mTextBaseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        // 水平中心点
        mCenterX = getMeasuredWidth() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (is321 && mStartProgress != mEndProgress && mCurAnimValue == mEndProgress * 1000) {
            return;
        }
        // 绘制背景
        canvas.drawCircle(getMeasuredWidth() * 1.0f / 2,
                getMeasuredHeight() * 1.0f / 2,
                getMeasuredWidth() * 1.0f / 2,
                mBgPaint);
        // 绘制背景色
        canvas.drawArc(mRectF, 0, 360, false, mBackPaint);
        // 总变化刻度
        float totalProgress = Math.abs(mEndProgress - mStartProgress) * 1000;
        // 变化刻度
        float changeProgress = Math.abs(mCurAnimValue - mStartProgress * 1000);
        // 变化角度
        float sweepAngle = 360.0f * changeProgress / totalProgress;
        // 绘制前景色
        canvas.drawArc(mRectF, 275, sweepAngle, false, mForePaint);
        // 绘制倒计时文字
        String progressText = mCurProgress + mSuffixText;
        if (mCurProgress == mEndProgress && mFinishText != null) {
            progressText = mFinishText;
            mTextPaint.setColor(mFinishTextColor);
        }
        mTextPaint.getTextBounds(progressText, 0, progressText.length(), mTextRect);
        canvas.drawText(progressText, mCenterX, mTextBaseline, mTextPaint);
    }

    /**
     * 设置当前进度后缀文本
     *
     * @param finishText 后缀文本
     */
    public void setFinishText(String finishText) {
        mFinishText = finishText;
    }

    /**
     * 设置当前进度后缀文本
     *
     * @param suffix 后缀文本
     */
    public void setSuffix(String suffix) {
        mSuffixText = suffix;
    }

    /**
     * 获取当前进度
     *
     * @return 当前进度（0-100）
     */
    public int getProgress() {
        return mCurProgress;
    }

    /**
     * 设置当前进度，并展示进度动画。如果动画时间小于等于0，则不展示动画
     *
     * @param startProgress 启始进度
     * @param endProgress   结束进度
     */
    public void setStartEndProgress(int startProgress, int endProgress) {
        mStartProgress = startProgress;
        mEndProgress = endProgress;
        invalidate();
    }

    /**
     * 倒计时 321 配合华为
     */
    public void start() {
        if (mStartProgress != mEndProgress && mCurAnimValue == mEndProgress * 1000) {
            return;
        }
        is321 = true;
        startAnim(4, 1, 3000);
    }

    /**
     * 贝塞尔曲线动画暂停 不执行
     */
    public void pause() {

    }

    /**
     * 贝塞尔曲线动画停止 不执行
     */
    public void stop() {

    }

    /**
     * 设置当前进度，并展示进度动画。如果动画时间小于等于0，则不展示动画
     *
     * @param startProgress 启始进度
     * @param endProgress   结束进度
     * @param animTime      动画时间（毫秒）
     */
    public void startAnim(int startProgress, int endProgress, long animTime) {
        mStartProgress = startProgress;
        mEndProgress = endProgress;
        if (mValueAnimator != null && mValueAnimator.isRunning()) {
            mValueAnimator.cancel();
            return;
        }
        mValueAnimator = ValueAnimator.ofInt(mStartProgress * 1000, mEndProgress * 1000);
        mValueAnimator.addUpdateListener(animation -> {
            mCurAnimValue = (int) animation.getAnimatedValue();
            mCurProgress = mCurAnimValue / 1000;
            if (mCurAnimValue == mEndProgress * 1000 && finishListener != null) {
                finishListener.onEndCountNum();
            }
            invalidate();
        });
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setDuration(animTime);
        mValueAnimator.start();
    }

    /**
     * 设置当前进度，并停止进度动画
     *
     * @param progress 进度
     */
    public void setProgress(int progress) {
        if (mValueAnimator != null && mValueAnimator.isRunning()) {
            mValueAnimator.cancel();
        }
        mCurProgress = Math.max(mStartProgress, progress);
        mCurProgress = Math.min(mEndProgress, progress);
        mCurAnimValue = progress * 1000;
        if (mCurAnimValue == mEndProgress * 1000 && finishListener != null) {
            finishListener.onEndCountNum();
        }
        invalidate();
    }

    /**
     * 设置圆环背景宽度
     *
     * @param width 背景圆环宽度
     */
    public void setBackWidth(int width) {
        mBackPaint.setStrokeWidth(width);
        invalidate();
    }

    /**
     * @param color 背景圆环颜色
     */
    public void setBackColor(@ColorRes int color) {
        mBackPaint.setColor(ContextCompat.getColor(getContext(), color));
        invalidate();
    }

    /**
     * 设置圆环前景宽度
     *
     * @param width 前景宽度
     */
    public void setForeWidth(int width) {
        mForePaint.setStrokeWidth(width);
        invalidate();
    }

    /**
     * 设置圆环前景色
     *
     * @param color 前景色
     */
    public void setForeColor(@ColorRes int color) {
        mForePaint.setColor(ContextCompat.getColor(getContext(), color));
        mForePaint.setShader(null);
        invalidate();
    }

    /**
     * 动画结束监听器
     */
    public interface OnCountNumListener {
        /**
         * 准备倒计时完成回调
         */
        void onEndCountNum();
    }

    /**
     * 设置动画结束监听器
     */
    public void setOnCountNumListener(OnCountNumListener listener) {
        this.finishListener = listener;
    }

}

