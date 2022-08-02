package com.farmsecurity.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="camera")
public class Camera {

    @Id
    private String camera_num;

    private String camera_name;

    @JoinColumn(name="member_id")
    private String id;

    public Camera(String camera_num, String camera_name, String id){
        this.camera_num = camera_num;
        this.camera_name = camera_name;
        this.id = id;
    }
}
