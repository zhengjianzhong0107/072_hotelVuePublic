package com.hsr.hotel.service;

import com.hsr.hotel.entity.DepartmentInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 部门信息表(DepartmentInfo)表服务接口
 *
 * @author makejava
 * @since 2022-01-02 22:42:38
 */
public interface DepartmentInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DepartmentInfo queryById(Integer id);


    /**
     * 新增数据
     *
     * @param departmentInfo 实例对象
     * @return 实例对象
     */
    DepartmentInfo insert(DepartmentInfo departmentInfo);

    /**
     * 修改数据
     *
     * @param departmentInfo 实例对象
     * @return 实例对象
     */
    DepartmentInfo update(DepartmentInfo departmentInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    PageInfo<DepartmentInfo> selectAllByPage(Integer currentNum, Integer pageSize, String search);

    List<DepartmentInfo> getALlDept();

    int deleteBatchIds(List<Integer> ids);
}
