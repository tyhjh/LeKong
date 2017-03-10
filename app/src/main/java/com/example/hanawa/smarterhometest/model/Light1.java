package com.example.hanawa.smarterhometest.model;

//灯
public class Light1{
    private String name;
    private String control_address;
    private String status_address;

    public Light1(String name, String control_address, String status_address) {
        this.name = name;
        this.control_address = control_address;
        this.status_address = status_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getControl_address() {
        return control_address;
    }

    public void setControl_address(String control_address) {
        this.control_address = control_address;
    }

    public String getStatus_address() {
        return status_address;
    }

    public void setStatus_address(String status_address) {
        this.status_address = status_address;
    }
}
