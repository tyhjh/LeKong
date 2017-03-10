package com.example.hanawa.smarterhometest.enums;

/**
 * Created by Hanawa on 2016/10/31.
 */

public enum LightControlEnum {
    SWITCH_TV_CHANDERLIER(0), SWITCH_RELAX_LIGHT(1), SWITCH_CENTER_LIGHT(2), SWTICH_TV_LIGHT(3);

    int code;

    LightControlEnum(int code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
