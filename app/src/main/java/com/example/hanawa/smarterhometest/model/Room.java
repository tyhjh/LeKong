package com.example.hanawa.smarterhometest.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tyhj on 2017/3/11.
 */

//房间
public class Room implements Serializable{
    private int type;
    private String name;
    private String image;
    private List<Light1> light1s;
    private List<Light2> light2s;
    private List<Window> windows;
    private List<AirCondition> airConditions;
    private List<NewWind> newWinds;
    List<Geotherm> geotherms;

    public Room(String name, String image, List<Light1> light1s, List<Light2> light2s, List<Window> windows, List<AirCondition> airConditions, List<NewWind> newWinds, List<Geotherm> geotherms) {
        this.name = name;
        this.image = image;
        this.light1s = light1s;
        this.light2s = light2s;
        this.windows = windows;
        this.airConditions = airConditions;
        this.newWinds = newWinds;
        this.geotherms = geotherms;
        type=1;
    }

    public Room(String name, String image) {
        this.name = name;
        this.image = image;
        type=1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public List<Light1> getLight1s() {
        return light1s;
    }

    public void setLight1s(List<Light1> light1s) {
        this.light1s = light1s;
    }

    public List<Light2> getLight2s() {
        return light2s;
    }

    public void setLight2s(List<Light2> light2s) {
        this.light2s = light2s;
    }

    public List<Window> getWindows() {
        return windows;
    }

    public void setWindows(List<Window> windows) {
        this.windows = windows;
    }

    public List<AirCondition> getAirConditions() {
        return airConditions;
    }

    public void setAirConditions(List<AirCondition> airConditions) {
        this.airConditions = airConditions;
    }

    public List<NewWind> getNewWinds() {
        return newWinds;
    }

    public void setNewWinds(List<NewWind> newWinds) {
        this.newWinds = newWinds;
    }

    public List<Geotherm> getGeotherms() {
        return geotherms;
    }

    public void setGeotherms(List<Geotherm> geotherms) {
        this.geotherms = geotherms;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

