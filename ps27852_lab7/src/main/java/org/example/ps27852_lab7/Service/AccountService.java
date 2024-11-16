package org.example.ps27852_lab7.Service;

import org.example.ps27852_lab7.entity.Account;
import org.springframework.data.domain.Page;

public interface AccountService {
    Page<Account> getAllAccountsSort(int page, int size);
    Account login(String username, String password);
    Account getAccount(String id);
    void addAccount(Account account);
    void deleteAccount(String id);
    public Page<Account> getAllAccounts(int page, int size, String sortBy);

}
