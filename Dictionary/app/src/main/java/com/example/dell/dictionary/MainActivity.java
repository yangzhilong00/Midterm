package com.example.dell.dictionary;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecycler;
    private EditText mSearchText;
    private Button mSearch;
    private Button Add;
    private FloatingActionButton myFlo;
    private AlertDialog.Builder myBuilder;
    private List<Person> persons =new ArrayList<>();
    private List<Map<String,Object>> list1 =new ArrayList<>();
    private List<Map<String,Object>> list2 =new ArrayList<>();
    private CommonAdapter commonAdapter;
    private DatabaseHandler dbHandler;
    private DrawerLayout mDrawerLayout;
    private DrawerLayout.DrawerListener mDrawerListener;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private int ID;
    private MyApp myapp;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initialGoodList();
        initialMusic();
//        setDrawerLayout();
        setListener();

//        S_Broadcast();
        EventBus.getDefault().register(this);  //EventBus订阅者注册
    }

    private void findView(){
        myRecycler = (RecyclerView) findViewById(R.id.recycler_view);
        myBuilder =new AlertDialog.Builder(this);
        mSearch = (Button)findViewById(R.id.Search);
        mSearchText = (EditText) findViewById(R.id.searchText);
        Add = (Button)findViewById(R.id.Add);
//        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
//        mToolbar=(Toolbar)findViewById(R.id.tool_bar);
        myapp = (MyApp) getApplication();
    }

    private void initialMusic(){
//        int mp3 = R.raw.yinyu;
//        mediaPlayer = MediaPlayer.create(MainActivity.this, mp3);
//        mediaPlayer.setLooping(true);
//        mediaPlayer.start();

    }

    private void initialGoodList() {
        dbHandler=new DatabaseHandler(this);
        dbHandler.addPerson(new Person(0,"刘备", "男 ", "（161 - 223）", "幽州涿郡涿（河北保定市涿州）", "蜀国", "刘备，蜀汉的开国皇帝，汉景帝之子中山靖王刘胜的后代。刘备少年孤贫，以贩鞋织草席为生。黄巾起义时，刘备与关羽、张飞桃园结义，成为异姓兄弟，一同剿除黄巾，有功，任安喜县尉，不久辞官；董卓乱政之际，刘备随公孙瓒讨伐董卓，三人在虎牢关战败吕布。后诸侯割据，刘备势力弱小，经常寄人篱下，先后投靠过公孙瓒、曹操、袁绍、刘表等人，几经波折，却仍无自己的地盘。赤壁之战前夕，刘备在荆州三顾茅庐，请诸葛亮出山辅助，在赤壁之战中，联合孙权打败曹操，奠定了三分天下的基础。刘备在诸葛亮的帮助下占领荆州，不久又进兵益州，夺取汉中，建立了横跨荆益两州的政权。后关羽战死，荆州被孙权夺取，刘备大怒，于称帝后伐吴，在夷陵之战中为陆逊用火攻打得大败，不久病逝于白帝城，临终托孤于诸葛亮。"));
        dbHandler.addPerson(new Person(1,"张飞", " 男", "（？ - 221）", "幽州涿郡（河北保定市涿州）", "蜀国", "与刘备和关羽桃园结义，张飞居第三。随刘备征讨黄巾，刘备终因功被朝廷受予平原相，后张飞鞭挞欲受赂的督邮。十八路诸侯讨董时，三英战吕布，其勇为世人所知。曹操以二虎竞食之计迫刘备讨袁术，刘备以张飞守徐州，诫禁酒，但还是因此而鞭打曹豹招致吕布东袭。刘备反曹后，反用劫寨计擒曹将刘岱，为刘备所赞。徐州终为曹操所破，张飞与刘备失散，占据古城。误以为降汉的关羽投敌，差点一矛将其杀掉。曹操降荊州后引骑追击，刘备败逃，张飞引二十余骑，立马于长阪桥，吓退曹军数十里。庞统死后刘备召其入蜀，张飞率军沿江而上，智擒巴郡太守严颜并生获之，张飞壮而释放。于葭萌关和马超战至夜间，双方点灯，终大战数百回合。瓦口关之战时扮作醉酒，智破张郃。后封为蜀汉五虎大将。及关羽卒，张飞悲痛万分，每日饮酒鞭打部下，导致为帐下将张达、范强所杀，他们持其首顺流而奔孙权。"));
        dbHandler.addPerson(new Person(2,"关羽", "男",  "（？ - 219）", "司隶河东郡解（山西运城市临猗县西南）", "蜀国","因本处势豪倚势凌人，关羽杀之而逃难江湖。闻涿县招军破贼，特来应募。与刘备、张飞桃园结义，羽居其次。使八十二斤青龙偃月刀随刘备东征西讨。虎牢关温酒斩华雄，屯土山降汉不降曹。为报恩斩颜良、诛文丑，解曹操白马之围。后得知刘备音信，过五关斩六将，千里寻兄。刘备平定益州后，封关羽为五虎大将之首，督荆州事。羽起军攻曹，放水淹七军，威震华夏。围樊城右臂中箭，幸得华佗医治，刮骨疗伤。但未曾提防东吴袭荆州，关羽父子败走麦城，突围中被捕，不屈遭害。"));
        dbHandler.addPerson(new Person(3,"曹操","男","（155 - 220）","豫州沛国谯（安徽亳州市亳县）","魏国","曹操是西园八校尉之一，曾只身行刺董卓，失败后和袁绍共同联合天下诸侯讨伐董卓，后独自发展自身势力，一生中先后战胜了袁术、吕布、张绣、袁绍、刘表、张鲁、马超等割据势力，统一了北方。但是在南下讨伐江东的战役中，曹操在赤壁惨败。后来在和蜀汉的汉中争夺战中，曹操再次无功而返。曹操一生未称帝，他病死后，曹丕继位后不久称帝，追封曹操为魏武皇帝。"));
//        dbHandler.addPerson(new Person(4,"吕布","男","（？ - 198）","并州五原郡九原（内蒙古包头市九原区麻池镇西北古城遗址）","董卓势力","吕布是骁勇善战的汉末诸侯，先后跟随丁原、董卓作战，并最终杀死了丁原和董卓。成为独立势力后，吕布与曹操为敌，和刘备、袁术等诸侯时敌时友，最终不敌曹操和刘备的联军，兵败人亡。吕布虽然勇猛，但是少有计策，为人反复无常，唯利是图。在演义中，吕布是天下无双的超一流武将，曾在虎牢关大战刘备、关羽、张飞三人联手，曾一人独斗曹操军六员大将，武艺可谓公认的演义第一。著名的美女貂蝉上演连环计后，成为吕布的妻室。"));
//        dbHandler.addPerson(new Person(5,"孙权","男","（182-252）","扬州吴郡富春（浙江杭州市富阳）","吴国","孙权19岁就继承了其兄孙策之位，力据江东，击败了黄祖。后东吴联合刘备，在赤壁大战击溃了曹操军。东吴后来又和曹操军在合肥附近鏖战，并从刘备手中夺回荆州、杀死关羽、大破刘备的讨伐军。曹丕称帝后孙权先向北方称臣，后自己建吴称帝，迁都建业。"));
//        dbHandler.addPerson(new Person(6,"小乔","女","不详","扬州庐江郡皖（安徽安庆市潜山县）","吴国","庐江皖县桥国老次女，秀美绝伦，貌压群芳，又琴棋书画无所不通周瑜攻取皖城，迎娶小乔为妻。周郎小乔英雄美女、郎才女貌 ，被流传为千古佳话。"));
//        dbHandler.addPerson(new Person(7,"大乔","女","不详","扬州庐江郡皖（安徽安庆市潜山县）","吴国","江东乔国老有二女，大乔和小乔。大乔有沉鱼落雁之资，倾国倾城之容。孙策征讨江东，攻取皖城，娶大乔为妻。自古美女配英雄，伯符大乔堪绝配。曹操赤壁鏖兵，虎视江东，曾有揽二乔娱暮年，还足平生之愿。"));
        persons = dbHandler.getAllPerson();
        ID = 0;
        for (Person p : persons) { //RecyclerView 商品列表
            if(p != null){
                Map<String, Object> temp = new LinkedHashMap<>();
                if(p.getName()!=null) temp.put("name", p.getName());
                temp.put("image", getImage(p));
                list1.add(temp);
                if(p.getId() >= ID) ID = p.getId()+1;
            }
        }

        myRecycler.setLayoutManager(new LinearLayoutManager(this));
        commonAdapter = new CommonAdapter<Map<String, Object>>(this, R.layout.heros_item, list1) {
            @Override
            public void convert(ViewHolder holder, Map<String, Object> data) {
                TextView name = holder.getView(R.id.heroname);
                name.setText(data.get("name").toString());
                ImageView img = holder.getView(R.id.heroimage);
                 img.setImageBitmap((Bitmap) data.get("image"));
            }

            @Override
            public void onItemDissmiss(int position) {
                dbHandler.deletePerson(persons.get(position));
                Toast.makeText(getApplicationContext(), "移除第" + position + "个人物", Toast.LENGTH_SHORT).show();
                UpdatePersonList();
            }
        };
        commonAdapter.notifyDataSetChanged();

        commonAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {  //点击触发intent切换界面
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MainActivity.this,herosinfo.class);  //
                Bundle bundle = new Bundle();  //新建一个bundle容器
                bundle.putSerializable("person",persons.get(position));  //得到此时点击的单个item信息放入bundle中
                intent.putExtras(bundle);  //Intent收入bundle容器
                startActivityForResult(intent,1); //
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);  //淡入淡出效果
            }
            @Override
            public void onLongClick(int position) {  //长按移除商品
                Toast.makeText(getApplicationContext(), "移除人物："+persons.get(position).getName(), Toast.LENGTH_SHORT).show();
                dbHandler.deletePerson(persons.get(position));
                persons.remove(position);
                list1.remove(position);
                commonAdapter.notifyItemRemoved(position);
                commonAdapter.notifyItemRangeChanged(position,list1.size()-position);
            }

        });

        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(commonAdapter);
        animationAdapter.setDuration(1000);
        myRecycler.setAdapter(animationAdapter);
        myRecycler.setItemAnimator(new OvershootInLeftAnimator());
        myBuilder.setTitle("移除商品");
    }

    public void UpdatePersonList(){
        persons = dbHandler.getAllPerson();
        list1.clear();
        for (Person p : persons) { //RecyclerView 商品列表
            if(p!=null){
                Map<String, Object> temp = new LinkedHashMap<>();
                temp.put("name", p.getName());
                temp.put("image", getImage(p));
                list1.add(temp);
            }
        }
        commonAdapter.notifyDataSetChanged();
    }


