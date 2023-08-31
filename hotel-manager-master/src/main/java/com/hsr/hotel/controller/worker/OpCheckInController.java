package com.hsr.hotel.controller.worker;

import com.hsr.hotel.entity.CheckIn;
import com.hsr.hotel.response.AjaxResult;
import com.hsr.hotel.response.ResponseTool;
import com.hsr.hotel.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/op/check-in")
public class OpCheckInController {

    @Autowired
    private CheckInService checkInService;

    /**
     * 入住登记
     * @param orderId 订单号
     * @param peoCount  入住人数
     * @param name   入住人
     * @param ids   身份证号
     * @return
     */
    @RequestMapping(value = "/in")
    public AjaxResult addCheckIn(String orderId, int peoCount, String name, String ids){
        CheckIn checkIn = new CheckIn();
        checkIn.setOrderId(orderId);
        checkIn.setPeoCount(peoCount);
        checkIn.setPersons(name);
        checkIn.setIds(ids);
        return ResponseTool.success(checkInService.checkIn(checkIn));
    }

    @RequestMapping(value = "/out")
    public AjaxResult checkOut(String roomNumber) {
        if(checkInService.checkOut(roomNumber)<0)
            return ResponseTool.failed("退房失败");
        return ResponseTool.success("退房成功");
    }


    @RequestMapping(value = "/delete")
    public AjaxResult deleteCheckIn(int checkId){
        if(checkInService.delete(checkId)!=1)
            return ResponseTool.failed("删除失败");
        return ResponseTool.success("删除成功");
    }

    @RequestMapping(value = "/update")
    public AjaxResult update(int checkId,String roomNumber){
        CheckIn checkIn = new CheckIn();
        checkIn.setCheckInId(checkId);
        checkIn.setRoomNumber(roomNumber);
        if(checkInService.update(checkIn)!=1)
            return ResponseTool.failed("更新失败");
        return ResponseTool.success("更新成功");
    }



    @RequestMapping(value = "/{checkId}")
    public AjaxResult getById(@PathVariable int checkId){
        return ResponseTool.success(checkInService.selectById(checkId));
    }

    @RequestMapping(value = "")
    public AjaxResult getAll(){
        return ResponseTool.success(checkInService.selectAll());
    }

    /**
     * 分页查询
     *
     * @return
     */
    @PostMapping()
    public AjaxResult getAll(@RequestParam(defaultValue = "1") Integer roomNumber,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestParam(defaultValue = "") String search
    ) {

        return ResponseTool.success(checkInService.selectAllByPage(roomNumber, pageSize, search));
    }

}
