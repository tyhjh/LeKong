package com.example.hanawa.smarterhometest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hanawa.smarterhometest.R;
import com.example.hanawa.smarterhometest.model.Floor;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tyhj on 2017/3/10.
 */

public class GetJson {

    private static String accessToken;
    private static String client;
    private static String uid;
    private static String tokenType;

    private static String user_id;

    private static String build_id;

    private static String socket_address;

    private static int socket_port;

    private static String ModelName;

    private static List<Floor> floors;

    public static List<Floor> getFloors() {
        return floors;
    }

    public static void setFloors(List<Floor> floors) {
        GetJson.floors = floors;
    }

    public static String getModelName() {
        return ModelName;
    }

    public static void setModelName(String modelName) {
        ModelName = modelName;
    }

    public static String getSocket_address() {
        return socket_address;
    }

    public static void setSocket_address(String socket_address) {
        GetJson.socket_address = socket_address;
    }

    public static int getSocket_port() {
        return socket_port;
    }

    public static void setSocket_port(int socket_port) {
        GetJson.socket_port = socket_port;
    }

    private static Map<String,String> map=new HashMap<String, String>();

    public static Map<String, String> getMap() {
        return map;
    }

    public static void setMap(Map<String, String> map) {
        GetJson.map = map;
    }

    public static void setHeads(){
        map.put("accessToken",accessToken);
        map.put("client",client);
        map.put("uid",uid);
        map.put("tokenType",tokenType);
    }

    public static String getBuild_id() {
        return build_id;
    }

    public static void setBuild_id(String build_id) {
        GetJson.build_id = build_id;
    }

    public static String getUser_id() {
        return user_id;
    }

    public static void setUser_id(String user_id) {
        GetJson.user_id = user_id;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        GetJson.accessToken = accessToken;
    }

    public static String getClient() {
        return client;
    }

    public static void setClient(String client) {
        GetJson.client = client;
    }

    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        GetJson.uid = uid;
    }

    public static String getTokenType() {
        return tokenType;
    }

    public static void setTokenType(String tokenType) {
        GetJson.tokenType = tokenType;
    }

    public static void savaPwd(String name, String pwd, Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name",name);
        editor.putString("pwd",pwd);
        editor.commit();
    }

    public static int getImage(String name){
        //设置头像
        switch (name){
            case "chess-cards":
                return R.mipmap.chess_cards_room;
            case "study":
                return R.mipmap.study_room;
            case "hallway":
                return R.mipmap.hallway_room;
            case "children":
                return R.mipmap.children_room;
            case "dining":
                return R.mipmap.dining_room;
            case "bar-counter":
                return R.mipmap.bar_counter_room;
            case "conference":
                return R.mipmap.conference_room;
            case "guest-rest":
                return R.mipmap.guest_rest_room;
            case "kitchen":
                return R.mipmap.kitchen_room;
            case "main-bath":
                return R.mipmap.main_bath_room;
            case "main-bde":
                return R.mipmap.main_bed_room;
            case "old":
                return R.mipmap.old_room;
            case "reading":
                return R.mipmap.reading_room;
            case "the-porch":
                return R.mipmap.the_porch_room;
            case "winter-garden":
                return R.mipmap.wind_bigger;
            case "other_room":
            default:
                return R.mipmap.other_room;
        }
    }

}
