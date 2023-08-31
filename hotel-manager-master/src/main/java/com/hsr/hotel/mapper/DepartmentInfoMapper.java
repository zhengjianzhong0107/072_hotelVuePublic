package com.hsr.hotel.mapper;

import com.hsr.hotel.entity.DepartmentInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 部门信息表(DepartmentInfo)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-02 22:42:36
 */
public interface DepartmentInfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DepartmentInfo queryById(Integer id);


    /**
     * 统计总行数
     *
     * @param departmentInfo 查询条件
     * @return 总行数
     */
    long count(DepartmentInfo departmentInfo);

    /**
     * 新增数据
     *
     * @param departmentInfo 实例对象
     * @return 影响行数
     */
    int insert(DepartmentInfo departmentInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DepartmentInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DepartmentInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DepartmentInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DepartmentInfo> entities);

    /**
     * 修改数据
     *
     * @param departmentInfo 实例对象
     * @return 影响行数
     */
    int update(DepartmentInfo departmentInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<DepartmentInfo> selectAllByPrimaryKey(String search);

    int deleteBatch(List<Integer> ids);
}

