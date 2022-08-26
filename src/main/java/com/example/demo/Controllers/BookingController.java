//package com.example.demo.Controllers;
//
//import com.example.demo.Repository.IBookingRepository;
//import org.springframework.web.bind.annotation.RestController;
//import com.example.demo.exceptions.BookingNotFoundException;
//import com.example.demo.exceptions.OpeningNotFoundException;
//
//import com.example.demo.Repository.IBookingRepository;
//import com.example.demo.Repository.IOpeningRepository;
//
//import com.example.demo.Data.Booking;
//import com.example.demo.Data.Opening;
//
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@RestController
//public class BookingController {
//    private final IBookingRepository repository;
//    private final IOpeningRepository openingRepository;
//
//    BookingController(IBookingRepository repository, IOpeningRepository openingRepo) {
//        this.repository = repository;
//        this.openingRepository = openingRepo;
//    }
//
//    @GetMapping("/bookings")
//    List<Booking> all() {
////        return repository.findAll();
//        return null;
//    }
//
//    @GetMapping("user/{userId}/bookings")
//    List<Booking> allByUser(@PathVariable Long userId) {
////        return repository.findAllByUserId(userId);
//        return null;
//    }
//
//    @PostMapping("/bookings")
//    Booking newBooking(@RequestBody Booking newBooking) {
//        List<Opening> mentorOpenings = openingRepository.findAllByOwner(newBooking.getMentorId());
//        Opening opening = null;
//        //check if mentor has an opening
//        return null;
//    }
//
//
//
//
//
//}
