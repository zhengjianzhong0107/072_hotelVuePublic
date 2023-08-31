package com.hsr.hotel.service.impl;


import com.hsr.hotel.entity.RoomType;
import com.hsr.hotel.mapper.RoomTypeMapper;
import com.hsr.hotel.service.RoomTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Override
    public int insert(RoomType roomType) {
        return roomTypeMapper.insertSelective(roomType);
    }

    @Override
    public int delete(int typeId) {
        return roomTypeMapper.deleteByPrimaryKey(typeId);
    }

    @Override
    public int update(RoomType roomType) {
        return roomTypeMapper.updateByPrimaryKeySelective(roomType);
    }

    @Override
    public RoomType selectByName(String roomType) {
        return roomTypeMapper.selectByRoomType(roomType);
    }

    @Override
    public RoomType selectById(int typeId) {
        return roomTypeMapper.selectByPrimaryKey(typeId);
    }

    @Override
    public List<RoomType> findAllType() {
        return roomTypeMapper.selectAll(null);
    }

    @Override
    public int updateRest(int typeId, int num) {
        RoomType rt = roomTypeMapper.selectByPrimaryKey(typeId);
        if (rt.getRest() <= 0 && num < 0) return -1;
        rt.setRest(rt.getRest() + num);
        int i = roomTypeMapper.updateByPrimaryKeySelective(rt);
        log.debug("{}", i);
        if (i > 0)
            return i;
        return -1;
    }

    @Override
    public int addRest(int typeId) {
        RoomType rt = roomTypeMapper.selectByPrimaryKey(typeId);
        rt.setTypeId(rt.getRest() + 1);
        return roomTypeMapper.updateByPrimaryKeySelective(rt);
    }

    @Override
    public int minusRest(int typeId) {
        RoomType rt = roomTypeMapper.selectByPrimaryKey(typeId);
        rt.setTypeId(rt.getRest() - 1);
        return roomTypeMapper.updateByPrimaryKeySelective(rt);
    }

    @Override
    public List<RoomType> findAllRestType() {
        return roomTypeMapper.selectAllWithRest();
    }

    @Override
    public PageInfo<RoomType> selectAllByPage(Integer pageNum, Integer pageSize, String search) {

        PageHelper.startPage(pageNum, pageSize);
        List<RoomType> rooms = roomTypeMapper.selectAll(search);
        PageInfo<RoomType> roomPageInfo = new PageInfo<>(rooms);
        return roomPageInfo;
    }

    @Override
    public int deleteBatchIds(List<Integer> ids) {

        return roomTypeMapper.deleteBatch(ids);
    }
    @Override
    public List<Map<String, Integer>> getAllTypeCount() {
        return roomTypeMapper.selectTypePercent();
    }


    public Integer selectSumType() {
        return roomTypeMapper.selectSumType();
    }
}
