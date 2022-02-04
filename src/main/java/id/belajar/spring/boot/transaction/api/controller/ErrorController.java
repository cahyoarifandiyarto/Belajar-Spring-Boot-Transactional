package id.belajar.spring.boot.transaction.api.controller;

import id.belajar.spring.boot.transaction.api.response.ApiResponse;
import id.belajar.spring.boot.transaction.usecase.exception.AccountIsExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> constraintViolationExceptionHandler(ConstraintViolationException constraintViolationException) {
        List<ApiResponse.Error> errors = new ArrayList<>();

        constraintViolationException.getConstraintViolations().forEach(constraintViolation -> {
            ApiResponse.Error error = ApiResponse.Error.builder()
                    .field(constraintViolation.getPropertyPath().toString())
                    .message(constraintViolation.getMessage())
                    .build();

            errors.add(error);
        });

        ApiResponse apiResponse = ApiResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .statusDescription(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errors(errors)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @ExceptionHandler(AccountIsExistsException.class)
    public ResponseEntity<ApiResponse> accountIsExistsExceptionHandler(AccountIsExistsException accountIsExistsException) {
        ApiResponse.Error error = ApiResponse.Error.builder()
                .message(accountIsExistsException.getMessage())
                .build();

        List<ApiResponse.Error> errors = List.of(error);

        ApiResponse apiResponse = ApiResponse.builder()
                .code(HttpStatus.CONFLICT.value())
                .status(HttpStatus.CONFLICT.name())
                .statusDescription(HttpStatus.CONFLICT.getReasonPhrase())
                .errors(errors)
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(apiResponse);
    }

}
