package com.example.dell.dictionary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by dell on 2017/11/17.
 */

public class StartActivity extends Activity {

    private static final long SPLASH_DELAY_MILLIS = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
    // 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
        new Handler().postDelayed(new Runnable() {
        public void run() {
        goHome();
        }
        }, SPLASH_DELAY_MILLIS);
    }
    private void goHome() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        StartActivity.this.startActivity(intent);
        StartActivity.this.finish();
    }
}
