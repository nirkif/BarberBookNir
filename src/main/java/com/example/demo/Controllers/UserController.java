package com.example.demo.Controllers;

import com.example.demo.Data.Barber;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.Data.User;
import com.example.demo.Repository.IUserRepository;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController{
    private final IUserRepository repository;

    UserController(IUserRepository repository){
        this.repository = repository;
    }




    //                ||    USER MAPPING      ||
    //                ||                      ||
    //                ||                      ||
    //                ||    POST REQUESTS     ||
    //                ||                      ||

    @PostMapping("/addUser")
    User newUser(@RequestBody User newUser)
    {
        try
        {
            System.out.println(newUser.toString());
            return repository.save(newUser);
        }
        catch (Exception err)
        {
            System.out.println(err.toString());
            return null;
        }
    }

    //                ||                      ||
    //                ||    GET REQUESTS      ||
    //                ||                      ||

    @GetMapping("/allUsers")
    List<User> all()
    {
        return repository.findAll();
    }

    @GetMapping("/findByName/{name}")
    List<User> getUserByNameTest(@PathVariable String name)
    {
        try
        {
            List<User> users= repository.findUserByName(name);
            for(User user : users)
            {
                System.out.println(user.toString()+"\n\n");
            }
            return users;
        }
        catch (Exception err)
        {
            System.out.println(err.toString());
            return null;
        }
    }
    @GetMapping("/findByID/{id}")
    User getUserByID(@PathVariable String id)
    {
        try
        {
            User result = repository.findUserById(id);
            System.out.println(result.toString());
            return result;
        }
        catch (Exception err)
        {
            System.out.println(err.toString());
            return null;
        }
    }

    //                ||                      ||
    //                ||   DELETE REQUESTS    ||
    //                ||                      ||

    @DeleteMapping("deleteUser/{id}")

    Void deleteUserByID(@PathVariable String id)
    {
        try
        {
            repository.deleteById(id);
        }
        catch (Exception err)
        {
            System.out.printf(err.toString());
        }
        System.out.printf("User "+id+" has been deleted.");
        return null;
    }

    //                ||    BARBER MAPPING    ||
    //                ||                      ||
    //                ||                      ||
    //                ||    POST REQUESTS     ||
    //                ||                      ||

    @PostMapping("/addBarber")
    User newBarber(@RequestBody Barber newBarber)
    {
        System.out.println("\nnew Barber");
        Barber b = newBarber;
        try
        {
            System.out.println(b.toString());
            return repository.save(newBarber);
        }
        catch (Exception err)
        {
            System.out.println(err.toString());
            return null;
        }
    }

    //                ||                      ||
    //                ||    GET  REQUESTS     ||
    //                ||                      ||

    @GetMapping("/allBarbers")
    List<User> allBarbers()
    {
        List<User> barbers = new ArrayList<>();
        try
        {
            for(User user : all())
            {
                if(user.getClass().equals(Barber.class))
                    barbers.add(user);
            }
        }
        catch (Exception err)
        {
            System.out.println(err.toString());
            return null;
        }
        return barbers;
    }

}