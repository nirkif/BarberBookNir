package com.example.demo.Controllers;

import com.example.demo.Data.User;
import com.example.demo.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserController {
    @Autowired
    private IUserRepository repository;
    @PostMapping("/createUsers")
    User newUser(@RequestBody User newUser){ return repository.save(newUser);}

}
