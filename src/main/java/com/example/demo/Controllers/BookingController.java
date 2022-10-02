package com.example.demo.Controllers;


import com.example.demo.Data.Barber;
import com.example.demo.Repository.IBookingRepository;
import com.example.demo.Repository.IOpeningRepository;
import com.example.demo.Repository.IUserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exceptions.BookingNotFoundException;
import com.example.demo.exceptions.OpeningNotFoundException;


import com.example.demo.Data.Booking;
import com.example.demo.Data.Opening;

import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
@RestController
public class BookingController {
    private final IBookingRepository bookingRepository;
    private final IOpeningRepository openingRepository;
    private final IUserRepository userRepository;
    private final int bookingPrice = 60;


    BookingController(@Autowired IOpeningRepository openingRepository,@Autowired IUserRepository userRepository, @Autowired IBookingRepository bookingRepository) {
        this.openingRepository = openingRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/setBooking")
    String newOpening(@RequestBody String body)
    {
        JSONObject jsonObject = new JSONObject(body);
        System.out.println(body);
        Opening opening = null;
        Booking newBooking = null;
        try {
            opening = openingRepository.findOpeningByID(jsonObject.getString("openingId"));
            if(opening.getAvailability() == false)
            {
                return "already booked "+opening.getId();
            }
            else {
                opening.setAvailability(false);
                openingRepository.save(opening);
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
            return "invalid opening id";
        }
        try {

            newBooking = new Booking(jsonObject.getString("barberUsername"), jsonObject.getString("username"), jsonObject.getString("openingId"),bookingPrice);
            bookingRepository.save(newBooking);
            return "new Booking created successfully ";
        }
        catch (Exception err){
            try {
                opening.setAvailability(true);
                openingRepository.save(opening);
            }
            catch (Exception error){
                System.out.println(error.toString()+"\nFATAL error: booking not succed and opening is not available "+opening.getId());
            }
            System.out.println(err.toString());
        }
        return "succefully booked "+newBooking.getId()+"on opening "+newBooking.getOpeningId();
    }

    @DeleteMapping("/deleteBooking")

    String deleteUserByID(@RequestBody String body)
    {
        JSONObject jsonObject = new JSONObject(body);
        String bookingId = jsonObject.getString("bookingId");
        String openingId = bookingRepository.findBookingByID(bookingId).getOpeningId();
        Opening opening = openingRepository.findOpeningByID(openingId);
        opening.setAvailability(true);
        openingRepository.save(opening);
        try
        {
            bookingRepository.deleteById(bookingId);
            System.out.println("booking deleted successfully.");
        }
        catch (Exception err)
        {
            opening.setAvailability(false);
            openingRepository.save(opening);
            System.out.printf(err.toString());
            return err.toString();
        }
        System.out.printf("Booking "+bookingId+" has been deleted.");
        return "Booking "+bookingId+" has been deleted.";
    }







}
