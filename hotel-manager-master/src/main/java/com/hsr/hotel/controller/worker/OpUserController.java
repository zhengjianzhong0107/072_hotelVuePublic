package com.hsr.hotel.controller.worker;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.hsr.hotel.entity.User;
import com.hsr.hotel.response.AjaxResult;
import com.hsr.hotel.response.MsgType;
import com.hsr.hotel.response.ResponseTool;
import com.hsr.hotel.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 管理客户信息
 */
@RestController
@RequestMapping(value = "/op/user")
public class OpUserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "")
    public AjaxResult getAllUser(){
        return ResponseTool.success(userService.selectAllUser());
    }

    @PostMapping()
    public AjaxResult getAllUseByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @RequestParam(defaultValue = "") String search){
        return ResponseTool.success(userService.selectAllUser(pageNum,pageSize,search));
    }

    @RequestMapping(value = "/count")
    public AjaxResult getUserCount(){
        return ResponseTool.success(userService.getUserCount());
    }

    @RequestMapping(value = "/delete/{userId}")
    public AjaxResult deleteUser(@PathVariable Integer userId){
        int re = userService.deleteUser(userId);
        if(re!=1) return ResponseTool.failed();
        return ResponseTool.success();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public AjaxResult userAdd(String username,String password,String name,String gender,String phone,String email,String address,String idcard){
        User user = new User(username,password,name,gender,phone,email,address,idcard);
        int re = userService.addUser(user);
        if(re!=1) return ResponseTool.failed();
        return ResponseTool.success();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public AjaxResult userUpdate(Integer userId, String name, String gender, String phone,
                                 String email, String address, String idcard, HttpServletRequest request){
        HttpSession session = request.getSession();
        if (!session.getAttribute("userId").equals(userId)){
            return ResponseTool.failed(MsgType.PERMISSION_DENIED);
        }
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setGender(gender);
        user.setPhone(phone);
        user.setEmail(email);
        user.setAddress(address);
        user.setIdcard(idcard);
        if(userService.updateUser(user)==1)
            return ResponseTool.success("修改成功");
        return ResponseTool.success("修改失败，请检查或稍后再试");
    }


    @RequestMapping(method = RequestMethod.POST,value = "/updatePassword")
    public AjaxResult updatePassword(String username,String oldPassword,String newPassword){
        User user = userService.selectByUsernameAndPassword(username,oldPassword);
        if (user == null){
            return ResponseTool.failed("密码不对");
        }
        user.setPassword(newPassword);
        if(userService.updateUser(user)==1)
            return ResponseTool.success("修改成功");
        return ResponseTool.failed("修改失败");
    }


    @RequestMapping(value = "/{userId}")
    public AjaxResult getProfile(@PathVariable Integer userId){
        User user = userService.selectById(userId);
        user.setPassword(null);
        return ResponseTool.success(user);
    }


    @RequestMapping(value = "/username/{username}")
    public AjaxResult getByUsername(@PathVariable String username){
        User user = userService.selectByUsername(username);
        user.setPassword(null);
        return ResponseTool.success(user);
    }

    /**
     * 导出用户人员信息
     * @param response
     */
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exporUser(HttpServletResponse response) {

        List<User> list = userService.selectAllUser();

        ExportParams params = new ExportParams("客户信息表", "客户信息表", ExcelType.HSSF);

        System.out.println(list);
        Workbook work = ExcelExportUtil.exportExcel(params, User.class, list);

        ServletOutputStream out = null;
        try {
            //流形式
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("客户信息表.xls", "UTF-8"));
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
