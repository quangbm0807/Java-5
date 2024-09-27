package com.quangbui.models;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	public static List<Student> ls = new ArrayList<>();
	
	public List<Student> getAll() {
		return ls;
	}

	public int save(Student student) {
		ls.add(student);
		return 1;
	}

	public int delete(int id) {
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getId() == id) {
				ls.remove(i);
				return 1;
			}
		}
		return -1;
	}

	public int update(Student student) {
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i).getId() == student.getId()) {
				ls.set(i, student);
				return 1;
			}
		}
		return -1;
	}

	public Student findById(int id) {
		for (Student student : ls) {
			if (student.getId() == id) {
				return student;
			}
		}
		return null;
	}
}
