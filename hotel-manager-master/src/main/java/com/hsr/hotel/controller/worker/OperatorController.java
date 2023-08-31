package com.hsr.hotel.controller.worker;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.hsr.hotel.entity.DepartmentInfo;
import com.hsr.hotel.entity.Worker;
import com.hsr.hotel.enums.Role;
import com.hsr.hotel.response.AjaxResult;
import com.hsr.hotel.response.ResponseTool;
import com.hsr.hotel.service.DepartmentInfoService;
import com.hsr.hotel.service.WorkerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/admin/operator")
public class OperatorController {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private DepartmentInfoService departmentInfoService;


    @RequestMapping(method = POST, value = "/delete/{workerId}")
    public AjaxResult deleteOperator(@PathVariable Integer workerId) {
        int re = workerService.delete(workerId);
        if (re != 1) ResponseTool.failed();
        return ResponseTool.success("删除成功");
    }

    /* @RequestMapping(value = "")
     public AjaxResult getAllOperator(){
         return ResponseTool.success(workerService.selectByRole(Role.OPERATOR.getValue()));
     }
 */
    @RequestMapping(method = POST, value = "/{workerId}")
    public AjaxResult getOperator(@PathVariable Integer workerId) {
        return ResponseTool.success(workerService.selectById(workerId));
    }

    @RequestMapping(method = POST, value = "/add")
    public AjaxResult addOperator(String username, String password, String pass,
                                  String name, String gender,
                                  String phone, String email,
                                  String address, int deptId
    ) {
        Worker worker = new Worker(username, password, name, gender, phone, email, address, deptId);
        worker.setRole(Role.OPERATOR.getValue());
        int re = workerService.insert(worker);
        if (re != 1) return ResponseTool.failed();
        return ResponseTool.success("添加成功");
    }


    @RequestMapping(method = POST, value = "/update")
    public AjaxResult updateOperator(int workerId, String name, String gender, String phone, String email, String address, Integer deptId) {
        Worker worker = new Worker();
        worker.setWorkerId(workerId);
        worker.setName(name);
        worker.setGender(gender);
        worker.setDepartment(deptId);
        worker.setPhone(phone);
        worker.setEmail(email);
        worker.setAddress(address);
        int re = workerService.updateById(worker);
        if (re != 1) return ResponseTool.failed();
        return ResponseTool.success("更新成功");
    }

    @GetMapping("/getDept/{deptId}")
    public AjaxResult updateOperator(@PathVariable("deptId") Integer deptId) {
        if (deptId != null) {
            DepartmentInfo department = departmentInfoService.queryById(deptId);
            return ResponseTool.success(department);
        }
        return ResponseTool.failed("id没有传入");

    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    @PostMapping()
    public AjaxResult getAllOperator(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     @RequestParam(defaultValue = "") String search) {
        return ResponseTool.success(workerService.AllOperatorByPage(pageNum, pageSize, Role.OPERATOR.getValue(), search));
    }



    /**
     * 批量删除操作员
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteBatch/{ids}")
    public AjaxResult deleteBatch(@PathVariable("ids") List<Integer> ids) {
        System.out.println(Arrays.toString(ids.toArray()));
        int res = workerService.deleteBatchIds(ids);
        if (res > 0)
            return ResponseTool.success();
        return ResponseTool.failed("删除失败!");
    }

    /**
     * 导出工作人员信息
     *
     * @param response
     */
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response) {

        List<Worker> list = workerService.findAll();
     /*   list.forEach(worker -> {
            if(worker.getRole().equals(Role.ADMIN.getValue()))
               list.remove(worker);
        });*/
        ExportParams params = new ExportParams("工作人员信息表", "工作人员信息表", ExcelType.HSSF);

        System.out.println(list);
        Workbook work = ExcelExportUtil.exportExcel(params, Worker.class, list);

        ServletOutputStream out = null;
        try {
            //流形式
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("工作人员信息表.xls", "UTF-8"));
            out = response.getOutputStream();
            work.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
