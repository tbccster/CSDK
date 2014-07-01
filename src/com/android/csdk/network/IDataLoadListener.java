package com.android.csdk.network;

public interface IDataLoadListener {
	public void onProgress(int progress, int totalLen);
	public void onSuccess(byte[] bytes);
	public void onError(int errorCode);
}
