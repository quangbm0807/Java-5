package com.quangbui.models;

import java.util.ArrayList;
import java.util.List;

public class MajorDAO {

	public static List<Major> ls = new ArrayList<>();
	
	public List<Major> getAll(){
		ls.clear();
		ls.add(new Major(1, "PTPM"));
		ls.add(new Major(2, "UDPM"));
		ls.add(new Major(3, "TKDH"));
		ls.add(new Major(4, "LTW"));
		ls.add(new Major(5, "LTMB"));
		return ls;
	}
	
	public Major findById(int id) {
		
		for(Major major:ls) {
			if(major.getId() == id) {
				return major;
			}
		}
		return null;
	}
}
