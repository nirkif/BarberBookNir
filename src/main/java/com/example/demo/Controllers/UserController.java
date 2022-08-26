package com.example.demo.Controllers;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.Data.User;
import com.example.demo.Repository.IUserRepository;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController{
    private final IUserRepository repository;

    UserController(IUserRepository repository){
        this.repository = repository;
    }
    @GetMapping("/users")
    List<User> all() { return repository.findAll();}

    @PostMapping("/users")
    User newUser(@RequestBody User newUser)
    {
       // List<User> users = all();

        //System.out.println("is users contain the newUser ? ");
            try {
                System.out.println(newUser.toString());
                return repository.save(newUser);
            }
            catch (Exception err){
                System.out.println(err.toString());
                return null;
            }
//        System.out.println("username already exists: "+newUser.getUsername());
//        return null;
    }

    @GetMapping("/users/{name}")
    User getUserByName(@PathVariable String name){
        User result = repository.findUserByName(name);
        System.out.println(result.getName());
        return result;
        }
//    @DeleteMapping("/users/{id}")
//    void deleteUser(@PathVariable Long id) { repository.deleteById(id);}
}