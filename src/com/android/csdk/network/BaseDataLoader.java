package com.android.csdk.network;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.net.Uri;
import android.text.TextUtils;

/**
 * provide base http connection
 * @author ccster
 *
 */
public class BaseDataLoader {
	private static final String TAG = "BaseDataLoader";
	private static final String METHOD_GET = "GET";
	private static final String METHOD_POST = "POST";
	
	private static final int CONNECT_TIME_OUT = 3000;
	private static final int READ_TIME_OUT = 30000;
	
	private String mUrl;
	private String mMethod;
	
	private IDataLoadListener mDataLoadListener;
	
	private HttpURLConnection mConnection = null;
	
	public BaseDataLoader() {
		mMethod = METHOD_GET;
	}
	
	public void loadData(String url, IDataLoadListener listener) {
		mUrl = url;
		mDataLoadListener = listener;
	}
	
	public void setMethod(String method) {
		if(!TextUtils.isEmpty(method)) {
			mMethod = method;
		}
	}
	
	private void run() {
		if(null != mUrl) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					InputStream is = null;
					ByteArrayOutputStream os = null; 
					try {
						URL url = new URL(mUrl);
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						connection.setRequestMethod(mMethod);
						connection.setDoInput(true);
						connection.setConnectTimeout(CONNECT_TIME_OUT);
						connection.setReadTimeout(READ_TIME_OUT);
					
						int totalLen = connection.getContentLength();
						is = connection.getInputStream();
						byte[] tmp = new byte[4096];
					
						int progress = 0;
						int len = 0;
						os = new ByteArrayOutputStream();
						while((len = is.read(tmp)) != -1) {
							os.write(tmp, progress, len);
							progress += len;
							if(null != mDataLoadListener) {
								mDataLoadListener.onProgress(progress, totalLen);
							}
						}
						if(null != mDataLoadListener) {
							mDataLoadListener.onSuccess(os.toByteArray());
						}
						is.close();
						os.close();
						
					} catch (Exception e) {
						e.printStackTrace();
						if(null != mDataLoadListener) {
							mDataLoadListener.onError(0);
						}
						if(null != is) {
							try {
								is.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						
						if(null != os) {
							try {
								os.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}).start();
		}
	}
	
}
