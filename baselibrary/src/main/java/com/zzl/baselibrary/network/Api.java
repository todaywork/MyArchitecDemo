package com.zzl.baselibrary.network;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zhenglin.zhu on 2020/11/20.
 */
public interface Api {
    /*
     * post数据格式：{"key":value,"key":"value"}
     * url：https://mam.tlkg.com.cn/category/getCategory
    * */
    @FormUrlEncoded
    @POST("category/getCategory")
    Call<ResponseBody> listData(@FieldMap Map<String, Object> map);


    /*
   *  post数据格式：{"posterPicString":"xxx"}
   *  将json数据转为RequestBody
   *  JSONObject object = new JSONObject();
       try {
           object.put("posterPicString", string);
       } catch (JSONException e) {
           e.printStackTrace();
       }
       RequestBody body = RequestBody.create(MediaType.parse("application/json"), object.toString());
   *
   * */
    @POST("getPic")
    Call<ResponseBody> listPic(@Body RequestBody body);

    /**
     * http://IP:8899/getPosterList
     */
    @GET("getPosterList")
    Call<ResponseBody> listAllPostList();

    /*http://IP:8899/send?key=21*/
    @GET("send")
    Call<ResponseBody> listKey(@Query("key") int key);

    /**
     * http://IP:8899/install?u=useinfo&package=com.zzl.test
     *
     * */
    @GET("install")
    Call<ResponseBody> InstallApk(@Query("u") String u,@Query("package") String packageName);

}
