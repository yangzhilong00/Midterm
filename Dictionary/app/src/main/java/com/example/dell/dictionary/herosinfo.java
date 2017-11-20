package com.example.dell.dictionary;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class herosinfo extends AppCompatActivity {
    private EditText nametext;
    private EditText typetext;
    private EditText sextext;
    private EditText yeartext;
    private EditText placetext;
    private Button edittext;
    private EditText infotext;
    private Person person;
    private ImageView image;
    private MyApp myapp;
    private static final int PHOTO_GRAPH = 2;// 拍照
    private static final int PHOTO_ZOOM = 3; // 缩放
    private static final int PHOTO_RESULT = 4;// 结果
    private static final String IMAGE_UNSPECIFIED = "image/*";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_herosinfo);

        nametext=(EditText)findViewById(R.id.nametext);
        typetext=(EditText)findViewById(R.id.typetext);
        sextext=(EditText)findViewById(R.id.sextext);
        placetext=(EditText)findViewById(R.id.placetext);
        yeartext=(EditText)findViewById(R.id.yeartext);
        infotext=(EditText)findViewById(R.id.infotext);
        image = (ImageView)findViewById(R.id.image);
        edittext=(Button)findViewById(R.id.edit);
        myapp = (MyApp)getApplication();

        //初始化

        nametext.setTypeface(Typeface.createFromAsset(this.getAssets(),"sunsatsen.ttf"));
        typetext.setTypeface(Typeface.createFromAsset(this.getAssets(),"sunsatsen.ttf"));
        sextext.setTypeface(Typeface.createFromAsset(this.getAssets(),"sunsatsen.ttf"));
        placetext.setTypeface(Typeface.createFromAsset(this.getAssets(),"sunsatsen.ttf"));
        yeartext.setTypeface(Typeface.createFromAsset(this.getAssets(),"sunsatsen.ttf"));
        infotext.setTypeface(Typeface.createFromAsset(this.getAssets(),"sunsatsen.ttf"));

        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        person = (Person) bundle.getSerializable("person");
        if(person != null){
            image.setImageBitmap(getImage(person));
            nametext.setText(person.getName());
            sextext.setText(person.getSex());
            typetext.setText(person.getType());
            yeartext.setText(person.getYear());
            placetext.setText(person.getPlace());
            infotext.setText(person.getInfo());
        }

        edittext.setText("编辑");
        nametext.setFocusable(false);
        sextext.setFocusable(false);
        typetext.setFocusable(false);
        placetext.setFocusable(false);
        yeartext.setFocusable(false);
        infotext.setFocusable(false);

        //设置按钮点击
        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edittext.getText().toString().equals("编辑")){
                    edittext.setText("保存");
                    nametext.setFocusable(true);
                    sextext.setFocusable(true);
                    typetext.setFocusable(true);
                    placetext.setFocusable(true);
                    yeartext.setFocusable(true);
                    infotext.setFocusable(true);

                    nametext.setFocusableInTouchMode(true);
                    sextext.setFocusableInTouchMode(true);
                    typetext.setFocusableInTouchMode(true);
                    placetext.setFocusableInTouchMode(true);
                    yeartext.setFocusableInTouchMode(true);
                    infotext.setFocusableInTouchMode(true);
                    nametext.requestFocus();
                }
                else {
                    if (edittext.getText().toString().equals("保存")) {
                        EventBus.getDefault().post(new Person(person.getId(), nametext.getText().toString(), sextext.getText().toString(), yeartext.getText().toString(), placetext.getText().toString(), typetext.getText().toString(), infotext.getText().toString()));
                        myapp.UpdateImage(image.getDrawingCache(),person);
                        edittext.setText("编辑");
                        nametext.setFocusable(false);
                        sextext.setFocusable(false);
                        typetext.setFocusable(false);
                        placetext.setFocusable(false);
                        yeartext.setFocusable(false);
                        infotext.setFocusable(false);
                    }
                }
            }
        });

        //头像点击
        image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (edittext.getText().toString().equals("保存")){
                    AlertDialog.Builder builder=new AlertDialog.Builder(herosinfo.this);
                    builder.setTitle("头像");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    String[] items=new String[]{"相册上传","拍摄上传","下载头像"};
                    builder.setItems(items, new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(which == 0){
                                //从相册获取图片
                                Intent intent = new Intent(Intent.ACTION_PICK, null);
                                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
                                startActivityForResult(intent, PHOTO_ZOOM);
                            }
                            else if(which == 1){
                                //从拍照获取图片
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
                                        .getExternalStorageDirectory(),"temp.jpg")));
                                startActivityForResult(intent, PHOTO_GRAPH);
                            }
                            else if(which == 2){
                                Matrix m = image.getImageMatrix();
                            }
                        }
                    });
                    builder.create().show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (resultCode == 0) return;

        // 拍照
        if (requestCode == PHOTO_GRAPH) {
            // 设置文件保存路径
            File picture = new File(Environment.getExternalStorageDirectory()
                    + "/temp.jpg");
            startPhotoZoom(Uri.fromFile(picture));
        }

        if (data == null) return;

        // 读取相册缩放图片
        if (requestCode == PHOTO_ZOOM) {
            startPhotoZoom(data.getData());
        }

        // 处理结果
        if (requestCode == PHOTO_RESULT) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);// (0-100)压缩文件
                //此处可以把Bitmap保存到sd卡中，具体请看：http://www.cnblogs.com/linjiqin/archive/2011/12/28/2304940.html
                image.setImageBitmap(photo); //把图片显示在ImageView控件上
            }
        }
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_RESULT);
    }

    private Bitmap getImage(Person temp_person){
        myapp = (MyApp)getApplication();
        if(temp_person.getId()<myapp.personimage.size())
            return myapp.personimage.get(temp_person.getId());
        else return BitmapFactory.decodeResource(getResources(),R.drawable.zhouyu);
    }
}