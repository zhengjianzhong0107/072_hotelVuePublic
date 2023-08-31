package com.hsr.hotel.service;

import com.hsr.hotel.entity.Worker;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface WorkerService {
    int insert(Worker worker);
    int delete(int workerId);
    int updateById(Worker worker);
    Worker selectById(int workerId);
    Worker selectByUsername(String username);
    List<Worker> findAll();
    List<Worker> selectByRole(String role);
    Worker login(String username,String password,String role);
    Worker login(String username,String password);


    PageInfo<Worker> AllOperatorByPage(Integer pageNum, Integer pageSize, String value, String search);

    int deleteBatchIds(List<Integer> ids);

    boolean insertBatchWorker(List<Worker> list);

    Object findAll(String search);
}
