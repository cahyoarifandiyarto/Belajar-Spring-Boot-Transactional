package id.belajar.spring.boot.transaction.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountResponseDto implements Serializable {

    private static final long serialVersionUID = 4335632047423445934L;

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

}
