package com.Lab_5.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Lab_5.Entity.Accounts;

public interface AccountDAO extends JpaRepository<Accounts, String>{

}
