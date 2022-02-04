package id.belajar.spring.boot.transaction.controller;

import id.belajar.spring.boot.transaction.api.controller.AccountController;
import id.belajar.spring.boot.transaction.api.mapper.AccountControllerMapper;
import id.belajar.spring.boot.transaction.api.request.CreateAccountRequest;
import id.belajar.spring.boot.transaction.api.response.ApiResponse;
import id.belajar.spring.boot.transaction.api.response.CreateAccountResponse;
import id.belajar.spring.boot.transaction.helper.ValidationHelper;
import id.belajar.spring.boot.transaction.usecase.AccountUseCase;
import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountRequestDto;
import id.belajar.spring.boot.transaction.usecase.dto.CreateAccountResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AccountControllerTest {

    private ValidationHelper validationHelper;

    private AccountControllerMapper accountControllerMapper;

    private AccountUseCase accountUseCase;

    private AccountController accountController;

    @BeforeEach
    void setup() {
        validationHelper = mock(ValidationHelper.class);
        accountControllerMapper = mock(AccountControllerMapper.class);
        accountUseCase = mock(AccountUseCase.class);
        accountController = new AccountController(validationHelper, accountControllerMapper, accountUseCase);
    }

    @Test
    void createAccount() {
        doNothing().when(validationHelper).validate(any());

        CreateAccountRequest createAccountRequest = CreateAccountRequest.builder()
                .firstName("Cahyo Arif")
                .lastName("Andiyarto")
                .email("cahyoarif.dev@gmail.com")
                .phoneNumber("081234567891")
                .build();

        CreateAccountRequestDto createAccountRequestDto = CreateAccountRequestDto.builder()
                .firstName(createAccountRequest.getFirstName())
                .lastName(createAccountRequest.getLastName())
                .email(createAccountRequest.getEmail())
                .phoneNumber(createAccountRequest.getPhoneNumber())
                .build();

        when(accountControllerMapper.createAccountRequestToDto(createAccountRequest))
                .thenReturn(createAccountRequestDto);

        CreateAccountResponseDto createAccountResponseDto = CreateAccountResponseDto.builder()
                .id(UUID.randomUUID().toString())
                .firstName(createAccountRequestDto.getFirstName())
                .lastName(createAccountRequestDto.getLastName())
                .email(createAccountRequestDto.getEmail())
                .phoneNumber(createAccountRequestDto.getPhoneNumber())
                .build();

        when(accountUseCase.createAccount(createAccountRequestDto))
                .thenReturn(createAccountResponseDto);

        CreateAccountResponse createAccountResponse = CreateAccountResponse.builder()
                .id(createAccountResponseDto.getId())
                .firstName(createAccountResponseDto.getFirstName())
                .lastName(createAccountResponseDto.getLastName())
                .email(createAccountResponseDto.getEmail())
                .phoneNumber(createAccountResponseDto.getPhoneNumber())
                .build();

        when(accountControllerMapper.createAccountResponseDtoToCreateAccountResponse(createAccountResponseDto))
                .thenReturn(createAccountResponse);

        ApiResponse apiResponse = ApiResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.name())
                .statusDescription(HttpStatus.CREATED.getReasonPhrase())
                .data(createAccountResponse)
                .build();

        ResponseEntity<ApiResponse> response = ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);

        ResponseEntity<ApiResponse> response1 = accountController.createAccount(createAccountRequest);

        assertEquals(response, response1);
    }

}
