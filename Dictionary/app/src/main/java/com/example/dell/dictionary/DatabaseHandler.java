package com.example.dell.dictionary;

/**
 * Created by lenovo on 2017/11/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    //建表语句
    private static final String CREATE_TABLE="create table person(id integer primary key,Name text," +
            "Sex text, Year text,Place text, Type text, Info text);";

    public DatabaseHandler(Context context) {
        super(context, "Test", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS person");
        onCreate(sqLiteDatabase);
    }

    public void addPerson(Person person){
        SQLiteDatabase db=this.getWritableDatabase();

        //使用ContentValues添加数据
        ContentValues values=new ContentValues();
        values.put("id",person.getId());
        values.put("Name",person.getName());
        values.put("Year",person.getYear());
        values.put("Sex",person.getSex());
        values.put("Place",person.getPlace());
        values.put("Type",person.getType());
        values.put("Info",person.getInfo());
        db.insert("person", null, values);
        db.close();
    }
    public Person getPerson(String name){
        SQLiteDatabase db=this.getWritableDatabase();

        //Cursor对象返回查询结果
        Cursor cursor=db.query("person",new String[]{"id","Name","Sex","Year","Place","Type","Info"},
                "Name=?",new String[]{name},null,null,null,null);


        Person person=null;
        //注意返回结果有可能为空
        if(cursor.moveToFirst()){
            person=new Person(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        }
        return person;
    }
    public int getStudentCounts(){
        String selectQuery="SELECT * FROM person";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        cursor.close();

        return cursor.getCount();
    }

    public List<Person> getAllPerson(){
        List<Person> list=new ArrayList<Person>();

        String selectQuery="SELECT * FROM person";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Person person=new Person(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                list.add(person);
            }while(cursor.moveToNext());
        }
        return list;
    }

    //更新student
    public int updatePerson(Person person){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("id",person.getId());
        values.put("Name",person.getName());
        values.put("Sex",person.getSex());
        values.put("Year",person.getYear());
        values.put("Place",person.getPlace());
        values.put("Type",person.getType());
        values.put("Info",person.getInfo());

        return db.update("person",values,"id=?",new String[]{String.valueOf(person.getId())});
    }
    public void deletePerson(Person person){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("person","id=?",new String[]{String.valueOf(person.getId())});
        db.close();
    }
}