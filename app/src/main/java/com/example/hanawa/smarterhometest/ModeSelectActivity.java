package com.example.hanawa.smarterhometest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanawa.smarterhometest.view.ModeSelectView;

public class ModeSelectActivity extends AppCompatActivity implements View.OnClickListener, ModeSelectView {

    private static final int MODE_NUM = 4;
    private static final int[] MODE_CODE_OFF = {R.mipmap.mode_back_home_nor, R.mipmap.mode_leave_home_nor
            , R.mipmap.mode_video_nor, R.mipmap.mode_receive_nor};
    private static final int[] MODE_CODE_ON = {R.mipmap.mode_back_home_sel, R.mipmap.mode_leave_home_sel
            , R.mipmap.mode_video_sel, R.mipmap.mode_receive_sel};
    RelativeLayout toolbar;
    LinearLayout back;
    TextView backTV, title;
    TextView[] modeTV;
    ImageView[] modeIV;
    private int mode_code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_select);
        modeIV = new ImageView[MODE_NUM];
        modeTV = new TextView[MODE_NUM];
        init();
        initData();
    }

    private void init() {
        toolbar = (RelativeLayout) findViewById(R.id.ms_toolbar);
        back = (LinearLayout) toolbar.findViewById(R.id.toolbar_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        backTV = (TextView) toolbar.findViewById(R.id.toolbar_tv_back);
        title = (TextView) toolbar.findViewById(R.id.toolbar_title);

        modeTV[0] = (TextView) findViewById(R.id.ms_tv_home_come);
        modeTV[1] = (TextView) findViewById(R.id.ms_tv_home_leave);
        modeTV[2] = (TextView) findViewById(R.id.ms_tv_radio);
        modeTV[3] = (TextView) findViewById(R.id.ms_tv_host);
        modeIV[0] = (ImageView) findViewById(R.id.ms_iv_home_come);
        modeIV[0].setOnClickListener(this);
        modeIV[1] = (ImageView) findViewById(R.id.ms_iv_home_leave);
        modeIV[1].setOnClickListener(this);
        modeIV[2] = (ImageView) findViewById(R.id.ms_iv_radio);
        modeIV[2].setOnClickListener(this);
        modeIV[3] = (ImageView) findViewById(R.id.ms_iv_host);
        modeIV[3].setOnClickListener(this);
    }

    private void initData() {
        backTV.setText(R.string.living_room);
        title.setText(R.string.mode_select);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                this.finish();
                break;
            case R.id.ms_iv_home_come:
                modeSelect(0);
                break;
            case R.id.ms_iv_home_leave:
                modeSelect(1);
                break;
            case R.id.ms_iv_radio:
                modeSelect(2);
                break;
            case R.id.ms_iv_host:
                modeSelect(3);
                break;
        }
    }

    @Override
    public void modeSelect(int code) {
        if (code != mode_code) {
            modeTV[code].setTextColor(getResources().getColor(R.color.colorOn));
            modeTV[mode_code].setTextColor(getResources().getColor(R.color.colorOff));
            modeIV[code].setImageResource(MODE_CODE_ON[code]);
            modeIV[mode_code].setImageResource(MODE_CODE_OFF[mode_code]);
            mode_code = code;
        }
    }
}
