package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value ="/api/users", headers = "Accept=application/json")
public class UsersController {
//    private final Post POST1 = new Post();
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

    @GetMapping("/email")
    public User getByEmail(@RequestParam String email){
        User user = new User(1l,"User420",email,"pasword1234",null, User.Role.ADMIN);
        return user;
    }

    @GetMapping("/username")
    public User getByUsername(@RequestParam String username){
        User user = new User(1L, username,"email450","password450",null, User.Role.ADMIN);
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

    @PutMapping("{userId}/updatePassword")
    private void updateUserPassword(@PathVariable Long userId, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword){
        System.out.printf("Backend wants to update user password for id %d with old pw %s new pw %s\n", userId, oldPassword, newPassword);
    }

    @DeleteMapping("{userId}")
    private void deleteById(@PathVariable Long userId){
        System.out.println("deleting user with ID: " + userId);
    }
}
