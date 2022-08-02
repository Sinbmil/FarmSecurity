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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="num")
    private long num;

    private String camera_name;

    @JoinColumn(name="member_id")
    private String id;

    public Camera(String camera_name, String id){
        this.camera_name = camera_name;
        this.id = id;
    }
}
