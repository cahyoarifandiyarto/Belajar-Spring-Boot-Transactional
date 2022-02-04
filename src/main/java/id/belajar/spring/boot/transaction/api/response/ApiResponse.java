package id.belajar.spring.boot.transaction.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse implements Serializable {

    private static final long serialVersionUID = -2090345726326251745L;

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusDescription")
    private String statusDescription;

    @JsonProperty("data")
    private Object data;

    @JsonProperty("errors")
    private List<Error> errors;

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Error implements Serializable {

        private static final long serialVersionUID = 4106948834635431669L;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("field")
        private String field;

        @JsonProperty("message")
        private String message;

    }

}
