package com.example.dell.dictionary;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/11/20.
 */

public class MyApp extends Application {
    public List<Bitmap> personimage;
    @Override
    public void onCreate() {
        super.onCreate();
        personimage = new ArrayList<Bitmap>(){
            {
                add(BitmapFactory.decodeResource(getResources(),R.drawable.liubei));
                add(BitmapFactory.decodeResource(getResources(),R.drawable.zhangfei));
                add(BitmapFactory.decodeResource(getResources(),R.drawable.guanyu));
                add(BitmapFactory.decodeResource(getResources(),R.drawable.caocao));
//                add(BitmapFactory.decodeResource(getResources(),R.drawable.lvbu));
//                add(5,BitmapFactory.decodeResource(getResources(),R.drawable.sunquan));
//                add(6,BitmapFactory.decodeResource(getResources(),R.drawable.xiaoqiao));
//                add(7,BitmapFactory.decodeResource(getResources(),R.drawable.daqiao));
            }
        };
    }
    public void UpdateImage(Bitmap bitmap, Person person){
        if(person.getId()<personimage.size())
            personimage.set(person.getId(), bitmap);
        else personimage.add(person.getId(),bitmap);
    }

}