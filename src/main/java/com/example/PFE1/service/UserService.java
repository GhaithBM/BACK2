package com.example.PFE1.service;



import java.util.List;

import com.example.PFE1.model.User;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User ajouterUser(User user);
    String signin(String email, String password);
    String signup(User user);
    User updateUser(Long userId, User userDetails);
    void deleteUser(Long userId);
    List<User> rechercherParMotCle(String keyword);
}

