package org.example.ps27852_lab7.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Service.CategoryService;
import org.example.ps27852_lab7.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    public Model reloadCategoryPage(int page, int size, HttpServletRequest request, Model model) {
        // Lấy dữ liệu từ service, trả về Page<Category>
        Page<Category> categoryPage = categoryService.getAllCategories(page, size);

        // Nạp dữ liệu vào model
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", categoryPage.getTotalPages()); // Số trang
        model.addAttribute("categories", categoryPage.getContent()); // Lấy danh sách danh mục
        model.addAttribute("content", "category.jsp"); // Đường dẫn đến file JSP cần hiển thị
        model.addAttribute("activeUri", request.getRequestURI()); // Thêm URI hiện tại

        return model;
    }


    @GetMapping
    public String category(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            HttpServletRequest request,
            Model model,
            @RequestParam(value = "field", defaultValue = "name") String field
            ) {

        Page<Category> category = categoryService.getAllCategories(page, size, field);
        model.addAttribute("action", "add");
        model.addAttribute("categories", category.getContent());
        model.addAttribute("totalPages", category.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("category", new Category());
        model.addAttribute("content", "category.jsp"); // Đường dẫn đến file JSP cần hiển thị
        model.addAttribute("activeUri", request.getRequestURI()); // Thêm URI hiện tại

        return "index"; // Trả về view index.jsp
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("category") Category category,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam(defaultValue = "0") int page,
                      @RequestParam(defaultValue = "4") int size,
                      HttpServletRequest request
    ) {

        if (bindingResult.hasErrors()) {
            reloadCategoryPage(0, 4, request, model);
            model.addAttribute("category", category);
            return "index";
        }
        categoryService.addCategory(category);
        return "redirect:/category";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id) {
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id,
            @Valid @ModelAttribute("category") Category category,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam(defaultValue = "0") int page,
                      @RequestParam(defaultValue = "4") int size,
                      HttpServletRequest request
    ) {

        if (bindingResult.hasErrors()) {
            reloadCategoryPage(0, 4, request, model);
            model.addAttribute("category", category);
            return "index";
        }
        categoryService.updateCategory(id,category);
        return "redirect:/category";
    }
    @GetMapping("/edit/{id}")
    public String editCategory(
            @PathVariable("id") String id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("action", "update/" + id);
        model.addAttribute("category", categoryService.getCategory(id));
        reloadCategoryPage(page, size, request, model);
        return "index";
    }
    @GetMapping("/clear")
    public String clear() {
        return "redirect:/category";
    }

}