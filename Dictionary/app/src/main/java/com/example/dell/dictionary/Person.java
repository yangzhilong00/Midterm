package com.example.dell.dictionary;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.io.Serializable;

/**
 * Created by dell on 2017/11/9.
 */

public class Person implements Serializable {
    private int id;
    private String Name;
    private String Year;
    private String Sex;
    private String Type;
    private String Info;
    private String Place;
    private MyApp myapp;


    public Person() {}

    public Person(int Id, String N,String S,String Y, String p,String T,String I)  //构造函数
    {
        this.id=Id;
        this.Name=N;
        this.Sex=S;
        this.Year=Y;
        this.Place=p;
        this.Type=T;
        this.Info=I;
    }

    public int getId(){return id;};
    public String getName(){
        return Name;
    }
    public String getYear(){
        return Year;
    }  //返回个别内容
    public String getSex(){return Sex;}
    public String getInfo(){
        return Info;
    }
    public String getType(){
        return Type;
    }
    public String getPlace() {return Place;}

}
