package com.example.hanawa.smarterhometest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanawa.smarterhometest.model.Room;

public class LivingRoomActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout modeSelect, lightControl, temControl, scenesControl;
    RelativeLayout toolbar;
    LinearLayout back;
    TextView backTV, title;
    Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room);
        room= (Room) getIntent().getSerializableExtra("room");
        init();
        initData();
    }

    private void init() {
        modeSelect = (LinearLayout) findViewById(R.id.living_room_ll_mode_select);
        lightControl = (LinearLayout) findViewById(R.id.living_room_ll_light_control);
        temControl = (LinearLayout) findViewById(R.id.living_room_ll_tem_control);
        scenesControl = (LinearLayout) findViewById(R.id.living_room_ll_scenes_control);
        toolbar = (RelativeLayout) findViewById(R.id.living_room_toolbar);
        back = (LinearLayout) toolbar.findViewById(R.id.toolbar_back);
        backTV = (TextView) toolbar.findViewById(R.id.toolbar_tv_back);
        title = (TextView) toolbar.findViewById(R.id.toolbar_title);

        back.setOnClickListener(this);
        modeSelect.setOnClickListener(this);
        lightControl.setOnClickListener(this);
        temControl.setOnClickListener(this);
        scenesControl.setOnClickListener(this);
    }

    private void initData() {
        back.setVisibility(View.VISIBLE);
        backTV.setText(R.string.home);
        title.setText(room.getName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.living_room_ll_mode_select:
                startActivity(new Intent(this, ModeSelectActivity.class));
                break;
            case R.id.living_room_ll_light_control:
                Bundle bundle=new Bundle();
                bundle.putSerializable("room_light",room);
                Intent intent=new Intent(this, LightControlActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.living_room_ll_tem_control:
                startActivity(new Intent(this, TemControlActivity.class));
                break;
            case R.id.living_room_ll_scenes_control:
                startActivity(new Intent(this, ScenesControlActivity.class));
                break;
            case R.id.toolbar_back:
                this.finish();
                break;
        }
    }
}
