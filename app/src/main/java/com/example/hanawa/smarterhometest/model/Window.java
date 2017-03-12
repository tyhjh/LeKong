package com.example.hanawa.smarterhometest.model;

import java.io.Serializable;

//窗户
public class Window implements Serializable {
    private String name;
    private String control_open;
    private String status_open;
    private String control_close;
    private String status_close;

    public Window(String name, String control_open, String status_open, String control_close, String status_close) {
        this.name = name;
        this.control_open = control_open;
        this.status_open = status_open;
        this.control_close = control_close;
        this.status_close = status_close;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getControl_open() {
        return control_open;
    }

    public void setControl_open(String control_open) {
        this.control_open = control_open;
    }

    public String getStatus_open() {
        return status_open;
    }

    public void setStatus_open(String status_open) {
        this.status_open = status_open;
    }

    public String getControl_close() {
        return control_close;
    }

    public void setControl_close(String control_close) {
        this.control_close = control_close;
    }

    public String getStatus_close() {
        return status_close;
    }

    public void setStatus_close(String status_close) {
        this.status_close = status_close;
    }
}
