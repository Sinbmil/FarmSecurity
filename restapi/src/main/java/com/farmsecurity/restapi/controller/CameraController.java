package com.farmsecurity.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.farmsecurity.restapi.model.Camera;
import com.farmsecurity.restapi.repository.CameraRepository;

import java.util.*;

@RequestMapping("/camera")
@RestController
public class CameraController {

    @Autowired
    private CameraRepository cameraRepository;

    @PostMapping("/insert") // CREATE
    public Camera insert(@RequestBody Map<String, String> map){
        return cameraRepository.save(
                new Camera(map.get("camera_num"), map.get("camera_name"), map.get("id"))
        );
    }

    @GetMapping("/select") // READ
    public List<Camera> selectAll(){
        return cameraRepository.findAll();
    }

    @GetMapping("/select/{camera_num}") // READ
    public Camera selectCamera(@PathVariable("camera_num") String camera_num){return cameraRepository.findById(camera_num).orElse(null);}

    @DeleteMapping("/delete/{camera_num}") // DELETE
    public String deleteCamera(@PathVariable("camera_num") String camera_num){
        cameraRepository.deleteById(camera_num);
        return "삭제 완료";
    }
}
