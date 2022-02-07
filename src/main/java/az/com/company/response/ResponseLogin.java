package az.com.company.response;

import lombok.Data;

@Data
public class ResponseLogin {

    private Long userId;
    private String username;
    private String companyName;
    private String token;
    private ResponseStatus status;

}
