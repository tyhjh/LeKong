package com.example.hanawa.smarterhometest.model;

import java.io.Serializable;

//空调
public class AirCondition implements Serializable {
    private String name;
    private String control;
    private String status;
    private String control_temp;
    private String status_temp;
    private String control_hot;
    private String status_hot;
    private String control_cold;
    private String status_cold;
    private String control_wind;
    private String status_wind;
    private String control_wet;
    private String status_wet;
    private String control_wind_little;
    private String status_wind_little;
    private String control_wind_middle;
    private String status_wind_middle;
    private String control_wind_high;
    private String status_wind_high;

    public AirCondition(String name, String control, String status, String control_temp, String status_temp, String control_hot, String status_hot, String control_cold, String status_cold, String control_wind, String status_wind, String control_wet, String status_wet, String control_wind_little, String status_wind_little, String control_wind_middle, String status_wind_middle, String control_wind_high, String status_wind_high) {
        this.name = name;
        this.control = control;
        this.status = status;
        this.control_temp = control_temp;
        this.status_temp = status_temp;
        this.control_hot = control_hot;
        this.status_hot = status_hot;
        this.control_cold = control_cold;
        this.status_cold = status_cold;
        this.control_wind = control_wind;
        this.status_wind = status_wind;
        this.control_wet = control_wet;
        this.status_wet = status_wet;
        this.control_wind_little = control_wind_little;
        this.status_wind_little = status_wind_little;
        this.control_wind_middle = control_wind_middle;
        this.status_wind_middle = status_wind_middle;
        this.control_wind_high = control_wind_high;
        this.status_wind_high = status_wind_high;
    }

    public String getControl_temp() {
        return control_temp;
    }

    public void setControl_temp(String control_temp) {
        this.control_temp = control_temp;
    }

    public String getStatus_temp() {
        return status_temp;
    }

    public void setStatus_temp(String status_temp) {
        this.status_temp = status_temp;
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

    public String getControl_hot() {
        return control_hot;
    }

    public void setControl_hot(String control_hot) {
        this.control_hot = control_hot;
    }

    public String getStatus_hot() {
        return status_hot;
    }

    public void setStatus_hot(String status_hot) {
        this.status_hot = status_hot;
    }

    public String getControl_cold() {
        return control_cold;
    }

    public void setControl_cold(String control_cold) {
        this.control_cold = control_cold;
    }

    public String getStatus_cold() {
        return status_cold;
    }

    public void setStatus_cold(String status_cold) {
        this.status_cold = status_cold;
    }

    public String getControl_wind() {
        return control_wind;
    }

    public void setControl_wind(String control_wind) {
        this.control_wind = control_wind;
    }

    public String getStatus_wind() {
        return status_wind;
    }

    public void setStatus_wind(String status_wind) {
        this.status_wind = status_wind;
    }

    public String getControl_wet() {
        return control_wet;
    }

    public void setControl_wet(String control_wet) {
        this.control_wet = control_wet;
    }

    public String getStatus_wet() {
        return status_wet;
    }

    public void setStatus_wet(String status_wet) {
        this.status_wet = status_wet;
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
