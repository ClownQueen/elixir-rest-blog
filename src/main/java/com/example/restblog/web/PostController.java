package com.example.restblog.web;

import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value ="/api/posts", headers = "Accept=application/json")
public class PostController {

    @GetMapping
    private List<Post> getAll(){
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Dream Post", "My dad awoke to himself saying 500 million dollars."));
        posts.add(new Post(2L, "Dinner Post", "I had 10 piece chicken nuggets, large fries, and large chocolate shake from McDonald's."));
        posts.add(new Post(3L, "Painting Post", "My dad painted for my little sibling's birthday that's on thursday."));
        return posts;
    }

    // GET /api/post/5 <-- fetch the blog post with id 5
    @GetMapping("{postId}")
    public Post getById(@PathVariable Long postId){
        Post post = new Post(postId, "Post " + postId, "Blah blah blah");
        return post;
    }

    @PostMapping
    private void createPost(@RequestBody Post newPost){
        System.out.println("Ready to add post: " + newPost);
    }

    @PutMapping("{postId}")
    private void updatePost(@PathVariable Long postId, @RequestBody Post updatedPost){
        System.out.println("Updatig post : " + postId + " with " + updatedPost);
    }

    @DeleteMapping("{postId}")
    private void deletePost(@PathVariable Long postId){
        System.out.println("Post has been deleted: " + postId);
    }
}
