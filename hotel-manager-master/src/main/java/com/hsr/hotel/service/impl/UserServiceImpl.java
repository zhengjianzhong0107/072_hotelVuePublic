package com.hsr.hotel.service.impl;

import com.hsr.hotel.entity.User;
import com.hsr.hotel.mapper.UserMapper;
import com.hsr.hotel.service.UserService;
import com.hsr.hotel.utils.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int addUser(User user) {
        user.setPassword(MD5Utils.MD5Encode(user.getPassword()));
        return userMapper.insertSelective(user);
    }

    @Override
    public int insertUser(User user) {
        user.setPassword(MD5Utils.MD5Encode(user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public int deleteUser(int userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer getUserCount() {
        return userMapper.getUserCount();
    }

    @Override
    public User selectByUsernameAndPassword(String username, String password) {
        String pass = MD5Utils.MD5Encode(password);
        return userMapper.selectByUsernameAndPassword(username,pass);
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public PageInfo<User> selectAllUser(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectAllByPage(search);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        return userPageInfo;
    }

    @Override
    public boolean insertBatchUser(List<User> list) {
        return userMapper.insertBatch(list);
    }

    @Override
    public int checkusernameAndemail(String username, String email, HttpSession session) {

        return 0;
    }

    @Override
    public void resetPassword(String username, String password) {

    }
}
