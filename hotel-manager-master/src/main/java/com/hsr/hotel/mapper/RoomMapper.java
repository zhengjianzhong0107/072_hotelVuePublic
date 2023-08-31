package com.hsr.hotel.mapper;

import com.hsr.hotel.entity.Room;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface RoomMapper {
    Integer getCount();
    int deleteByPrimaryKey(Integer roomId);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer roomId);

    Room selectByNumber(String roomNumber);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    List<Room> selectByType(Integer typeId);

    List<Room> selectByStatus(Integer roomStatus);

    List<Room> selectAll(String search);

    Room randomSelectByTypeAndStatus(@Param("typeId") Integer typeId,@Param("roomStatus") Integer roomStatus);

    int deleteBatch(List<Integer> ids);

    List<Map<String, Integer>> selectGroupByType();
}
