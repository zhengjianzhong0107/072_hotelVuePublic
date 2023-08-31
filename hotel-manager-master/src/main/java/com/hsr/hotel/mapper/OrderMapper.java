package com.hsr.hotel.mapper;

import com.hsr.hotel.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface OrderMapper {

    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    Order selectByNameAndPhone(Order record);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Integer getOrderCount();

    List<Order> selectAll();

    //查询用户下所有订单
    List<Order> selectByUserId(Integer userId);

    List<Order> selectAllByUser(@Param("userId") Integer userId,@Param("orderStatus") Integer orderStatus);


    int updateByTradeno(String tradeNo);

    Order selectByTradeno(String tradeNo);


    List<Order> selectByUserBySearch(String search);

    List<Map<String, Integer>> selectTypePercent();

    Integer selectSumType();

}
