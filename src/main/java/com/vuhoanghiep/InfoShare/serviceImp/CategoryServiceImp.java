package com.vuhoanghiep.InfoShare.serviceImp;

import com.vuhoanghiep.InfoShare.exception.NotFoundException;
import com.vuhoanghiep.InfoShare.model.dto.CategoryDTO;
import com.vuhoanghiep.InfoShare.model.entity.Category;
import com.vuhoanghiep.InfoShare.repository.CategoryRepository;
import com.vuhoanghiep.InfoShare.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //Get All Category
    public List<Category> getAllCategory(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    //Get category by id
    public Optional<Category> getCategoryById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category;
        }else{
            throw new NotFoundException("Category không tồn tại");
        }
    }

    //Create category
    public void createCategory(CategoryDTO categoryDTO){
        Optional<Category> category = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if(category.isPresent()){
            throw new NotFoundException("Category đã tồn tại!");
        }else{
            Category category1 = new Category();
            category1.setCategoryName(categoryDTO.getCategoryName());
            categoryRepository.save(category1);
        }
    }
}
