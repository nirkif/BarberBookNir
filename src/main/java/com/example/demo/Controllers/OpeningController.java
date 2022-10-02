package com.example.demo.Controllers;

import com.example.demo.Data.Barber;
import com.example.demo.Data.Opening;
import com.example.demo.Repository.IOpeningRepository;
import com.example.demo.Repository.IUserRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OpeningController{
    private final IOpeningRepository openingRepository;
    private final IUserRepository userRepository;
    OpeningController(@Autowired IOpeningRepository iOpeningRepository, @Autowired IUserRepository userRepository) {
        this.openingRepository = iOpeningRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/addOpening")
    void newOpening(@RequestBody String body)
    {
        JSONObject jsonObject = new JSONObject(body);
        Opening newOpening;
        //System.out.println(jsonObject.getString("startTime"));
        System.out.println(body);
        Barber barber = (Barber)userRepository.findUserByUserName(jsonObject.getString("userName"));
        try {
            newOpening = new Opening(barber.getUsername(),barber.getName(),jsonObject.getString("startTime"));
            openingRepository.save(newOpening);
            System.out.println("new opening created successfully with barber "+newOpening.getBarberName()+" at "+newOpening.startTime+" until "+newOpening.endTime);
        }
        catch (Exception err){
            System.out.println(err.toString());
        }
    }
    @GetMapping("/allOpenings/")
    List<Opening> allOpenings()
    {
        return openingRepository.findAll();
    }

    @GetMapping("/getOpenings/{username}")
    List<Opening> getOpeningsByUserName(@PathVariable String username)
    {

        return openingRepository.findOpeningsByBarberUsername(username);
    }

    @DeleteMapping("/deleteOpening")

    String deleteUserByID(@RequestBody String body)
    {

        JSONObject jsonObject = new JSONObject(body);
        String id = jsonObject.getString("openingId");
        try
        {
            if(openingRepository.findOpeningByID(id).getAvailability() == false)
                return "cannot delete because opening already booked.";
            openingRepository.deleteById(id);
        }
        catch (Exception err)
        {
            System.out.printf(err.toString());
        }
        System.out.printf("Opening "+id+" has been deleted.");
        return "Opening "+id+" has been deleted.";
    }





    @PostMapping("/addOpeningsByTime")
    List<Opening> newOpeningsAdd(@RequestBody Barber barber,Integer from ,Integer to)
    {
        return null;
    }






















////    @GetMapping("/GetOpenings")
////        List<Opening> allOpenings() { return openingRepository.findAll(); }
////    @GetMapping("/GetOpeningsByBarberName/{name}")
////    List<Opening> allByOwner(@PathVariable String name)
////    {
////        List<Opening> ownedBy = new ArrayList<>();
////        for(Opening opening : allOpenings()){
////            System.out.println(opening.getBarber().toString());
////            if (opening.getBarber().getName().equals(name))
////                ownedBy.add(opening);
////        }
////        return ownedBy;
////    }
////    @GetMapping("/GetOpeningsByBarberId/{id}")
////        List<Opening> allByOwner(@PathVariable String id)
////        {
////            List<Opening> ownedBy = new ArrayList<>();
////            for(Opening opening : allOpenings()){
////                System.out.println(opening.getBarber().toString());
////                if (opening.getBarber().getId().equals(id))
////                    ownedBy.add(opening);
////            }
////            return ownedBy;
////        }
////    @PostMapping("/addOpening")
////    Opening newOpening(@RequestBody Barber barber)
////    {
////
////        Opening newOpening = new Opening();
////        try {
////            System.out.println(newOpening.toString());
////            return openingRepository.save(newOpening);
////        }
////        catch (Exception err){
////            System.out.println(err.toString());
////            return null;
////        }
////    }
////    @PostMapping("/addOpeningsByTime")
////    List<Opening> newOpeningsAdd(@RequestBody Barber barber,Integer from ,Integer to)
////    {
////        return null;
////    }
//
////    @PostMapping("/createDay/{id}")
////    List<Opening> createDayToBarber(PathVariable id)
////    {
////
////
////
//////        Barber s = (Barber)uc.findUserById(id.toString());
//////        System.out.println("string: "+s.getName()+"   "+from + " " + to);
////        try {
////            userRepository.findById(id.toString());
////            System.out.println("sucess");
////        }
////        catch(Exception e){
////            System.out.println("failed");
////        }
//////        List<Opening> openingsAvailable = new ArrayList<>();
//////        LocalTime timeStart = LocalTime.of(from,0);
//////        LocalTime timeEnd = LocalTime.of(to,0);
//////        while(timeStart.isBefore(timeEnd))
//////        {
//////            Opening p = new Opening(s , timeStart);
//////            openingsAvailable.add(p);
//////            timeStart = timeStart.plusMinutes(15);
//////        }
//////        return openingsAvailable;
////        return null;
////    }
//    @PostMapping("/CreateWorkDay/{id}")
//    List<Opening> createWorkDay(@RequestBody BarberShift bs)
//    {
//
//        try
//        {
////            Optional<User> barber = userRepository.findById(id);
////            System.out.println(barber);
////            List<Opening> workDay = new ArrayList<>();
////            Integer intStart = Integer.parseInt(start);
////            Integer intEnd = Integer.parseInt(end);
////            LocalTime startWorkDay = LocalTime.of(intStart,0);
////            LocalTime endWorkDay = LocalTime.of(intEnd,0);
////
////            if(barber.isPresent()) {
////                while (startWorkDay.isBefore(endWorkDay)) {
////                    Opening p = new Opening((Barber)barber.get(), startWorkDay);
////                    startWorkDay.plusMinutes(30);
////                    openingRepository.save(p);
////                    System.out.println("Start of day");
////                    System.out.println(p.toString());
////                }
////            }
////            else {
////                System.out.println("the variable Optional<User> barber is empty.");
////            }
//
//        }
//        catch (Exception err)
//        {
//            System.out.printf(err.toString());
//        }
//        return null;
//    }
//
//@PostMapping("/CreateDay/{id}")
//    List<Opening> createDay(@PathVariable String id,Integer start,Integer end)
//{
//    try{
//        if(start==null || end == null)
//        {
//            end = 6;
//            start = 0;
//        }
//        Optional<User> b = userRepository.findById(id);
//        List<Opening> openingsAvailable = new ArrayList<>();
//        LocalTime startTime = LocalTime.of(start,0);
//        LocalTime endTime = LocalTime.of(end,0);
//        while(startTime.isBefore(endTime))
//        {
//            Opening p = new Opening(startTime);
//            openingsAvailable.add(p);
//            startTime = startTime.plusMinutes(15);
//            openingRepository.save(p);
//        }
//        System.out.println("new day has of work has been assigned to "+ b.get().getName()+"\nbetween "+start+" to "+end);
//        return openingsAvailable;
//    }
//    catch (Exception err)
//    {
//        System.out.println( "Error: "+err.toString());
//    }
//
//    return null;
//
//}

}
