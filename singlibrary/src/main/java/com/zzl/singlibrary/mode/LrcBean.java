package com.zzl.singlibrary.mode;

import java.util.List;

/**
 * Created by zhenglin.zhu on 2020/11/27.
 */

class LrcBean {

    /**
     * from : 0
     * majorVersion : 1
     * minorVersion : 2
     * roles : [{"leaderTrack":0,"name":"A","whatSentence":[4,5,6,7,10,11,12,13,14,15,20,21,22,23,26,27,28,29,30,31]},{"leaderTrack":0,"name":"B","whatSentence":[0,1,2,3,8,9,16,17,18,19,24,25]}]
     * singersList : ["m1","f1"]
     * singingSentences : 32
     */

    public AdditionDataBean additionData;
    /**
     * composerName :
     * lyricistName :
     * musicStyle : -1
     * singerName :
     * singerNameKana :
     * singerStyle : -1
     * songTitle :
     * songTitleKana :
     */

    public InfoDataBean infoData;
    public LyricDataBean lyricData;
    public PitchDataBean pitchData;

    public static class AdditionDataBean {
        public int from;
        public int majorVersion;
        public int minorVersion;
        public int singingSentences;
        /**
         * leaderTrack : 0
         * name : A
         * whatSentence : [4,5,6,7,10,11,12,13,14,15,20,21,22,23,26,27,28,29,30,31]
         */

        public List<RolesBean> roles;
        public List<String> singersList;

        public static class RolesBean {
            public int leaderTrack;
            public String name;
            public List<Integer> whatSentence;
        }
    }

    public static class InfoDataBean {
        public String composerName;
        public String lyricistName;
        public int musicStyle;
        public String singerName;
        public String singerNameKana;
        public int singerStyle;
        public String songTitle;
        public String songTitleKana;
    }

    public static class LyricDataBean {
        /**
         * beanWidth : 0
         * blockList : [{"blockWidth":0,"colorSchemes":{"afterTextColor":10,"afterTextEdgeColor":0,"beforeTextColor":1,"beforeTextEdgeColor":0,"status":0},"kanaList":[],"number":0,"positionX":0,"startTime":17213,"wordCtxList":["暖","阳","下"],"wordDurationList":[201,357,335],"wordWidthList":[48,48,48]},{"blockWidth":0,"colorSchemes":{"afterTextColor":10,"afterTextEdgeColor":0,"beforeTextColor":1,"beforeTextEdgeColor":0,"status":0},"kanaList":[],"number":1,"positionX":144,"startTime":18106,"wordCtxList":[" "],"wordDurationList":[335],"wordWidthList":[48]},{"blockWidth":0,"colorSchemes":{"afterTextColor":10,"afterTextEdgeColor":0,"beforeTextColor":1,"beforeTextEdgeColor":0,"status":0},"kanaList":[],"number":2,"positionX":192,"startTime":18441,"wordCtxList":["我","迎","芬","芳"],"wordDurationList":[203,313,407,307],"wordWidthList":[48,48,48,48]}]
         * endTime : 19671
         * number : 0
         * offset : 1.4E-45
         * positionY : 0
         * rolesList : ["B"]
         * singersList : ["m1"]
         * startTime : 17213
         */

        public List<LyricListBean> lyricList;
        /**
         * b : 8
         * fix : 0
         * g : 8
         * r : 8
         */

        public List<PaletteListBean> paletteList;

        public static class LyricListBean {
            public int beanWidth;
            public int endTime;
            public int number;
            public double offset;
            public int positionY;
            public int startTime;
            /**
             * blockWidth : 0
             * colorSchemes : {"afterTextColor":10,"afterTextEdgeColor":0,"beforeTextColor":1,"beforeTextEdgeColor":0,"status":0}
             * kanaList : []
             * number : 0
             * positionX : 0
             * startTime : 17213
             * wordCtxList : ["暖","阳","下"]
             * wordDurationList : [201,357,335]
             * wordWidthList : [48,48,48]
             */

            public List<BlockListBean> blockList;
            public List<String> rolesList;
            public List<String> singersList;

            public static class BlockListBean {
                public int blockWidth;
                /**
                 * afterTextColor : 10
                 * afterTextEdgeColor : 0
                 * beforeTextColor : 1
                 * beforeTextEdgeColor : 0
                 * status : 0
                 */

                public ColorSchemesBean colorSchemes;
                public int number;
                public int positionX;
                public int startTime;
                public List<?> kanaList;
                public List<String> wordCtxList;
                public List<Integer> wordDurationList;
                public List<Integer> wordWidthList;

                public static class ColorSchemesBean {
                    public int afterTextColor;
                    public int afterTextEdgeColor;
                    public int beforeTextColor;
                    public int beforeTextEdgeColor;
                    public int status;
                }
            }
        }

        public static class PaletteListBean {
            public int b;
            public int fix;
            public int g;
            public int r;
        }
    }

    public static class PitchDataBean {
        /**
         * freqDurationList : [330,332,526,184,286,373,499]
         * freqList : [196,233,233,155,233,233,233]
         * startTime : 17181
         * trunkid : 0
         */

        public List<PitchListBean> pitchList;

        public static class PitchListBean {
            public int startTime;
            public int trunkid;
            public List<Integer> freqDurationList;
            public List<Integer> freqList;
        }
    }
}
