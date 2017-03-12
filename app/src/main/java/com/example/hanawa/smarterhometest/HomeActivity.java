package com.example.hanawa.smarterhometest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hanawa.smarterhometest.adpter.RoomAdpter;
import com.example.hanawa.smarterhometest.listener.update;
import com.example.hanawa.smarterhometest.model.Room;
import com.example.hanawa.smarterhometest.utils.GetJson;
import com.example.hanawa.smarterhometest.utils.HttpUtils;
import com.example.hanawa.smarterhometest.view.HomeView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeView, View.OnClickListener,update {

    private ImageView iv_set;

    private RecyclerView recyclerView;

    private RoomAdpter adpter;

    private SwipeRefreshLayout refreshLayout;

    List<Room> rooms;
    String name;
    String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sh = getSharedPreferences("data", MODE_PRIVATE);
        name = sh.getString("name", null);
        pwd = sh.getString("pwd", null);
        init();
        if (name != null && pwd != null) {
            HttpUtils httpUtils=new HttpUtils(HomeActivity.this, iv_set, "HomeActivity");
            httpUtils.setUpdate(this);
            httpUtils.signIn(name, pwd);
        }
    }


    private void init() {
        rooms = new ArrayList<Room>();
        refreshLayout= (SwipeRefreshLayout) findViewById(R.id.refresh);
        iv_set = (ImageView) findViewById(R.id.iv_set);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        iv_set.setOnClickListener(this);
        adpter=new RoomAdpter(rooms,this);
        recyclerView.setAdapter(adpter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //刷新
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_dark,
                android.R.color.holo_green_light);// 设置刷新动画的颜色
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    refreshLayout.setRefreshing(true);// 开始刷新
                    // 执行刷新后需要完成的操作
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (name != null) {
                                HttpUtils httpUtils=new HttpUtils(HomeActivity.this, iv_set, "HomeActivity");
                                httpUtils.setUpdate(HomeActivity.this);
                                httpUtils.signIn(name, pwd);
                            }
                        }
                    }).start();
            }
        });
    }


    @Override
    public void jumpAcitivty(int code) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_set:
                startActivity(new Intent(HomeActivity.this, SignIn.class));
                break;
        }
    }

    @Override
    public void updateView(List<Room> listrooms) {
        rooms.clear();
        rooms.addAll(listrooms);
        handler.sendEmptyMessage(1);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.rooms.clear();
        rooms.addAll(HttpUtils.setDate());
        handler.sendEmptyMessage(1);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    adpter.notifyDataSetChanged();
                    refreshLayout.setRefreshing(false);
                    break;
            }
        }
    };

}
