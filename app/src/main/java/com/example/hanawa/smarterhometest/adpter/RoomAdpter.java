package com.example.hanawa.smarterhometest.adpter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hanawa.smarterhometest.LivingRoomActivity;
import com.example.hanawa.smarterhometest.R;
import com.example.hanawa.smarterhometest.model.Room;
import com.example.hanawa.smarterhometest.utils.GetJson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Tyhj on 2017/3/11.
 */

public class RoomAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Room> rooms;
    private Context context;
    private LayoutInflater inflater;

    public RoomAdpter(List<Room> rooms, Context context) {
        this.rooms = rooms;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public int getItemViewType(int position) {
        if(rooms.get(position).getType()==0){
            return 0;
        }
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType==0){
            view=inflater.inflate(R.layout.item_floor,parent,false);
            return new FloorHolder(view);
        }else {
            view=inflater.inflate(R.layout.item_room,parent,false);
            return new RoomHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if(holder.getItemViewType()==0){
            FloorHolder floorHolder= (FloorHolder) holder;
            floorHolder.tv_floor_name.setText(rooms.get(holder.getPosition()).getName());
        }else {
            RoomHolder roomHolder= (RoomHolder) holder;
            roomHolder.tv_room_name.setText(rooms.get(holder.getPosition()).getName());
            Picasso.with(context).load(GetJson.getImage(rooms.get(holder.getPosition()).getImage())).into(roomHolder.home_rl_iv_living);
            roomHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newActivity(holder);
                }
            });

        }
    }

    private void newActivity(RecyclerView.ViewHolder holder) {
        Intent intent=new Intent(context, LivingRoomActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("room",rooms.get(holder.getPosition()));
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }



    class RoomHolder extends RecyclerView.ViewHolder{
        ImageView home_rl_iv_living;
        TextView tv_room_name;
        public RoomHolder(View itemView) {
            super(itemView);
            home_rl_iv_living= (ImageView) itemView.findViewById(R.id.home_rl_iv_living);
            tv_room_name= (TextView) itemView.findViewById(R.id.tv_room_name);
        }
    }


    class FloorHolder extends RecyclerView.ViewHolder{
        TextView tv_floor_name;
        public FloorHolder(View itemView) {
            super(itemView);
            tv_floor_name= (TextView) itemView.findViewById(R.id.tv_floor_name);
        }
    }
}
