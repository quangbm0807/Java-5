package com.quangbui.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quangbui.models.Major;
import com.quangbui.models.MajorDAO;
import com.quangbui.models.Student;
import com.quangbui.models.StudentDAO;

@Controller
@RequestMapping("/student")
public class StudentController {

	MajorDAO majorDAO = new MajorDAO();
	StudentDAO studentDAO = new StudentDAO();

	@ModelAttribute("list_major")
	public List<Major> getMajor() {
		return majorDAO.getAll();
	}

	@GetMapping("/")
	public String addOrEdit(ModelMap model) {
		model.addAttribute("STUDENT", new Student());
		return "student-form";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @ModelAttribute("STUDENT") Student student) {
		Major m = majorDAO.findById(student.getMajor().getId());
	    student.setMajor(m);
	    if (studentDAO.findById(student.getId()) != null) {
	        studentDAO.update(student);
	    } else {
	        studentDAO.save(student);
	    }
		model.addAttribute("list_student", studentDAO.getAll());
		return "student-list";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, ModelMap model) {
		Student student = studentDAO.findById(id);
		if (student != null) {
			model.addAttribute("STUDENT", student);
			model.addAttribute("list_student", studentDAO.getAll());
			return "student-form";
		} else {
			model.addAttribute("errorMessage", "Sinh viên không tồn tại!");
			return "student-list";
		}
	}

	@PostMapping("/delete/{studentId}")
	public String delete(@PathVariable("studentId") int id, ModelMap model) {
		Student student = studentDAO.findById(id);
		if (student != null) {
			studentDAO.delete(id);
			System.out.println("Đã xóa sinh viên: " + student.toString());
			model.addAttribute("list_student", studentDAO.getAll());
			return "redirect:/student/list";
		} else {
			model.addAttribute("errorMessage", "Sinh viên không tồn tại!");
			return "redirect:/student/list";
		}
	}

	@GetMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("list_student", studentDAO.getAll());
		return "student-list";
	}
}
