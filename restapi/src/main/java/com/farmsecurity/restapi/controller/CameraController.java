package com.farmsecurity.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.farmsecurity.restapi.model.Camera;
import com.farmsecurity.restapi.repository.CameraRepository;

import java.util.*;

@RequestMapping("/camera") // 카메라 테이블
@RestController
public class CameraController {

    @Autowired
    private CameraRepository cameraRepository;

    // 카메라 삽입
    @PostMapping("/insert")
    public Camera insert(@RequestBody Map<String, String> map){
        return cameraRepository.save(
                new Camera(map.get("cameraNum"), map.get("cameraName"), map.get("memberId"))
        );
    }

    // 카메라 전체 검색
    @GetMapping("/select")
    public List<Camera> selectAll(){
        return cameraRepository.findAll();
    }

    // 카메라 검색
    @GetMapping("/select/{cameraNum}") 
    public Camera selectCamera(@PathVariable("cameraNum") String cameraNum){return cameraRepository.findById(cameraNum).orElse(null);}

    // 카메라 삭제
    @DeleteMapping("/delete/{memberId}") // DELETE
    public String deleteCamera(@PathVariable("memberId") String memberId){
        cameraRepository.deleteByMemberId(memberId);
        return "삭제 완료";
    }
}
