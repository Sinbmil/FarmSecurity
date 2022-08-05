package com.farmsecurity.restapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name ="camera")
public class Camera {

    @Id
    private String cnum;

    private String camera_name;

    @JoinColumn(name="member_id")
    private String id;

    public Camera(String cnum, String camera_name, String id){
        this.cnum = cnum;
        this.camera_name = camera_name;
        this.id = id;
    }
}
