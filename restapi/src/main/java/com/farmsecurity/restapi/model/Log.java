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
    @Column(name="num")
    private long num;
    @JoinColumn(name="camera_cnum")
    private String cnum;
    private String camera_name;
    private String link;
    private String level;
    private String time;

    @CreationTimestamp
    private Date createdAt;

    public Log(String cnum, String camera_name, String link, String level, String time){
        this.cnum = cnum;
        this.camera_name = camera_name;
        this.link = link;
        this.level = level;
        this.time = time;
    }
}
