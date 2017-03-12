package com.example.hanawa.smarterhometest.utils;

/**
 * Created by Tyhj on 2017/3/10.
 */

public class GlCode {

    public static String code = "7a4000010001005a";


    String star,type_write,type_read,mainAd,middleAd,sonAd,status,check,end;
    String len;


    public GlCode(String ad) {
        String[] ads=ad.split("/");
        for(int i=0;i<ads.length;i++){
            if(ads[2].length()==1){
                ads[2]="0"+ads[2];
            }
        }
        star="7a";
        type_write="40";
        type_read="41";
        mainAd=ads[0];
        middleAd=ads[1];
        sonAd=ads[2];
        check="00";
        end="5a";
    }


    //灯的开关和读取状态代码合成
    public String send_control_light_1(String status,boolean type){
        if(type)
            return star+type_write+mainAd+middleAd+sonAd+"00"+status+check+end;
        else
            return star+type_read+mainAd+middleAd+sonAd+"00"+check+end;
    }

    public String get_status(){
        return star+type_read+mainAd+middleAd+sonAd+check+end;
    }


    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getMainAd() {
        return mainAd;
    }

    public void setMainAd(String mainAd) {
        this.mainAd = mainAd;
    }

    public String getMiddleAd() {
        return middleAd;
    }

    public void setMiddleAd(String middleAd) {
        this.middleAd = middleAd;
    }

    public String getSonAd() {
        return sonAd;
    }

    public void setSonAd(String sonAd) {
        this.sonAd = sonAd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

}
