package com.farmsecurity.restapi.controller;

import com.farmsecurity.restapi.firebase.FcmMessage;
import com.farmsecurity.restapi.firebase.FirebaseCloudMessageService;
import com.farmsecurity.restapi.firebase.MainController;
import com.farmsecurity.restapi.model.Camera;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.farmsecurity.restapi.model.Log;
import com.farmsecurity.restapi.repository.LogRepository;
import com.farmsecurity.restapi.repository.CameraRepository;

import java.io.IOException;
import java.util.*;

@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private MainController maincontroller;

    @Autowired
    private FirebaseCloudMessageService fcm;


    @PostMapping("/insert") // CREATE
    public Log insert(@RequestBody Map<String, String> map) throws FirebaseMessagingException, IOException {
        List<Camera> camera = cameraRepository.findByCameraNumAndId(map.get("cameraNum"), map.get("id"));
        if(camera.size() == 1){
            map.put("cameraName",camera.get(0).getCameraName());
            map.put("id",camera.get(1).getId());
            fcm.sendMessageTo("cAn4cdFJQImXCx2o0zj3Qa:APA91bEfwsYFh1sV78d-VKpAzOcjD-OyCJlREQirIMy12Ua82C2Ukho_XGjGd6gQW7g5RdMshnyJFhqngqkQDh7e4p70HjElaVSEkyLu60OBkVRmicQpvXs-uWSMqBCt-8Gi36z5oSre","알림","현재 농장의 상태를 확인해주세요");
             return logRepository.save(
                     new Log(map.get("id"),map.get("cameraNum"), map.get("cameraName"), map.get("link"), map.get("level"), map.get("time"))
             );
        } else{
            throw new IllegalStateException("카메라가 존재하지 않습니다.");
        }
    }

    @GetMapping("/select") // READ
    public List<Log> selectAll(){
        return logRepository.findAll();
    }

    @GetMapping("/select/{num}") // READ
    public Log selectLog(@PathVariable("num") String num){return logRepository.findById(num).orElse(null);}

    @DeleteMapping("/delete/{num}") // DELETE
    public String deleteLog(@PathVariable("num") String num){
        logRepository.deleteById(num);
        return "삭제 완료";
    }
}
