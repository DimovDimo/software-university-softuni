package com.minkov.judge.web.controllers;

import com.minkov.judge.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class HomeController implements CommandLineRunner {

    private final static String CATEGORIES_JSON_FILE_PATH = System.getProperty("user.dir") + ("\\src\\main\\resources\\files\\categories.json");

    private final CategoryService categoryService;

    @Autowired
    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.importCategories();
    }

    private void importCategories() throws IOException {
        System.out.println(this.categoryService.importCategories(CATEGORIES_JSON_FILE_PATH));

    }
}
