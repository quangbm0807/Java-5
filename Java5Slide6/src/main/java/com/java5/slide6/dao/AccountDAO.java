package com.java5.slide6.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.java5.slide6.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String>{

	Account findByUsername(String username);
}
