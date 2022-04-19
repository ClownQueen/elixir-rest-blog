package com.example.restblog.web;

import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value ="/api/users", headers = "Accept=application/json")
public class UsersController {
    @GetMapping
    private List<User> getAll(){
        ArrayList<User> user = new ArrayList<>();
        user.add(new User(1L, "User 1", "email 1m", "password 1", null, User.Role.ADMIN));
        user.add(new User(2L, "User 2", "email 2", "password2", null, User.Role.ADMIN));
        user.add(new User(3L, "User 3", "email 3", "password 3", null, User.Role.ADMIN));
        return user;
    }

    @GetMapping("{userId}")
    public User getById(@PathVariable Long userId){
        User user = new User(userId, "Bob Smith", "bobsemail", "password 1", null, User.Role.ADMIN);
        return user;
    }

    @PostMapping
    private void create(@RequestBody User newUser){
        System.out.println("Ready to add user: " + newUser);
    }

    @PutMapping("{userId}")
    private void update(@PathVariable Long userId, @RequestBody User updatedUser){
        System.out.println("Updating user number : " + userId + " with " + updatedUser);
    }

    @DeleteMapping("{userId}")
    private void deleteById(@PathVariable Long userId){
        System.out.println("deleting user with ID: " + userId);
    }
}
