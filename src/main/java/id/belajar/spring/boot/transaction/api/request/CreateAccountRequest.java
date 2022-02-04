package id.belajar.spring.boot.transaction.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest implements Serializable {

    private static final long serialVersionUID = 5700190716699603926L;

    @JsonProperty("firstName")
    @NotBlank
    private String firstName;

    @JsonProperty("lastName")
    @NotBlank
    private String lastName;

    @JsonProperty("email")
    @NotBlank
    @Email
    private String email;

    @JsonProperty("phoneNumber")
    @NotBlank
    private String phoneNumber;

}
