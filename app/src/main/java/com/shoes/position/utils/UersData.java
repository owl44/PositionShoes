package com.shoes.position.utils;


import android.content.Context;
import android.content.Intent;

import com.shoes.position.ui.MainActivity;

/**
 * Created by Prarui on 2017/7/7.
 */

public class UersData {
    public static String APP_VESION = "android";
    public static String APP_VISION_CODE = "1";
    public static String TOKEN = "token";
    public static String PHONE = "phone";

    public static void isRefresh(boolean refresh){
        SharePreferenceUtils.put("refresh",refresh);
    }

    public static boolean getRefresh(){
        return SharePreferenceUtils.getData("refresh");
    }

    public static void saveToken(String token) {
        SharePreferenceUtils.put(TOKEN, token);
    }

    public static void saveUerPhoneN(String phone) {
        SharePreferenceUtils.put(PHONE, phone);
    }

    public static String getUerPhoneN() {
        return SharePreferenceUtils.getPrsonData(PHONE);
    }

    public static void clearUerPhoneN() {
        SharePreferenceUtils.remove(PHONE);
    }

    public static String getToken() {
        return SharePreferenceUtils.getPrsonData("token");
    }

    public static boolean isLogin(Context context) {
        if (getToken().length() == 0 && getToken().equals("")) {
            context.startActivity(new Intent(context, MainActivity.class));
            return false;
        }
        return true;
    }

    public static void clearAll() {
        clearToken();
        clearUerPhoneN();
    }

    public static void clearToken() {
        /**
         * 清除token
         */
        SharePreferenceUtils.remove(TOKEN);
    }
}
