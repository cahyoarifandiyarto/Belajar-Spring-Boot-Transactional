package id.belajar.spring.boot.transaction.persistence.service;

import id.belajar.spring.boot.transaction.persistence.entity.Account;
import id.belajar.spring.boot.transaction.persistence.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Boolean existsByEmail(String email) {
        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isEmpty()) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public Account findById(String id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            return null;
        }

        return account.get();
    }

    public Account save(Account account) {
        account = accountRepository.save(account);
        return account;
    }

}
