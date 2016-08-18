package com.example.jsonrss_test_1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.ToggleButton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Userpreference extends AppCompatActivity implements View.OnClickListener{
    public Button Btn1,Btn2,Btn3,Btn4,Btn5,Btn6,Btn7,Btn8,Btn9,Btn10;
    public int i = 0, j=0,a=0,b=0,c=0,d=0,e=0,f=0,g=0,k=0;
    String url = "http://chi01.xuleijr.com/api/subscriptions/";
    public HttpResponse httpResponse;
    ArrayList<String> str = new ArrayList<String>();
    ArrayList<String> str1 = new ArrayList<String>();
    ArrayList<Integer> str2 = new ArrayList<Integer>();
    public String id = null;
    private static final String TAG = Userpreference.class.getSimpleName();
    private static final String[] PLANETS = new String[]{"5min", "10min", "15min", "20min", "25min", "30min", "40min", "50min"};
    String[] title = {"SHOW COUNTDOWN","COUNTDOWN TIME","COOLDOWN INTERVAL"};
    public String item1,item2;
    public Button btn,btn_main;
    public ListView listView;
    public ToggleButton mTogBtn1;
    public String Username;

//    public ToggleButton mTogBtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_preference);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn = (Button)findViewById(R.id.user_show_dialog_btn);
        findViewById(R.id.user_show_dialog_btn).setOnClickListener(this);
        listView = (ListView) findViewById(R.id.itemlist_user);
        mTogBtn1 = (ToggleButton) findViewById(R.id.mTogBtn1); // 获取到控件

        btn_main = (Button)findViewById(R.id.main_show_dialog_btn);
        findViewById(R.id.main_show_dialog_btn).setOnClickListener(this);
        getData();
        SimpleAdapter adapter = new SimpleAdapter(this,
                getData(),
                R.layout.list_item, new String[] {
                "a"}, new int[] {
                R.id.title1});
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);
        listView.setSelection(0);
        //SHOW COUNTDOWN
        mTogBtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    //选中
                    System.out.println("Checked");
                    j = 1;
                } else {
                    //未选中
                    System.out.println("Unchecked");
                    j = 0;
                }
            }
        });// 添加监听事件
