package com.example.dell.dictionary;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dell on 2017/11/9.
 */

public class OneActivity  extends AppCompatActivity {

    private ImageView image;
    private Button save;
    private Button back;
    private EditText nametext;
    private EditText sextext;
    private EditText typetext;
    private EditText yeartext;
    private EditText placetext;
    private EditText infotext;

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.herosinfo);

        findView();
        initial();
        setListener();
    }

    private void findView()
    {
        image = (ImageView)findViewById(R.id.image);
        nametext = (EditText)findViewById(R.id.nametext);
        sextext = (EditText)findViewById(R.id.sextext);
        typetext = (EditText)findViewById(R.id.typetext);
        yeartext = (EditText)findViewById(R.id.yeartext);
        placetext = (EditText)findViewById(R.id.placetext);
        infotext = (EditText)findViewById(R.id.infotext);
        save = (Button)findViewById(R.id.save);
        back = (Button)findViewById(R.id.back);
    }

    private void initial (){
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        if(bundle != null){
            person = (Person) bundle.getSerializable("person");
            image.setImageResource(R.drawable.zhouyu2);
            nametext.setText(person.getName());
            sextext.setText(person.getSex());
            typetext.setText(person.getType());
            yeartext.setText(person.getYear());
            placetext.setText(person.getPlace());
            infotext.setText(person.getInfo());
        }

    }

    private void setListener() {
        back.setOnClickListener(new View.OnClickListener() {  //点击back回到主界面
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);  //边缘左进右出效果
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new Person(person.getId(),nametext.getText().toString(),yeartext.getText().toString(),sextext.getText().toString(),placetext.getText().toString(),typetext.getText().toString(),infotext.getText().toString()));
            }
        });
    }
}
