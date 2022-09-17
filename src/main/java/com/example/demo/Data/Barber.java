package com.example.demo.Data;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@Document("users")
public class Barber extends User{
    public Barber(User newBarber){
        super(newBarber.getUsername(),newBarber.getName(),newBarber.getPhoneNum());
        System.out.println(this.getClass().toString());
        newBarber.toString();
    }
    public Barber(){}


    @Override
    public String toString() {
        return "Type: Barber\nID: "+super.getId()+"\nUsername: "+super.getUsername()+"\nName: "+super.getName()+"\nPhone Number: "+super.getPhoneNum()+"\nCreated at: "+ created_at;
    }
}
