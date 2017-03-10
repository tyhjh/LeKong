package com.example.hanawa.smarterhometest.model;

import android.support.v7.widget.SwitchCompat;
import android.widget.SeekBar;

import com.example.hanawa.smarterhometest.listener.LightControlListener;

/**
 * Created by Hanawa on 2016/10/24
 */

public interface LightControlModel {

    /**
     * 根据SwtichCompat状态更改来改变家居的状态
     */
    void changeAppliance(SwitchCompat switchCompat, LightControlListener listener);

    /**
     * 根据SeekBar的进度条改变来改变灯光的亮度
     */
    void changeLight(SeekBar seekBar,LightControlListener listener);

}