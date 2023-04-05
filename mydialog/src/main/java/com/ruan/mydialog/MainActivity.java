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
import com.ruan.request.ArticleList;
import com.ruan.request.IWanAndroid;

import java.util.ArrayList;

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

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(IWanAndroid.Base_URL)
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


                //.articleListCall();



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