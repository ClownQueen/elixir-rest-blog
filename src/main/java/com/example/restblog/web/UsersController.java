package com.example.restblog.web;

import com.example.restblog.data.User;
import com.example.restblog.data.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value ="/api/user", headers = "Accept=application/json")
public class UsersController {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public UsersController(UserRepository userRepository, PasswordEncoder encoder){
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @GetMapping
    private List<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping("{userId}")
    public Optional<User> getById(@PathVariable Long userId){
        return userRepository.findById(userId);
    }

    @GetMapping("/email")
    public User getByEmail(@RequestParam String email){
        return userRepository.findByEmail(email);
    }

    @GetMapping("/username")
    public User getByUsername(@RequestParam String username){
        return userRepository.findByUsername(username);
    }

    @PostMapping
    private void create(@RequestBody User newUser){
        User user = newUser;
        user.setCreatedAt(LocalDate.now());
        user.setRole(User.Role.USER);
        System.out.println("Ready to add user: " + newUser);
        String encryptedPassword = newUser.getPassword();
        encryptedPassword = encoder.encode(encryptedPassword);
        newUser.setPassword(encryptedPassword);
        userRepository.save(user);
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
