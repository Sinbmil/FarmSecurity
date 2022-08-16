package com.farmsecurity.restapi.controller;

import com.farmsecurity.restapi.firebase.FcmMessage;
import com.farmsecurity.restapi.firebase.FirebaseCloudMessageService;
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
    private FirebaseCloudMessageService fcm;

    @PostMapping("/insert") // CREATE
    public Log insert(@RequestBody Map<String, String> map) throws FirebaseMessagingException, IOException {
        List<Camera> camera = cameraRepository.findByCameraNum(map.get("cameraNum"));

        if(camera.size() == 1){
            map.put("cameraName",camera.get(0).getCameraName());
            map.put("memberId", camera.get(0).getId());
            // List<Member> member = memberRepository.findByToken(map.get("memberId"));
            // String s = member.get(0).getToken();
            fcm.sendMessageTo("e2oQs1h2QmWxJ7YdCK9fct:APA91bFR4P0BFbWgzHP_1wSyf4D5P2eRsLvWD94fKufOj47F5RnmAluXqh-KM5XP-rWH78jw5Y9CL6qx2RbrP_wQV6hr0QGq-T9S9xldP62DKNjn7V9dmDMVkf6z0cv37Msr6zaCgnAN","알림","현재 농장의 상태를 확인해주세요");
            // System.out.println("what the" + s);
             return logRepository.save(
                     new Log(map.get("memberId"), map.get("cameraNum"), map.get("cameraName"), map.get("link"), map.get("level"), map.get("time"))
             );
        } else{
            throw new IllegalStateException("카메라가 존재하지 않습니다.");
        }
    }

    @GetMapping("/select") // READ
    public List<Log> selectAll(){
        return logRepository.findAll();
    }


    @GetMapping("/select/{memberId}") // READ
    public Log findByMemberId(@PathVariable("memberId") String memberId){
        List<Log> logs = logRepository.findByMemberId(memberId);
        return logs.get(logs.size()-1);
    }

    @DeleteMapping("/delete/{num}") // DELETE
    public String deleteLog(@PathVariable("num") String num){
        logRepository.deleteById(num);
        return "삭제 완료";
    }
}
