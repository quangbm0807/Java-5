package org.example.ps27852_lab7.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Repository.AccountRepository;
import org.example.ps27852_lab7.Service.AccountService;
import org.example.ps27852_lab7.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Page<Account> getAllAccountsSort(int page, int size) {
        // Tạo Pageable object để xác định page và size
        Pageable pageable = PageRequest.of(page, size);

        // Sử dụng repository để trả về Page<Account>
        return accountRepository.findAll(pageable);
    }
    @Override
    public Account login(String username, String password) {
        // Tìm tài khoản dựa trên tên người dùng
        Optional<Account> accountOptional = accountRepository.findById(username);

        if (accountOptional.isPresent()) {
            return accountOptional.get(); // Trả về tài khoản nếu đúng
        }
        return null; // Trả về null nếu không tìm thấy hoặc mật khẩu sai
    }

    @Override
    public Account getAccount(String id) {
        Optional<Account> accountOptional = accountRepository.findById(id); // Tìm tài khoản dựa trên tên người dùng
        if (accountOptional.isPresent()) {
            return accountOptional.get();
        }
        return null;
    }

    @Override
    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void deleteAccount(String username) {
        accountRepository.deleteById(username);
    }

    public Page<Account> getAllAccounts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));
        return accountRepository.findAll(pageable);
    }





}
