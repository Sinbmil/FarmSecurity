package com.farmsecurity.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.farmsecurity.restapi.model.Log;
import com.farmsecurity.restapi.repository.LogRepository;

import java.util.*;

@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    private LogRepository logRepository;

    @PostMapping("/insert") // CREATE
    public Log insert(@RequestBody Map<String, String> map){
        return logRepository.save(
                new Log(map.get("camera_num"), map.get("link"), map.get("level"), map.get("time"))
        );
    }

    @GetMapping("/selectLog") // READ
    public List<Log> selectAll(){
        return logRepository.findAll();
    }

    @GetMapping("/select/{num}") // READ
    public Log selectLog(@PathVariable("num") String log_num){return logRepository.findById(log_num).orElse(null);}


    @DeleteMapping("/delete/{num}") // DELETE
    public String deleteLog(@PathVariable("num") String log_num){
        logRepository.deleteById(log_num);
        return "삭제 완료";
    }
}
