package com.example.demo.Repository;
import com.example.demo.Data.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IUserRepository extends MongoRepository<User, String> {

    @Query("{name:'?0'}")
    User findUserByName(String name);

    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<User> findAll(String category);

    //find all barbers

    public long count();

}