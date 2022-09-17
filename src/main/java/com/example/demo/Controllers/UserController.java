package com.example.demo.Controllers;

import com.example.demo.Data.Barber;
import com.example.demo.Data.Customer;
import com.example.demo.Data.Opening;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.Data.User;
import com.example.demo.Repository.IUserRepository;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController

public class UserController{

    private final IUserRepository repository;
    public IUserRepository getRepository(){return this.repository; }
    public UserController(IUserRepository repository){
        this.repository = repository;
    }
    @GetMapping("/users")
    List<User> all() { return repository.findAll();}
    @GetMapping("/barbers")
    List<User> allBarbers()
    {
        List<User> barbers = new ArrayList<>();
        for(User user : all())
            if(user.getClass().equals(Barber.class))
                barbers.add(user);

        return barbers;
    }
    @GetMapping("/customers")
    List<User> allCustomers()
    {
        List<User> customers = new ArrayList<>();
        for(User user: all())
            if(user.getClass().equals(Customer.class))
                customers.add(user);

        return customers;
    }
    @PostMapping("/addUser")
    User newUser(@RequestBody User newUser)
    {
            try {
                System.out.println(newUser.toString());
                return repository.save(newUser);
            }
            catch (Exception err){
                System.out.println(err.toString());
                return null;
            }
    }

    @DeleteMapping("/deleteUser/{id}")
    Void deleteUser(@PathVariable String id){
        if(findUserById(id) == null)
            return null;
        System.out.println("user "+findUserById(id).getName()+" has been deleted.");
        repository.deleteById(id);
        return null;
    }

    @GetMapping("/findUser/{name}")
    User getUserByName(@PathVariable String name)
    {
        User result = repository.findUserByName(name);
        System.out.println(result.getName());
        return result;
    }
    @GetMapping("/findUserById/{id}")
    public User findUserById(@PathVariable String id){
        List<User> users = all();
        for(User user : users)
            if(id.equals(user.getId()))
                return user;

        System.out.println("User "+id+" not found.");
        return null;
    }




    @PostMapping("/addBarber")
    User newBarber(@RequestBody User newBarber)
    {
        Barber barb = new Barber(newBarber);
        try{
            System.out.println(barb.toString());
            return repository.save(barb);
        }
        catch (Exception err){
            System.out.println(err.toString());
            return null;
        }
    }
    @PostMapping("/addCustomer")
    User newCustomer(@RequestBody User newCustomer)
    {
        Customer cust = new Customer(newCustomer);
        try{
            System.out.println(cust.toString());
            return repository.save(cust);
        }
        catch (Exception err){
            System.out.println(err.toString());
            return null;
        }
    }




    }
