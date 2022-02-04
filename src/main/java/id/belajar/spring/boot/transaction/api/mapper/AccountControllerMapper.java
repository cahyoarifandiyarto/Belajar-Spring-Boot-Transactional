package id.belajar.spring.boot.transaction.api.mapper;

import id.belajar.spring.boot.transaction.api.request.CreateAccountRequest;
import id.belajar.spring.boot.transaction.api.response.CreateAccountResponse;
import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountRequestDto;
import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountResponseDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface AccountControllerMapper {

    CreateAccountRequestDto createAccountRequestToDto(CreateAccountRequest createAccountRequest);

    CreateAccountResponse createAccountResponseDtoToCreateAccountResponse(CreateAccountResponseDto createAccountResponseDto);

}
