package com.ruan.mydialog;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.WorkerThread;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView viewById1 = findViewById(R.id.my_rv);
        ArrayList<String> objects = new ArrayList<>();
        objects.add("dddd");
        objects.add("2222");

        viewById1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        viewById1.addItemDecoration(new RvItemDecoration(this));


        viewById1.setAdapter(new DemoAdapter(objects));


        View viewById = findViewById(R.id.mtv);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Interceptor inter = new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Log.e(TAG, "intercept: dddd");


                        String token = getToken();

                        Log.e(TAG, "intercept: dddd222");
                        return chain.proceed(chain.request());
                    }
                };
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(inter)
                        .build();

                Request getRequest = new Request.Builder()
                        .url("https://www.wanandroid.com/article/list/0/json")
                        .build();


                client.newCall(getRequest).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    }
                });

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


}