package com.hsr.hotel.service;

import com.hsr.hotel.entity.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {

    int insert(Order order);

    int addOrder(Order order);

    int delete(Integer orderId);

    Order selectById(Integer orderId);

    Order selectByNameAndPhone(String name,String phone);

    int update(Order record);

    int payOrder(int orderId);

    int cancelOrder(int orderId);

    Integer getOrderCount();

    List<Order> selectByUserId(int userId);

    List<Order> AllOrders();

    List<Order> UsersAllOrders(int userId);

    PageInfo<Order> UsersAllOrders(int userId, int pageNum, int pageSize);

    int update(String tradeNo);

    Order selectByTradeno(String tradeNo);

    PageInfo<Order> AllOrders(Integer pageNum, Integer pageSize, String search);

}
