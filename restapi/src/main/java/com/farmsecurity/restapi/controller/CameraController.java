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
                new Camera(map.get("cnum"), map.get("camera_name"), map.get("id"))
        );
    }

    @GetMapping("/select") // READ
    public List<Camera> selectAll(){
        return cameraRepository.findAll();
    }

    @GetMapping("/select/{cnum}") // READ
    public Camera selectCamera(@PathVariable("cnum") String cnum){return cameraRepository.findById(cnum).orElse(null);}

    @DeleteMapping("/delete/{cnum}") // DELETE
    public String deleteCamera(@PathVariable("cnum") String cnum){
        cameraRepository.deleteById(cnum);
        return "삭제 완료";
    }
}
