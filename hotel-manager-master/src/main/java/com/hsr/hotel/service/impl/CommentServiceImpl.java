package com.hsr.hotel.service.impl;

import com.hsr.hotel.entity.Comment;
import com.hsr.hotel.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Comment)表服务实现类
 *
 * @author makejava
 * @since 2021-12-29 19:37:30
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private com.hsr.hotel.mapper.CommentMapper CommentMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Comment queryById(Integer id) {
        return this.CommentMapper.queryById(id);
    }

   /* *//**
     * 分页查询
     *
     * @param comment 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     *//*
    @Override
    public Page<Comment> queryByPage(Comment comment, PageRequest pageRequest) {
        long total = this.CommentMapper.count(comment);
        return new PageImpl<>(this.CommentMapper.queryAllByLimit(comment, pageRequest), pageRequest, total);
    }*/

    /**
     * 新增数据

     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment insert(Comment comment) {
        this.CommentMapper.insert(comment);
        return comment;
    }

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment update(Comment comment) {
        this.CommentMapper.update(comment);
        return this.queryById(comment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.CommentMapper.deleteById(id) > 0;
    }



    @Override
    public PageInfo<Comment> queryAllComment(Integer pageNum, Integer pageSize, String roomNumber) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = CommentMapper.queryAll(roomNumber);
        PageInfo<Comment> commentPageInfo = new PageInfo<>(comments);
        return commentPageInfo;
    }

    @Override
    public List<Comment> queryAllComment(String roomNumber) {
        return  CommentMapper.queryAll(roomNumber);
    }
}
