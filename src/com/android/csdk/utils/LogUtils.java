package com.android.csdk.utils;

import android.util.Log;

public class LogUtils {
	private static final boolean sPrintLog = true;
	
	public static void LogI(String tag, String msg) {
		if(sPrintLog) {
			Log.i(tag, msg);
		}
	}
	
	public static void LogV(String tag, String msg) {
		if(sPrintLog) {
			Log.v(tag, msg);
		}
	}
}
