package com.example.demo.Data;
import com.example.demo.Utility.GetTimeStamp;


import java.sql.Timestamp;

//@Entity
public class Booking {
//    @Id
//    @GeneratedValue
    private long id;
    private long userId;
    private long mentorId;
    private String message;
    private String date_time;
    private Timestamp created_at;
    private Timestamp modified_at;

    public Booking(){};

    public Booking(Long mentorId,Long userId,String message, String date_time){
        this.mentorId = mentorId;
        this.userId = userId;
        this.message = message;
        this.created_at = new GetTimeStamp().getTimestamp();
        this.modified_at = new GetTimeStamp().getTimestamp();
    }



}
