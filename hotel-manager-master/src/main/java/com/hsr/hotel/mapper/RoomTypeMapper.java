package com.hsr.hotel.mapper;

import com.hsr.hotel.entity.RoomType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface RoomTypeMapper {
    Integer getCount();
    int deleteByPrimaryKey(Integer typeId);

    int insert(RoomType record);

    int insertSelective(RoomType record);

    RoomType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(RoomType record);

    int updateByPrimaryKey(RoomType record);

    List<RoomType> selectAll(String search);

    RoomType selectByRoomType(String roomType);

    List<RoomType> selectAllWithRest();

    int deleteBatch(List<Integer> ids);

    List<Map<String, Integer>> selectTypePercent();

    Integer selectSumType();
}
