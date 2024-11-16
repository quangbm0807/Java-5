package org.example.ps27852_lab7.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Service.CategoryService;
import org.example.ps27852_lab7.Service.ProductService;
import org.example.ps27852_lab7.entity.Product;
import org.example.ps27852_lab7.entity.Report;
import org.example.ps27852_lab7.unti.FileUploadUtil;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public Model reloadPage(int page, int size, HttpServletRequest request, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", productService.getAllProducts(page, size).getTotalPages());
        model.addAttribute("products", productService.getAllProducts(page, size));
        model.addAttribute("content", "product.jsp"); // Đường dẫn đến file JSP cần hiện thị
        model.addAttribute("activeUri", request.getRequestURI()); // Thêm URI hiện tại

        return model;
    }

    @GetMapping
    public String product(
            HttpServletRequest request,
                          Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "4") int size,
                            @RequestParam(value = "field", defaultValue = "name") String field
    ) {
        model.addAttribute("categories", categoryService.getAllCategories());
        Page<Product> products = productService.getAllProducts(page, size, field);
        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("product", new Product());
        model.addAttribute("content", "product.jsp"); // Đường dẫn đến file JSP cần hiện thị
        model.addAttribute("activeUri", request.getRequestURI()); // Thêm URI hiện tại

        return "index"; // Trả về view index.jsp
    }



    @PostMapping("/upload")
    public String uploadProduct(@RequestParam("upload") MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Vui lòng chọn tệp!");
        }

        try {
            FileUploadUtil.saveFile(file.getOriginalFilename(), file, "products");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi tải tệp lên!");
        }

        return "redirect:/product"; // Chuyển hướng về trang admin
    }


    @PostMapping("/add")
    public String add(@Valid  @ModelAttribute("product") Product product,
                      BindingResult bindingResult,
                      @RequestParam("upload") MultipartFile file,
                      Model model,
                      HttpServletRequest request

    ) {
        if (bindingResult.hasErrors()) {
            reloadPage(0, 4, request, model);
            model.addAttribute("product", product);
            return "index";  // Trả về trang hiển thị lỗi
        }

        // Nếu file không rỗng, gọi đến phương thức upload để xử lý
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            product.setImage(fileName); // Lưu tên file vào account
            uploadProduct(file); // Gọi phương thức upload để lưu file
        }

        try {
            productService.addProduct(product);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "index";
        }
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "4") int size,
                       HttpServletRequest request,
                       Model model) {
        reloadPage(page, size, request, model);
        model.addAttribute("product", productService.getProduct(id));
        return "index";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return "redirect:/product";
    }
    @GetMapping("/clear")
    public String clear(Model model) {
        model.addAttribute("product", new Product());
        return "redirect:/product";
    }
    @GetMapping("/RepostProuctByprice")
    public String RepostProuctByprice(
            HttpServletRequest request,
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(value = "field", defaultValue = "name") String field
    ) {
        Page<Product> products = productService.getAllProducts(page, size, field);
        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("content", "ThongKeSanPhamTheoGia.jsp"); // Đường dẫn đến file JSP cần hiện thị
        model.addAttribute("activeUri", request.getRequestURI()); // Thêm URI hiện tại

        return "index";
    }

    @GetMapping("/RepostProuctByName")
    public String RepostProuctByName(
            HttpServletRequest request,
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(value = "field", defaultValue = "name") String field
    ) {
        Page<Product> products = productService.getAllProducts(page, size, field);
        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("content", "ThongKeSanPhamTheoTen.jsp"); // Đường dẫn đến file JSP cần hiện thị
        model.addAttribute("activeUri", request.getRequestURI()); // Thêm URI hiện tại

        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) Double min,
                         @RequestParam(required = false) Double max,
                         Model model,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "4") int size,
                         @RequestParam(value = "field", defaultValue = "name") String field,
                         HttpServletRequest request) {

        // Kiểm tra xem người dùng có nhập min hoặc max không
        if (min == null && max == null) {
            // Nếu không nhập, gọi phương thức để lấy tất cả sản phẩm
            Page<Product> products = productService.getAllProducts(page, size, field); // Thay đổi phương thức để lấy tất cả sản phẩm
            model.addAttribute("products", products.getContent());
            model.addAttribute("totalPages", products.getTotalPages());
            model.addAttribute("currentPage", page);
            model.addAttribute("size", size);
            model.addAttribute("content", "ThongKeSanPhamTheoGia.jsp");
            model.addAttribute("activeUri", request.getRequestURI());
            return "index"; // Trả về tên view
        }

        // Nếu có giá nhập vào, tìm sản phẩm theo khoảng giá
        Page<Product> products = productService.findByPrice(min, max, page, size, field);

        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("min", min); // Thêm tham số min
        model.addAttribute("max", max); // Thêm tham số max
        model.addAttribute("content", "ThongKeSanPhamTheoGia.jsp");
        model.addAttribute("activeUri", request.getRequestURI());

        return "index"; // Trả về tên view
    }
    @GetMapping("/searchName")
    public String searchName(
            @RequestParam("name") String name,
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(value = "field", defaultValue = "name") String field,
            HttpServletRequest request) {

        if (name == null) {
            // Nếu không nhập, gọi phương thức để lấy tất cả sản phẩm
            Page<Product> products = productService.getAllProducts(page, size, field); // Thay đổi phương thức để lấy tất cả sản phẩm
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("products", products.getContent());
            model.addAttribute("totalPages", products.getTotalPages());
            model.addAttribute("currentPage", page);
            model.addAttribute("size", size);
            model.addAttribute("content", "ThongKeSanPhamTheoTen.jsp");
            model.addAttribute("activeUri", request.getRequestURI());
            return "index"; // Trả về tên view
        }

        // Tìm sản phẩm theo từ khóa
        Page<Product> products = productService.findByKeyword(name, page, size, field);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("name", name);
        model.addAttribute("content", "ThongKeSanPhamTheoTen.jsp");
        model.addAttribute("activeUri", request.getRequestURI());

        return "index"; // Trả về tên view
    }

    @RequestMapping("/inventoryByCategory")
    public String inventory(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(value = "field", defaultValue = "o.category.name") String field,
            HttpServletRequest request
    ) {
        List<Report> items = productService.findInventoryByCategory(page, size, field);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", items);
        System.out.println("Sản phẩm hàng tồn kho là: " + items);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", items.size());
        model.addAttribute("content", "ThongKeHangTon.jsp");
        model.addAttribute("activeUri", request.getRequestURI());
        return "index";
    }
    @PostMapping("/selectCategory")
    public String selectCategory(
            @RequestParam(value = "categoryId", required = false) String categoryId,  // Cho phép giá trị null
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(value = "field", defaultValue = "o.category.name") String field,
            HttpServletRequest request
    ) {
        List<Report> items;
        // Kiểm tra nếu categoryId là "null" hoặc rỗng thì hiển thị tất cả sản phẩm
        if (categoryId == null || categoryId.equals("null") || categoryId.isEmpty()) {
            // Truy vấn tất cả sản phẩm
            items = productService.findInventoryByCategory(page, size, field);
            System.out.println("Hiển thị tất cả sản phẩm tồn kho.");
            model.addAttribute("products", items);
        } else {
            // Truy vấn sản phẩm theo categoryId
            items = productService.findProductByCategory(categoryId, page, size, field);
            System.out.println("Category ID được chọn: " + categoryId);
            model.addAttribute("products", items);
        }

        model.addAttribute("totalPages", items.size());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("currentPage", page);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("content", "ThongKeHangTon.jsp");
        model.addAttribute("activeUri", request.getRequestURI());

        return "index";
    }


}
