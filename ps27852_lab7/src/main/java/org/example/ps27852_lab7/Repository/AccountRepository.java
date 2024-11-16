package org.example.ps27852_lab7.Repository;

import org.example.ps27852_lab7.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, String> {


}
