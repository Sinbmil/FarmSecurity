package com.farmsecurity.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.farmsecurity.restapi.model.Camera;

import java.util.List;

@Repository
public interface CameraRepository extends JpaRepository<Camera, String> {

}
