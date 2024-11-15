package com.quangbui.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.quangbui.model.PhoneContact;

@Service
public class PhoneService {
	private List<PhoneContact> phoneContacts = new ArrayList<>();

	public PhoneService() {
		phoneContacts.add(new PhoneContact(1, "Bùi Minh Quang", "0123456789", "abc", "Quận 1"));
		phoneContacts.add(new PhoneContact(2, "Bùi Minh", "0987654321", "xyz", "Quận 2"));
		phoneContacts.add(new PhoneContact(3, "Bùi", "0147258369", "ihk", "Quận 3"));
	}

	public List<PhoneContact> findAll() {
		return phoneContacts;
	}

	public void add(PhoneContact phoneContact) {
		if (phoneContact.getId() == 0) {
			phoneContact.setId(phoneContacts.size() + 1);
		}
		phoneContacts.add(phoneContact);
	}

	public void deleteById(int id) {
		if (phoneContacts.get(id) != null) {
			phoneContacts.remove(id);
		}
	}

	public boolean findByID(int id) {
		if (phoneContacts.get(id) != null) {
			return true;
		}
		return false;
	}

	public void update(PhoneContact phoneContact) {
		for (int i = 0; i < phoneContacts.size(); i++) {
			if (phoneContacts.get(i).getId() == phoneContact.getId()) {
				phoneContacts.set(i, phoneContact);
			}
		}
	}
}
