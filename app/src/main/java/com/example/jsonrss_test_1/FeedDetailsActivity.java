package com.example.jsonrss_test_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jsonrss_test_1.asynctaask.ImageDownloaderTask;
import com.example.jsonrss_test_1.model.FeedItem;

public class FeedDetailsActivity extends AppCompatActivity {

	private FeedItem feed;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed_details);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
//		// Get a support ActionBar corresponding to this toolbar
//		ActionBar ab = getSupportActionBar();
//		// Enable the Up button
//		ab.setDisplayHomeAsUpEnabled(true);

		feed = (FeedItem) this.getIntent().getSerializableExtra("feed");

		if (null != feed) {
			ImageView thumb = (ImageView) findViewById(R.id.featuredImg);
			new ImageDownloaderTask(thumb).execute(feed.getAttachmentUrl());

			TextView title = (TextView) findViewById(R.id.title);
			title.setText(feed.getTitle());

			TextView htmlTextView = (TextView) findViewById(R.id.content);
			htmlTextView.setText(Html.fromHtml(feed.getContent(), null, null));

			ImageView icon = (ImageView) findViewById(R.id.icon);
			new ImageDownloaderTask(icon).execute(feed.getAttachmentUrl());

			TextView data = (TextView) findViewById(R.id.data);
			data.setText(feed.getDate());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.none, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
	        case R.id.menu_share:
	        	shareContent();
	            return true;
	        case R.id.menu_view:
	        	Intent intent = new Intent(FeedDetailsActivity.this, WebViewActivity.class);
				intent.putExtra("url", feed.getUrl());
				startActivity(intent);
				
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void shareContent() {
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, feed.getTitle() + "\n" + feed.getUrl());
		sendIntent.setType("text/plain");
		startActivity(Intent.createChooser(sendIntent, "Share using"));

	}
}
