package com.hsr.hotel.service;

import com.hsr.hotel.entity.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Comment)表服务接口
 *
 * @author makejava
 * @since 2021-12-29 19:37:30
 */
public interface CommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment queryById(Integer id);

    /**
     * 分页查询
     *
     * @param comment 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    Page<Comment> queryByPage(Comment comment, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment insert(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    //分页查询


    PageInfo<Comment> queryAllComment(Integer pageNum, Integer pageSize, String roomNumber);

    List<Comment> queryAllComment(String roomNumber);
}
