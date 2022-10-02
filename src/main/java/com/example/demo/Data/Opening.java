package com.example.demo.Data;

import com.example.demo.Utility.GetTimeStamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@Document("openings")
public class Opening {  //    every opening must have half an hour
    @Id
    private String id;
    private String barberUserName;
    private String barberName;
    public boolean isAvailable = true;
    public LocalDateTime startTime;
    public LocalDateTime endTime;

    public String openingInfo;
    public Timestamp timeStamp;

    public String getBarberUserName(){ return this.barberUserName; }
    public void setBarberUserName(String userName) { this.barberUserName = userName; }

    public String getBarberName(){ return this.barberName; }
    public void setBarberName(String name) { this.barberName = name; }


    public boolean getAvailability(){return this.isAvailable; }
    public void setAvailability(boolean status) { this.isAvailable = status; }

    public Opening(String username){
        this.barberUserName = username;
        timeStamp = new Timestamp(System.currentTimeMillis());
        System.out.println("opening: \n"+" created succesfully");

    }
    public Opening(String userName,String name,String startTime)
    {
        timeStamp = new Timestamp(System.currentTimeMillis());
        LocalDateTime date = LocalDateTime.of(timeStamp.toLocalDateTime().getYear(),
                timeStamp.toLocalDateTime().getMonth(),
                timeStamp.toLocalDateTime().getDayOfMonth(),
                Integer.parseInt(startTime)
                ,0);

        this.startTime = date;//date.toString().split("T")[0]+" "+date.toString().split("T")[1];
        this.endTime = date.plusMinutes(30);//date.toString().split("T")[0]+" "+date.toString().split("T")[1];;
        this.openingInfo = "new opening created with "+this.barberName+"\n at "+date.toString().split("T")[0]
                +"from "+date.toString().split("T")[1]+" until "+date.toString().split("T")[1];

        this.barberUserName = userName;
        this.barberName = name;
        //dateShown = ts.toString().substring(0, 19);
        System.out.println(openingInfo);
    }


    @Override
    public String toString() {
        return "new opening with "+barberName+" at "+startTime+" until "+endTime;

    }
}
