package com.example.demo.Data;

//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document("users")
public class User {
    @Id
//    @GeneratedValue
    private String id;
    @Indexed(unique = true)
    private String username;
    @Indexed(unique = true)
    private String phoneNum;
    private String name;


    public LocalDate created_at = LocalDate.now();
    public User() {};

    public String getPhoneNum() { return phoneNum; }
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public User(String username, String name, String phoneNumber) {
        this.username = username;
        this.name = name;
        created_at = LocalDate.now();
        this.phoneNum = phoneNumber;
    }


}
