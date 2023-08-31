package com.hsr.hotel.controller.worker;

import com.hsr.hotel.entity.ChatMsg;
import com.hsr.hotel.entity.Worker;
import com.hsr.hotel.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class WsController {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(HttpServletRequest request, ChatMsg chatMsg){
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        Worker user = workerService.selectById(userId);
        chatMsg.setFrom(user.getUsername());
        chatMsg.setFromNickName("operator");
        chatMsg.setDate(LocalDateTime.now());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(),"/queue/chat",chatMsg);
    }
}
