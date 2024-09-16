package com.quangbui.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quangbui.model.MobilePhone;

@Service
public class MobilePhoneService {

	private List<MobilePhone> mobilePhones = new ArrayList<>(); // <1>

	public MobilePhoneService() {
		mobilePhones.add(new MobilePhone("iPhone 16 Pro Max", 9999.0, "Apple", "iphone-12.png", "USA"));
		mobilePhones.add(new MobilePhone("Samsung Galaxy S21", 7999.0, "Samsung", "galaxy-s21.png", "Korea"));
		mobilePhones.add(new MobilePhone("Oppo Find X3 Pro", 7999.0, "Oppo", "galaxy-s21.png", "China"));
	}

	public List<MobilePhone> getAllMobilePhone() {
        return mobilePhones;     		
    }
	
	public MobilePhone getMobilePhoneById(int index) {
		for (int i = 0; i < mobilePhones.size(); i++) {
			MobilePhone mobilePhone = mobilePhones.get(i);
			if (i == index) {
				return mobilePhone;
			}
		}
		return null;
	}
	
	public void addMobilePhone(MobilePhone mobilePhone) {
		mobilePhones.add(mobilePhone);
	}

	public void deleteMobilePhone(int index) {
		mobilePhones.remove(index);
	}

	public void updateMobilePhone(int index, MobilePhone mobilePhone) {
		mobilePhones.set(index, mobilePhone);
	}

	public List<MobilePhone> getMobilePhoneByCountry(String country) {
		List<MobilePhone> result = new ArrayList<>();
		for (MobilePhone mobilePhone : mobilePhones) {
			if (mobilePhone.getCountry().equals(country)) {
				result.add(mobilePhone);
			}
		}
		return result;
	}
	
	public List<MobilePhone> getMobilePhoneByMaker(String name) {
		List<MobilePhone> result = new ArrayList<>();
		for (MobilePhone mobilePhone : mobilePhones) {
			if (mobilePhone.getMaker().equals(name)) {
				result.add(mobilePhone);
			}
		}
		return result;
		
	}
}
