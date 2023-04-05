package com.ruan.mydialog;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ruan.mydialog.uikit.UikitProgress;
import com.ruan.request.ApiException;
import com.ruan.request.ArticleList;
import com.ruan.request.ErrorConsumer;
import com.ruan.request.IWanAndroid;
import com.ruan.request.NetworkManager;
import com.ruan.request.ResponseTransformer;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UikitProgress progress = findViewById(R.id.uikitProgress);
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setProgress(80);

                //standReq();
                //rxReq();
                //rxReqException();
                reMerge();
            }
        });


        RecyclerView viewById1 = findViewById(R.id.my_rv);
        ArrayList<String> objects = new ArrayList<>();
        objects.add("dddd");
        objects.add("2222");

        viewById1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        viewById1.addItemDecoration(new RvItemDecoration(this));


        viewById1.setAdapter(new DemoAdapter(objects));


    }

    private void reMerge() {

        Observable<ArticleList> compose = NetworkManager.getInstance().initRetrofitRxJava()
                .create(IWanAndroid.class)
                .articleListCallRxjava(60)
                .subscribeOn(Schedulers.io());

        Observable<ArticleList> compose2 = NetworkManager.getInstance().initRetrofitRxJava()
                .create(IWanAndroid.class)
                .articleListCallRxjava(222229999999L)
                .subscribeOn(Schedulers.io());//切换到IO线程


        Observable.merge(compose, compose2)
                .observeOn(AndroidSchedulers.mainThread())//切换到主线程
                .compose(ResponseTransformer.obtain())
                .subscribe(new Consumer<ArticleList.DataBean>() {
                    @Override
                    public void accept(ArticleList.DataBean dataBean) throws Throwable {
                        Log.e(TAG, "accept: 全部请求成功" + dataBean);
                    }
                }, new ErrorConsumer() {
                    @Override
                    protected void error(ApiException e) {
                        Log.e(TAG, "accept: 全部请求失败了鲁大师");
                    }
                });
                //.subscribe(new Consumer<Object>() {
                //    @Override
                //    public void accept(Object o) throws Throwable {
                //        if (o instanceof ArticleList.DataBean) {
                //            Log.e(TAG, "accept: 全部请求成功" + o);
                //        } else {
                //            Log.e(TAG, "accept: " + o );
                //        }
                //    }
                //}, new ErrorConsumer() {
                //    @Override
                //    protected void error(ApiException e) {
                //        Log.e(TAG, "error: 请求失败" );
                //    }
                //});
    }

    private void rxReqException() {
        NetworkManager.getInstance().initRetrofitRxJava()
                .create(IWanAndroid.class)
                .articleListCallRxjava(222229999999L)
                .subscribeOn(Schedulers.io())//切换到IO线程
                .observeOn(AndroidSchedulers.mainThread())//切换到主线程
                .compose(ResponseTransformer.obtain())
                .subscribe(new Consumer<ArticleList.DataBean>() {
                    @Override
                    public void accept(ArticleList.DataBean dataBean) throws Throwable {
                        Log.d(TAG, "rxReqException() called with: dataBean = [" + dataBean + "]");
                    }
                }, new ErrorConsumer() {
                    @Override
                    protected void error(ApiException e) {
                        Log.e(TAG, "rxReqException: ",  e );
                    }
                });
    }

    private void rxReq() {
        NetworkManager.getInstance().initRetrofitRxJava()
                .create(IWanAndroid.class)
                .articleListCallRxjava(60)
                .subscribeOn(Schedulers.io())//切换到IO线程

                .observeOn(AndroidSchedulers.mainThread())//切换到主线程

                .subscribe(listResponseData -> {
                    //请求成功
                    Log.e(TAG, "rxReq: " + listResponseData.toString() );
                }, throwable -> {
                    //请求失败
                    Log.e(TAG, "rxReq: error " );

                });
    }

    private void standReq() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IWanAndroid.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        IWanAndroid iWanAndroid = retrofit.create(IWanAndroid.class);

        iWanAndroid.articleListCall(60).enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response.body().toString() + "]");
            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {

            }
        });
    }

    @WorkerThread
    private String getToken() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "null222";
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged() called with: newConfig = [" + newConfig + "]");
    }
}