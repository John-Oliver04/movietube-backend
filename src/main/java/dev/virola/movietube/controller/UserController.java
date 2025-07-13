package dev.virola.movietube.controller;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.virola.movietube.model.Movie;
import dev.virola.movietube.model.User;
import dev.virola.movietube.repository.UserRepository;
import dev.virola.movietube.service.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // Adjust the origin as needed
public class UserController {
 
    @Autowired private UserRepository repo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtUtil jwtUtil;
    

     @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        if (repo.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return ResponseEntity.ok("Signup successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> found = repo.findByUsername(user.getUsername());
        if (found.isPresent() && encoder.matches(user.getPassword(), found.get().getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    // CONTROLLER OF MOVIE CONTROLLER
    @PostMapping("/{username}/favorites")
    public ResponseEntity<String> addFavorite(@PathVariable String username, @RequestBody Movie movie) {
        Optional<User> userOpt = repo.findByUsername(username);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        User user = userOpt.get();
        user.getFavoriteMovies().add(movie);
        repo.save(user);
        return ResponseEntity.ok("Added to favorites");
    }

    @PostMapping("/{username}/watchlist")
    public ResponseEntity<String> addToWatchlist(@PathVariable String username, @RequestBody Movie movie) {
        Optional<User> userOpt = repo.findByUsername(username);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        User user = userOpt.get();
        user.getWatchListMovies().add(movie);
        repo.save(user);
        return ResponseEntity.ok("Added to watchlist");
    }

    @GetMapping("/{username}/library")
    public ResponseEntity<Map<String, Object>> getLibrary(@PathVariable String username) {
        Optional<User> userOpt = repo.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Map<String, Object> library = Map.of(
                "favorites", user.getFavoriteMovies(),
                "watchlist", user.getWatchListMovies()
            );
            return ResponseEntity.ok(library);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
