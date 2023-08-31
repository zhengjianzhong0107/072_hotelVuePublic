package com.hsr.hotel.service.impl;

import com.hsr.hotel.entity.Worker;
import com.hsr.hotel.mapper.WorkerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WorkerServiceImplTest {

    @Autowired
    private WorkerMapper workerMapper;

    @Test
    void allOperatorByPage() {
        List<Worker> workers = workerMapper.selectAll("admin", "张三");
        for (Worker worker : workers) {
            System.out.println(
                    worker
            );
        }
    }
}