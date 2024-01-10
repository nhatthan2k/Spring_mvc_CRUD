package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class categoryController {
    @Autowired
    private CategoryService categoryService;

//    hiển thị tất cả
    @GetMapping("/category")
    public String category(Model model) {
        List<Category> list = categoryService.findAll();
        model.addAttribute("list", list);
        return "Category/category";
    }

//    thêm mới
    @GetMapping("/category/add")
    public String add() {
        return "Category/addCategory";
    }

    @PostMapping("/category/save")
    public String save(
            @RequestParam("name") String name,
            @RequestParam("status") Boolean status
    ) {
        Category category = new Category(null, name, status);
        boolean check = categoryService.save(category);
        if (check) {
            return "redirect:/category";
        }
        return "redirect:/Category/addCategory";
    }

//    Sửa
    @GetMapping("/category/edit/{id}")
    public String edit(
            @PathVariable Integer id,
            Model model
    ) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "Category/editCategory";
    }

    @PostMapping("/category/update")
    public String update(
        @RequestParam("id") Integer id,
        @RequestParam("name") String name,
        @RequestParam("status") Boolean status
    ) {
        Category category = new Category(id, name, status);
        boolean checkUpdate = categoryService.save(category);
        if (checkUpdate) {
            return "redirect:/category";
        }
        return "redirect:/Category/editCategory";
    }

//   Xóa
    @GetMapping("/category/delete/{id}")
    public String delete(
            @PathVariable Integer id
    ) {
        categoryService.delete(id);
        return "redirect:/category";
    }
}
