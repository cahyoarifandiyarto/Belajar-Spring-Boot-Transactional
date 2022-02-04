package id.belajar.spring.boot.transaction.api.controller;

import id.belajar.spring.boot.transaction.api.mapper.AccountControllerMapper;
import id.belajar.spring.boot.transaction.api.request.CreateAccountRequest;
import id.belajar.spring.boot.transaction.api.response.ApiResponse;
import id.belajar.spring.boot.transaction.api.response.CreateAccountResponse;
import id.belajar.spring.boot.transaction.helper.ValidationHelper;
import id.belajar.spring.boot.transaction.usecase.AccountUseCase;
import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountRequestDto;
import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts")
@AllArgsConstructor
public class AccountController {

    private final ValidationHelper validationHelper;

    private final AccountControllerMapper accountControllerMapper;

    private final AccountUseCase accountUseCase;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        validationHelper.validate(createAccountRequest);

        CreateAccountRequestDto createAccountRequestDto = accountControllerMapper.createAccountRequestToDto(createAccountRequest);

        CreateAccountResponseDto createAccountResponseDto = accountUseCase.createAccount(createAccountRequestDto);

        CreateAccountResponse createAccountResponse = accountControllerMapper.createAccountResponseDtoToCreateAccountResponse(createAccountResponseDto);

        ApiResponse apiResponse = ApiResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.name())
                .statusDescription(HttpStatus.CREATED.getReasonPhrase())
                .data(createAccountResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }

}
