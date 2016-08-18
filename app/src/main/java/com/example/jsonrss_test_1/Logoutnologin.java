package com.example.jsonrss_test_1;

/**
 * Created by fenghui on 3/24/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Logoutnologin extends Activity {
    public Button btn1,btn2;
    //private List<Activity> activities = new ArrayList<Activity>();
    //private String[] str ={"FBLogin","Userpreferencenologin","FeedListActivitynologin","FeedDetailsActvity","Proverbnologin","Logoutnologin"};
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logoutnologin);
        //activities.add(FBLogin);
        //Retry
        btn1 =(Button)findViewById(R.id.retry);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Logoutnologin.this, FBLogin.class);
                startActivity(intent);
            }
        });
        //Quit
        btn2 =(Button)findViewById(R.id.quit);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for (Activity activity : str) {
//                    if (activity!=null) {
//                        activity.finish();
//                    }
//                }
                System.exit(4);
            }
        });

    }
}
