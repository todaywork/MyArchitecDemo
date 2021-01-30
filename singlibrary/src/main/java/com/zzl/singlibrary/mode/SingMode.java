package com.zzl.singlibrary.mode;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zhenglin.zhu on 2020/11/19.
 * 数据mode：该数据不能随便改动，否则会导致gson解析失败
 */
public class SingMode implements Parcelable {


    /**
     * s : 02qfM0v0000
     * c : {"childType":2,"name":"热门单曲","image":"http://t2.tianlaikge.com/music/pic/hd/kalaok/筷子兄弟_1.jpg","list":[{"id":515239,"name":"站着等你三千年","nameTraditional":"站著等你三千年","nameKey":"song_name_key_515239","filterKey":"ZZDNSQN","filterKeyTraditional":"ㄓㄓㄉㄋㄙㄑㄋ","ismv":1,"ismusic":1,"ishd":0,"issd":1,"isfhd":0,"isOnlineAccompany":1,"isHdOnlineAccompany":1,"isFhdOnlineAccompany":0,"isonline":1,"category":{"id":52166,"name":"王琪","nameKey":"songCategory_name_key_52166","image":"http://t2.tianlaikge.com/file/cover/d202fbb0-31d7-4f7a-a97b-d7a91511a29f_%CD%F5%E7%F7.jpg","filterKey":"wq","filterKeyTraditional":"ㄨㄑ"},"isMVCopyright":1,"isMVPlayCopyright":1,"isMVOnlineCopyright":1,"isAudioOriginalCopyright":1,"isAudioOriginalPlayCopyright":1,"isAudioAccompanyCopyright":1,"downloadFee":-1,"onlineFee":0}],"total":473}
     */

    public String s;
    /**
     * childType : 2
     * name : 热门单曲
     * image : http://t2.tianlaikge.com/music/pic/hd/kalaok/筷子兄弟_1.jpg
     * list : [{"id":515239,"name":"站着等你三千年","nameTraditional":"站著等你三千年","nameKey":"song_name_key_515239","filterKey":"ZZDNSQN","filterKeyTraditional":"ㄓㄓㄉㄋㄙㄑㄋ","ismv":1,"ismusic":1,"ishd":0,"issd":1,"isfhd":0,"isOnlineAccompany":1,"isHdOnlineAccompany":1,"isFhdOnlineAccompany":0,"isonline":1,"category":{"id":52166,"name":"王琪","nameKey":"songCategory_name_key_52166","image":"http://t2.tianlaikge.com/file/cover/d202fbb0-31d7-4f7a-a97b-d7a91511a29f_%CD%F5%E7%F7.jpg","filterKey":"wq","filterKeyTraditional":"ㄨㄑ"},"isMVCopyright":1,"isMVPlayCopyright":1,"isMVOnlineCopyright":1,"isAudioOriginalCopyright":1,"isAudioOriginalPlayCopyright":1,"isAudioAccompanyCopyright":1,"downloadFee":-1,"onlineFee":0}]
     * total : 473
     */

    public CBean c;

    protected SingMode(Parcel in) {
        s = in.readString();
    }

