package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post/*the data type*/, Long/* the type of primary key*/> {

    List<Post> findAllByCategories(Category category);
}
