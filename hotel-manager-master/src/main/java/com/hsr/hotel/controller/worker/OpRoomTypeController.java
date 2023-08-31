package com.hsr.hotel.controller.worker;

import com.hsr.hotel.entity.RoomType;
import com.hsr.hotel.response.AjaxResult;
import com.hsr.hotel.response.ResponseTool;
import com.hsr.hotel.service.RoomService;
import com.hsr.hotel.service.RoomTypeService;
import com.hsr.hotel.service.impl.RoomTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/op/room-type")
public class OpRoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomService roomService;

    /**
     * 获取对应的房间类型占总数的百分比
     * @return
     */
    @GetMapping(value = "/getRoomPercent")
    public AjaxResult getAllRoomPercent() {
        List<Map<String, Integer>> listOrder = roomTypeService.getAllTypeCount();
        RoomTypeServiceImpl os = (RoomTypeServiceImpl) roomTypeService;
        Integer sumType = os.selectSumType();

        Iterator<Map<String, Integer>> iterator = listOrder.iterator();
        while (iterator.hasNext()) {
            Map<String, Integer> next = iterator.next();
            Iterator<String> keySet = next.keySet().iterator();
            while (keySet.hasNext()) {
                String mapKey = keySet.next();
                Integer count = next.get(mapKey);//获取到对应类型的个数
                // 创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                // 设置精确到小数点后2位,可以写0不带小数位
                numberFormat.setMaximumFractionDigits(2);
                String result = numberFormat.format((float) count / (float) sumType * 100);
                next.put(mapKey, Integer.valueOf(result));
            }
        }


        return ResponseTool.success(listOrder);
    }

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


    /**
     * 添加房型
     *
     * @param roomType
     * @param price
     * @param discount
     * @param area
     * @param bedNum
     * @param bedSize
     * @param window
     * @param remark
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public AjaxResult addRoomType(String roomType, Double price,
                                  Double discount, Integer area,
                                  Integer bedNum, String bedSize,
                                  Integer window, String remark,
                                  Integer rest,
                                  String cover) {
        RoomType rt = new RoomType(roomType, remark, price, discount, area, bedNum, bedSize, window, cover);
        rt.setRest(rest);
        int result = roomTypeService.insert(rt);
        if (result != 1) return ResponseTool.failed("添加失败");
        return ResponseTool.success("添加成功");
    }

    /**
     * 修改房型
     *
     * @param typeId
     * @param roomType
     * @param price
     * @param discount
     * @param area
     * @param bedNum
     * @param bedSize
     * @param window
     * @param rest
     * @param remark
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public AjaxResult updateRoomType(Integer typeId, String roomType,
                                     Double price, Double discount,
                                     Integer area, Integer bedNum,
                                     String bedSize, Integer window,
                                     Integer rest, String remark,
                                     String cover) {
        RoomType rt = new RoomType(roomType, remark, price, discount, area, bedNum, bedSize, window, cover);
        rt.setTypeId(typeId);
        rt.setRest(rest);
        int result = roomTypeService.update(rt);
        if (result != 1) return ResponseTool.failed("修改失败");
        return ResponseTool.success("修改成功");
    }

    /**
     * 删除房型
     *
     * @param typeId
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delete/{typeId}")
    public AjaxResult deleteRoomType(@PathVariable Integer typeId) {
        int result = roomTypeService.delete(typeId);
        if (result != 1) return ResponseTool.failed("删除失败");
        return ResponseTool.success("删除成功");
    }

    /**
     * 分页查询所有房间类型
     *
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    @PostMapping
    public AjaxResult getAll(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestParam(defaultValue = "") String search
    ) {

        return ResponseTool.success(roomTypeService.selectAllByPage(pageNum, pageSize, search));
    }

    @PostMapping("/deleteBatch/{ids}")
    public AjaxResult deleteBatch(@PathVariable("ids") List<Integer> ids) {
        System.out.println(Arrays.toString(ids.toArray()));
        int res = roomTypeService.deleteBatchIds(ids);
        if (res > 0)
            return ResponseTool.success();
        return ResponseTool.failed("删除失败!");
    }


}
