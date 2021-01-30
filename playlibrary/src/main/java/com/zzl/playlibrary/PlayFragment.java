package com.zzl.playlibrary;

import android.graphics.BitmapFactory;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zzl.baselibrary.constant.Constant;
import com.zzl.baselibrary.ui.BaseFragment;
import com.zzl.playlibrary.databinding.FragmentPlayLayoutBinding;
import com.zzl.playlibrary.mode.LyricModel;
import com.zzl.playlibrary.ui.LrcView;
import com.zzl.playlibrary.viewmodel.PlayViewModel;

import java.util.List;

/**
 * Created by zhenglin.zhu on 2020/11/26.
 */
@Route(path = Constant.fragment_play_name)
public class PlayFragment extends BaseFragment<FragmentPlayLayoutBinding, PlayViewModel> {
    private static final String TAG = PlayFragment.class.getSimpleName();
    private List<LyricModel.LyricDataBean.LyricListBean> lyricList;

    @Override
    protected int getLayutId() {
        return R.layout.fragment_play_layout;
    }

    @Override
    protected void initView() {
        getViewMode().getData().mLiveData.observe(this, lyricModel -> getDataBinding().lrcView.setLrc(lyricModel));
        setLrcConfig();

    }

    @Override
    protected void initData() {
        //数据处理
        getViewMode().requestData();
    }
    /**
     * 初始化歌词效果参数
     */
    private void setLrcConfig() {
        // 设置歌词监听
        setLrcLister();
        // 高亮行被唱过的颜色
        getDataBinding().lrcView.setHighlightFontColor(getResources().getColor(android.R.color.holo_red_light));
        // 高亮行歌词颜色
        getDataBinding().lrcView.setHighlightRowColor(getResources().getColor(android.R.color.white));
        // 正常歌词颜色
        getDataBinding().lrcView.setNormalRowColor(getResources().getColor(R.color.white_60));
        // 倒计时圆点颜色
        getDataBinding().lrcView.setCountDownColor(getResources().getColor(android.R.color.holo_red_light));
        // 高亮歌词大小
        getDataBinding().lrcView.setHighlightLrcFontSize(getResources().getDimensionPixelSize(R.dimen.lrc_high_night_font_size));
        // 正常歌词大小
        getDataBinding().lrcView.setLrcFontSize(getResources().getDimensionPixelSize(R.dimen.lrc_normal_font_size));
        // 歌词行间距
        getDataBinding().lrcView.setLineSpace(getResources().getDimensionPixelSize(R.dimen.lrc_line_space));
        // 倒计时圆点半径大小
        getDataBinding().lrcView.setCountDownRadius(getResources().getDimensionPixelSize(R.dimen.lrc_countdown_radius));
        // 倒计时圆点间距
        getDataBinding().lrcView.setCountDownSpace(getResources().getDimensionPixelSize(R.dimen.lrc_countdown_space));
        // 距离顶部的距离
        getDataBinding().lrcView.setLrcPaddingTop(48);
        // 最大显示8行歌词 // 如果需要折叠打分控件，歌词的行数折叠后增加，这里可以不预设行数
        getDataBinding().lrcView.setMaxLine(8);
        // 不显示倒计时，用lotte动画替代
        getDataBinding().lrcView.setShowCountDown(false);
        // 设置拖拽箭头
        getDataBinding().lrcView.setDragBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.karaoke_sing_ic_drag),
                getResources().getDimensionPixelSize(R.dimen.lrc_drag_icon_size),
                getResources().getDimensionPixelSize(R.dimen.lrc_drag_icon_size));

    }
    /**
     * 设置歌词监听
     */
    private void setLrcLister() {
        getDataBinding().lrcView.setListener(new LrcView.ILrcViewListener() {
            @Override
            public void onLrcSeek(int position) {

            }

            @Override
            public void overSkip() {
            }

            @Override
            public void touchPause() {
            }

            @Override
            public void touchResume() {
            }

            @Override
            public void countDownStart() {
            }

            // 倒计时结束
            @Override
            public void countDownFinish() {
            }

        });
    }

    /**
     * 更新歌词
     *
     * @param lyric 歌词
     */
    private void updateLrc(LyricModel lyric) {
        lyricList = lyric.getLyricData().getLyricList();
        getDataBinding().lrcView.setLrc(lyric);
        getDataBinding().lrcView.setCountDownTime(lyricList.get(0).getStartTime());
        getDataBinding().lrcView.seekLrc(0, false);
    }

    /**
     * 重唱
     */
    protected void rePlay() {
        getDataBinding().lrcView.reStart();
        getDataBinding().lrcView.setCountDownTime(lyricList.get(0).getStartTime());
        getDataBinding().lrcView.seekLrc(0, false);
    }
}
