package com.foodify.foodify.Repository;

import com.foodify.foodify.Entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity,String> {
    Optional<User> findByEmail(String email);
}
