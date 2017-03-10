package com.example.hanawa.smarterhometest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.hanawa.smarterhometest.view.HomeView;

public class HomeActivity extends AppCompatActivity implements HomeView, View.OnClickListener {

    private RelativeLayout livingRoom, dinningRoom, mainRoom, studyRoom, childrenRoom, videoRoom;
    private ImageView iv_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

    }

    private void init() {
        iv_set= (ImageView) findViewById(R.id.iv_set);
        iv_set.setOnClickListener(this);
        livingRoom = (RelativeLayout) findViewById(R.id.home_rl_living);
        livingRoom.setOnClickListener(this);
        dinningRoom = (RelativeLayout) findViewById(R.id.home_rl_dining);
        dinningRoom.setOnClickListener(this);
        mainRoom = (RelativeLayout) findViewById(R.id.home_rl_main);
        mainRoom.setOnClickListener(this);
        studyRoom = (RelativeLayout) findViewById(R.id.home_rl_study);
        studyRoom.setOnClickListener(this);
        childrenRoom = (RelativeLayout) findViewById(R.id.home_rl_children);
        childrenRoom.setOnClickListener(this);
        videoRoom = (RelativeLayout) findViewById(R.id.home_rl_video);
        videoRoom.setOnClickListener(this);
    }


    @Override
    public void jumpAcitivty(int code) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_rl_living:
                startActivity(new Intent(this, LivingRoomActivity.class));
                break;
            case R.id.home_rl_dining:
                break;
            case R.id.home_rl_main:

                break;
            case R.id.home_rl_study:

                break;
            case R.id.home_rl_children:

                break;
            case R.id.home_rl_video:

                break;
            case R.id.iv_set:
                startActivity(new Intent(HomeActivity.this,SignIn.class));
                break;
        }
    }
}
