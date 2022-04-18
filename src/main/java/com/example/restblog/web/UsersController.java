package com.example.restblog.web;

import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value ="/api/users", headers = "Accept=application/json")
public class UsersController {
    @GetMapping
    private List<User> getAll(){
        ArrayList<User> user = new ArrayList<>();
        user.add(new User(1L, "twinArmageddons", "solluxrules@gmail.com", "toohackerliife", LocalDate.now(), User.Role.ADMIN));
        user.add(new User(2L, "carcinogenesis", "awesomeKarkat@gmail.com", "COOLESTGUYINTHEROOM", LocalDate.now(), User.Role.ADMIN));
        user.add(new User(3L, "turntechGodhead", "coolestdave@gmail.com", "turntechgodhead1", LocalDate.now(), User.Role.ADMIN));
        return user;
    }

    @GetMapping("{userId}")
    public User getById(@PathVariable Long userId){
        User user = new User(userId, "timaeusTestified", "RadHuman69@gmail.com", "yobroimhome", LocalDate.now(), User.Role.USER);
        return user;
    }

    @PostMapping
    private void create(@RequestBody User newUser){
        System.out.println("Ready to add user: " + newUser);
    }

    @PutMapping("{userId}")
    private void update(@PathVariable Long userId, @RequestBody User updatedUser){
        System.out.println("Updating User : " + userId + " with " + updatedUser);
    }

    @DeleteMapping("{userId}")
    private void deleteById(@PathVariable Long userId){
        System.out.println("User has been deleted: " + userId);
    }
}
