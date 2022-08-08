package com.farmsecurity.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.farmsecurity.restapi.model.Camera;

import java.util.List;

@Repository
public interface CameraRepository extends JpaRepository<Camera, String> { // 카메라 레포지토리

    List<Camera> findByCameraNumAndId(String cameraNum, String id); // 카메라 이름, 아이디 찾기

}
