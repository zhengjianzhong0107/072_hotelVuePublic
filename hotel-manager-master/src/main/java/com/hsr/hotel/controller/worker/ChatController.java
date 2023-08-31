package com.hsr.hotel.controller.worker;

import com.hsr.hotel.entity.Worker;
import com.hsr.hotel.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private WorkerService workerService;

    @GetMapping("/worker")
    public List<Worker> getWorker(
            @RequestParam(defaultValue = "") String search) {
        return (List<Worker>) workerService.findAll(search);
    }


}
