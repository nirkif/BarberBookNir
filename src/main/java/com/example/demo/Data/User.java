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

    private String name;


    public LocalDate created_at = LocalDate.now();
    //private static SimpleDateFormat form1 = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
   //private Date currDate = new Date();
    //private Timestamp created_at;
    public User() {};

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public User(String username, String name) {
        this.username = username;
        this.name = name;
        created_at = LocalDate.now();
    }

    @Override
    public String toString() {
        return "ID: "+this.id+"\nUsername: "+this.getUsername()+"\nName: "+this.name+"\nCreated at: "+ created_at;
    }
}
