package com.example.hanawa.smarterhometest.model;

import java.util.List;

/**
 * Created by Tyhj on 2017/3/11.
 */

public class Floor {
    private String name;
    private List<Room> rooms;

    public Floor(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


    @Override
    public String toString() {
        return "Floor{" +
                "name='" + name + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
