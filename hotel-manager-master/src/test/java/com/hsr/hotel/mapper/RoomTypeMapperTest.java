package com.hsr.hotel.mapper;

import com.hsr.hotel.entity.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RoomTypeMapperTest {

    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Test
    void updateByPrimaryKeySelective() {
        RoomType roomType =roomTypeMapper.selectByPrimaryKey(1);
        roomType.setRest(11);
//        roomType.setTypeId(1);
        int i = roomTypeMapper.updateByPrimaryKey(roomType);

        System.out.println(i);
    }

    @Test
    void updateByPrimaryKey() {
    }

    @Test
    void select() {

        List<RoomType> roomTypes =
                roomTypeMapper.selectAll("单人房");

        System.out.println(roomTypes);
    }

}