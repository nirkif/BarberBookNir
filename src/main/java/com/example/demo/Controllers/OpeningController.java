package com.example.demo.Controllers;

import com.example.demo.Data.Barber;
import com.example.demo.Data.Opening;
import com.example.demo.Data.User;
import com.example.demo.Repository.IOpeningRepository;
import com.example.demo.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OpeningController{
    private final IOpeningRepository openingRepository;
    private final IUserRepository userRepository;
    OpeningController(@Autowired IOpeningRepository iOpeningRepository, @Autowired IUserRepository userRepository) {
        this.openingRepository = iOpeningRepository;
        this.userRepository = userRepository;
    }
    @GetMapping("/GetOpenings")
        List<Opening> allOpenings() { return openingRepository.findAll(); }
    @GetMapping("/GetOpeningsByOwnerId/{id}")
        List<Opening> allByOwner(@PathVariable String id)
        {
            List<Opening> ownedBy = new ArrayList<>();
            for(Opening opening : allOpenings()){
                if (opening.getBarber().getId().equals(id))
                    ownedBy.add(opening);
            }
            return ownedBy;
        }
    @PostMapping("/addOpening")
    Opening newOpening(@RequestBody Barber barber)
    {
        Opening newOpening = new Opening(barber);
        try {
            System.out.println(newOpening.toString());
            return openingRepository.save(newOpening);
        }
        catch (Exception err){
            System.out.println(err.toString());
            return null;
        }
    }
//    @PostMapping("/createDay/{id}")
//    List<Opening> createDayToBarber(PathVariable id)
//    {
//
//
//
////        Barber s = (Barber)uc.findUserById(id.toString());
////        System.out.println("string: "+s.getName()+"   "+from + " " + to);
//        try {
//            userRepository.findById(id.toString());
//            System.out.println("sucess");
//        }
//        catch(Exception e){
//            System.out.println("failed");
//        }
////        List<Opening> openingsAvailable = new ArrayList<>();
////        LocalTime timeStart = LocalTime.of(from,0);
////        LocalTime timeEnd = LocalTime.of(to,0);
////        while(timeStart.isBefore(timeEnd))
////        {
////            Opening p = new Opening(s , timeStart);
////            openingsAvailable.add(p);
////            timeStart = timeStart.plusMinutes(15);
////        }
////        return openingsAvailable;
//        return null;
//    }
@PostMapping("/CreateDay/{id}")
    List<Opening> createDay(@PathVariable String id,Integer start,Integer end)
{
    try{
        if(start==null || end == null)
        {
            end = 6;
            start = 0;
        }
        Optional<User> b = userRepository.findById(id);
        List<Opening> openingsAvailable = new ArrayList<>();
        LocalTime startTime = LocalTime.of(start,0);
        LocalTime endTime = LocalTime.of(end,0);
        while(startTime.isBefore(endTime))
        {
            Opening p = new Opening((Barber)b.get(),startTime);
            openingsAvailable.add(p);
            startTime = startTime.plusMinutes(15);
            openingRepository.save(p);
        }
        System.out.println("new day has of work has been assigned to "+ b.get().getName()+"\nbetween "+start+" to "+end);
        return openingsAvailable;
    }
    catch (Exception err)
    {
        System.out.println( "Error: "+err.toString());
    }

    return null;

}

}
