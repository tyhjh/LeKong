package com.example.hanawa.smarterhometest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hanawa.smarterhometest.value.GlobalConfig;

/**
 * Created by Hanawa on 2016/10/31.
 */

public class MySharePreferencesUtil {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static void save(Context context, String fileName, String key, Object value) {
        if (null == sharedPreferences) {
            sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        if (null == editor) {
            editor = sharedPreferences.edit();
        }

        if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        }
        editor.commit();
        editor = null;
        sharedPreferences = null;
    }

    public static Object get(Context context, String fileName, String key, int valueType) {
        Object result = null;
        sharedPreferences = context.getSharedPreferences(key, valueType);
        switch (valueType) {
            case GlobalConfig.TYPE_INTEGER:
                result = sharedPreferences.getInt(key, -1);
                break;
            case GlobalConfig.TYPE_STRING:
                result = sharedPreferences.getString(key,"");
                break;
            case GlobalConfig.TYPE_BOOLEAN:
                result = sharedPreferences.getBoolean(key,false);
                break;
            case GlobalConfig.TYPE_LONG:
                result = sharedPreferences.getLong(key,-1);
                break;
            case GlobalConfig.TYPE_FLOAT:
                result = sharedPreferences.getFloat(key,-1);
                break;
        }
        sharedPreferences = null;
        return result;
    }

}
