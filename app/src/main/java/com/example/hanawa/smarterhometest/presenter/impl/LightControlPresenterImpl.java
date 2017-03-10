package com.example.hanawa.smarterhometest.presenter.impl;


import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.SeekBar;

import com.example.hanawa.smarterhometest.enums.ErrorEnum;
import com.example.hanawa.smarterhometest.listener.LightControlListener;
import com.example.hanawa.smarterhometest.model.LightControlModel;
import com.example.hanawa.smarterhometest.model.impl.LightControlModelImpl;
import com.example.hanawa.smarterhometest.presenter.LightControlPresenter;
import com.example.hanawa.smarterhometest.view.LightControlView;

/**
 * Created by Hanawa on 2016/10/24
 */

public class LightControlPresenterImpl implements LightControlPresenter {

    private LightControlView lightControlView;
    private LightControlModel model;

    public LightControlPresenterImpl(LightControlView view) {
        this.lightControlView = view;
        model = new LightControlModelImpl();
    }

    @Override
    public void changeSwitchCompatStatus(final SwitchCompat switchCompat) {
        model.changeAppliance(switchCompat, new LightControlListener() {
            @Override
            public void success(View view) {
                // view不做任何处理
                lightControlView.showSuccessMsg();
            }

            @Override
            public void failure(View view) {
                switchCompat.setChecked(!switchCompat.isChecked());
                lightControlView.showErrorMsg(ErrorEnum.CONNECT_ERROR);
            }
        });
    }

    @Override
    public void changeSeekBarProgress(final SeekBar seekBar, final int last) {
        model.changeLight(seekBar, new LightControlListener() {
            @Override
            public void success(View view) {
                //seekBar不做任何处理
                lightControlView.showSuccessMsg();
            }

            @Override
            public void failure(View view) {
                seekBar.setProgress(last);
                lightControlView.showErrorMsg(ErrorEnum.CONNECT_ERROR);
            }
        });
    }
}