package com.shoes.position.utils;

import android.widget.Toast;

import com.shoes.position.config.PSApplicationConfig;

public class ToastUtils {

	private static Toast toast;

	public static void showToast(String text) {
		if (toast == null) {
			toast = Toast.makeText(PSApplicationConfig.getInstance(), text,
					Toast.LENGTH_SHORT);
		} else {
			toast.setText(text);
		}
		toast.show();
	}

	public static void showToast(int textRes) {
		if (toast == null) {
			toast = Toast.makeText(PSApplicationConfig.getInstance(), textRes,
					Toast.LENGTH_SHORT);
		} else {
			toast.setText(textRes);
		}
		toast.show();
	}

}
