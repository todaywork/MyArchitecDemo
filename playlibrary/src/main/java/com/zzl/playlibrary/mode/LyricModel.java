package com.zzl.playlibrary.mode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 歌词实体bean
 *
 * PitchData，TimingData作为TV端绘制歌词添加的信息，手机端可选择忽略
 *
 */
public class LyricModel implements Serializable {

    /**
     *
     * 保存附加信息，包括版本号，来源，和整首歌的歌手列表。歌手用m1，代表男1，f1代表女1，s代表独唱。
     *
     */
    private AdditionDataBean additionData;

    /**
     *
     * 保存歌曲附带信息，包括歌曲风格，歌曲名，歌手名，作词作曲等。
     *
     */
    private InfoDataBean infoData;

    /**
     *
     * 打分需要的pitch信息，客户端歌词展示可忽略。
     * PitchData作为TV端绘制歌词添加的信息，手机端可选择忽略
     *
     */
    private PitchDataBean pitchData;

    /**
     *
     * 一行歌词信息。由多个Block构成。每个block包含一些歌词文字，和变色不变色等信息。
     *
     */
    private LyricDataBean lyricData;


    /**
     *
     * 歌词显示事件，及绘制信息，（手动绘制歌词需要，可忽略）。
     *
     * TimingData作为TV端绘制歌词添加的信息，手机端可选择忽略
     *
     */
    private TimingDataBean timingData;

    public AdditionDataBean getAdditionData() {
        return additionData;
    }

    public void setAdditionData(AdditionDataBean additionData) {
        this.additionData = additionData;
    }

    public InfoDataBean getInfoData() {
        return infoData;
    }

    public void setInfoData(InfoDataBean infoData) {
        this.infoData = infoData;
    }

    public PitchDataBean getPitchData() {
        return pitchData;
    }

    public void setPitchData(PitchDataBean pitchData) {
        this.pitchData = pitchData;
    }

    public LyricDataBean getLyricData() {
        return lyricData;
    }

    public void setLyricData(LyricDataBean lyricData) {
        this.lyricData = lyricData;
    }

    public TimingDataBean getTimingData() {
        return timingData;
    }

    public void setTimingData(TimingDataBean timingData) {
        this.timingData = timingData;
    }

    /**
     *
     * 文件附带信息
     *
     */
    public static class AdditionDataBean implements Serializable{
        /**
         * majorVersion : 1
         * minorVersion : 0
         * from : 1
         * singersList:[m1,f1]
         */

        private int majorVersion; ////大版本号


        private int singingSentences; ////有效打分行数
        private int minorVersion;//小版本号
        private int from; //来源 0国内，1日本
        private List<String > singersList; //m1，男1，f1，女1，n1，未知独唱，n1，n2未知合唱

        /**
         * 歌词中存在的角色
         */
        public List<Role> roles;


        public int getSingingSentences() {
            return singingSentences;
        }

        public void setSingingSentences(int singingSentences) {
            this.singingSentences = singingSentences;
        }


        public int getMajorVersion() {
            return majorVersion;
        }

        public void setMajorVersion(int majorVersion) {
            this.majorVersion = majorVersion;
        }

        public int getMinorVersion() {
            return minorVersion;
        }

        public void setMinorVersion(int minorVersion) {
            this.minorVersion = minorVersion;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public List<String> getSingersList() {
            return singersList;
        }

        public void setSingersList(List<String> singersList) {
            this.singersList = new ArrayList<>(singersList);
        }
    }


    /**
     * 角色对象
     *
     * 用户选择模式的
     */
    public static class Role implements Serializable{
        /**
         * 角色名称
         * A
         * B
         * C
         * 。。。
         */
        public String name;
        /**
         * 是否是主唱
         * 这个主要影响 打分线
         * 如果是合唱
         * 需要给打分使用
         * 与模式选择页使用
         * 
         */
        public int leaderTrack;
        /**
         * 角色所唱的句子
         */
        public int[] whatSentence;

    }

