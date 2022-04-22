package com.example.restblog.web;

import com.example.restblog.data.*;
import com.example.restblog.services.EmailService;
import org.springframework.web.bind.annotation.*;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value ="/api/posts", headers = "Accept=application/json")
public class PostController {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final EmailService emailService;

    public PostController(PostsRepository postsRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postsRepository = postsRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


//    private final User USER1 = new User(1L, "User 1", "User1@gmail.com", "11111", null, User.Role.USER);
//    private final User USER2 = new User(2L, "User 2", "User2@gmail.com", "22222", null, User.Role.USER);
//    private final User USER3 = new User(3L, "User 3", "User3@gmail.com", "33333", null, User.Role.ADMIN);
//    private final User USER4 = new User(4L, "User 4", "User4@gmail.com", "44444", null, User.Role.ADMIN);

    @GetMapping
    private List<Post> getAll(){
        ArrayList<Post> posts = new ArrayList<>();
        return postsRepository.findAll();
    }

    @GetMapping("{postId}")
    private Optional<Post> getById(@PathVariable Long postId){
        return postsRepository.findById(postId);
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost){
        newPost.setAuthor(userRepository.getById(1L));
        List<Category> categories = new ArrayList<>();
        for (Category category: newPost.getCategories()) {
            if (categoryRepository.findCategoriesByName(category.getName()) != null) {
                categories.add(categoryRepository.findCategoriesByName(category.getName()));
            } else {
                Category newCat = new Category();
                newCat.setName(category.getName());
                categoryRepository.save(newCat);
                categories.add(categoryRepository.findCategoriesByName(newCat.getName()));
            }
        }
        newPost.setCategories(categories);
        postsRepository.save(newPost);
        System.out.println("Ready to add post: " + newPost);
        emailService.prepareAndSend(newPost, "Check this out!", "");
    }

    @PutMapping("{postId}")
    private void updatePost(@PathVariable Long postId, @RequestBody Post updatedPost){
        Post postToUpdate = postsRepository.getById(postId);
        postToUpdate.setContent(updatedPost.getContent());
        postToUpdate.setTitle(updatedPost.getTitle());
        postsRepository.save(postToUpdate);
        System.out.println("Updatig post : " + postId + " with " + updatedPost);
    }

    @DeleteMapping("{postId}")
    private void deletePost(@PathVariable Long postId){
        Post postToDelete = postsRepository.getById(postId);
        postsRepository.delete(postToDelete);
        System.out.println("Post has been deleted: " + postId);
    }

    @GetMapping("searchByCategory")
    private List<Post> searchByCategory(@RequestParam String category){
        return postsRepository.findAllByCategories(categoryRepository.findCategoriesByName(category));
    }

    @GetMapping("searchByTitle")
    private List<Post> searchPostByTitleKeyword(@RequestParam String keyword){
        return postsRepository.searchByTitleLike(keyword);
    }

}
