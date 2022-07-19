package com.example.demo.Repository;

import com.example.demo.Data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IUserRepository extends JpaRepository<User, Long>{

}