    /**
     *
     * 乐曲附带的各种信息
     *
     */
    public static class InfoDataBean implements Serializable{
        /**
         * musicStyle : 1
         * singerStyle : 1
         * songTitle : WINDING ROAD
         * singerName : 絢香×コブクロ
         * lyricistName : 絢香・小渕健太郎・黒田俊介
         * composerName : 絢香・小渕健太郎・黒田俊介
         * songTitleKana : ワインディングロード
         * singerNameKana : アヤカコブクロ
         */

        private int musicStyle;  //0独唱，1男女，2男男，3女女
        private int singerStyle; //0,演唱系;1,pops系
        private String songTitle;//曲名
        private String singerName;//歌手名
        private String lyricistName;//作词者
        private String composerName; //作曲者
        private String songTitleKana; //曲名kana(假名)
        private String singerNameKana;//歌手名kana(假名)

        public int getMusicStyle() {
            return musicStyle;
        }

        public void setMusicStyle(int musicStyle) {
            this.musicStyle = musicStyle;
        }

        public int getSingerStyle() {
            return singerStyle;
        }

        public void setSingerStyle(int singerStyle) {
            this.singerStyle = singerStyle;
        }

        public String getSongTitle() {
            return songTitle;
        }

        public void setSongTitle(String songTitle) {
            this.songTitle = songTitle;
        }

        public String getSingerName() {
            return singerName;
        }

        public void setSingerName(String singerName) {
            this.singerName = singerName;
        }

        public String getLyricistName() {
            return lyricistName;
        }

        public void setLyricistName(String lyricistName) {
            this.lyricistName = lyricistName;
        }

        public String getComposerName() {
            return composerName;
        }

        public void setComposerName(String composerName) {
            this.composerName = composerName;
        }

        public String getSongTitleKana() {
            return songTitleKana;
        }

        public void setSongTitleKana(String songTitleKana) {
            this.songTitleKana = songTitleKana;
        }

        public String getSingerNameKana() {
            return singerNameKana;
        }

        public void setSingerNameKana(String singerNameKana) {
            this.singerNameKana = singerNameKana;
        }
    }

    /**
     *
     * 乐曲音高信息
     *
     */
    public static class PitchDataBean implements Serializable{
        private List<PitchListBean> pitchList;

        public List<PitchListBean> getPitchList() {
            return pitchList;
        }

        public void setPitchList(List<PitchListBean> pitchList) {
            this.pitchList = new ArrayList<>(pitchList);
        }

        public static class PitchListBean implements Serializable{
            /**
             * trunkid : 0
             * startTime : 12969
             * freqList : [523,0,523,0,523,0,523,0,466,0,587]
             * freqDurationList : [330,14,330,14,330,14,215,14,330,14,445]
             */

            private int trunkid; //pitch通道id
            private int startTime; //开始时间
            private List<Integer> freqList;  //音高列表
            private List<Integer> freqDurationList;  //音高持续时间列表

            public int getTrunkid() {
                return trunkid;
            }

            public void setTrunkid(int trunkid) {
                this.trunkid = trunkid;
            }

            public int getStartTime() {
                return startTime;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public List<Integer> getFreqList() {
                return freqList;
            }

            public void setFreqList(List<Integer> freqList) {
                this.freqList = new ArrayList<>(freqList);
            }

            public List<Integer> getFreqDurationList() {
                return freqDurationList;
            }

            public void setFreqDurationList(List<Integer> freqDurationList) {
                this.freqDurationList = new ArrayList<>(freqDurationList);
            }
        }
    }

    /**
     *
     * 乐曲歌词信息
     *
     */
    public static class LyricDataBean implements Serializable{
        private List<PaletteListBean> paletteList;////  调色板列表
        private List<LyricListBean> lyricList;//歌词列表

        public List<PaletteListBean> getPaletteList() {
            return paletteList;
        }

