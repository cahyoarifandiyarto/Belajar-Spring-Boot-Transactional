package id.belajar.spring.boot.transaction.usecase;

import id.belajar.spring.boot.transaction.persistence.entity.Account;
import id.belajar.spring.boot.transaction.persistence.service.AccountService;
import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountRequestDto;
import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountResponseDto;
import id.belajar.spring.boot.transaction.usecase.exception.AccountIsExistsException;
import id.belajar.spring.boot.transaction.usecase.impl.AccountUseCaseImpl;
import id.belajar.spring.boot.transaction.usecase.mapper.AccountUseCaseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AccountUseCaseTest {

    private AccountUseCaseMapper accountUseCaseMapper;

    private AccountService accountService;

    private AccountUseCase accountUseCase;

    @BeforeEach
    void setup() {
        accountUseCaseMapper = mock(AccountUseCaseMapper.class);
        accountService = mock(AccountService.class);
        accountUseCase = new AccountUseCaseImpl(accountUseCaseMapper, accountService);
    }

    @Test
    void createAccount() {
        when(accountService.existsByEmail(anyString()))
                .thenReturn(Boolean.FALSE);

        CreateAccountRequestDto createAccountRequestDto = CreateAccountRequestDto.builder()
                .firstName("Cahyo Arif")
                .lastName("Andiyarto")
                .email("cahyoarif.dev@gmail.com")
                .phoneNumber("081234567812")
                .build();

        Account account = Account.builder()
                .firstName(createAccountRequestDto.getFirstName())
                .lastName(createAccountRequestDto.getLastName())
                .email(createAccountRequestDto.getEmail())
                .phoneNumber(createAccountRequestDto.getPhoneNumber())
                .build();

        when(accountUseCaseMapper.createAccountRequestDtoToAccount(createAccountRequestDto))
                .thenReturn(account);
        account.setId(UUID.randomUUID().toString());

        when(accountService.save(account))
                .thenReturn(account);

        CreateAccountResponseDto createAccountResponseDto = CreateAccountResponseDto.builder()
                .id(account.getId())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .phoneNumber(account.getPhoneNumber())
                .build();

        when(accountUseCaseMapper.accountToCreateAccountResponseDto(account))
                .thenReturn(createAccountResponseDto);

        CreateAccountResponseDto createAccountResponseDto1 = accountUseCase.createAccount(createAccountRequestDto);

        assertSame(createAccountResponseDto, createAccountResponseDto1);
    }

    @Test
    void createAccountErrorAccountIsExistsException() {
        when(accountService.existsByEmail(anyString()))
                .thenReturn(Boolean.TRUE);

        CreateAccountRequestDto createAccountRequestDto = CreateAccountRequestDto.builder()
                .firstName("Cahyo Arif")
                .lastName("Andiyarto")
                .email("cahyoarif.dev@gmail.com")
                .phoneNumber("081234567812")
                .build();

        assertThrows(AccountIsExistsException.class, () -> accountUseCase.createAccount(createAccountRequestDto));
    }

}
