package com.example.hanawa.smarterhometest.adpter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanawa.smarterhometest.R;
import com.example.hanawa.smarterhometest.model.Connect;
import com.example.hanawa.smarterhometest.model.Light2;
import com.example.hanawa.smarterhometest.utils.GlCode;

import java.io.IOException;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Tyhj on 2017/3/11.
 */

public class LightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Light2> light2s;
    private Context context;
    private LayoutInflater inflater;

    public LightAdapter(List<Light2> light2s, Context context) {
        this.light2s = light2s;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (light2s.get(position).getType() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = inflater.inflate(R.layout.item_light1, parent, false);
            return new Light1Holder(view);
        } else {
            view = inflater.inflate(R.layout.item_light2, parent, false);
            return new Light2Holder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            Light1Holder light1Holder = (Light1Holder) holder;
            light1Holder.tv_name_light1.setText(light2s.get(holder.getPosition()).getName());
            light1Holder.switch_light1.setChecked(light2s.get(holder.getPosition()).isState());
            getStatus(holder);
            onclick1(holder, light1Holder);
        } else {
            getStatus(holder);
            Light2Holder light2Holder = (Light2Holder) holder;
            light2Holder.tv_name_light2.setText(light2s.get(holder.getPosition()).getName());
            light2Holder.sb_light2.setMax(100);
            light2Holder.sb_light2.setProgress(0);
            light2Holder.switch_light2.setChecked(light2s.get(holder.getPosition()).isState());
            oclick2(light2Holder,holder);
        }

    }

    //调光灯
    private void oclick2(Light2Holder light2Holder,final RecyclerView.ViewHolder holder) {
        //亮度
        light2Holder.sb_light2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //开关
        light2Holder.switch_light2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                if (!Connect.isConnect()) {
                    handler.sendEmptyMessage(0);
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (isChecked) {
                                Connect.getSingleton().sendMsg(new GlCode(light2s.get(holder.getPosition()).getControl()).send_control_light_1( "01",true));
                            } else {
                                Connect.getSingleton().sendMsg(new GlCode(light2s.get(holder.getPosition()).getControl()).send_control_light_1("00",true));
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            handler.sendEmptyMessage(0);
                        }
                    }
                }).start();
            }
        });
    }

    //获取状态
    private void getStatus(final RecyclerView.ViewHolder holder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(!Connect.getSingleton().sendMsg(new GlCode(light2s.get(holder.getPosition()).getControl()).send_control_light_1( "01",false))){
                        handler.sendEmptyMessage(0);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //开关灯
    private void onclick1(final RecyclerView.ViewHolder holder, Light1Holder light1Holder) {
        light1Holder.switch_light1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                    if (!Connect.isConnect()) {
                        handler.sendEmptyMessage(0);
                        return;
                    }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (isChecked) {
                                Connect.getSingleton().sendMsg(new GlCode(light2s.get(holder.getPosition()).getControl()).send_control_light_1( "01",true));
                            } else {
                                Connect.getSingleton().sendMsg(new GlCode(light2s.get(holder.getPosition()).getControl()).send_control_light_1("00",true));
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            handler.sendEmptyMessage(0);
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return light2s.size();
    }

    class Light1Holder extends RecyclerView.ViewHolder {
        TextView tv_name_light1;
        SwitchCompat switch_light1;

        public Light1Holder(View itemView) {
            super(itemView);
            tv_name_light1 = (TextView) itemView.findViewById(R.id.tv_name_light1);
            switch_light1 = (SwitchCompat) itemView.findViewById(R.id.switch_light1);
        }
    }

    class Light2Holder extends RecyclerView.ViewHolder {
        TextView tv_name_light2;
        SwitchCompat switch_light2;
        SeekBar sb_light2;

        public Light2Holder(View itemView) {
            super(itemView);
            tv_name_light2 = (TextView) itemView.findViewById(R.id.tv_name_light2);
            switch_light2 = (SwitchCompat) itemView.findViewById(R.id.switch_light2);
            sb_light2 = (SeekBar) itemView.findViewById(R.id.sb_light2);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(context, context.getString(R.string.tcp_erro), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
