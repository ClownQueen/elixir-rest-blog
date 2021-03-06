package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoriesByName(String name);
    Category findCategoryById(long id);
}
