package com.example.jsonrss_test_1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by fenghui on 3/16/16.
 */
public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    public Button rect,time_set;
    public int i = 0,j=0;
    public ListView listView;
    public ToggleButton mTogBtn1;
    public ToggleButton mTogBtn2;
//    List<String> adapterData;
    private static final String TAG = SettingActivity.class.getSimpleName();
    private static final String[] PLANETS = new String[]{"5min", "10min", "15min", "20min", "25min", "30min", "40min", "50min"};
    public String item1;
    public Button btn;
//    String[] from={"title"};
//    int[] to={R.id.title};
    String[] title = {"SHOW COUNTDOWN","NOTIFICAITON","COOLDOWN INTERVAL"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        listView = (ListView) findViewById(R.id.itemlist);
        mTogBtn1 = (ToggleButton) findViewById(R.id.mTogBtn1); // 获取到控件
        mTogBtn2 = (ToggleButton) findViewById(R.id.mTogBtn2);
        btn = (Button)findViewById(R.id.main_show_dialog_btn);
//        time_set=(Button)findViewById(R.id.time_setting);
        findViewById(R.id.main_show_dialog_btn).setOnClickListener(this);
//        adapterData =  new ArrayList<String>();
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        getData();
        //initView();
//        listView = (ListView) findViewById(R.id.itemlist);
//        final List<String> adapterData = new ArrayList<String>();
//
//        adapterData.add("SHOW COUNTDOWN");
//        adapterData.add("NOTIFICATION");
//        adapterData.add("COOLDOWN INTERVAL");

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                R.layout.list_item, adapterData);
//        listView.setAdapter(adapter);
        SimpleAdapter adapter = new SimpleAdapter(this,
                getData(),
                R.layout.list_item, new String[] {
                "a"}, new int[] {
                R.id.title1});
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);
        listView.setSelection(0);


        rect = (Button) findViewById(R.id.rect);
        rect.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN&&i==0 ) {
                    // change color
                    rect.setBackgroundResource(R.drawable.btn_shape_black);
                    System.out.println("Change color!");
                    i=1;
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN&&i==1) {
                    // set to normal color
                    rect.setBackgroundResource(R.drawable.btn_shape);
                    System.out.println("set to normal color!");
                    i=0;
                }

                return true;
            }
        });
        //SHOW COUNTDOWN
        mTogBtn1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    //选中
                    System.out.println("Checked");
                } else {
                    //未选中
                    System.out.println("Unchecked");
                }
            }
        });// 添加监听事件
        //NOTIFICATION
        mTogBtn2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    //选中
                    System.out.println("Noticed");
                    j=1;
                } else {
                    //未选中
                    System.out.println("Unnoticed");
                    j=0;
                }
            }
        });// 添加监听事件



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_show_dialog_btn:
                View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
//                Button btn = new Button();
//                btn = outerView.findViewById(R.id.main_show_dialog_btn);
                wv.setOffset(2);
                wv.setItems(Arrays.asList(PLANETS));
                wv.setSeletion(3);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {

                        Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                        item1 = item;

                    }

                });

                new AlertDialog.Builder(this)
                        .setTitle("WheelView in Dialog")
                        .setView(outerView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println(item1);
                                btn.setText(item1);
                            }
                        })
                        .show();

                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, FeedListActivity.class);

                Bundle bundle=new Bundle();
                bundle.putString("time", item1);
                bundle.putInt("notice",j);
                intent.putExtras(bundle);
                startActivity(intent);

                break;
        }

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//    private void initView() {
//        mTogBtn = (ToggleButton) findViewById(R.id.mTogBtn); //获取到控件
//        mTogBtn.setOnCheckedChangeListener(this);//添加监听事件
//
//    }
//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if(isChecked){
//            System.out.println("Checked");
//        }else{
//            System.out.println("Unchecked");
//        }
//    }

//mTogBtn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//        @Override
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            // TODO Auto-generated method stub
//            if (isChecked) {
//                //选中
//                System.out.println("Checked");
//            } else {
//                //未选中
//                System.out.println("Unchecked");
//            }
//        }
//    });// 添加监听事件
//      rect.setOnTouchListener(new View.OnTouchListener() {
//
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                // change color
//            }
//            else if (event.getAction() == MotionEvent.ACTION_UP) {
//                // set to normal color
//            }
//
//            return true;
//        }
//    });
    public List getData(){
//        listView = (ListView) findViewById(R.id.itemlist);
//        List<String> adapterData = new ArrayList<String>();
//
//        adapterData.add("SHOW COUNTDOWN");
//        adapterData.add("NOTIFICATION");
//        adapterData.add("COOLDOWN INTERVAL");
//        return adapterData;
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        int size = 3;
        for(int i=0;i<size;i++){
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("a", title[i]);
//            item.put("b", "Test");
//            item.put("c", "Notification");
        data.add(item);}
        return data;
    }
}
