package com.farmsecurity.restapi.controller;

import com.farmsecurity.restapi.firebase.FcmMessage;
import com.farmsecurity.restapi.firebase.FirebaseCloudMessageService;
import com.farmsecurity.restapi.firebase.MainController;
import com.farmsecurity.restapi.model.Camera;
import com.farmsecurity.restapi.model.Member;
import com.farmsecurity.restapi.repository.MemberRepository;
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
    private MemberRepository memberRepository;
    @Autowired
    private FirebaseCloudMessageService fcm;

    @PostMapping("/insert") // CREATE
    public Log insert(@RequestBody Map<String, String> map) throws FirebaseMessagingException, IOException {
        List<Camera> camera = cameraRepository.findByCameraNum(map.get("cameraNum"));
        // List<Member> member = memberRepository.findByToken(map.get("token"));

        if(camera.size() == 1){
            map.put("cameraName",camera.get(0).getCameraName());
            map.put("id", camera.get(0).getId());
            fcm.sendMessageTo("clZ1uXFKSrm7vIVnkYUJXf:APA91bFQwzSMaE4pquKNnW-XB3aO_xMOrAhYMkxNXmreaKarn2rRZQoCdhEXnHpl6buQvqYSrM5LdG06-uBBduPLJyTKmPa4-noNtljMW9uslyaZim3a6MheW5PDYYZNQ4xldoOcuy3G","알림","현재 농장의 상태를 확인해주세요");
             return logRepository.save(
                     new Log(map.get("id"), map.get("cameraNum"), map.get("cameraName"), map.get("link"), map.get("level"), map.get("time"))
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