    public static final Creator<SingMode> CREATOR = new Creator<SingMode>() {
        @Override
        public SingMode createFromParcel(Parcel in) {
            return new SingMode(in);
        }

        @Override
        public SingMode[] newArray(int size) {
            return new SingMode[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(s);
    }

    public static class CBean implements Parcelable{
        public int childType;
        public String name;
        public String image;
        public int total;
        /**
         * id : 515239
         * name : 站着等你三千年
         * nameTraditional : 站著等你三千年
         * nameKey : song_name_key_515239
         * filterKey : ZZDNSQN
         * filterKeyTraditional : ㄓㄓㄉㄋㄙㄑㄋ
         * ismv : 1
         * ismusic : 1
         * ishd : 0
         * issd : 1
         * isfhd : 0
         * isOnlineAccompany : 1
         * isHdOnlineAccompany : 1
         * isFhdOnlineAccompany : 0
         * isonline : 1
         * category : {"id":52166,"name":"王琪","nameKey":"songCategory_name_key_52166","image":"http://t2.tianlaikge.com/file/cover/d202fbb0-31d7-4f7a-a97b-d7a91511a29f_%CD%F5%E7%F7.jpg","filterKey":"wq","filterKeyTraditional":"ㄨㄑ"}
         * isMVCopyright : 1
         * isMVPlayCopyright : 1
         * isMVOnlineCopyright : 1
         * isAudioOriginalCopyright : 1
         * isAudioOriginalPlayCopyright : 1
         * isAudioAccompanyCopyright : 1
         * downloadFee : -1
         * onlineFee : 0
         */

        public List<ListBean> list;

        protected CBean(Parcel in) {
            childType = in.readInt();
            name = in.readString();
            image = in.readString();
            total = in.readInt();
            list = in.createTypedArrayList(ListBean.CREATOR);
        }

        public static final Creator<CBean> CREATOR = new Creator<CBean>() {
            @Override
            public CBean createFromParcel(Parcel in) {
                return new CBean(in);
            }

            @Override
            public CBean[] newArray(int size) {
                return new CBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(childType);
            dest.writeString(name);
            dest.writeString(image);
            dest.writeInt(total);
            dest.writeTypedList(list);
        }
    }
    public static class ListBean implements Parcelable{
        public int id;
        public String name;
        public String nameTraditional;
        public String nameKey;
        public String filterKey;
        public String filterKeyTraditional;
        public int ismv;
        public int ismusic;
        public int ishd;
        public int issd;
        public int isfhd;
        public int isOnlineAccompany;
        public int isHdOnlineAccompany;
        public int isFhdOnlineAccompany;
        public int isonline;
        /**
         * id : 52166
         * name : 王琪
         * nameKey : songCategory_name_key_52166
         * image : http://t2.tianlaikge.com/file/cover/d202fbb0-31d7-4f7a-a97b-d7a91511a29f_%CD%F5%E7%F7.jpg
         * filterKey : wq
         * filterKeyTraditional : ㄨㄑ
         */

        public CategoryBean category;
        public int isMVCopyright;
        public int isMVPlayCopyright;
        public int isMVOnlineCopyright;
        public int isAudioOriginalCopyright;
        public int isAudioOriginalPlayCopyright;
        public int isAudioAccompanyCopyright;
        public int downloadFee;
        public int onlineFee;

        protected ListBean(Parcel in) {
            id = in.readInt();
            name = in.readString();
            nameTraditional = in.readString();
            nameKey = in.readString();
            filterKey = in.readString();
            filterKeyTraditional = in.readString();
            ismv = in.readInt();
            ismusic = in.readInt();
            ishd = in.readInt();
            issd = in.readInt();
            isfhd = in.readInt();
            isOnlineAccompany = in.readInt();
            isHdOnlineAccompany = in.readInt();
            isFhdOnlineAccompany = in.readInt();
            isonline = in.readInt();
            category = in.readParcelable(CategoryBean.class.getClassLoader());
            isMVCopyright = in.readInt();
            isMVPlayCopyright = in.readInt();
            isMVOnlineCopyright = in.readInt();
            isAudioOriginalCopyright = in.readInt();
            isAudioOriginalPlayCopyright = in.readInt();
            isAudioAccompanyCopyright = in.readInt();
            downloadFee = in.readInt();
            onlineFee = in.readInt();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel in) {
                return new ListBean(in);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeString(nameTraditional);
            dest.writeString(nameKey);
            dest.writeString(filterKey);
            dest.writeString(filterKeyTraditional);
            dest.writeInt(ismv);
            dest.writeInt(ismusic);
            dest.writeInt(ishd);
            dest.writeInt(issd);
            dest.writeInt(isfhd);
            dest.writeInt(isOnlineAccompany);
            dest.writeInt(isHdOnlineAccompany);
            dest.writeInt(isFhdOnlineAccompany);
            dest.writeInt(isonline);
            dest.writeParcelable(category, flags);
            dest.writeInt(isMVCopyright);
            dest.writeInt(isMVPlayCopyright);
            dest.writeInt(isMVOnlineCopyright);
            dest.writeInt(isAudioOriginalCopyright);
            dest.writeInt(isAudioOriginalPlayCopyright);
            dest.writeInt(isAudioAccompanyCopyright);
            dest.writeInt(downloadFee);
            dest.writeInt(onlineFee);
        }
    }
    public static class CategoryBean implements Parcelable {
        public int id;
        public String name;
        public String nameKey;
        public String image;
        public String filterKey;
        public String filterKeyTraditional;

        protected CategoryBean(Parcel in) {
            id = in.readInt();
            name = in.readString();
            nameKey = in.readString();
            image = in.readString();
            filterKey = in.readString();
            filterKeyTraditional = in.readString();
        }

        public static final Creator<CategoryBean> CREATOR = new Creator<CategoryBean>() {
            @Override
            public CategoryBean createFromParcel(Parcel in) {
                return new CategoryBean(in);
            }

            @Override
            public CategoryBean[] newArray(int size) {
                return new CategoryBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeString(nameKey);
            dest.writeString(image);
            dest.writeString(filterKey);
            dest.writeString(filterKeyTraditional);
        }
    }
}
