package com.minkov.judge.service;

import java.io.IOException;

public interface CategoryService {

    String importCategories(String categoriesFilePath) throws IOException;
}
