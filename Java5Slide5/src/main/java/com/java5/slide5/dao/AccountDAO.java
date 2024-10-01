package com.java5.slide5.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.java5.slide5.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String>{
}
