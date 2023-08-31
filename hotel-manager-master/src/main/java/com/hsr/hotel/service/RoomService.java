package com.hsr.hotel.service;

import com.hsr.hotel.entity.Room;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


public interface RoomService {
    int insert(Room room);

    int delete(int roomId);

    int update(Room room);

    Room selectById(int roomId);

    Room selectByNumber(String roomNumber);

    List<Room> selectByStatus(int roomStatus);

    List<Room> selectByType(int typeId);

    List<Room> selectAll();

    int orderRoom(int typeId);

    int inRoom(int typeId);

    int booKingRoom(int typeId);

    int outRoom(int typeId);

    PageInfo<Room> selectAllByPage(Integer pageNum, Integer pageSize, String search);

    int deleteBatchIds(List<Integer> ids);

    List<Map<String, Integer>> selectGroupByType();

}
