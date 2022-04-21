package com.example.restblog.web;

import com.example.restblog.data.Category;
import com.example.restblog.data.CategoryRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/categories", headers = "Accept=application/json")
public class CategoriesController {
    public final CategoryRepository categoryRepository;

    public CategoriesController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    List<Category> getAll(){
        return categoryRepository.findAll();
    }
}