//    private void setDrawerLayout() {
//        mToolbar.setTitle("Menu");//customize the title,个性化设置title
//        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));//设置title颜色
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//show back button and make it enabled
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_layout_open, R.string.drawer_layout_close);
//        mDrawerToggle.setDrawerIndicatorEnabled(true);
//        mDrawerToggle.setHomeAsUpIndicator(R.mipmap.ic_launcher);//channge the icon,改变图标
//        mDrawerToggle.syncState();////show the default icon nd sync the DrawerToggle state,如果你想改变图标的话，这句话要去掉。这个会使用默认的三杠图标
//
//        mDrawerLayout.addDrawerListener(mDrawerToggle);
//        mDrawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.orange));//设置状态栏颜色
//    }

    private void setListener() {
//        myFlo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (myRecycler.getVisibility() == View.VISIBLE) {
//                    myRecycler.setVisibility(View.GONE);
//                    myList.setVisibility(View.VISIBLE);
//                    myFlo.setImageResource(R.mipmap.mainpage);
//                } else {
//                    myRecycler.setVisibility(View.VISIBLE);
//                    myList.setVisibility(View.GONE);
//                    myFlo.setImageResource(R.mipmap.shoplist);
//                }
//            }
//        });
//
//
//        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (id == 0) return;
//                Intent intent = new Intent(MainActivity.this, OneActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("goods", myShoppinglist.get(position));
//                intent.putExtras(bundle);
//                startActivityForResult(intent, 1);
//
//            }
//        });
//
//        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                if (position == 0) return true;
//                myBuilder.setMessage("从购物车中清除" + myShoppinglist.get(position).getName() + "?");
//                myBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(getApplicationContext(), "已经从购物车移除此商品", Toast.LENGTH_SHORT).show();
//                        myShoppinglist.remove(position);
//                        list2.remove(position);
//                        simpleAdapter.notifyDataSetChanged();
//                    }
//                });
//                myBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                    }
//                }).create().show();
//                return true;
//            }
//        });
        //搜索键
//        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            // 当点击搜索按钮时触发该方法
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Person temp = dbHandler.getPerson(query);
//                if(temp == null)  Toast.makeText(getApplicationContext(), "没有此人", Toast.LENGTH_SHORT).show();
//                else{
//                    Toast.makeText(getApplicationContext(), temp.getName(), Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(MainActivity.this,OneActivity.class);  //
//                    Bundle bundle = new Bundle();  //新建一个bundle容器
//                    bundle.putSerializable("person",temp);  //得到此时点击的单个item信息放入bundle中
//                    intent.putExtras(bundle);  //Intent收入bundle容器
//                    startActivityForResult(intent,1); //
//                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);  //淡入淡出效果
//                }
//                return false;
//            }
//
//            // 当搜索内容改变时触发该方法
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });

        mSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String s = mSearchText.getText().toString();
                Person temp = dbHandler.getPerson(s);
                if(temp == null)  Toast.makeText(getApplicationContext(), "没有此人", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(getApplicationContext(), temp.getName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,herosinfo.class);  //
                    Bundle bundle = new Bundle();  //新建一个bundle容器
                    bundle.putSerializable("person",temp);  //得到此时点击的单个item信息放入bundle中
                    intent.putExtras(bundle);  //Intent收入bundle容器
                    startActivityForResult(intent,1); //
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);  //淡入淡出效果
                }
            }
        });

        Add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Person new_person = new Person(ID++,"","","","","","");
                dbHandler.addPerson(new_person);
                myapp = (MyApp)getApplication();
                myapp.UpdateImage(BitmapFactory.decodeResource(getResources(),R.drawable.zhouyu),new_person);

                UpdatePersonList();
                Bundle bundle = new Bundle();
                bundle.putSerializable("person", new_person);
                Intent intent = new Intent(MainActivity.this,herosinfo.class);  //
                intent.putExtras(bundle);
                startActivityForResult(intent,1); //
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);  //淡入淡出效果
            }
        });
    }

    private Bitmap getImage(Person temp_person){
        myapp = (MyApp)getApplication();
        if(temp_person.getId()<myapp.personimage.size())
            return myapp.personimage.get(temp_person.getId());
        else return BitmapFactory.decodeResource(getResources(),R.drawable.zhouyu);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Person person){
        dbHandler.updatePerson(person);
        UpdatePersonList();
    }
}

