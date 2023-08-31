package com.hsr.hotel.service.impl;

import com.hsr.hotel.entity.CheckIn;
import com.hsr.hotel.entity.Order;
import com.hsr.hotel.entity.Room;
import com.hsr.hotel.entity.RoomType;
import com.hsr.hotel.enums.OrderStatus;
import com.hsr.hotel.enums.RoomStatus;
import com.hsr.hotel.mapper.CheckInMapper;
import com.hsr.hotel.service.CheckInService;
import com.hsr.hotel.service.OrderService;
import com.hsr.hotel.service.RoomService;
import com.hsr.hotel.service.RoomTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CheckInServiceImpl implements CheckInService {
    @Autowired
    private CheckInMapper checkInMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomService roomService;

    @Override
    public int insert(CheckIn checkIn) {
        return checkInMapper.insert(checkIn);
    }

    /**
     * 入住登记
     * @param checkIn
     * 1.获取订单
     * 2.获取房间类型
     * 3.获取房间
     * @return
     */
    @Override
    @Transactional()
    public Room checkIn(CheckIn checkIn) {
        Order order = orderService.selectByTradeno(checkIn.getOrderId());//查询到订单

        RoomType rt = roomTypeService.selectById(order.getRoomTypeId());
        Room r = roomService.selectById(roomService.inRoom(order.getRoomTypeId()));
        if(r==null)
            return null;
        checkIn.setRoomId(r.getRoomId());//给客户房间号
        checkIn.setRoomType(order.getRoomType());
        checkIn.setRoomNumber(r.getRoomNumber());//
        roomTypeService.updateRest(rt.getTypeId(),-1);//修改空闲房间数量
        order.setOrderStatus(OrderStatus.CHECK_IN.getCode());//修改订单状态
        orderService.update(order);
        checkInMapper.insert(checkIn);
        return r;
    }

    /**
     * 退房登记
     * 1.获取房间
     * 2.获取房型
     * 3.获取checkIn
     * @param roomNumber
     * @return
     */
    @Override
    public int checkOut(String  roomNumber){
        Room r = roomService.selectByNumber(roomNumber);
        RoomType ty = roomTypeService.selectById(r.getTypeId());
        CheckIn checkIn = checkInMapper.selectLatestByRoomNumber(roomNumber);
        r.setRoomStatus(RoomStatus.AVAILABLE.getCode());
        if(roomService.update(r) <=0 )return -3;
        if (roomTypeService.updateRest(ty.getTypeId(),1)<=0)return -2;
        return checkInMapper.checkOut(checkIn.getCheckInId());
    }

    @Override
    public int delete(int checkInId) {
        return checkInMapper.deleteByPrimaryKey(checkInId);
    }

    @Override
    public int update(CheckIn checkIn) {
        return checkInMapper.updateByPrimaryKeySelective(checkIn);
    }



    @Override
    public int updateByRoomNumber(String roomNumber) {
        return checkInMapper.updateByRoomNumber(roomNumber);
    }

    @Override
    public CheckIn selectById(int checkInId) {
        return checkInMapper.selectByPrimaryKey(checkInId);
    }

    @Override
    public List<CheckIn> selectAll() {
        return checkInMapper.selectAll(null);
    }

    @Override
    public PageInfo<CheckIn> selectAllByPage(Integer currentNum, Integer pageSize, String search) {
        PageHelper.startPage(currentNum, pageSize);
        List<CheckIn> rooms = checkInMapper.selectAll(search);
        PageInfo<CheckIn> roomPageInfo = new PageInfo<>(rooms);
        return roomPageInfo;
    }
}
