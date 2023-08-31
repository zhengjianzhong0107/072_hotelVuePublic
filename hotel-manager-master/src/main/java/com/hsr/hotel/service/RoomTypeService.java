package com.hsr.hotel.service;

import com.hsr.hotel.entity.RoomType;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoomTypeService {

    int insert(RoomType roomType);

    int delete(int typeId);

    int update(RoomType roomType);

    RoomType selectByName(String roomType);

    RoomType selectById(int typeId);

    List<RoomType> findAllType();

    int updateRest(int typeId,int num);

    int addRest(int typeId);

    int minusRest(int typeId);

    List<RoomType> findAllRestType();

    PageInfo<RoomType> selectAllByPage(Integer pageNum, Integer pageSize, String search);

    int deleteBatchIds(List<Integer> ids);

    List<Map<String, Integer>> getAllTypeCount();

}
