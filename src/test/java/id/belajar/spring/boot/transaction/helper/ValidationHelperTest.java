package id.belajar.spring.boot.transaction.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ValidationHelperTest {

    private Validator validator;

    private ValidationHelper validationHelper;

    @BeforeEach
    void setup() {
        validator = mock(Validator.class);
        validationHelper = new ValidationHelper(validator);
    }

    @Test
    void validate() {
        ExampleRequest exampleRequest = ExampleRequest.builder()
                .fullName("Cahyo Arif Andiyarto")
                .build();

        when(validator.validate(exampleRequest))
                .thenReturn(Set.of());

        assertDoesNotThrow(() -> validationHelper.validate(exampleRequest));
    }

    @Test
    void validateThrowConstraintViolationException() {
        ExampleRequest exampleRequest = ExampleRequest.builder()
                .fullName("")
                .build();

        Set<ConstraintViolation<ExampleRequest>> constraintViolations = new HashSet<>();
        constraintViolations.add(any());

        when(validator.validate(exampleRequest))
                .thenReturn(constraintViolations);

        assertThrows(ConstraintViolationException.class, () -> validationHelper.validate(exampleRequest));
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExampleRequest implements Serializable {

        private static final long serialVersionUID = -5009624859555313169L;

        @NotBlank
        private String fullName;

    }

}