//        //NOTIFICATION
//        mTogBtn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                // TODO Auto-generated method stub
//                if (isChecked) {
//                    //选中
//                    System.out.println("Noticed");
//
//                } else {
//                    //未选中
//                    System.out.println("Unnoticed");
//
//                }
//            }
//        });// 添加监听事件

        //Technology Button
        Btn1 = (Button) findViewById(R.id.Technology);
        Btn1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && i == 0) {
                    // change color
                    Btn1.setBackgroundResource(R.drawable.btn_shape_black);
//                    JSONObject json = getJSONFromUrl(url);
//                    parseJson(json);
//                    id = str.get(1);
                    str2.add(0);
                    new DownloadFilesTask().execute(url);

                    System.out.println("Change color!");
                    i = 1;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN && i == 1) {
                    // set to normal color
                    Btn1.setBackgroundResource(R.drawable.btn_shape);
                    str1.remove(str.get(0));
                    System.out.println(str1);
                    System.out.println("set to normal color!");
                    i = 0;
                }

                return true;
            }
        });
        //Economy Button

        Btn2 = (Button) findViewById(R.id.Economy);
        Btn2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && j == 0) {
                    // change color
                    Btn2.setBackgroundResource(R.drawable.btn_shape_black);
//                    JSONObject json = getJSONFromUrl(url);
//                    parseJson(json);
//                    id = str.get(1);
                    str2.add(1);
                    new DownloadFilesTask().execute(url);

                    System.out.println("Economy Change color!");
                    j = 1;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN && j == 1) {
                    // set to normal color
                    Btn2.setBackgroundResource(R.drawable.btn_shape);
                    str1.remove(str.get(1));
                    System.out.println(str1);
                    System.out.println("Economy set to normal color!");
                    j = 0;
                }

                return true;
            }
        });

        //Weather Button
        Btn3 = (Button) findViewById(R.id.Weather);
        Btn3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && a == 0) {
                    // change color
                    Btn3.setBackgroundResource(R.drawable.btn_shape_black);
//                    JSONObject json = getJSONFromUrl(url);
//                    parseJson(json);
//                    id = str.get(1);
                    str2.add(2);
                    new DownloadFilesTask().execute(url);

                    System.out.println("Change color!");
                    a = 1;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN && a == 1) {
                    // set to normal color
                    Btn3.setBackgroundResource(R.drawable.btn_shape);
                    str1.remove(str.get(2));
                    System.out.println(str1);
                    System.out.println("set to normal color!");
                    a = 0;
                }

                return true;
            }
        });
        //Entertainment Button
        Btn4 = (Button) findViewById(R.id.Entertainment);
        Btn4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && b == 0) {
                    // change color
                    Btn4.setBackgroundResource(R.drawable.btn_shape_black);
//                    JSONObject json = getJSONFromUrl(url);
//                    parseJson(json);
//                    id = str.get(1);
                    str2.add(3);
                    new DownloadFilesTask().execute(url);

                    System.out.println("Change color!");
                    b = 1;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN && b == 1) {
                    // set to normal color
                    Btn4.setBackgroundResource(R.drawable.btn_shape);
                    str1.remove(str.get(3));
                    System.out.println(str1);
                    System.out.println("set to normal color!");
                    b = 0;
                }

                return true;
            }
        });
        //Education Button
        Btn5 = (Button) findViewById(R.id.Education);
        Btn5.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && c == 0) {
                    // change color
                    Btn5.setBackgroundResource(R.drawable.btn_shape_black);
//                    JSONObject json = getJSONFromUrl(url);
//                    parseJson(json);
//                    id = str.get(1);
                    str2.add(4);
                    new DownloadFilesTask().execute(url);

                    System.out.println("Change color!");
                    c = 1;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN && c == 1) {
                    // set to normal color
                    Btn5.setBackgroundResource(R.drawable.btn_shape);
                    str1.remove(str.get(4));
                    System.out.println(str1);
                    System.out.println("set to normal color!");
                    c = 0;
                }

                return true;
            }
        });
        //News Button
        Btn6 = (Button) findViewById(R.id.News);
        Btn6.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && d == 0) {
                    // change color
                    Btn6.setBackgroundResource(R.drawable.btn_shape_black);
//                    JSONObject json = getJSONFromUrl(url);
//                    parseJson(json);
//                    id = str.get(1);
                    str2.add(5);
                    new DownloadFilesTask().execute(url);

                    System.out.println("Change color!");
                    d = 1;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN && d == 1) {
                    // set to normal color
                    Btn6.setBackgroundResource(R.drawable.btn_shape);
                    str1.remove(str.get(5));
                    System.out.println(str1);
                    System.out.println("set to normal color!");
                    d = 0;
                }

                return true;
            }
        });
        //Sport Button
        Btn7 = (Button) findViewById(R.id.Sport);
        Btn7.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && e == 0) {
                    // change color
                    Btn7.setBackgroundResource(R.drawable.btn_shape_black);
//                    JSONObject json = getJSONFromUrl(url);
//                    parseJson(json);
//                    id = str.get(1);
                    str2.add(6);
                    new DownloadFilesTask().execute(url);

                    System.out.println("Change color!");
                    e = 1;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN && e == 1) {
                    // set to normal color
                    Btn7.setBackgroundResource(R.drawable.btn_shape);
                    str1.remove(str.get(6));
                    System.out.println(str1);
                    System.out.println("set to normal color!");
                    e = 0;
                }

                return true;
            }
        });
        //Test Button
        Btn8 = (Button) findViewById(R.id.Test);
        Btn8.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && f == 0) {
                    // change color
                    Btn8.setBackgroundResource(R.drawable.btn_shape_black);
//                    JSONObject json = getJSONFromUrl(url);
//                    parseJson(json);
//                    id = str.get(1);
                    str2.add(7);
                    new DownloadFilesTask().execute(url);

                    System.out.println("Change color!");
                    f = 1;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN && f == 1) {
                    // set to normal color
                    Btn8.setBackgroundResource(R.drawable.btn_shape);
                    str1.remove(str.get(7));
                    System.out.println(str1);
                    System.out.println("set to normal color!");
                    f = 0;
                }

                return true;
            }
        });
        //Game Button
        Btn9 = (Button) findViewById(R.id.Game);
        Btn9.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && g == 0) {
                    // change color
                    Btn9.setBackgroundResource(R.drawable.btn_shape_black);
//                    JSONObject json = getJSONFromUrl(url);
//                    parseJson(json);
//                    id = str.get(1);
                    str2.add(8);
                    new DownloadFilesTask().execute(url);

                    System.out.println("Change color!");
                    g = 1;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN && g == 1) {
                    // set to normal color
                    Btn9.setBackgroundResource(R.drawable.btn_shape);
                    str1.remove(str.get(8));
                    System.out.println(str1);
                    System.out.println("set to normal color!");
                    g = 0;
                }

                return true;
            }
        });
        //Outdoor Button
        Btn10 = (Button) findViewById(R.id.Outdoor);
        Btn10.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && k == 0) {
                    // change color
                    Btn10.setBackgroundResource(R.drawable.btn_shape_black);
//                    JSONObject json = getJSONFromUrl(url);
//                    parseJson(json);
//                    id = str.get(1);
                    str2.add(9);
                    new DownloadFilesTask().execute(url);

                    System.out.println("Change color!");
                    k = 1;
                } else if (event.getAction() == MotionEvent.ACTION_DOWN && k == 1) {
                    // set to normal color
                    Btn10.setBackgroundResource(R.drawable.btn_shape);
                    str1.remove(str.get(9));
                    System.out.println(str1);
                    System.out.println("set to normal color!");
                    k = 0;
                }

                return true;
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_show_dialog_btn:
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
                        .setTitle("Select time")
                        .setView(outerView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println(item1);
                                btn.setText(item1);
                            }
                        })
                        .show();
                break;
            case R.id.main_show_dialog_btn:
                View outerView1 = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                WheelView wv1 = (WheelView) outerView1.findViewById(R.id.wheel_view_wv);
