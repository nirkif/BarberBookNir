package com.example.demo.Data;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String name;
    private Timestamp created_at;

    public User() {};

    public User(String username, String name) {
        this.username = username;
        this.name = name;
        Date date = new Date();
        Long timestamp = date.getTime();
        this.created_at = new Timestamp(timestamp);
    }
}
