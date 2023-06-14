package com.trashgo.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

// sohenney LoadingActivity.java 추가
public class LoadingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceStart){
        super.onCreate(savedInstanceStart);
        setContentView(R.layout.loading);

        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 700);
    }
}
