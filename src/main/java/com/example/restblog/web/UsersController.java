package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import com.example.restblog.data.UserRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value ="/api/users", headers = "Accept=application/json")
public class UsersController {

    private UserRepository userRepository;

    public UsersController (UserRepository userRepository){
        this.userRepository = userRepository;
    }
//    private static final Post POST1 = new Post(1L, "Post 1", "Blah", null);
//    private static final Post POST2 = new Post(2L, "Post 2", "Blah blah", null);
//    private static final Post POST3 = new Post(3L, "Post 3", "Blah blah blah", null);
//    private static final Post POST4 = new Post(4L, "Post 4", "Blah blah blah blah", null);
//    private static final Post POST5 = new Post(5L, "Post 5", "Blah blah blah blah blah", null);
//    private static final Post POST6 = new Post(6L, "Post 6", "Blah blah blah blah blah blah", null);
//    private static final Post POST7 = new Post(7L, "Post 7", "Blah blah blah blah blah blah", null);
//    private static final Post POST8 = new Post(8L, "Post 8", "Blah blah blah blah blah blah", null);
//    private static final Post POST9 = new Post(9L, "Post 9", "Blah blah blah blah blah blah", null);

    @GetMapping
    private List<User> getAll(){
//        ArrayList<User> user = new ArrayList<>();
//        user.add(new User(1L, "User 1", "email 1m", "password 1", null, User.Role.ADMIN, Arrays.asList(POST1,POST2)));
//        user.add(new User(2L, "User 2", "email 2", "password2", null, User.Role.ADMIN, Arrays.asList(POST3,POST4)));
//        user.add(new User(3L, "User 3", "email 3", "password 3", null, User.Role.ADMIN, Arrays.asList(POST5,POST6)));
        return userRepository.findAll();
    }

    @GetMapping("{userId}")
    public User getById(@PathVariable Long userId){
//        User user = new User(userId, "Bob Smith", "bobsemail", "password1234", null, User.Role.ADMIN, Arrays.asList(POST7, POST8));
        return userRepository.getById(userId);
    }

//    @GetMapping("/email")
//    public User getByEmail(@RequestParam String email){
//        User user = new User(1l,"User420",email,"pasword1234",null, User.Role.ADMIN);
//        return user;
//    }

//    @GetMapping("/username")
//    public User getByUsername(@RequestParam String username){
//        User user = new User(1L, username,"email450","password450",null, User.Role.ADMIN);
//        return user;
//    }

    @PostMapping
    private void create(@RequestBody User newUser){
        User user = new User(newUser.getUsername(), newUser.getEmail(), newUser.getPassword(), newUser.getCreatedAt(), newUser.getRole());
        userRepository.save(user);
        System.out.println("Ready to add user: " + newUser);
    }

    @PutMapping("{userId}")
    private void update(@PathVariable Long userId, @RequestBody User updatedUser){
        User userToUpdate = userRepository.getById(userId);
        userToUpdate.setUsername(userToUpdate.getUsername());
        userToUpdate.setEmail(userToUpdate.getEmail());
        userToUpdate.setPassword(userToUpdate.getPassword());
        userToUpdate.setCreatedAt(userToUpdate.getCreatedAt());
        userToUpdate.setRole(userToUpdate.getRole());
        userRepository.save(updatedUser);
        System.out.println("Updating user number : " + userId + " with " + updatedUser);
    }

    @PutMapping("{userId}/updatePassword")
    private void updateUserPassword(@PathVariable Long userId, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword){
        User userRequestUpdate = userRepository.getById(userId);
        userRequestUpdate.setPassword(newPassword);
        userRepository.save(userRequestUpdate);
        System.out.println("Updating User: " + userId + "Password from: " + oldPassword + "to: " + newPassword);
    }

    @DeleteMapping("{userId}")
    private void deleteById(@PathVariable Long userId){
        User useToDelete = userRepository.getById(userId);
        userRepository.delete(useToDelete);
        System.out.println("deleting user with ID: " + userId);
    }
}
