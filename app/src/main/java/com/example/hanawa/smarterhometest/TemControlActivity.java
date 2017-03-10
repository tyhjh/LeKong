package com.example.hanawa.smarterhometest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanawa.smarterhometest.view.TemControlView;

public class TemControlActivity extends AppCompatActivity implements View.OnClickListener, TemControlView {

    private static final int WIND_MODE_NUM = 5;
    private static final int WIND_SELECTED_WIDTH = 60, WIND_NO_SELECT_WIDTH = 40, TEXT_MARGIN_BOTTOM = 23;
    private static final int[] WIND_NO_SELECT_MARGIN_LEFT = {-20, 104, 228, 352, 476};
    private static final int[] WIND_SELECTED_MARGIN_LEFT = {-30, 94, 218, 342, 466};
    private static final int[] TEM_MODE_NO_SELECT = {R.mipmap.mode_refrigeration, R.mipmap.mode_heating, R.mipmap.mode_desiccant, R.mipmap.mode_ventilation};
    private static final int[] TEM_MODE_SELECTED = {R.mipmap.mode_refrigeration_sel, R.mipmap.mode_heating_sel, R.mipmap.mode_desiccant_sel, R.mipmap.mode_ventilation_sel};
    private static final int TEM_MODE_NUM = 4;
    RelativeLayout toolbar, wind;
    LinearLayout back;
    TextView backTV, title;
    TextView[] windTV = new TextView[WIND_MODE_NUM];
    Button[] windBTN = new Button[WIND_MODE_NUM];
    RelativeLayout[] windRL = new RelativeLayout[WIND_MODE_NUM];
    ImageView[] temIVs = new ImageView[TEM_MODE_NUM];
    TextView[] temTVs = new TextView[TEM_MODE_NUM];
    private int wind_code = 1, tem_code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tem_control);

        init();
        initData();
    }

    private void init() {
        toolbar = (RelativeLayout) findViewById(R.id.tc_toolbar);
        back = (LinearLayout) toolbar.findViewById(R.id.toolbar_back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        backTV = (TextView) toolbar.findViewById(R.id.toolbar_tv_back);
        title = (TextView) toolbar.findViewById(R.id.toolbar_title);

        // wind select
        wind = (RelativeLayout) findViewById(R.id.tc_rl_wind);
        windRL[0] = (RelativeLayout) wind.findViewById(R.id.lgw_rl1);
        windRL[1] = (RelativeLayout) wind.findViewById(R.id.lgw_rl2);
        windRL[2] = (RelativeLayout) wind.findViewById(R.id.lgw_rl3);
        windRL[3] = (RelativeLayout) wind.findViewById(R.id.lgw_rl4);
        windRL[4] = (RelativeLayout) wind.findViewById(R.id.lgw_rl5);
        windTV[0] = (TextView) wind.findViewById(R.id.lgw_tv1);
        windTV[1] = (TextView) wind.findViewById(R.id.lgw_tv2);
        windTV[2] = (TextView) wind.findViewById(R.id.lgw_tv3);
        windTV[3] = (TextView) wind.findViewById(R.id.lgw_tv4);
        windTV[4] = (TextView) wind.findViewById(R.id.lgw_tv5);
        windBTN[0] = (Button) wind.findViewById(R.id.lgw_btn1);
        windBTN[1] = (Button) wind.findViewById(R.id.lgw_btn2);
        windBTN[2] = (Button) wind.findViewById(R.id.lgw_btn3);
        windBTN[3] = (Button) wind.findViewById(R.id.lgw_btn4);
        windBTN[4] = (Button) wind.findViewById(R.id.lgw_btn5);
        windBTN[0].setOnClickListener(this);
        windBTN[1].setOnClickListener(this);
        windBTN[2].setOnClickListener(this);
        windBTN[3].setOnClickListener(this);
        windBTN[4].setOnClickListener(this);
        // tem mode select
        temIVs[0] = (ImageView) findViewById(R.id.tc_iv_mod_cold);
        temIVs[1] = (ImageView) findViewById(R.id.tc_iv_mod_heat);
        temIVs[2] = (ImageView) findViewById(R.id.tc_iv_mod_dehum);
        temIVs[3] = (ImageView) findViewById(R.id.tc_iv_mod_host);
        temIVs[0].setOnClickListener(this);
        temIVs[1].setOnClickListener(this);
        temIVs[2].setOnClickListener(this);
        temIVs[3].setOnClickListener(this);
        temTVs[0] = (TextView) findViewById(R.id.tc_tv_mod_cold);
        temTVs[1] = (TextView) findViewById(R.id.tc_tv_mod_heat);
        temTVs[2] = (TextView) findViewById(R.id.tc_tv_mod_dehum);
        temTVs[3] = (TextView) findViewById(R.id.tc_tv_mod_host);
    }

    private void initData() {
        backTV.setText(R.string.living_room);
        title.setText(R.string.temperaturre_control);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                this.finish();
                break;
            case R.id.lgw_btn1:
                windSelect(0);
                break;
            case R.id.lgw_btn2:
                windSelect(1);
                break;
            case R.id.lgw_btn3:
                windSelect(2);
                break;
            case R.id.lgw_btn4:
                windSelect(3);
                break;
            case R.id.lgw_btn5:
                windSelect(4);
                break;
            case R.id.tc_iv_mod_cold:
                modeSelect(0);
                break;
            case R.id.tc_iv_mod_heat:
                modeSelect(1);
                break;
            case R.id.tc_iv_mod_dehum:
                modeSelect(2);
                break;
            case R.id.tc_iv_mod_host:
                modeSelect(3);
                break;
        }
    }

    @Override
    public void windSelect(int code) {
        if (code != wind_code) {
            // selected
            RelativeLayout.LayoutParams lpBTNSelected = new RelativeLayout.LayoutParams(WIND_SELECTED_WIDTH, WIND_SELECTED_WIDTH);
            lpBTNSelected.addRule(RelativeLayout.CENTER_VERTICAL);
            RelativeLayout.LayoutParams lpRLSelected = new RelativeLayout.LayoutParams(WIND_SELECTED_WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpRLSelected.addRule(RelativeLayout.ABOVE, R.id.lgw_line);
            if (code == 4) {
                lpBTNSelected.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lpBTNSelected.setMargins(0, 0, WIND_SELECTED_MARGIN_LEFT[0], 0);
                lpRLSelected.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lpRLSelected.setMargins(0, 0, WIND_SELECTED_MARGIN_LEFT[0], TEXT_MARGIN_BOTTOM);
            } else {
                lpBTNSelected.setMargins(WIND_SELECTED_MARGIN_LEFT[code], 0, 0, 0);
                lpRLSelected.setMargins(WIND_SELECTED_MARGIN_LEFT[code], 0, 0, TEXT_MARGIN_BOTTOM);
            }
            windBTN[code].setLayoutParams(lpBTNSelected);
            windBTN[code].setBackgroundResource(R.drawable.circular_selected);
            windRL[code].setLayoutParams(lpRLSelected);
            windTV[code].setTextColor(getResources().getColor(R.color.colorOn));
            // no selected
            RelativeLayout.LayoutParams lpBTNNoSelect = new RelativeLayout.LayoutParams(WIND_NO_SELECT_WIDTH, WIND_NO_SELECT_WIDTH);
            lpBTNNoSelect.addRule(RelativeLayout.CENTER_VERTICAL);
            RelativeLayout.LayoutParams lpRLNoSelect = new RelativeLayout.LayoutParams(WIND_NO_SELECT_WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpRLNoSelect.addRule(RelativeLayout.ABOVE, R.id.lgw_line);
            if (wind_code == 4) {
                lpBTNNoSelect.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lpBTNNoSelect.setMargins(0, 0, WIND_NO_SELECT_MARGIN_LEFT[0], 0);
                lpRLNoSelect.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lpRLNoSelect.setMargins(0, 0, WIND_NO_SELECT_MARGIN_LEFT[0], TEXT_MARGIN_BOTTOM);
            } else {
                lpBTNNoSelect.setMargins(WIND_NO_SELECT_MARGIN_LEFT[wind_code], 0, 0, 0);
                lpRLNoSelect.setMargins(WIND_NO_SELECT_MARGIN_LEFT[wind_code], 0, 0, TEXT_MARGIN_BOTTOM);
            }
            windBTN[wind_code].setLayoutParams(lpBTNNoSelect);
            windBTN[wind_code].setBackgroundResource(R.drawable.circular_no_select);
            windRL[wind_code].setLayoutParams(lpRLNoSelect);
            windTV[wind_code].setTextColor(getResources().getColor(R.color.colorWhite30));

            wind_code = code;
        }
    }

    @Override
    public void modeSelect(int code) {
        if (code != tem_code) {
            temIVs[code].setImageResource(TEM_MODE_SELECTED[code]);
            temTVs[code].setTextColor(getResources().getColor(R.color.colorOn));
            temIVs[tem_code].setImageResource(TEM_MODE_NO_SELECT[tem_code]);
            temTVs[tem_code].setTextColor(getResources().getColor(R.color.colorOff));
            tem_code = code;
        }
    }
}
