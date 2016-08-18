package com.example.jsonrss_test_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jsonrss_test_1.asynctaask.ImageDownloaderTask;
import com.example.jsonrss_test_1.model.FeedItem;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

	private ArrayList<FeedItem> listData;

	private LayoutInflater layoutInflater;

	private Context mContext;
	
	public CustomListAdapter(Context context, ArrayList<FeedItem> listData) {
		this.listData = listData;
		layoutInflater = LayoutInflater.from(context);
		mContext = context;
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.list_row_layout, null);
			holder = new ViewHolder();
			holder.headlineView = (TextView) convertView.findViewById(R.id.title);
			//holder.reportedDateView = (TextView) convertView.findViewById(R.id.date);
			holder.reportedContentView = (TextView) convertView.findViewById(R.id.content);
			holder.imageView = (ImageView) convertView.findViewById(R.id.thumbImage);
			holder.iconView = (ImageView) convertView.findViewById(R.id.icon);
			holder.reportedDataView = (TextView) convertView.findViewById(R.id.data);
			holder.reportedSummaryView = (TextView)convertView.findViewById(R.id.summary);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		FeedItem newsItem = (FeedItem) listData.get(position);
		holder.headlineView.setText(newsItem.getTitle());
		//holder.reportedDateView.setText(newsItem.getDate());
		holder.reportedContentView.setText(newsItem.getContent());
		holder.reportedSummaryView.setText(newsItem.getSummary());
		holder.reportedDataView.setText(newsItem.getDate());

		if (holder.imageView != null) {
			new ImageDownloaderTask(holder.imageView).execute(newsItem.getAttachmentUrl());
		}
		if(holder.iconView !=null){
			new ImageDownloaderTask(holder.iconView).execute(newsItem.getAttachmentUrl());
		}

		return convertView;
	}

	static class ViewHolder {
		TextView headlineView;
		//TextView reportedDateView;
		TextView reportedContentView;
		ImageView imageView;
		TextView reportedSummaryView;
		ImageView iconView;
		TextView reportedDataView;
	}
}
