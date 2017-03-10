package com.example.hanawa.smarterhometest.enums;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Hanawa on 2016/11/1.
 */

public enum ErrorEnum {
    CONNECT_ERROR(90000);

    private int code;

    ErrorEnum(int code) {
        this.code = code;
    }

    private int getCode() {
        return code;
    }

    public static void showErrorCode(Context context, ErrorEnum code) {
        Toast.makeText(context, "ERROR CODE:" + code.getCode() + "," + code.toString(), Toast.LENGTH_SHORT).show();
    }
}
