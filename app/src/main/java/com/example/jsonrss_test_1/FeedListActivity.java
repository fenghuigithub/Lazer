package com.example.jsonrss_test_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jsonrss_test_1.model.FeedItem;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FeedListActivity extends AppCompatActivity {

	private ArrayList<FeedItem> feedList = null;
	private ProgressBar progressbar = null;
	private ListView feedListView = null;
	private HttpResponse httpResponse;
	public ArrayList<String> str = null;
	public String time = null;
	public String cooldown = null;
	//public String time1 = null;

	private TextView textView;
	private int count = 10;
	public int k=2;
	//Bundle bundle1;
	//public int count = SettingActivity.class.item1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_posts_list);
		Bundle bundle = this.getIntent().getExtras();

		str = bundle.getStringArrayList("name");
		time = bundle.getString("time");
		cooldown = bundle.getString("cooldown");
		k=bundle.getInt("notice");

		int len = time.length();
		time = time.substring(0, len - 3);

		count = Integer.parseInt(time);
		count*=60;
//		Intent intent = new Intent();
//		intent.setClass(FeedListActivity.this, Proverb.class);
//		bundle1.putString("cool", cooldown);
//		intent.putExtras(bundle);
//		startActivity(intent);

		progressbar = (ProgressBar) findViewById(R.id.progressBar);
		String url = "http://chi01.xuleijr.com/api/subscriptions/7c96422964215320482";
		new DownloadFilesTask().execute(url);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		textView = (TextView) findViewById(R.id.countdown);
		handler.sendEmptyMessageDelayed(0, 1000);

	}

	public void updateList() {
		feedListView= (ListView) findViewById(R.id.custom_list);
		feedListView.setVisibility(View.VISIBLE);
		progressbar.setVisibility(View.GONE);
		
		feedListView.setAdapter(new CustomListAdapter(this, feedList));
		feedListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,	long id) {
				Object o = feedListView.getItemAtPosition(position);
				FeedItem newsData = (FeedItem) o;

				Intent intent = new Intent(FeedListActivity.this, FeedDetailsActivity.class);
				intent.putExtra("feed", newsData);
				startActivity(intent);
			}
		});
	}

	private class DownloadFilesTask extends AsyncTask<String, Integer, Void> {

		@Override
		protected void onProgressUpdate(Integer... values) {
		}

		@Override
		protected void onPostExecute(Void result) {
			if (null != feedList) {
				updateList();
			}
		}

		@Override
		protected Void doInBackground(String... params) {
			String url = params[0];

			// getting JSON string from URL
			JSONObject json = getJSONFromUrl(url);

			//parsing json data
			parseJson(json);
			return null;
		}
	}

	
	public JSONObject getJSONFromUrl(String url) {
		InputStream is = null;
		JSONObject jObj = null;
		String json = null;
		int len = str.size();

		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			List<NameValuePair> params = new ArrayList<NameValuePair>();

			NameValuePair pair1 = new BasicNameValuePair("channels", str.get(0));
			params.add(pair1);
//			NameValuePair pair1 = new BasicNameValuePair("channels", "56dba1f14f2b69c417409f68");
//			NameValuePair pair2 = new BasicNameValuePair("channels", "56dc5cf41c9f585b22d26190");
//			//params.add(new BasicNameValuePair("channels", "56dba1f14f2b69c417409f68"));
//			params.add(pair1);
//			params.add(pair2);

			HttpEntity httpEntity = new UrlEncodedFormEntity(params);
			httpPost.setEntity(httpEntity);

			httpResponse = httpClient.execute(httpPost);
			httpEntity = httpResponse.getEntity();
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
			//System.out.println(json);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
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
				JSONArray posts = json.getJSONArray("items");

				feedList = new ArrayList<FeedItem>();

				for (int i = 0; i < posts.length(); i++) {

					JSONObject post = (JSONObject) posts.getJSONObject(i);
					FeedItem item = new FeedItem();
					item.setTitle(post.getString("title"));
					item.setDate(post.getString("date"));
					item.setId(post.getString("_id"));
					item.setUrl(post.getString("link"));
					item.setContent(post.getString("content"));
					item.setAttachmentUrl(post.getString("cover"));
					item.setSummary(post.getString("summary"));
					item.setAttachmentUrl(post.getString("icon"));

//					item.setOrigin(post.getString("original"));
//					JSONArray attachments = post.getJSONArray("cover");
//
//					if (null != attachments && attachments.length() > 0) {
//						JSONObject attachment = attachments.getJSONObject(0);
//						if (attachment != null)
//							item.setAttachmentUrl(attachment.getString("url"));
//					}

					feedList.add(item);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private int getCount() {
		count--;
		if (count == 0) {
			Intent intent = new Intent(this, Proverb.class);
			Bundle bundle=new Bundle();
			bundle.putString("cool",cooldown);
			intent.putExtras(bundle);
			startActivity(intent);
			finish();
		}
//        for(int i = 9;i>=0;i--){
//            if(count == i*60){return i;}
//        }
		if(count/60 == 0){return count;}

		return count/60;
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				if(count>60)
				{textView.setText(getCount()+""+"min");
				textView.setVisibility(View.GONE);
					if(k==1) {textView.setVisibility(View.VISIBLE);}}
				else
				{textView.setText(getCount()+""+"s");
					textView.setVisibility(View.GONE);
					if(k==1) {textView.setVisibility(View.VISIBLE);}
					textView.setTextColor(Color.rgb(255, 0, 0));}

				handler.sendEmptyMessageDelayed(0, 1000);


			}

		};

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.none, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		super.onOptionsItemSelected(item);
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			//openSettings(item);
			return true;
		}
//        if(id == R.id.setting){
		//openSettings(item);
//        }

//        return super.onOptionsItemSelected(item);
		return true;
	}
//	public void openSettings(MenuItem item){
//		Intent intent = new Intent();
//		intent.setClass(FeedListActivity.this, SettingActivity.class);
//		startActivity(intent);
//
//	}

}
