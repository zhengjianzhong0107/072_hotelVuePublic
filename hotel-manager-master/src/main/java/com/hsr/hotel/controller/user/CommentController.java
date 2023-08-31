package com.hsr.hotel.controller.user;

import com.hsr.hotel.entity.Comment;
import com.hsr.hotel.response.AjaxResult;
import com.hsr.hotel.response.ResponseTool;
import com.hsr.hotel.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * (Comment)表控制层
 *
 * @author makejava
 * @since 2021-12-29 19:37:12
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;

    @GetMapping("/getAll/{roomNum}")
    public AjaxResult queryAllNoPage(@PathVariable("roomNum") String roomNum) {
        return ResponseTool.success(this.commentService.queryAllComment(roomNum));
    }

    @PostMapping("/getAll")
    public AjaxResult queryAll(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "5") Integer pageSize,
                               @RequestParam(value = "roomType",defaultValue = "") String roomType) {
        return ResponseTool.success(this.commentService.queryAllComment(pageNum, pageSize, roomType));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public AjaxResult queryById(@PathVariable("id") Integer id) {
        return ResponseTool.success(this.commentService.queryById(id));
    }

    /**
     * 新增数据
     * <p>
     * *   "userId": 1,
     * *         "content": "评论测试",
     * *         "roomId": 0109,
     * *         "approve": 2,
     * *         "createTime": null
     *
     * @return 新增结果
     */
    @PostMapping
    public AjaxResult add(Integer typeId,String content , HttpSession session) {
        Comment comment = new Comment(typeId,content);
        Integer userId = (Integer) session.getAttribute("userId");
        comment.setUserId(userId);
        return ResponseTool.success(this.commentService.insert(comment));
    }


    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    public AjaxResult deleteById(@PathVariable("id") Integer id) {
        return ResponseTool.success(this.commentService.deleteById(id));
    }

}

