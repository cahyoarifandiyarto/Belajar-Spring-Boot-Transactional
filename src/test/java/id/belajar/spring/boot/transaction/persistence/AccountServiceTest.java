package id.belajar.spring.boot.transaction.persistence;

import id.belajar.spring.boot.transaction.persistence.entity.Account;
import id.belajar.spring.boot.transaction.persistence.repository.AccountRepository;
import id.belajar.spring.boot.transaction.persistence.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    private AccountRepository accountRepository;

    private AccountService accountService;

    @BeforeEach
    void setup() {
        accountRepository = mock(AccountRepository.class);
        accountService = new AccountService(accountRepository);
    }

    @Test
    void existsByEmailReturnTrue() {
        Account account = Account.builder()
                .id(UUID.randomUUID().toString())
                .firstName("Cahyo Arif")
                .lastName("Andiyarto")
                .email("cahyoarif.dev@gmail.com")
                .phoneNumber("081234567812")
                .build();

        when(accountRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(account));

        Boolean result = accountService.existsByEmail("cahyoarif.dev@gmail.com");

        assertTrue(result);
    }

    @Test
    void existsByEmailReturnFalse() {
        when(accountRepository.findByEmail(anyString()))
                .thenReturn(Optional.empty());

        Boolean result = accountService.existsByEmail("cahyoarif.dev@gmail.com");

        assertFalse(result);
    }

    @Test
    void save() {
        Account account = Account.builder()
                .id(UUID.randomUUID().toString())
                .firstName("Cahyo Arif")
                .lastName("Andiyarto")
                .email("cahyoarif.dev@gmail.com")
                .phoneNumber("081234567812")
                .build();

        when(accountRepository.save(account))
                .thenReturn(account);

        Account account1 = accountService.save(account);

        assertSame(account, account1);
    }

}
