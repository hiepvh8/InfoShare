package com.vuhoanghiep.InfoShare.controller;

import com.vuhoanghiep.InfoShare.model.dto.CategoryDTO;
import com.vuhoanghiep.InfoShare.model.entity.Category;
import com.vuhoanghiep.InfoShare.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "client gửi request GetMethod để hiển thị danh sách các Danh Mục"
    )
    //Trả về danh sách tất cả các danh mục
    @GetMapping("")
    public ResponseEntity<?> getAllCategory(){
        List<Category> categories = categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @Operation(
            summary = "client gửi request GetMethod để hiển thị Danh mục theo Id"
    )
    //Trả về Danh mục theo id
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long categoryId){
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @Operation(
            summary = "client gửi request PostMethod để tạo một danh mục"
    )
    //Tạo mới một danh mục
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok("Thành công!");
    }
}
