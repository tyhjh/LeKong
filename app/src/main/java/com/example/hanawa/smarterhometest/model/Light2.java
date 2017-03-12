package com.example.hanawa.smarterhometest.model;

import java.io.Serializable;

//调光灯
public class Light2 implements Serializable {
    private String name;
    private String control;
    private String status;
    private String control_light;
    private String status_light;
    private int type;
    private boolean state;

    public Light2(String name,String control, String status, String control_light, String status_light) {
        this.name=name;
        this.control = control;
        this.status = status;
        this.control_light = control_light;
        this.status_light = status_light;
        type=1;
        state=false;
    }

    public Light2(String name,String control, String status) {
        this.control = control;
        this.status = status;
        this.name=name;
        type=0;
        state=false;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getControl_light() {
        return control_light;
    }

    public void setControl_light(String control_light) {
        this.control_light = control_light;
    }

    public String getStatus_light() {
        return status_light;
    }

    public void setStatus_light(String status_light) {
        this.status_light = status_light;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
