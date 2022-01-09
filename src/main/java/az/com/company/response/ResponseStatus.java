package az.com.company.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatus {
    private Integer statusCode;
    private String statusMessage;

    private static final Integer SUCCESS_CODE = 1;
    private static final String SUCCESS_MESSAGE = "Success";

    public static ResponseStatus getSuccessMessage() {
        return new ResponseStatus(SUCCESS_CODE, SUCCESS_MESSAGE);
    }
}
