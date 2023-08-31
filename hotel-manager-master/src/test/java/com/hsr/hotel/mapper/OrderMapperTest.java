package com.hsr.hotel.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    void selectByUserBySearch() {

        System.out.println(orderMapper.selectByUserBySearch("1475817145893502976"));
    }
}