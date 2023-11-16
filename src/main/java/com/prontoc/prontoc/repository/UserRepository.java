package com.prontoc.prontoc.repository;

import com.prontoc.prontoc.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);
}