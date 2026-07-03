package com.railwayguide.service;

import com.railwayguide.entity.User;
import com.railwayguide.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        // Return all users excluding password field for security
        List<User> users = userRepository.findAll();
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id).map(user -> {
            user.setPassword(null);
            return user;
        });
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username).map(user -> {
            user.setPassword(null);
            return user;
        });
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(Integer id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user.setUsername(userDetails.getUsername());
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(userDetails.getPassword());
        }
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
