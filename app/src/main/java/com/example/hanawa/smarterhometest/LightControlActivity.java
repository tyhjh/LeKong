package com.example.hanawa.smarterhometest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hanawa.smarterhometest.adpter.LightAdapter;
import com.example.hanawa.smarterhometest.model.Light1;
import com.example.hanawa.smarterhometest.model.Light2;
import com.example.hanawa.smarterhometest.model.Room;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class LightControlActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout toolbar;
    LinearLayout back;
    TextView backTV, title;
    Room room;
    RecyclerView recyclerView;
    LightAdapter adapter;
    private List<Light2> light2s;

    MsgBoradCastReceiver msgBoradCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_control);
        room = (Room) getIntent().getSerializableExtra("room_light");
        signBroadCast();
        init();
        initData();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        toolbar = (RelativeLayout) findViewById(R.id.lc_toolbar);
        back = (LinearLayout) toolbar.findViewById(R.id.toolbar_back);
        back.setOnClickListener(this);
        back.setVisibility(View.VISIBLE);
        backTV = (TextView) toolbar.findViewById(R.id.toolbar_tv_back);
        title = (TextView) toolbar.findViewById(R.id.toolbar_title);

        backTV.setText(room.getName());
        title.setText(R.string.light_control);

        light2s = new ArrayList<Light2>();
        adapter = new LightAdapter(light2s, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void initData() {
        List<Light1> light1s = room.getLight1s();
        for (int i = 0; i < light1s.size(); i++) {
            light2s.add(new Light2(light1s.get(i).getName(), light1s.get(i).getControl_address(), light1s.get(i).getStatus_address()));
        }
        light2s.addAll(room.getLight2s());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                this.finish();
                break;
        }
    }

    //广播
    class MsgBoradCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String messge = (String) intent.getSerializableExtra("light");
            Log.e(TAG, "" + messge);
            boolean state;
            String address;

            address = messge.substring(4, 5) + "/" + messge.substring(5, 6) + "/";
            if (messge.substring(6, 7).equals("0")) {
                address = address + messge.substring(7, 8);
            } else {
                address = address + messge.substring(6, 8);
            }
            if (messge.substring(10, 12).equals("00")) {
                state = false;
            } else {
                state = true;
            }

            for(int i=0;i<light2s.size();i++){
                if(light2s.get(i).getControl().equals(address)){
                    light2s.get(i).setState(state);
                    adapter.notifyItemChanged(i);
                    break;
                }
            }

        }
    }

    //注册广播
    private void signBroadCast() {
        msgBoradCastReceiver=new MsgBoradCastReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("boradcast.action.GETMESSAGE");
        intentFilter.setPriority(1000);
        registerReceiver(msgBoradCastReceiver,intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(msgBoradCastReceiver);
    }
}
