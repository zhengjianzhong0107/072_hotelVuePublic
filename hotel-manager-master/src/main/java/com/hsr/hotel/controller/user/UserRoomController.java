package com.hsr.hotel.controller.user;

import com.hsr.hotel.entity.RoomType;
import com.hsr.hotel.response.AjaxResult;
import com.hsr.hotel.response.ResponseTool;
import com.hsr.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/hotel/room")
public class UserRoomController {

    @Autowired
    private RoomTypeService roomTypeService;

    /**
     * 所有房型
     *
     * @return
     */
    @RequestMapping(value = "")
    public AjaxResult getAllRoomType() {
        List<RoomType> rooms = roomTypeService.findAllType();
        return ResponseTool.success(rooms);
    }

    /**
     * 所有房型
     *
     * @return
     */
    @PostMapping("/getAllBypage")
    public AjaxResult getAllRoomTypeByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize) {

        return ResponseTool.success(roomTypeService.selectAllByPage(pageNum, pageSize, null));
    }

    /**
     * 查找有余量的房型
     *
     * @return
     */
    @RequestMapping(value = "/rest")
    public AjaxResult findAllRestRoomType() {
        return ResponseTool.success(roomTypeService.findAllRestType());
    }

    /**
     * 根据id查找房型
     *
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/{typeId}")
    public AjaxResult getById(@PathVariable int typeId) {
        return ResponseTool.success(roomTypeService.selectById(typeId));
    }


}
