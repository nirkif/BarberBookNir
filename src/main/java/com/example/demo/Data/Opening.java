package com.example.demo.Data;

import com.example.demo.Utility.GetTimeStamp;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
import java.sql.Timestamp;


public class Opening {
    private Long id;
    private long owner;
    private String date_time;
    private Timestamp created_at;
    private Timestamp modified_at;

    public Opening(){}
    public Opening(Long owner,String date_time){
        this.owner = owner;
        this.date_time = date_time;
        this.created_at = new GetTimeStamp().getTimestamp();
        this.modified_at = new GetTimeStamp().getTimestamp();
    }
}
