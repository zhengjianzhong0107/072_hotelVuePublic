package com.hsr.hotel.service;

import com.hsr.hotel.entity.CheckIn;
import com.hsr.hotel.entity.Room;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CheckInService {

    int insert(CheckIn checkIn);

    Room checkIn(CheckIn checkIn);

    int delete(int checkInId);

    int update(CheckIn checkIn);

    int checkOut(String roomNumber);

    int updateByRoomNumber(String roomNumber);

    CheckIn selectById(int checkInId);

    List<CheckIn> selectAll();

    PageInfo<CheckIn> selectAllByPage(Integer currentNum, Integer pageSize, String search);
}