        public void setPaletteList(List<PaletteListBean> paletteList) {
            this.paletteList = new ArrayList<>(paletteList);
        }

        public List<LyricListBean> getLyricList() {
            return lyricList;
        }

        public void setLyricList(List<LyricListBean> lyricList) {
            this.lyricList = new ArrayList<>(lyricList);
        }


        /**
         *
         *
         * 调色板列表
         *
         */
        public static class PaletteListBean implements Serializable{
            /**
             * fix : 0
             * r : 8
             * g : 8
             * b : 8
             */

            private int fix;//保留
            private int r;//r 0-255
            private int g;//g 0-255
            private int b;//b 0-255

            public int getFix() {
                return fix;
            }

            public void setFix(int fix) {
                this.fix = fix;
            }

            public int getR() {
                return r;
            }

            public void setR(int r) {
                this.r = r;
            }

            public int getG() {
                return g;
            }

            public void setG(int g) {
                this.g = g;
            }

            public int getB() {
                return b;
            }

            public void setB(int b) {
                this.b = b;
            }
        }

        /**
         *
         * 一句歌词
         *
         */
        public static class LyricListBean implements Serializable{

            /**
             * number : 0
             * positionY : 289
             * blockList : [{"number":0,"positionX":44,"colorSchemes":{"status":255,"beforeTextColor":7,"afterTextColor":7,"beforeTextEdgeColor":0,"afterTextEdgeColor":0},"wordCtxList":["◆"],"wordWidthList":[46],"startTime":0,"wordDurationList":[],"kanaList":[]},{"number":1,"positionX":90,"colorSchemes":{"status":255,"beforeTextColor":4,"afterTextColor":4,"beforeTextEdgeColor":0,"afterTextEdgeColor":0},"wordCtxList":["◇"],"wordWidthList":[52],"startTime":0,"wordDurationList":[],"kanaList":[]},{"number":2,"positionX":142,"colorSchemes":{"status":255,"beforeTextColor":5,"afterTextColor":3,"beforeTextEdgeColor":0,"afterTextEdgeColor":0},"wordCtxList":["●"],"wordWidthList":[46],"startTime":0,"wordDurationList":[],"kanaList":[]},{"number":3,"positionX":188,"colorSchemes":{"status":0,"beforeTextColor":6,"afterTextColor":9,"beforeTextEdgeColor":0,"afterTextEdgeColor":1},"wordCtxList":["曲","が","り","く","ね","っ","た"],"wordWidthList":[48,48,36,36,48,36,44],"startTime":12572,"wordDurationList":[342,342,354,224,171,171,313],"kanaList":[{"number":0,"offsetX":12,"wordCtxList":["ま"],"wordWidthList":[24]}]}]
             */

            private int number;//句子id
            private int positionY;//y坐标
            private List<BlockListBean> blockList;  //句子块列表

            private  List<String> singersList;  //歌手列表，m1(男1),m2(男2),f1(女1),f2(女2),s(独唱)
            private  List<String> singersCharList;//歌手图标列表,与singersList一一对应

            private int beanWidth=0;
            /**
             *
             * 每句歌词的开始时间
             *
             */
            private int startTime;
            private int endTime;
            //歌词位置的偏移量
            private float offset = Float.MIN_VALUE;

            public float getOffset() {
                return offset;
            }

            public void setOffset(float offset) {
                this.offset = offset;
            }

            /**
             * 某一行歌词的演唱角色
             * 可能情况
             * A
             * B
             * AB
             * C
             * AC
             * BC
             * ABC
             * D
             * 。。。
             */
            public String[] rolesList;


            public int getBeanWidth(){
                if(beanWidth==0) {
                    for (int i = 0; i < blockList.size(); i++)
                        beanWidth += blockList.get(i).getBlockWidth();
                }
                return beanWidth;
            }




            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public void setEndTime(int endTime) {
                this.endTime = endTime;
            }



