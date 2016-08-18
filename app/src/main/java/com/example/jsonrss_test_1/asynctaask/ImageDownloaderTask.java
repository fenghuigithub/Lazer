package com.example.jsonrss_test_1.asynctaask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.example.jsonrss_test_1.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;

import java.io.InputStream;
import java.lang.ref.WeakReference;

public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
	private final WeakReference<ImageView> imageViewReference;//注意，这里对ImageView使用 WeakReference弱引用的目的是确保 AsyncTask不会妨碍系统对ImageView必要时候的垃圾回收。否则可能会出现内存泄露.


	public ImageDownloaderTask(ImageView imageView) {
		imageViewReference = new WeakReference<ImageView>(imageView);
	}

	@Override
	// Actual download method, run in the task thread
	protected Bitmap doInBackground(String... params) {
		// params comes from the execute() call: params[0] is the url.
		return downloadBitmap(params[0]);
	}

	@Override
	// Once the image is downloaded, associates it to the imageView
	protected void onPostExecute(Bitmap bitmap) {
		if (isCancelled()) {
			bitmap = null;
		}

		if (imageViewReference != null) {//同时，我们一定要在Task执行完毕后对ImageView的存在性进行判断，因为不能保证Task执行完毕后，ImageView还会存在。
			ImageView imageView = imageViewReference.get();
			if (imageView != null) {

				if (bitmap != null) {
					imageView.setImageBitmap(bitmap);
				} else {
					imageView.setImageDrawable(imageView.getContext().getResources().getDrawable(R.drawable.list_placeholder));
				}
			}

		}
	}	

	static Bitmap downloadBitmap(String url) {
		if(URLUtil.isValidUrl(url)){

			final AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
			final HttpGet getRequest = new HttpGet(url);
			try {
				HttpResponse response = client.execute(getRequest);
				final int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != HttpStatus.SC_OK) {
					Log.w("ImageDownloader", "Error " + statusCode
							+ " while retrieving bitmap from " + url);
					return null;
				}

				final HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream inputStream = null;
					try {
						inputStream = entity.getContent();
						final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
						return bitmap;
					} finally {
						if (inputStream != null) {
							inputStream.close();
						}
						entity.consumeContent();
					}
				}
			} catch (Exception e) {
				// Could provide a more explicit error message for IOException or
				// IllegalStateException
				getRequest.abort();
				Log.w("ImageDownloader", "Error while retrieving bitmap from " + url);
			} finally {
				if (client != null) {
					client.close();
				}
			}
			return null;
		
		}
		return null;
	}

}



