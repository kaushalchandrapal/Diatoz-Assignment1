package com.example.assignment1.repository;

import com.example.assignment1.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends MongoRepository<Users, Integer> {
    @Query("{ 'createdOn' : ?0 }")
    Optional<Users> findByCreatedOn(String createdOn);

    @Query("{ 'userStatus' : ?0 }")
    List<Users> findUsersByUserStatus(String userStatus);

    @Query("{ 'createdOn' : ?0 ,'userStatus' : ?1 }")
    List<Users> findUsersByCreatedOnAndUserStatus(String createdOn,String userStatus);
}
