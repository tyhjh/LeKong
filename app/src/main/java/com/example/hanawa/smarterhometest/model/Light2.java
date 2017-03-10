package com.example.hanawa.smarterhometest.model;

//调光灯
public class Light2{
    private String room;
    private String control;
    private String status;
    private String control_light;
    private String status_light;

    public Light2(String room, String control, String status, String control_light, String status_light) {
        this.room = room;
        this.control = control;
        this.status = status;
        this.control_light = control_light;
        this.status_light = status_light;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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
}
