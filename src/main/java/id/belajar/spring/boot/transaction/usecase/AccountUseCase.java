package id.belajar.spring.boot.transaction.usecase;

import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountRequestDto;
import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountResponseDto;

public interface AccountUseCase {

    CreateAccountResponseDto createAccount(CreateAccountRequestDto createAccountRequestDto);

}
