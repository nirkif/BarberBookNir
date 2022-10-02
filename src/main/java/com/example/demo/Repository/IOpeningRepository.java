package com.example.demo.Repository;
import com.example.demo.Data.Opening;
import com.example.demo.Data.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
//
public interface IOpeningRepository extends MongoRepository<Opening, String>{
//    Opening findAllByBarber(String barberName);

    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    Opening findAll(String category);

    @Query("{id:'?0'}")
    Opening findOpeningByID(String id);

    //query all openings in range
    @Query("{barberUsername:'?0', startTime:{ $gte: ?1, $lte: ?2 }}")
    List<Opening> findOpeningsInRangeByBarber(String barberUsername, LocalDateTime startTime, LocalDateTime endTime);

    @Query("{barberUsername:'?0'")
    List<Opening> findOpeningsByBarberUsername(String barberUsername);


}
