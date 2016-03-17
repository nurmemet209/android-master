package com.cn.util;

import android.content.Context;
import android.widget.Toast;

public class CustomToast {
	private Context context;
	private int duration;
	private String text;

	public CustomToast(Context context, String text, int duration) {
		this.context = context;
		this.duration = duration;
		this.text = text;
	}

	public static CustomToast makeText(Context context, String text,
			int duration) {

		return new CustomToast(context, text, duration);
	}

	public void show() {
		Toast toast = Toast.makeText(context, text, duration);
		UyghurCharUtilities.getUtilities(context).setSystemViewText(
				toast.getView(), context);
		toast.show();
	}

}
