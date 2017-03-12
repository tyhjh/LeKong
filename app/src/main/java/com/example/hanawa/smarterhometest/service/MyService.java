package com.example.hanawa.smarterhometest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.example.hanawa.smarterhometest.R;
import com.example.hanawa.smarterhometest.listener.SendBordCast;
import com.example.hanawa.smarterhometest.model.Connect;


public class MyService extends Service implements SendBordCast{

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                //初始化socket并监听端口
                try {
                    if(!Connect.getSingleton().setLisener(MyService.this))
                        handler.sendEmptyMessage(0);
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(0);
                }
            }
        }).start();


        return super.onStartCommand(intent, flags, startId);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Toast.makeText(getApplicationContext(), getString(R.string.tcp_erro), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void sendBordcast(String messge) {
        Intent intent = new Intent("boradcast.action.GETMESSAGE");
        intent.putExtra("light", messge);
        sendOrderedBroadcast(intent,null);
    }
}
