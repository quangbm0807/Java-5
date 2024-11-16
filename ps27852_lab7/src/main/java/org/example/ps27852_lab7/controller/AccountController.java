package org.example.ps27852_lab7.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.ps27852_lab7.Service.AccountService;
import org.example.ps27852_lab7.entity.Account;
import org.example.ps27852_lab7.unti.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@RequestMapping("/admin")
public class AccountController {

    @Autowired
    private AccountService accountService; // Dịch vụ quản lý tài khoản
    public Model reloadAdminPage(int page, int size, HttpServletRequest request, Model model) {
        // Lấy dữ liệu từ service, trả về Page<Account>
        Page<Account> accountPage = accountService.getAllAccountsSort(page, size);

        // Nạp dữ liệu vào model
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("accounts", accountPage.getContent()); // Lấy danh sách tài khoản
        model.addAttribute("totalPages", accountPage.getTotalPages()); // Số trang
        model.addAttribute("content", "admin.jsp"); // Đường dẫn đến file JSP cần hiển thị
        model.addAttribute("activeUri", request.getRequestURI()); // Thêm URI hiện tại

        return model;
    }

//
//
//    @GetMapping
//    public String showAccounts(Model model, HttpServletRequest request,
//                               @RequestParam(defaultValue = "0") int page,
//                               @RequestParam(defaultValue = "4") int size,
//                               @RequestParam(value = "field", defaultValue = "username") List<String> fields // Thay đổi ở đây
//    ) {
//
//
//        List<Account> accounts = accountService.getAllAccounts(fields);
//        reloadAdminPage(page, size, request, model);
//        model.addAttribute("accounts", accounts);
//        model.addAttribute("account", new Account());
//        return "index"; // Trả về trang JSP
//    }


    @GetMapping
    public String showAccounts(Model model, HttpServletRequest request,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "4") int size,
                               @RequestParam(value = "field", defaultValue = "username") String field) {

        // Phân trang và sắp xếp
        Page<Account> accounts = accountService.getAllAccounts(page, size, field);
        model.addAttribute("accounts", accounts.getContent());
        model.addAttribute("totalPages", accounts.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("account", new Account());
        model.addAttribute("content", "admin.jsp"); // Đường dẫn đến file JSP cần hiển thị
        model.addAttribute("activeUri", request.getRequestURI());
        return "index"; // Trả về trang JSP
    }

    private boolean validateFile(MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Vui lòng chọn tệp hình!");
            return false;
        }

        // Kiểm tra định dạng tệp
        String filename = file.getOriginalFilename();
        String fileExtension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        if (!fileExtension.equals("jpg") && !fileExtension.equals("jpeg") && !fileExtension.equals("png")) {
            model.addAttribute("message", "Tệp phải là định dạng JPG hoặc PNG!");
            return false;
        }

        // Kiểm tra loại MIME
        String mimeType = file.getContentType();
        if (mimeType == null || !mimeType.startsWith("image/")) {
            model.addAttribute("message", "Tệp hình không hợp lệ!");
            return false;
        }

        return true; // Tệp hợp lệ
    }


    @PostMapping("/upload")
    public String uploadAccount(@RequestParam("file") MultipartFile file, Model model) {
        // Kiểm tra tệp có tồn tại hay không
        if (file.isEmpty()) {
            model.addAttribute("message", "Vui lòng chọn tệp hình!");
            return "index"; // Chuyển hướng về trang admin với thông báo lỗi
        }
        try {
            FileUploadUtil.saveFile(file.getOriginalFilename(), file, "img");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi tải tệp lên!");
        }

        return "redirect:/admin"; // Chuyển hướng về trang admin
    }

    @PostMapping("/add")
    public String addAccount(@Valid @ModelAttribute("account") Account account,
                             BindingResult bindingResult,
                             @RequestParam("file") MultipartFile file,
                             Model model,
                             HttpServletRequest request) {
        // Kiểm tra và xử lý validate tài khoản
        if (bindingResult.hasErrors()) {
            reloadAdminPage(0, 4, request, model);
            model.addAttribute("account", account);
            return "index"; // Quay lại trang index để hiển thị lỗi
        }

        // Kiểm tra tệp tải lên
        if (!validateFile(file, model)) {
            reloadAdminPage(0, 4, request, model);
            model.addAttribute("account", account); // Giữ lại thông tin tài khoản
            return "index"; // Quay lại trang index để hiển thị lỗi
        }

        // Nếu tất cả kiểm tra đều qua, lưu tên file vào account
        account.setPhoto(file.getOriginalFilename());

        // Gọi phương thức upload để lưu file
        uploadAccount(file, model);

        // Sau khi tải ảnh lên, thêm tài khoản vào cơ sở dữ liệu
        accountService.addAccount(account);

        return "redirect:/admin"; // Chuyển hướng về trang admin
    }



    @GetMapping("/delete/{username}")
    public String deleteAccount(@PathVariable("username") String username) {
        if (username.equals("admin")) {
            return "redirect:/admin";
        } else {
            accountService.deleteAccount(username);
        }

        return "redirect:/admin"; // Trở lại trang quản lý tài khoản
    }

    @GetMapping("/edit/{username}")
    public String editAccount(@PathVariable("username") String username, Model model
            ,   @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "4") int size
                            , HttpServletRequest request
    ) {
        // Lấy dữ liệu từ service, trả về Page<Account>
       model = reloadAdminPage(page, size, request, model);
        model.addAttribute("account", accountService.getAccount(username));
        return "index";
    }
    @GetMapping("/clear")
    public String clear(Model model) {
        model.addAttribute("account", new Account());
        return "redirect:/admin";
    }
}
