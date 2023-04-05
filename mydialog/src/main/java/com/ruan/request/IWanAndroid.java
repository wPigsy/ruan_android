package com.ruan.request;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IWanAndroid {

    String BASE_URL = "https://www.wanandroid.com/";

    //https://www.wanandroid.com


    //https://www.wanandroid.com/article/list/0/json?cid=2
    //
    //方法：GET
    //参数：
    //cid 分类的id，上述二级目录的id
    //page_size 页码：拼接在链接上，从0开始。

    @GET("article/list/0/json")
    Call<ArticleList> articleListCall(@Query("cid") int cid);

    @GET("article/list/0/json")
    Observable<ArticleList> articleListCallRxjava(@Query("cid") int cid);


}