//                Button btn = new Button();
//                btn = outerView.findViewById(R.id.main_show_dialog_btn);
                wv1.setOffset(2);
                wv1.setItems(Arrays.asList(PLANETS));
                wv1.setSeletion(3);
                wv1.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {

                        Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                        item2 = item;

                    }

                });

                new AlertDialog.Builder(this)
                        .setTitle("WheelView in Dialog")
                        .setView(outerView1)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println(item2);
                                btn_main.setText(item2);
                            }
                        })
                        .show();

//                Intent intent = new Intent();
//                intent.setClass(Userpreference.this, FeedListActivity.class);
//
//                Bundle bundle=new Bundle();
//                bundle.putString("time", item1);
//                intent.putExtras(bundle);
//                startActivity(intent);

                break;
        }

    }

    private class DownloadFilesTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

//        @Override
//        protected void onPostExecute(Void result) {
//            if (null != feedList) {
//                updateList();
//            }
//        }

        @Override
        protected Void doInBackground(String... params) {
            String url = params[0];

            // getting JSON string from URL
            JSONObject json = getJSONFromUrl(url);

            //parsing json data
            parseJson(json);
            for(int j=0;j<str2.size();j++){
                if(str1.indexOf(str.get(str2.get(j)))>=0) continue;
                str1.add(str.get(str2.get(j)));
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.done, menu);
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
            openSettings(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //Done the settings and jump to FeedListActivity
    public void openSettings(MenuItem item){
        Intent intent = new Intent();
        intent.setClass(Userpreference.this, FeedListActivity.class);
        Bundle bundle=new Bundle();
        bundle.putStringArrayList("name", str1);
        bundle.putString("time", item1);
        bundle.putString("cooldown",item2);
        bundle.putInt("notice", j);
        intent.putExtras(bundle);
        startActivity(intent);

//        Bundle bundle1 = this.getIntent().getExtras();
//        Username = bundle1.getString("username");

    }


    public JSONObject getJSONFromUrl(String url) {
        InputStream is = null;
        JSONObject jObj = null;
        String json = null;
        String strResult = null;

        // Making HTTP request
        try {
//            // defaultHttpClient
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(url);
            HttpGet httpGet = new HttpGet(url);
            HttpClient httpClient = new DefaultHttpClient();
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
//            strResult = EntityUtils.toString(httpResponse.getEntity());
//            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//
//                strResult = EntityUtils.toString(httpResponse.getEntity());
//                //textView.setText(strResult);
//            } else {
//                //textView.setText("请求错误");
//            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;


    }

    public void parseJson(JSONObject json) {
        try {

            // parsing json object
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                JSONArray posts = json.getJSONArray("channels");

//                feedList = new ArrayList<FeedItem>();

                for (int i = 0; i < posts.length(); i++) {
                    JSONObject post = (JSONObject) posts.getJSONObject(i);
                    str.add(i, post.getString("_id"));
//                    System.out.println(str);
//                    FeedItem item = new FeedItem();
//                    item.setTitle(post.getString("title"));
//                    item.setDate(post.getString("date"));
//                    item.setId(post.getString("id"));
//                    item.setUrl(post.getString("url"));
//                    item.setContent(post.getString("content"));
//                    JSONArray attachments = post.getJSONArray("attachments");
//
//                    if (null != attachments && attachments.length() > 0) {
//                        JSONObject attachment = attachments.getJSONObject(0);
//                        if (attachment != null)
//                            item.setAttachmentUrl(attachment.getString("url"));
                }

//                    feedList.add(item);
//                System.out.println(str);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
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
