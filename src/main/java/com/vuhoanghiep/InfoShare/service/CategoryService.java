package com.vuhoanghiep.InfoShare.service;

import com.vuhoanghiep.InfoShare.model.dto.CategoryDTO;
import com.vuhoanghiep.InfoShare.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    //Get All Category
    public List<Category> getAllCategory();
    //Get category by id
    public Optional<Category> getCategoryById(Long id);
    //Create category
    public void createCategory(CategoryDTO categoryDTO);
}
