package com.hsr.hotel.controller.worker;

import com.hsr.hotel.entity.Room;
import com.hsr.hotel.entity.RoomType;
import com.hsr.hotel.response.AjaxResult;
import com.hsr.hotel.response.ResponseTool;
import com.hsr.hotel.service.RoomService;
import com.hsr.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/op/room")
public class OpRoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomTypeService roomTypeService;

    @RequestMapping(value = "/add")
    public AjaxResult addRoom(String roomNumber, Integer typeId,
                              String roomType, double roomPrice,
                              double roomDiscount,
                              int roomStatus, String remark
    ) {


        if (roomService.selectByNumber(roomNumber) != null) return ResponseTool.failed("房间号码重复");
        Room room = new Room(roomNumber, typeId, roomType, roomPrice, roomDiscount, roomStatus, remark);
        RoomType rt = new RoomType();
        if (roomService.insert(room) == 1) {
            rt.setTypeId(typeId);
            //每添加一个房间所属的类型，该类型的房间数量+1
            rt.setRest(roomTypeService.selectById(typeId).getRest() + 1);
            if (roomTypeService.update(rt) != 1) return ResponseTool.failed("添加失败");
        } else return ResponseTool.failed("添加失败");
        return ResponseTool.success("添加成功");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete/{roomId}")
    public AjaxResult deleteRoom(@PathVariable Integer roomId) {
        Room room = roomService.selectById(roomId);
        if (roomService.delete(roomId) != 1) return ResponseTool.failed("删除失败");

        roomTypeService.updateRest(room.getTypeId(), -1);
        return ResponseTool.success("删除成功");
    }

    @RequestMapping(value = "/update")
    public AjaxResult updateRoom(Integer roomId, String roomNumber,
                                 Integer typeId, String roomType,
                                 double roomPrice, double roomDiscount,
                                 int roomStatus, String remark
    ) {
        Room room = new Room(roomNumber, typeId, roomType, roomPrice, roomDiscount, roomStatus, remark);
        room.setRoomId(roomId);
        if (roomService.update(room) != 1) return ResponseTool.failed("更新失败");
        return ResponseTool.success("更新成功");
    }

    @RequestMapping(value = "/{id}")
    public AjaxResult getById(@PathVariable Integer id) {
        return ResponseTool.success(roomService.selectById(id));
    }

    @RequestMapping(value = "/type/{typeId}")
    public AjaxResult getByType(@PathVariable Integer typeId) {
        return ResponseTool.success(roomService.selectByType(typeId));
    }

    @RequestMapping(value = "/status/{statusId}")
    public AjaxResult getByStatus(@PathVariable Integer statusId) {
        return ResponseTool.success(roomService.selectByStatus(statusId));
    }

    /**
     *  查询曲全部房间，分页查询
     * @return
     */
   /* @RequestMapping(method = RequestMethod.POST)
    public AjaxResult getAll(){

        return ResponseTool.success(roomService.selectAll());
    }
*/

    /**
     * 查询全部房间，分页查询
     *
     * @return
     */
    @PostMapping()
    public AjaxResult getAll(@RequestParam(defaultValue = "1") Integer currentNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestParam(defaultValue = "") String search
    ) {

        return ResponseTool.success(roomService.selectAllByPage(currentNum, pageSize, search));
    }

    @PostMapping("/deleteBatch/{ids}")
    public AjaxResult deleteBatch(@PathVariable("ids") List<Integer> ids) {
        System.out.println(Arrays.toString(ids.toArray()));
        int res = roomService.deleteBatchIds(ids);
        if (res > 0)
            return ResponseTool.success();
        return ResponseTool.failed("删除失败!");
    }
}
