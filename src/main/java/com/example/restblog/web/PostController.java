package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.data.PostsRepository;
import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value ="/api/posts", headers = "Accept=application/json")
public class PostController {

    private PostsRepository postsRepository;

    public PostController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

//    private final User USER1 = new User(1L, "User 1", "User1@gmail.com", "11111", null, User.Role.USER);
//    private final User USER2 = new User(2L, "User 2", "User2@gmail.com", "22222", null, User.Role.USER);
//    private final User USER3 = new User(3L, "User 3", "User3@gmail.com", "33333", null, User.Role.ADMIN);
//    private final User USER4 = new User(4L, "User 4", "User4@gmail.com", "44444", null, User.Role.ADMIN);

    @GetMapping
    private List<Post> getAll(){
        ArrayList<Post> posts = new ArrayList<>();
//        posts.add(new Post(1L, "Dream Post", "My dad awoke to himself saying 500 million dollars.", USER1));
//        posts.add(new Post(2L, "Dinner Post", "I had 10 piece chicken nuggets, large fries, and large chocolate shake from McDonald's.", USER2));
//        posts.add(new Post(3L, "Painting Post", "My dad painted for my little sibling's birthday that's on thursday.", USER3));
        return posts;
    }

//    @GetMapping("{postId}")
//    public Post getById(@PathVariable Long postId){
//        Post post = new Post(postId, "Post " + postId, "Blah blah blah", USER4);
//        return post;
//    }

    @PostMapping
    private void createPost(@RequestBody Post newPost){
        Post postToAdd = new Post(newPost.getTitle(), newPost.getContent());
        postsRepository.save(postToAdd);
        System.out.println("Ready to add post: " + newPost);
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
}
