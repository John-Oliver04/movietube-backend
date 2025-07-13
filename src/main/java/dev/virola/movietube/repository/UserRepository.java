package dev.virola.movietube.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.virola.movietube.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    // Additional query methods can be defined here if needed
    Optional<User> findByUsername(String username);

}
