package com.hsr.hotel.service.impl;

import com.hsr.hotel.entity.DepartmentInfo;
import com.hsr.hotel.mapper.DepartmentInfoMapper;
import com.hsr.hotel.service.DepartmentInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门信息表(DepartmentInfo)表服务实现类
 *
 * @author makejava
 * @since 2022-01-02 22:42:38
 */
@Service("departmentInfoService")
public class DepartmentInfoServiceImpl implements DepartmentInfoService {
    @Resource
    private DepartmentInfoMapper departmentInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DepartmentInfo queryById(Integer id) {
        return this.departmentInfoDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param departmentInfo 实例对象
     * @return 实例对象
     */
    @Override
    public DepartmentInfo insert(DepartmentInfo departmentInfo) {
        this.departmentInfoDao.insert(departmentInfo);
        return departmentInfo;
    }

    /**
     * 修改数据
     *
     * @param departmentInfo 实例对象
     * @return 实例对象
     */
    @Override
    public DepartmentInfo update(DepartmentInfo departmentInfo) {
        this.departmentInfoDao.update(departmentInfo);
        return this.queryById(departmentInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.departmentInfoDao.deleteById(id) > 0;
    }

    @Override
    public PageInfo<DepartmentInfo> selectAllByPage(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<DepartmentInfo> rooms = departmentInfoDao.selectAllByPrimaryKey(search);
        PageInfo<DepartmentInfo> roomPageInfo = new PageInfo<>(rooms);
        return roomPageInfo;
    }

    @Override
    public List<DepartmentInfo> getALlDept() {

        return departmentInfoDao.selectAllByPrimaryKey(null);
    }

    @Override
    public int deleteBatchIds(List<Integer> ids) {
        return departmentInfoDao.deleteBatch(ids);
    }
}
