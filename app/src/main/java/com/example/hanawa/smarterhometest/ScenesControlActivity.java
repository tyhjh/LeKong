
package com.example.hanawa.smarterhometest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanawa.smarterhometest.view.ScenesControlView;

public class ScenesControlActivity extends AppCompatActivity implements View.OnClickListener, ScenesControlView {

    private static final int[] CURTAINS_CODE_ON = {R.mipmap.open_click, R.mipmap.pause_click, R.mipmap.close_click};
    private static final int[] CURTAINS_CODE_OFF = {R.mipmap.open, R.mipmap.pause, R.mipmap.close};
    private static final int[] SCREEN_CODE_ON = {R.mipmap.up_click, R.mipmap.pause_click, R.mipmap.down_click};
    private static final int[] SCREEN_CODE_OFF = {R.mipmap.up, R.mipmap.pause, R.mipmap.down};
    private static final int CODE_NUM = 3;
    private int curtain_code = 0, screen_code = 0;

    RelativeLayout toolbar;
    LinearLayout back;
    TextView backTV, title;
    ImageView[] curtainsIVs, screenIVs;
    TextView[] curtainsTVs, screenTVs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenes_control);
        curtainsIVs = new ImageView[CODE_NUM];
        screenIVs = new ImageView[CODE_NUM];
        curtainsTVs = new TextView[CODE_NUM];
        screenTVs = new TextView[CODE_NUM];
        init();
        initData();
    }

    private void init() {
        toolbar = (RelativeLayout) findViewById(R.id.sc_toolbar);
        back = (LinearLayout) toolbar.findViewById(R.id.toolbar_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        backTV = (TextView) toolbar.findViewById(R.id.toolbar_tv_back);
        title = (TextView) toolbar.findViewById(R.id.toolbar_title);

        curtainsIVs[0] = (ImageView) findViewById(R.id.sc_iv_curtain_on);
        curtainsIVs[0].setOnClickListener(this);
        curtainsIVs[1] = (ImageView) findViewById(R.id.sc_iv_curtain_pause);
        curtainsIVs[1].setOnClickListener(this);
        curtainsIVs[2] = (ImageView) findViewById(R.id.sc_iv_curtain_off);
        curtainsIVs[2].setOnClickListener(this);
        screenIVs[0] = (ImageView) findViewById(R.id.sc_iv_screen_on);
        screenIVs[0].setOnClickListener(this);
        screenIVs[1] = (ImageView) findViewById(R.id.sc_iv_screen_pause);
        screenIVs[1].setOnClickListener(this);
        screenIVs[2] = (ImageView) findViewById(R.id.sc_iv_screen_off);
        screenIVs[2].setOnClickListener(this);

        curtainsTVs[0] = (TextView) findViewById(R.id.sc_tv_curtain_on);
        curtainsTVs[1] = (TextView) findViewById(R.id.sc_tv_curtain_pause);
        curtainsTVs[2] = (TextView) findViewById(R.id.sc_tv_curtain_off);
        screenTVs[0] = (TextView) findViewById(R.id.sc_tv_screen_on);
        screenTVs[1] = (TextView) findViewById(R.id.sc_tv_screen_pause);
        screenTVs[2] = (TextView) findViewById(R.id.sc_tv_screen_off);
    }

    private void initData() {
        backTV.setText(R.string.living_room);
        title.setText(R.string.scenes_control);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                this.finish();
                break;
            case R.id.sc_iv_curtain_on:
                curtainSelect(0);
                break;
            case R.id.sc_iv_curtain_pause:
                curtainSelect(1);
                break;
            case R.id.sc_iv_curtain_off:
                curtainSelect(2);
                break;
            case R.id.sc_iv_screen_on:
                screenSelect(0);
                break;
            case R.id.sc_iv_screen_pause:
                screenSelect(1);
                break;
            case R.id.sc_iv_screen_off:
                screenSelect(2);
                break;
        }
    }

    @Override
    public void curtainSelect(int code) {
        if (code != curtain_code) {
            curtainsIVs[code].setImageResource(CURTAINS_CODE_ON[code]);
            curtainsTVs[code].setTextColor(getResources().getColor(R.color.colorOn));
            curtainsIVs[curtain_code].setImageResource(CURTAINS_CODE_OFF[curtain_code]);
            curtainsTVs[curtain_code].setTextColor(getResources().getColor(R.color.colorOff));
            curtain_code = code;
        }
    }

    @Override
    public void screenSelect(int code) {
        if (code != screen_code) {
            screenIVs[code].setImageResource(SCREEN_CODE_ON[code]);
            screenTVs[code].setTextColor(getResources().getColor(R.color.colorOn));
            screenIVs[screen_code].setImageResource(SCREEN_CODE_OFF[screen_code]);
            screenTVs[screen_code].setTextColor(getResources().getColor(R.color.colorOff));
            screen_code = code;
        }
    }
}
