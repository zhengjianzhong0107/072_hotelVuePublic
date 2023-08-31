package com.hsr.hotel.controller.worker;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.hsr.hotel.entity.DepartmentInfo;
import com.hsr.hotel.entity.User;
import com.hsr.hotel.entity.Worker;
import com.hsr.hotel.response.AjaxResult;
import com.hsr.hotel.response.ResponseTool;
import com.hsr.hotel.service.DepartmentInfoService;
import com.hsr.hotel.service.UserService;
import com.hsr.hotel.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    @Value("${server.port}")
    private String port;

    @Value("${file.ip}")
    private String ip;

    @Autowired
    private DepartmentInfoService departmentInfoService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private UserService userService;

    /**
     * 上传接口
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();  // 获取源文件的名称
        // 定义文件的唯一标识（前缀）
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/files/" + flag + "_" + originalFilename;  // 获取上传的路径
        File saveFile = new File(rootFilePath);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        FileUtil.writeBytes(file.getBytes(), rootFilePath);  // 把文件写入到上传的路径
        return ResponseTool.success("http://" + ip + ":" + port + "/files/" + flag);  // 返回结果 url
    }

    /**
     * 富文本文件上传接口
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/editor/upload")
    public JSON editorUpload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();  // 获取源文件的名称
        // 定义文件的唯一标识（前缀）
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/files/" + flag + "_" + originalFilename;  // 获取上传的路径
        File saveFile = new File(rootFilePath);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        FileUtil.writeBytes(file.getBytes(), rootFilePath);  // 把文件写入到上传的路径
        String url = "http://" + ip + ":" + port + "/files/" + flag;
        JSONObject json = new JSONObject();
        json.set("errno", 0);
        JSONArray arr = new JSONArray();
        JSONObject data = new JSONObject();
        arr.add(data);
        data.set("url", url);
        json.set("data", arr);
        return json;  // 返回结果 url
    }

    /**
     * 下载接口
     * @param flag
     * @param response
     */
    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os;  // 新建一个输出流对象
        String basePath = System.getProperty("user.dir") + "/files/";  // 定于文件上传的根路径
        List<String> fileNames = FileUtil.listFileNames(basePath);  // 获取所有的文件名称
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");  // 找到跟参数一致的文件
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);  // 通过文件的路径读取文件字节流
                os = response.getOutputStream();   // 通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }

    /**
     * 导入
     *
     * @param file
     * @return
     */
    @PostMapping("/import")
    public AjaxResult importEmployee(MultipartFile file) {
        ImportParams params = new ImportParams();
        //去掉标题行
        params.setTitleRows(1);
        List<DepartmentInfo> departments = departmentInfoService.getALlDept();
        try {
            List<Worker> list = ExcelImportUtil.importExcel(file.getInputStream(), Worker.class, params);
            list.forEach(worker -> {
                System.out.println(worker);
                //根绝部门名称返回部门id
                String deptName = worker.getDeptName();
                DepartmentInfo departmentInfo = new DepartmentInfo(deptName);
                Integer deptId = departments.get(departments.indexOf(departmentInfo)).getId();
                worker.setDepartment(deptId);
            });
            /*批量插入*/
            if (workerService.insertBatchWorker(list)) {
                return ResponseTool.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseTool.failed("导入失败");
    }

    /**
     * 导入
     * @param file
     * @return
     */
    @PostMapping("/userImport")
    public AjaxResult importUser(MultipartFile file) {
        ImportParams params = new ImportParams();
        //去掉标题行
        params.setTitleRows(1);
        try {
            List<User> list = ExcelImportUtil.importExcel(file.getInputStream(), User.class, params);

            /*批量插入*/
            if (userService.insertBatchUser(list)) {
                return ResponseTool.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseTool.failed("导入失败");
    }

}
