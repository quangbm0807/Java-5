package com.quangbui.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.quangbui.model.Gender;
import com.quangbui.model.Hobby;
import com.quangbui.model.Province;
import com.quangbui.model.Student;
import com.quangbui.service.ProvinceService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private ProvinceService provinceService;

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    private List<Student> students = new ArrayList<>();


    public StudentController() {
        students.add(new Student("PS123", "Bùi Minh Quang", "Chơi game", "quangbmps28437@fpt.edu.vn", "Ho Chi Minh City", "quang.png", true, 9.0));
        students.add(new Student("PS456", "Nguyễn Văn A", "Ngủ nướng", "anv123@fpt.edu.vn", "Ho Chi Minh City", "quang.png", true, 9.0));
        students.add(new Student("PS789", "Lê Thị B", "Đọc sách", "blt222@fpt.edu.vn", "Ho Chi Minh City", "quang.png", true, 9.0));
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", students);
        return "studentList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        addFormAttributes(model);
        return "studentForm";
    }

    @PostMapping("/add")
    public String addStudent(@Valid @ModelAttribute("student") Student student, 
                             BindingResult result, 
                             @RequestParam("file") MultipartFile file,
                             Model model) {
        if (result.hasErrors()) {
            addFormAttributes(model);
            return "studentForm";
        }

        if (!file.isEmpty()) {
            try {
                handleFileUpload(student, file);
            } catch (IOException e) {
                model.addAttribute("error", "Lỗi tải file lên");
                addFormAttributes(model);
                return "studentForm";
            }
        }

        students.add(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = findStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            addFormAttributes(model);
            return "studentForm";
        }
        return "redirect:/students";
    }

    @PostMapping("/edit")
    public String updateStudent(@Valid @ModelAttribute("student") Student student, 
                                BindingResult result, 
                                @RequestParam("file") MultipartFile file,
                                Model model) {
        if (result.hasErrors()) {
            addFormAttributes(model);
            return "studentForm";
        }

        Student existingStudent = findStudentById(student.getId());
        if (existingStudent == null) {
            model.addAttribute("error", "Student không tồn tại");
            addFormAttributes(model);
            return "studentForm";
        }

        if (!file.isEmpty()) {
            try {
                handleFileUpload(student, file);
            } catch (IOException e) {
                model.addAttribute("error", "Lỗi tải file lên");
                addFormAttributes(model);
                return "studentForm";
            }
        }

        updateExistingStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        students.removeIf(s -> s.getId().equals(id));
        return "redirect:/students";
    }

    private void addFormAttributes(Model model) {
        List<Province> provinces = provinceService.getAllProvinces();
        List<Hobby> hobbies = Arrays.asList(
            new Hobby(1, "Reading"),
            new Hobby(2, "Sports"),
            new Hobby(3, "Music")
        );
        List<Gender> genders = Arrays.asList(
            new Gender(true, "Male"),
            new Gender(false, "Female")
        );

        model.addAttribute("provinces", provinces);
        model.addAttribute("hobbies", hobbies);
        model.addAttribute("genders", genders);
    }

    private void handleFileUpload(Student student, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIRECTORY + file.getOriginalFilename());
        Files.write(path, bytes);
        student.setImagePath(file.getOriginalFilename());
    }

    private Student findStudentById(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private void updateExistingStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(updatedStudent.getId())) {
                students.set(i, updatedStudent);
                break;
            }
        }
    }
}