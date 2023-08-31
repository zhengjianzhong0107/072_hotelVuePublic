package com.hsr.hotel.service;


import com.hsr.hotel.entity.User;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    User selectById(int userId);

    int addUser(User user);

    int insertUser(User user);

    int deleteUser(int userId);

    int updateUser(User user);

    Integer getUserCount();

    User selectByUsernameAndPassword(String username, String password);

    User selectByUsername(String username);

    List<User> selectAll();

    List<User> selectAllUser();

    PageInfo<User> selectAllUser(Integer pageNum, Integer pageSize, String search);

    boolean insertBatchUser(List<User> list);

    int checkusernameAndemail(String username, String email, HttpSession session);

    void resetPassword(String username, String password);
}
