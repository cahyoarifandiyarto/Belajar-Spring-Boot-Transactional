package id.belajar.spring.boot.transaction.usecase.mapper;

import id.belajar.spring.boot.transaction.persistence.entity.Account;
import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountRequestDto;
import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountResponseDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface AccountUseCaseMapper {

    Account createAccountRequestDtoToAccount(CreateAccountRequestDto createAccountRequestDto);

    CreateAccountResponseDto accountToCreateAccountResponseDto(Account account);

}
