package com.shoes.position.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class SharePreferenceUtils {
    public static final String FILE_NAME = "share_data";
    private static Context context;

    public static void buid(Context context) {
        SharePreferenceUtils.context = context;
    }

    /**
     * 将数据写入Sp
     *
     * @param
     * @param key
     * @param object
     */
    public static void put(String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        }
        editor.commit();
    }

    /**
     * 返回所有的键值对。
     */
    public static Map<String, ?> getALL() {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

    public static boolean getData(String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static synchronized String getPrsonData(String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static void clear() {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 查找是否包含此key的Map
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 删除某一个key所对应的值
     *
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences spPreferences = context.getSharedPreferences(
                FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spPreferences.edit();
        editor.remove(key);
        editor.commit();

    }
}
