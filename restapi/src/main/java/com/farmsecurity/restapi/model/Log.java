package com.farmsecurity.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "log")

public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="log_num")
    private String log_num;
    private String camera_name;
    private String camera_num;
    private String link;
    private String level;
    private String time;

    @CreationTimestamp
    private Date createdAt;

    public Log(String log_num, String camera_name, String camera_num, String link, String level, String time){
        this.log_num = log_num;
        this.camera_name = camera_name;
        this.camera_num = camera_num;
        this.link = link;
        this.level = level;
        this.time = time;
    }
}
