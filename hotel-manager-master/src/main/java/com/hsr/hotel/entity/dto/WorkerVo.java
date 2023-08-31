package com.hsr.hotel.entity.dto;

import com.hsr.hotel.entity.Worker;


public class WorkerVo extends Worker {


    private String departName;


    public WorkerVo(String departName) {
        this.departName = departName;
    }

    public WorkerVo(String username, String password, String name, String gender, String phone, String email, String address, int department, String departName) {
        super(username, password, name, gender, phone, email, address, department);
        this.departName = departName;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }


}
