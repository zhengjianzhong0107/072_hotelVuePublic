package com.hsr.hotel.controller.worker;

import com.hsr.hotel.entity.OrderType;
import com.hsr.hotel.response.AjaxResult;
import com.hsr.hotel.response.ResponseTool;
import com.hsr.hotel.service.OrderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/op/order-type")
public class OpOrderTypeController {
    @Autowired
    private OrderTypeService orderTypeService;

    @RequestMapping(value = "/add")
    public AjaxResult addOrderType(String type, String remark){
        OrderType orderType = new OrderType(type,remark);

        int re = orderTypeService.insertOrderType(orderType);
        if(re!=1) return ResponseTool.failed();
        return ResponseTool.success();
    }

    @RequestMapping(value = "/delete/{typeId}")
    public AjaxResult deleteOrderType(@PathVariable Integer typeId){
        int re= orderTypeService.deleteOrderType(typeId);
        if(re!=1) return ResponseTool.failed();
        return ResponseTool.success();
    }

    @RequestMapping(value = "/update")
    public AjaxResult updateOrderType(Integer typeId,String type,String remark){
        OrderType orderType = new OrderType(type,remark);
        orderType.setTypeId(typeId);
        orderType.setUpdateTime(new Date(System.currentTimeMillis()));
        int re = orderTypeService.updateOrderType(orderType);
        if(re!=1) return ResponseTool.failed();
        return ResponseTool.success();
    }

    @RequestMapping(value = "/{typeId}")
    public AjaxResult getById(@PathVariable Integer typeId){
        return ResponseTool.success(orderTypeService.selectById(typeId));
    }

    @RequestMapping(value = "")
    public AjaxResult getAllType(){
        return ResponseTool.success(orderTypeService.selectAll());
    }

    @PostMapping
    public AjaxResult getAllType(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestParam(defaultValue = "") String search
    ) {

        return ResponseTool.success(orderTypeService.selectAllByPage(pageNum,pageSize,search));
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/deleteBatch/{ids}")
    public AjaxResult deleteBatch( @PathVariable("ids") List<Integer> ids) {
        System.out.println(Arrays.toString(ids.toArray()));
        int res = orderTypeService.deleteBatchIds(ids);
        if (res > 0)
            return ResponseTool.success();
        return ResponseTool.failed("删除失败!");
    }

}
