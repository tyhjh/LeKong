package com.example.hanawa.smarterhometest.model;

import java.io.Serializable;

//新风
public class NewWind implements Serializable {
    private String name;
    private String control;
    private String status;
    private String control_wind_little;
    private String status_wind_little;
    private String control_wind_middle;
    private String status_wind_middle;
    private String control_wind_high;
    private String status_wind_high;

    public NewWind(String name, String control, String status, String control_wind_little, String status_wind_little, String control_wind_middle, String status_wind_middle, String control_wind_high, String status_wind_high) {
        this.name = name;
        this.control = control;
        this.status = status;
        this.control_wind_little = control_wind_little;
        this.status_wind_little = status_wind_little;
        this.control_wind_middle = control_wind_middle;
        this.status_wind_middle = status_wind_middle;
        this.control_wind_high = control_wind_high;
        this.status_wind_high = status_wind_high;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getControl_wind_little() {
        return control_wind_little;
    }

    public void setControl_wind_little(String control_wind_little) {
        this.control_wind_little = control_wind_little;
    }

    public String getStatus_wind_little() {
        return status_wind_little;
    }

    public void setStatus_wind_little(String status_wind_little) {
        this.status_wind_little = status_wind_little;
    }

    public String getControl_wind_middle() {
        return control_wind_middle;
    }

    public void setControl_wind_middle(String control_wind_middle) {
        this.control_wind_middle = control_wind_middle;
    }

    public String getStatus_wind_middle() {
        return status_wind_middle;
    }

    public void setStatus_wind_middle(String status_wind_middle) {
        this.status_wind_middle = status_wind_middle;
    }

    public String getControl_wind_high() {
        return control_wind_high;
    }

    public void setControl_wind_high(String control_wind_high) {
        this.control_wind_high = control_wind_high;
    }

    public String getStatus_wind_high() {
        return status_wind_high;
    }

    public void setStatus_wind_high(String status_wind_high) {
        this.status_wind_high = status_wind_high;
    }
}
