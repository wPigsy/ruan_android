package com.ruan.mydialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.WorkerThread;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruan.mydialog.uikit.UikitProgress;

import java.util.ArrayList;

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


}