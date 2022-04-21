package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post/*the data type*/, Long/* the type of primary key*/> {

    List<Post> findAllByCategories(Category category);

    @Query("from Post a where a.title like %:term")
    List<Post> searchByTitleLike(@Param("term") String term);
}
