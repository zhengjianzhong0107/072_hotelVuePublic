package com.hsr.hotel.mapper;

import com.hsr.hotel.entity.Worker;
import com.hsr.hotel.entity.dto.WorkerVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WorkerMapper {
    Integer getCount();
    int deleteByPrimaryKey(Integer workerId);

    int insert(Worker record);

    int insertSelective(Worker record);

    Worker selectByPrimaryKey(Integer workerId);

    int updateByPrimaryKeySelective(Worker record);

    int updateByPrimaryKey(Worker record);

    Worker selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password, @Param("role") String role);

    Worker selectByUsernamePassword(@Param("username") String username, @Param("password") String password);

    List<Worker> selectByRole(String role);

    Worker selectByUsername(String username);

    List<Worker> selectAll(String role, String search);

    int deleteBatch(List<Integer> ids);

    List<WorkerVo> selectAllByPage(String role, String search);

    boolean insertBatch(List<Worker> list);
}
