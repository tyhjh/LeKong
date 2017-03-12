package com.example.hanawa.smarterhometest.model;

import android.util.Log;

import com.example.hanawa.smarterhometest.listener.SendBordCast;
import com.example.hanawa.smarterhometest.utils.ByteStringUtil;

import java.io.IOException;
import java.net.Socket;



public class Connect {

    private SendBordCast bordCast;

    private volatile static Connect connect; //声明成 volatile


    private static String ip;

    private static int port;

    private static Socket client = null;


    private Connect(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
            Log.e("Connect","尝试建立连接");
            client = new Socket(ip, port);
            client.setSoTimeout(3000);
    }

    public boolean sendMsg(String str){
            try {
                if(client.isClosed()) {
                    return false;
                }
                client.getOutputStream().write(ByteStringUtil.hexStrToByteArray(str));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
    }



    public boolean setLisener(SendBordCast cast) throws IOException {

        this.bordCast=cast;

        if(!isConnect())
            return false;
        while (client!=null&&!client.isClosed()) {
            byte[] bytes = new byte[0];
                bytes = new byte[client.getInputStream().available()];
                client.getInputStream().read(bytes);
                if (bytes.length != 0) {
                    bordCast.sendBordcast(ByteStringUtil.byteArrayToHexStr(bytes));
                    //Log.e("Mainactivity", ByteStringUtil.byteArrayToHexStr(bytes));
                }
        }
        return true;
    }

    public void setBordCast(SendBordCast bordCast) {
        this.bordCast = bordCast;
    }

    public static boolean isConnect(){
        if (client==null){
            return false;
        }
        if(client.isClosed())
            return false;

        if(client.isConnected())
            return true;
        return false;
    }


    public static Connect getSingleton() throws IOException {
        if (connect == null||client.isClosed()) {
            synchronized (Connect.class) {
                if (connect == null||client.isClosed()) {
                    if (ip == null) {
                        return null;
                    }
                    Log.e("Connect","我重连了一次");
                    connect = new Connect(ip, port);
                }
            }
        }
        return connect;
    }

    public static void setPort(String ip, int port) {
        Connect.ip = ip;
        Connect.port = port;
    }
}
