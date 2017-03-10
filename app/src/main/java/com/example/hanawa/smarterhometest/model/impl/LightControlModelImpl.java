package com.example.hanawa.smarterhometest.model.impl;


import android.support.v7.widget.SwitchCompat;
import android.widget.SeekBar;

import com.example.hanawa.smarterhometest.listener.LightControlListener;
import com.example.hanawa.smarterhometest.model.LightControlModel;

/**
 * Created by Hanawa on 2016/10/24
 */

public class LightControlModelImpl implements LightControlModel {

    @Override
    public void changeAppliance(SwitchCompat switchCompat, LightControlListener listener) {
        listener.success(switchCompat);
    }

    @Override
    public void changeLight(SeekBar seekBar, LightControlListener listener) {
        listener.failure(seekBar);
    }
}