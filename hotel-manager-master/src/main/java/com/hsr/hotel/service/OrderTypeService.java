package com.hsr.hotel.service;

import com.hsr.hotel.entity.OrderType;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderTypeService {

    int insertOrderType(OrderType orderType);

    int deleteOrderType(int typeId);

    int updateOrderType(OrderType orderType);

    OrderType selectById(int typeId);

    List<OrderType> selectAll();

    PageInfo<OrderType> selectAllByPage(Integer pageNum, Integer pageSize, String search);

    int deleteBatchIds(List<Integer> ids);
}
