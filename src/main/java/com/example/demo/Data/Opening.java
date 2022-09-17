package com.example.demo.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Document("openings")
public class Opening {
    @Id
    private String id;
    private Barber barber;
    public LocalDate date_time;

    public LocalTime day_time;

    public String dateShown;

    public Barber getBarber(){ return this.barber; }
    public Opening(){}
    public Opening(Barber barber){

        this.barber = barber;
        this.date_time = LocalDate.now();
        this.day_time = LocalTime.now();
        dateShown = date_time.toString() +" | "+day_time.toString().substring(0,5);
        //this.toString();
    }
    public Opening(Barber barber ,LocalTime lt)
    {
        this.barber = barber;
        this.date_time = LocalDate.now();
        this.day_time = lt;
        dateShown = date_time.toString() +" | "+day_time.toString().substring(0,5);
    }

//    public List<Opening> createDay(Barber barber, int from, int to)
//    {
//        List<Opening> openingsAvailable = new ArrayList<>();
//        LocalTime timeStart = LocalTime.of(from,0);
//        LocalTime timeEnd = LocalTime.of(to,0);
//        while(timeStart.isBefore(timeEnd))
//        {
//            Opening p = new Opening(barber , timeStart);
//            openingsAvailable.add(p);
//            timeStart = timeStart.plusMinutes(15);
//        }
//        return openingsAvailable;
//    }

    @Override
    public String toString() {
        return "new opening with barber "+this.barber.getName()+"\nat "+dateShown + " until "+
                day_time.plusMinutes(15).toString().substring(0,5);
    }
}
