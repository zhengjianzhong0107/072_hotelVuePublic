package com.hsr.hotel.controller.worker;

import com.hsr.hotel.entity.DepartmentInfo;
import com.hsr.hotel.response.AjaxResult;
import com.hsr.hotel.response.ResponseTool;
import com.hsr.hotel.service.DepartmentInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 部门信息表(DepartmentInfo)表控制层
 *
 * @author makejava
 * @since 2022-01-02 22:42:36
 */
@RestController
@RequestMapping("/op/depart")
public class DepartmentInfoController {
    /**
     * 服务对象
     */
    @Resource
    private DepartmentInfoService departmentInfoService;

    @GetMapping("/getAll")
    public AjaxResult getAll() {
        return ResponseTool.success(departmentInfoService.getALlDept());
    }

    @PostMapping("/getAll")
    public AjaxResult getAllByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @RequestParam(defaultValue = "") String search
    ) {

        return ResponseTool.success(departmentInfoService.selectAllByPage(pageNum, pageSize, search));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult queryById(@PathVariable("id") Integer id) {
        return ResponseTool.success(this.departmentInfoService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param departmentInfo 实体
     * @return 新增结果
     */
    @PostMapping()
    public AjaxResult add(DepartmentInfo departmentInfo) {
        return ResponseTool.success(this.departmentInfoService.insert(departmentInfo));
    }

    /**
     * 编辑数据
     *
     * @param departmentInfo 实体
     * @return 编辑结果
     */
    @PutMapping()
    public AjaxResult edit(Integer id ,String department,String remark) {
        DepartmentInfo departmentInfoController = new DepartmentInfo(id, department, remark);
        return ResponseTool.success(this.departmentInfoService.update(departmentInfoController));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable("id") Integer id) {
        return ResponseTool.success(this.departmentInfoService.deleteById(id));
    }

    @PostMapping("/deleteBatch/{ids}")
    public AjaxResult deleteBatch(@PathVariable("ids") List<Integer> ids) {
        System.out.println(Arrays.toString(ids.toArray()));
        int res = departmentInfoService.deleteBatchIds(ids);
        if (res > 0)
            return ResponseTool.success();
        return ResponseTool.failed("删除失败!");
    }
}

