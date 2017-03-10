package com.example.hanawa.smarterhometest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanawa.smarterhometest.enums.ErrorEnum;
import com.example.hanawa.smarterhometest.presenter.LightControlPresenter;
import com.example.hanawa.smarterhometest.presenter.impl.LightControlPresenterImpl;
import com.example.hanawa.smarterhometest.view.LightControlView;

import java.util.logging.Logger;


public class LightControlActivity extends AppCompatActivity implements View.OnClickListener,
        LightControlView, SeekBar.OnSeekBarChangeListener, SwitchCompat.OnCheckedChangeListener {

    private static final Logger logger = Logger.getLogger(LightControlActivity.class.toString());
    RelativeLayout toolbar;
    LinearLayout back;
    TextView backTV, title;
    SwitchCompat lcSC1, lcSC2, lcSC3, lcSC4;
    SeekBar bright;
    private LightControlPresenter presenter;
    private int last_seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_control);
        init();
        initData();
    }

    private void init() {
        presenter = new LightControlPresenterImpl(this);
        toolbar = (RelativeLayout) findViewById(R.id.lc_toolbar);
        back = (LinearLayout) toolbar.findViewById(R.id.toolbar_back);
        back.setOnClickListener(this);
        back.setVisibility(View.VISIBLE);
        backTV = (TextView) toolbar.findViewById(R.id.toolbar_tv_back);
        title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        lcSC1 = (SwitchCompat) findViewById(R.id.lc_sc_tv_chandelier);
        lcSC2 = (SwitchCompat) findViewById(R.id.lc_sc_relax_light);
        lcSC3 = (SwitchCompat) findViewById(R.id.lc_sc_center_light);
        lcSC4 = (SwitchCompat) findViewById(R.id.lc_sc_tv_light);
        lcSC1.setOnCheckedChangeListener(this);
        lcSC2.setOnCheckedChangeListener(this);
        lcSC3.setOnCheckedChangeListener(this);
        lcSC4.setOnCheckedChangeListener(this);
        bright = (SeekBar) findViewById(R.id.lc_sb_bright);
        bright.setOnSeekBarChangeListener(this);
    }

    private void initData() {
        backTV.setText(R.string.living_room);
        title.setText(R.string.light_control);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                this.finish();
                break;
        }
    }

    @Override
    public void showSuccessMsg() {
        Toast.makeText(this, "Change success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMsg(ErrorEnum code) {
        ErrorEnum.showErrorCode(this, code);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        logger.info("onProgressChanged-progress:" + progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        logger.info("onStartTrackingTouch-progress" + seekBar.getProgress());
        last_seekBar = seekBar.getProgress();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        logger.info("onStopTrackingTouch-progress:" + seekBar.getProgress());
        presenter.changeSeekBarProgress(seekBar, last_seekBar);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        logger.info("isChecked:" + isChecked);
        presenter.changeSwitchCompatStatus((SwitchCompat) buttonView);
    }
}
