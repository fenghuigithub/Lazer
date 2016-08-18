package com.example.jsonrss_test_1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


/**
 * Created by fenghui on 3/16/16.
 */
public class Proverbnologin extends AppCompatActivity{
    //    private String str = null;
    private int count =10;
    private int len=0;
    public String str = null;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proverb);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
//        Bundle bundle = this.getIntent().getExtras();
//        str = bundle.getString("cool");
//        len = str.length();
//        str = str.substring(0, len - 3);
//        count = Integer.parseInt(str);
//        getCount();
//        new getCount().execute();
        textView = (TextView) findViewById(R.id.countdown_proverb);
        handler.sendEmptyMessageDelayed(0, 1000);

    }
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                if(count>60)
                {textView.setText(getCount()+""+"min");
                    textView.setVisibility(View.GONE);}
                else
                {textView.setText(getCount() + "" + "s");

                    textView.setVisibility(View.GONE);}

                handler.sendEmptyMessageDelayed(0, 1000);


            }

        };

    };
    private int getCount() {
        count--;
        if (count == 0) {
            Intent intent = new Intent(this, FBLogin.class);
            startActivity(intent);
            finish();
        }
//        for(int i = 9;i>=0;i--){
//            if(count == i*60){return i;}
//        }
        if(count/60 == 0){return count;}

        return count/60;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.none, menu);
//        for(;count>=0;) {
//            if (count == 0) {
//                Intent intent = new Intent();
//                intent.setClass(Proverb.this, Logout.class);
//                startActivity(intent);
//            }
//            count--;
//        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            openSettings(item);
            return true;
        }
//        for(;count>=0;) {
//            if (count == 0) {
//                Intent intent = new Intent();
//                intent.setClass(Proverb.this, Logout.class);
//                startActivity(intent);
//            }
//            count--;
//        }
//        System.out.println("Yeah");

        return super.onOptionsItemSelected(item);
    }

//  public void openSettings(MenuItem item){
//      count--;
//      if(count == 0){
//          Intent intent = new Intent();
//          intent.setClass(Proverb.this, Logout.class);
//          startActivity(intent);
//      }
//
//
//  }
}
