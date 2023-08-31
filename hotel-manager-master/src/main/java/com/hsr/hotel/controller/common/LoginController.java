package com.hsr.hotel.controller.common;

import com.hsr.hotel.entity.DepartmentInfo;
import com.hsr.hotel.entity.User;
import com.hsr.hotel.entity.Worker;
import com.hsr.hotel.response.AjaxResult;
import com.hsr.hotel.response.ResponseTool;
import com.hsr.hotel.service.DepartmentInfoService;
import com.hsr.hotel.service.UserService;
import com.hsr.hotel.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private WorkerService workerService;

    @Autowired
    private DepartmentInfoService departmentInfoService;
    /**
     * 客户登录使用
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public AjaxResult userLogin(String username, String password,
                                HttpServletRequest request) {
        if (StringUtils.isEmpty(username)) {
            return ResponseTool.failed("用户名不能为空");
        } else if (StringUtils.isEmpty(password)) {
            return ResponseTool.failed("密码不能为空");
        }
        User user = userService.selectByUsernameAndPassword(username, password);
        if (user == null) {
            return ResponseTool.failed("用户名或密码不正确");
        }
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("username", user.getUsername());
        HashMap map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("userId", user.getUserId());
        return ResponseTool.success(map);
    }

    /**
     * 工作人员修改个人的消息
     * @param workerId
     * @param name
     * @param gender
     * @param phone
     * @param email
     * @param address
     * @param deptId
     * @return
     */
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

    /**
     * 工作人员或者管理员登录
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public AjaxResult workerLogin(String username, String password,
                                  HttpServletRequest request) {
        if (StringUtils.isEmpty(username)) {
            return ResponseTool.failed("用户名不能为空");
        } else if (StringUtils.isEmpty(password)) {
            return ResponseTool.failed("密码不能为空");
        }
        Worker worker = workerService.login(username, password);
        if (worker == null) {
            return ResponseTool.failed("用户名或密码不正确");
        }
        HttpSession session = request.getSession();
        session.setAttribute("userId", worker.getWorkerId());
        session.setAttribute("role", worker.getRole());
        HashMap<String, String> map = new HashMap<>();
        map.put("sessionId", session.getId());
        map.put("role", worker.getRole());
        return ResponseTool.success(map);
    }

    /**
     * 获取管理员或者操作员信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public AjaxResult workerInfo(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        Worker user = workerService.selectById(userId);
        DepartmentInfo departmentInfo = departmentInfoService.queryById(user.getDepartment());
        user.setDeptName(departmentInfo.getDepartment());
        return ResponseTool.success(user);
    }

    //退出登录
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public AjaxResult workerlogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("userId");
        session.removeAttribute("role");
        return ResponseTool.success("注销成功");
    }

}
