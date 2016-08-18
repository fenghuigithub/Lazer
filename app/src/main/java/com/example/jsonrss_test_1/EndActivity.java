package com.example.jsonrss_test_1;

/**
 * Created by fenghui on 3/24/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EndActivity extends Activity {
    public Button btn;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity);
        btn =(Button)findViewById(R.id.logout_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(EndActivity.this,Logout.class);
                startActivity(intent);
            }
        });

    }
}
