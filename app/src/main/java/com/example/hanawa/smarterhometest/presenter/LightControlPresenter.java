package com.example.hanawa.smarterhometest.presenter;

import android.support.v7.widget.SwitchCompat;
import android.widget.SeekBar;

/**
 * Created by Hanawa on 2016/10/24.
 */

public interface LightControlPresenter {
    void changeSwitchCompatStatus(SwitchCompat switchCompat);

    void changeSeekBarProgress(SeekBar seekBar,int last);
}
