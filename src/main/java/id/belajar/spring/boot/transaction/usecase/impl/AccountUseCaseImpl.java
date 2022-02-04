package id.belajar.spring.boot.transaction.usecase.impl;

import id.belajar.spring.boot.transaction.persistence.entity.Account;
import id.belajar.spring.boot.transaction.persistence.service.AccountService;
import id.belajar.spring.boot.transaction.usecase.AccountUseCase;
import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountRequestDto;
import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountResponseDto;
import id.belajar.spring.boot.transaction.usecase.exception.AccountIsExistsException;
import id.belajar.spring.boot.transaction.usecase.mapper.AccountUseCaseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AccountUseCaseImpl implements AccountUseCase {

    private final AccountUseCaseMapper accountUseCaseMapper;

    private final AccountService accountService;

    @Override
    public CreateAccountResponseDto createAccount(CreateAccountRequestDto createAccountRequestDto) {
        Boolean emailIsExists = accountService.existsByEmail(createAccountRequestDto.getEmail());
        if (Boolean.TRUE.equals(emailIsExists)) {
            throw new AccountIsExistsException(String.format("Account with email %s is exists", createAccountRequestDto.getEmail()));
        }

        Account account = accountUseCaseMapper.createAccountRequestDtoToAccount(createAccountRequestDto);
        account.setId(UUID.randomUUID().toString());

        account = accountService.save(account);

        return accountUseCaseMapper.accountToCreateAccountResponseDto(account);
    }
}
