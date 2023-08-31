package com.hsr.hotel.mapper;

import com.hsr.hotel.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    List<User> selectAllByPage(String search);
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Integer getUserCount();

    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User selectByUsername(String username);

    List<User> selectAll();

    List<User> selectAllUser();

    boolean insertBatch(List<User> list);
}