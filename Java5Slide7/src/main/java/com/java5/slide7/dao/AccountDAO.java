package com.java5.slide7.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.java5.slide7.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String>{

	Account findByUsername(String username);
}
