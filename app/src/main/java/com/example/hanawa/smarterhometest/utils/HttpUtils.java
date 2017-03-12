package com.example.hanawa.smarterhometest.utils;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hanawa.smarterhometest.R;
import com.example.hanawa.smarterhometest.listener.update;
import com.example.hanawa.smarterhometest.model.AirCondition;
import com.example.hanawa.smarterhometest.model.Connect;
import com.example.hanawa.smarterhometest.model.Floor;
import com.example.hanawa.smarterhometest.model.Geotherm;
import com.example.hanawa.smarterhometest.model.Light1;
import com.example.hanawa.smarterhometest.model.Light2;
import com.example.hanawa.smarterhometest.model.NewWind;
import com.example.hanawa.smarterhometest.model.Room;
import com.example.hanawa.smarterhometest.model.Window;
import com.example.hanawa.smarterhometest.service.MyService;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Tyhj on 2017/3/11.
 */

public class HttpUtils {

    private update update;

    private Context context;
    private View et_number;
    private String TAG;

    public HttpUtils(Context context, View et_number, String TAG) {
        this.context = context;
        this.et_number = et_number;
        this.TAG = TAG;
    }

    //登录
    public void signIn(final String email, final String password) {
        if (email.equals("") || password.equals("")) {
            return;
        }
        OkHttpUtils
                .post()
                .url(context.getString(R.string.sign_in))
                .addParams("email", email)
                .addParams("password", password)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        String msg = response.body().string();
                        //Log.e(TAG+"signIn",msg);
                        GetJson.setAccessToken(response.header("access-token").toString());
                        GetJson.setClient(response.header("client").toString());
                        GetJson.setTokenType(response.header("token-type").toString());
                        GetJson.setUid(response.header("uid").toString());
                        GetJson.savaPwd(email, password, context);
                        getToken();
                        Snackbar.make(et_number, "登录成功", Snackbar.LENGTH_SHORT).show();
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Snackbar.make(et_number, "登录失败", Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        return;
                    }
                });
    }

    //验证并获取user_id
    private void getToken() {
        OkHttpUtils
                .get()//
                .url(context.getString(R.string.token))//
                .addParams("access-token", GetJson.getAccessToken())
                .addParams("client", GetJson.getClient())
                .addParams("uid", GetJson.getUid())
                .addParams("token-type", GetJson.getTokenType())
                .build()//
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        String msg = response.body().string();
                        //Log.e(TAG+"getToken",msg);
                        GetJson.setUser_id(new JSONObject(msg).getJSONObject("data").getString("id"));
                        getBuilds();
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG + "getToken", e.toString());
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        return;
                    }
                });
    }

    //获取项目信息
    private void getBuilds() {
        OkHttpUtils
                .get()//
                .url(context.getString(R.string.builds))//
                .addParams("user_id", GetJson.getUser_id())
                .addHeader("access-token", GetJson.getAccessToken())
                .addHeader("client", GetJson.getClient())
                .addHeader("uid", GetJson.getUid())
                .addParams("token-type", GetJson.getTokenType())
                .build()//
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        String msg = response.body().string();
                        JSONArray jsonArry = new JSONArray(msg);
                        JSONObject json = jsonArry.getJSONObject(0);
                        //Log.e(TAG+"getBuilds",msg);
                        GetJson.setBuild_id(json.getString("id"));
                        GetJson.setSocket_address(json.getString("socket_address"));
                        GetJson.setSocket_port(json.getInt("socket_port"));
                        getBuildDetail();
                        Connect.setPort(GetJson.getSocket_address(),GetJson.getSocket_port());
                        //Connect.setPort("192.168.31.215",4196);
                        context.startService(new Intent(context, MyService.class));
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG + "getBuilds", e.toString());
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        return;
                    }
                });
    }

    //获取详情
    private void getBuildDetail() {
        OkHttpUtils
                .get()//
                .url(context.getString(R.string.building_detail))//
                .addParams("building_id", GetJson.getBuild_id())
                .addHeader("access-token", GetJson.getAccessToken())
                .addHeader("client", GetJson.getClient())
                .addHeader("uid", GetJson.getUid())
                .addParams("token-type", GetJson.getTokenType())
                .build()//
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        String msg = response.body().string();
                        Log.e(TAG,"getBuildDetail");
                        getMsg(msg);
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(Object response, int id) {

                    }
                });
    }

    //解析这个json
    private void getMsg(String msg) {
        try {
            JSONObject j1 = new JSONObject(msg);
            GetJson.setSocket_address(j1.getString("socket_address"));
            GetJson.setSocket_port(j1.getInt("socket_port"));
            GetJson.setModelName(j1.getString("name"));

            List<Floor> floors = new ArrayList<Floor>();
            JSONArray arry1 = j1.getJSONArray("floors");
            //每个楼层
            for (int i = 0; i < arry1.length(); i++) {
                JSONObject j2 = arry1.getJSONObject(i);
                Floor floor = new Floor(j2.getString("name"), null);
                JSONArray arry2 = j2.getJSONArray("areas");
                List<Room> rooms = new ArrayList<Room>();
                //每个房间
                for (int j = 0; j < arry2.length(); j++) {
                    JSONObject j3 = arry2.getJSONObject(j);
                    Room room = new Room(j3.getString("name"), j3.getString("image_name"));
                    JSONArray arry3 = j3.getJSONArray("devices");
                    List<Light1> light1s = new ArrayList<Light1>();
                    List<Light2> light2s = new ArrayList<Light2>();
                    List<Window> windows = new ArrayList<Window>();
                    List<AirCondition> airConditions = new ArrayList<AirCondition>();
                    List<NewWind> newWinds = new ArrayList<NewWind>();
                    List<Geotherm> geotherms = new ArrayList<Geotherm>();
                    //每个设备
                    for (int k = 0; k < arry3.length(); k++) {

                        JSONObject j4 = arry3.getJSONObject(k);
                        JSONObject j5 = j4.getJSONArray("cams").getJSONObject(0);
                        switch (j4.getInt("i_type")) {
                            case 1:
                                light1s.add(new Light1(j4.getString("name"), j5.getString("control_address"), j5.getString("status_address")));
                                break;
                            case 2:
                                JSONObject j6 = j4.getJSONArray("cams").getJSONObject(1);
                                light2s.add(new Light2(j4.getString("name"), j5.getString("control_address"),
                                        j5.getString("status_address"), j6.getString("control_address"),
                                        j6.getString("status_address")));
                                break;
                            case 3:
                                j6 = j4.getJSONArray("cams").getJSONObject(1);
                                windows.add(new Window(j4.getString("name"), j5.getString("control_address"),
                                        j5.getString("status_address"), j6.getString("control_address"),
                                        j6.getString("status_address")));
                                break;
                            case 4:
                                j6 = j4.getJSONArray("cams").getJSONObject(1);
                                JSONObject j7 = j4.getJSONArray("cams").getJSONObject(2);
                                JSONObject j8 = j4.getJSONArray("cams").getJSONObject(3);
                                JSONObject j9 = j4.getJSONArray("cams").getJSONObject(4);
                                JSONObject j10 = j4.getJSONArray("cams").getJSONObject(5);
                                JSONObject j11 = j4.getJSONArray("cams").getJSONObject(6);
                                JSONObject j12 = j4.getJSONArray("cams").getJSONObject(7);
                                JSONObject j13 = j4.getJSONArray("cams").getJSONObject(8);

                                airConditions.add(new AirCondition(j4.getString("name"),
                                        j5.getString("control_address"), j5.getString("status_address"),
                                        j6.getString("control_address"), j6.getString("status_address"),
                                        j7.getString("control_address"), j7.getString("status_address"),
                                        j8.getString("control_address"), j8.getString("status_address"),
                                        j9.getString("control_address"), j9.getString("status_address"),
                                        j10.getString("control_address"), j10.getString("status_address"),
                                        j11.getString("control_address"), j11.getString("status_address"),
                                        j12.getString("control_address"), j12.getString("status_address"),
                                        j13.getString("control_address"), j13.getString("status_address")));
                                break;
                            case 5:
                                j6 = j4.getJSONArray("cams").getJSONObject(1);
                                geotherms.add(new Geotherm(j4.getString("name"), j5.getString("control_address"),
                                        j5.getString("status_address"), j6.getString("control_address"),
                                        j6.getString("status_address")));
                                break;
                            case 6:
                                j6 = j4.getJSONArray("cams").getJSONObject(1);
                                j7 = j4.getJSONArray("cams").getJSONObject(2);
                                j8 = j4.getJSONArray("cams").getJSONObject(3);
                                newWinds.add(new NewWind(j4.getString("name"),
                                        j5.getString("control_address"), j5.getString("status_address"),
                                        j6.getString("control_address"), j6.getString("status_address"),
                                        j7.getString("control_address"), j7.getString("status_address"),
                                        j8.getString("control_address"), j8.getString("status_address")));
                                break;
                            default:
                                break;
                        }
                    }
                    room.setLight1s(light1s);
                    room.setLight2s(light2s);
                    room.setWindows(windows);
                    room.setAirConditions(airConditions);
                    room.setNewWinds(newWinds);
                    room.setGeotherms(geotherms);
                    rooms.add(room);
                }
                floor.setRooms(rooms);
                floors.add(floor);
            }
            GetJson.setFloors(floors);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e(TAG,"getMsg");
        update.updateView(setDate());
    }

    //更新界面
    public static List<Room> setDate() {
        List<Room> rooms=new ArrayList<Room>();
        if (GetJson.getFloors() != null) {
            for (int i = 0; i < GetJson.getFloors().size(); i++) {
                Room room = new Room(GetJson.getFloors().get(i).getName(), null);
                room.setType(0);
                rooms.add(room);
                rooms.addAll(GetJson.getFloors().get(i).getRooms());
            }
        }
        return rooms;
    }

    public com.example.hanawa.smarterhometest.listener.update getUpdate() {
        return update;
    }

    public void setUpdate(com.example.hanawa.smarterhometest.listener.update update) {
        this.update = update;
    }
}
