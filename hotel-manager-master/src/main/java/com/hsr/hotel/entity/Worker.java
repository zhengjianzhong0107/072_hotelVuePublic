package com.hsr.hotel.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Worker {
    private Integer workerId;

    @Excel(name="角色")
    private String role;
    @Excel(name="用户名",width = 12)
    private String username;

    @Excel(name="密码",width = 37)
    private String password;

    @Excel(name="姓名",width = 10)
    private String name;

    @Excel(name="性别")
    private String gender;

    @Excel(name="电话号码",width = 16)
    private String phone;

//    @Excel(name="部门")
    private Integer department;

    @Excel(name="部门")
    private String deptName;

    @Excel(name="邮箱",width = 21)
    private String email;

    @Excel(name="地址",width = 35)
    private String address;

    @Excel(name="创建时间",width = 30,format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    @Excel(name="更新时间",width = 30,format = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Worker() {
    }

    public Worker(String username, String password, String name, String gender, String phone, String email, String address, int department) {
        this.department=department;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "workerId=" + workerId +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", department=" + department +
                ", deptName='" + deptName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
