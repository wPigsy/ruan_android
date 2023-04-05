package com.ruan.request;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private static volatile NetworkManager instances;
    private static volatile OkHttpClient okHttpClient;
    private static volatile Retrofit retrofit;
 
    public static NetworkManager getInstance() {
        if (instances == null) {
            synchronized (NetworkManager.class) {
                if (instances == null) {
                    instances = new NetworkManager();
                }
            }
 
        }
        return instances;
    }
 
    private static int TIME_OUT = 30; //30秒超时断开连接
 
    private OkHttpClient initClient() {
        if (okHttpClient == null) {
            synchronized (NetworkManager.class) {
                if (okHttpClient == null) {
                    //请求日志打印
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {

                    });
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    //注释1：创建OkHttpClient
                    okHttpClient = new OkHttpClient.Builder()
                            //.sslSocketFactory(new NetworkSSL(TrustManager.trustAllCert), TrustManager.trustAllCert)
                            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                            .addInterceptor(loggingInterceptor)
                            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return okHttpClient;
 
    }
 
    public Retrofit initRetrofit() {
        if (retrofit == null) {
            synchronized (NetworkManager.class) {
                if (retrofit == null) {
                    //注释2：创建Retrofit
                    retrofit = new Retrofit.Builder()
                            .client(initClient())//选填
                            .baseUrl(IWanAndroid.BASE_URL)//必填
                            .addConverterFactory(GsonConverterFactory.create())//选填(数据转换器，解析)
                            .build();
                }
            }
        }
        return retrofit;
    }

    public Retrofit initRetrofitRxJava() {
        if (retrofit == null) {
            synchronized (NetworkManager.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .client(initClient())//选填
                            .baseUrl(IWanAndroid.BASE_URL)//必填
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())//新增网络请求适配器
                            .addConverterFactory(GsonConverterFactory.create())//选填(数据转换器，解析)
                            .build();
                }
            }
        }
        return retrofit;
    }
}