package com.minkov.judge.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.NotNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CategoryImportDto {

    @Expose
    private Integer id;

    @Expose
    private String name;

    @Expose
    @SerializedName("category")
    private CategoryImportDto parentCategory;

    @Expose
    @SerializedName("categories")
    private CategoryImportDto[] subcategories;

    public CategoryImportDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 4)
    @Pattern(regexp = "[A-Z][a-zA-Z]+")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryImportDto getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryImportDto parentCategory) {
        this.parentCategory = parentCategory;
    }

    public CategoryImportDto[] getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(CategoryImportDto[] subcategories) {
        this.subcategories = subcategories;
    }
}