            public int getStartTime() {
                return startTime;
            }

            public int getEndTime() {
                return endTime;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getPositionY() {
                return positionY;
            }

            public void setPositionY(int positionY) {
                this.positionY = positionY;
            }

            public List<BlockListBean> getBlockList() {
                return blockList;
            }

            public void setBlockList(List<BlockListBean> blockList) {
                this.blockList = new ArrayList<>(blockList);
            }

            public List<String> getSingersList() {
                return singersList;
            }

            public void setSingersList(List<String> singersList) {
                this.singersList = new ArrayList<>(singersList);
            }

            public List<String> getSingersCharList() {
                return singersCharList;
            }

            public void setSingersCharList(List<String> singersCharList) {
                this.singersCharList = new ArrayList<>(singersCharList);
            }

//            public int getStartTime() {
//
//
//                if (startTime != 0){
//                    return startTime;
//                }
//
//                if (blockList != null && blockList.size() > 0){
//                    for (int i = 0;i < blockList.size();i ++){
//                        BlockListBean blockListBean = blockList.get(i);
//                        if (blockListBean.colorSchemes != null && blockListBean.colorSchemes.status == 0){
//                            startTime = blockListBean.startTime;
//                            break;
//                        }
//                    }
//                }
//
//                return startTime;
//            }
//
//
//
//            public int getEndTime(){
//                if (endTime != 0){
//                    return endTime;
//                }
//
//                 endTime = getStartTime();
//
//                if (blockList != null && blockList.size() > 0){
//                    for (int i = 0;i < blockList.size();i++){
//                        BlockListBean blockListBean = blockList.get(i);
//                        if (blockListBean.wordDurationList != null && blockListBean.wordDurationList.size() > 0){
//                            for (int j = 0;j< blockListBean.wordDurationList.size();j++ ){
//                                endTime += blockListBean.wordDurationList.get(j);
//                            }
//                        }
//                    }
//
//                }
//                return endTime;
//            }



            public static class BlockListBean implements Serializable{
                /**
                 * number : 0
                 * positionX : 44
                 * colorSchemes : {"status":255,"beforeTextColor":7,"afterTextColor":7,"beforeTextEdgeColor":0,"afterTextEdgeColor":0}
                 * wordCtxList : ["◆"]
                 * wordWidthList : [46]
                 * startTime : 0
                 * wordDurationList : []
                 * kanaList : []
                 */

                private int number;  //block编号，全局的
                private int positionX; //x坐标单位像素
                private ColorSchemesBean colorSchemes;//配色方案
                private int startTime;  //开始变色时间，毫秒
                private List<String> wordCtxList; //文字内容，一个字一个元素
                private List<Integer> wordWidthList;//字宽列表
                private List<Integer> wordDurationList;//字时长列表
                private List<KanaListBean> kanaList;//假名数组

                private int blockWidth=0;
                public int getBlockWidth(){
                    if(blockWidth==0) {
                        for (int i = 0; i < wordWidthList.size(); i++)
                            blockWidth += wordWidthList.get(i);
                    }
                    return blockWidth;
                }

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
                }

                public int getPositionX() {
                    return positionX;
                }

                public void setPositionX(int positionX) {
                    this.positionX = positionX;
                }

                public ColorSchemesBean getColorSchemes() {
                    return colorSchemes;
                }

                public void setColorSchemes(ColorSchemesBean colorSchemes) {
                    this.colorSchemes = colorSchemes;
                }

                public int getStartTime() {
                    return startTime;
                }

                public void setStartTime(int startTime) {
                    this.startTime = startTime;
                }

                public List<String> getWordCtxList() {
                    return wordCtxList;
                }

                public void setWordCtxList(List<String> wordCtxList) {
                    this.wordCtxList = new ArrayList<>(wordCtxList);
                }

                public List<Integer> getWordWidthList() {
                    return wordWidthList;
                }

                public void setWordWidthList(List<Integer> wordWidthList) {
                    this.wordWidthList = new ArrayList<>(wordWidthList);
                }

                public List<Integer> getWordDurationList() {
                    return wordDurationList;
                }

                public void setWordDurationList(List<Integer> wordDurationList) {
                    this.wordDurationList = new ArrayList<>(wordDurationList);
                }

                public static class KanaListBean implements Serializable{
                    private int number;//kana编号
                    private int offsetX; // 偏移像素值，相对本行第一个字的positionX
                    private List<String> wordCtxList;//文字内容，字列表
                    private List<Integer> wordWidthList;  //文字宽度
                    public int getNumber() {
                        return number;
                    }

                    public void setNumber(int number) {
                        this.number = number;
                    }

                    public int getOffsetX() {
                        return offsetX;
                    }

                    public void setOffsetX(int offsetX) {
                        this.offsetX = offsetX;
                    }
                    public List<String> getWordCtxList() {
                        return wordCtxList;
                    }

                    public void setWordCtxList(List<String> wordCtxList) {
                        this.wordCtxList =new ArrayList<>( wordCtxList);
                    }

                    public List<Integer> getWordWidthList() {
                        return wordWidthList;
                    }

                    public void setWordWidthList(List<Integer> wordWidthList) {
                        this.wordWidthList = new ArrayList<>(wordWidthList);
                    }
                }

                    public List<KanaListBean> getKanaList() {
                    return kanaList;
                }

                public void setKanaList(List<KanaListBean> kanaList) {
                    this.kanaList = new ArrayList<>(kanaList);
                }

                public static class ColorSchemesBean implements Serializable{
                    /**
                     * status : 255
                     * beforeTextColor : 7
                     * afterTextColor : 7
                     * beforeTextEdgeColor : 0
                     * afterTextEdgeColor : 0
                     */

                    private int status; //255不变色，0，变色
                    private int beforeTextColor; //文本变色前调色板编号 list 索引
                    private int afterTextColor;//文本变色后调色板编号   list 索引
                    private int beforeTextEdgeColor; //边框变色前调色板编号   list 索引
                    private int afterTextEdgeColor; //边框变色后调色板编号  list 索引

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public int getBeforeTextColor() {
                        return beforeTextColor;
                    }

                    public void setBeforeTextColor(int beforeTextColor) {
                        this.beforeTextColor = beforeTextColor;
                    }

                    public int getAfterTextColor() {
                        return afterTextColor;
                    }

                    public void setAfterTextColor(int afterTextColor) {
                        this.afterTextColor = afterTextColor;
                    }

                    public int getBeforeTextEdgeColor() {
                        return beforeTextEdgeColor;
                    }

                    public void setBeforeTextEdgeColor(int beforeTextEdgeColor) {
                        this.beforeTextEdgeColor = beforeTextEdgeColor;
                    }

                    public int getAfterTextEdgeColor() {
                        return afterTextEdgeColor;
                    }

                    public void setAfterTextEdgeColor(int afterTextEdgeColor) {
                        this.afterTextEdgeColor = afterTextEdgeColor;
                    }
                }
            }
        }
    }

    /**
     *
     * 乐曲事件信息
     *
     */
    public static class TimingDataBean implements Serializable{
        private List<EventListBean> eventList;

        public List<EventListBean> getEventList() {
            return eventList;
        }

        public void setEventList(List<EventListBean> eventList) {
            this.eventList = new ArrayList<>(eventList);
        }

        public static class EventListBean implements Serializable{
            /**
             * type : 4
             * startTime : 7988
             * dataList : []
             */

            private int type; //事件类型
            private int startTime; //触发时间
            private List<Integer> dataList;//附加数据

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getStartTime() {
                return startTime;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public List<Integer> getDataList() {
                return dataList;
            }

            public void setDataList(List<Integer> dataList) {
                this.dataList = new ArrayList<>(dataList);
            }
        }
    }



}
