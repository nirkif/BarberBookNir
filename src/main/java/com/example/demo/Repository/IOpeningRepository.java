package com.example.demo.Repository;
import com.example.demo.Data.Opening;
import com.example.demo.Data.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
//
public interface IOpeningRepository extends MongoRepository<Opening, String>{
    List<Opening> findAllByBarber(String barberName);

    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<Opening> findAll(String category);

}
