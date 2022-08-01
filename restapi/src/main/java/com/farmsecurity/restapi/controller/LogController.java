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
                new Log(map.get("log_num"), map.get("camera_name"),  map.get("camera_num"), map.get("link"), map.get("level"), map.get("time"))
        );
    }

    @GetMapping("/select") // READ
    public List<Log> selectAll(){
        return logRepository.findAll();
    }

    @GetMapping("/select/{log_num}") // READ
    public Log selectLog(@PathVariable("log_num") String id){return logRepository.findById(id).orElse(null);}


    @DeleteMapping("/delete/{log_num}") // DELETE
    public String deleteLog(@PathVariable("log_num") String id){
        logRepository.deleteById(id);
        return "삭제 완료";
    }
}
