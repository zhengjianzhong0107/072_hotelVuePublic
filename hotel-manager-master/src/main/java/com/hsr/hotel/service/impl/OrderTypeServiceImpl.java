package com.hsr.hotel.service.impl;

import com.hsr.hotel.entity.OrderType;
import com.hsr.hotel.mapper.OrderTypeMapper;
import com.hsr.hotel.service.OrderTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderTypeServiceImpl implements OrderTypeService {
    @Autowired
    private OrderTypeMapper orderTypeMapper;

    @Override
    public int insertOrderType(OrderType orderType) {
        return orderTypeMapper.insertSelective(orderType);
    }

    @Override
    public int deleteOrderType(int typeId) {
        return orderTypeMapper.deleteByPrimaryKey(typeId);
    }

    @Override
    public int updateOrderType(OrderType orderType) {
        return orderTypeMapper.updateByPrimaryKeySelective(orderType);
    }

    @Override
    public OrderType selectById(int typeId) {
        return orderTypeMapper.selectByPrimaryKey(typeId);
    }

    @Override
    public List<OrderType> selectAll() {
        return orderTypeMapper.selectAll(null);
    }

    @Override
    public PageInfo<OrderType> selectAllByPage(Integer pageNum, Integer pageSize, String search) {

        PageHelper.startPage(pageNum, pageSize);
        List<OrderType> orderTypes = orderTypeMapper.selectAll(search);
        PageInfo<OrderType> roomPageInfo = new PageInfo<>(orderTypes);
        return roomPageInfo;
    }

    @Override
    public int deleteBatchIds(List<Integer> ids) {
        return orderTypeMapper.deleteBatch(ids);
    }
}
