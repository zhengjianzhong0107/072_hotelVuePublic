package com.hsr.hotel.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoomMapperTest {

    @Autowired
    private RoomMapper roomMapper;

    @Test
    void selectAll() {
        System.out.println(roomMapper.selectAll(""));
    }
}