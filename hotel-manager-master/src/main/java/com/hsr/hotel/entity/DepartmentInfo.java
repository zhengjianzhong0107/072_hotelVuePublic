package com.hsr.hotel.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;
import java.util.Objects;

/**
 * 部门信息表(DepartmentInfo)实体类
 *
 * @author makejava
 * @since 2022-01-02 22:42:37
 */
@NoArgsConstructor
public class DepartmentInfo implements Serializable {
    private static final long serialVersionUID = 623864577848624367L;
    /**
     * 部门id
     */
    private Integer id;
    /**
     * 部门
     */
    @Excel(name = "部门")
    private String department;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public DepartmentInfo(String department) {
        this.department = department;
    }

    public DepartmentInfo(Integer id, String department, String remark) {
        this.id = id;
        this.department = department;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentInfo that = (DepartmentInfo) o;
        return Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department);
    }
}

