package com.farmsecurity.restapi.controller;

import com.farmsecurity.restapi.model.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.farmsecurity.restapi.model.Log;
import com.farmsecurity.restapi.repository.LogRepository;
import com.farmsecurity.restapi.repository.CameraRepository;

import java.util.*;

@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    private LogRepository logRepository;

    private CameraRepository cameraRepository;

    @PostMapping("/insert") // CREATE
    public Log insert(@RequestBody Map<String, String> map){
        List<Camera> camera = cameraRepository.findByCnum(map.get("cnum"));
        if(camera.size() == 1){
            map.put("camera_name",camera.get(0).toString());
             return logRepository.save(
                     new Log(map.get("camera_num"), map.get("camera_name"), map.get("link"), map.get("level"), map.get("time"))
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
