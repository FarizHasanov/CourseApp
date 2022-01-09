package az.com.company.response;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseStudent {
    private Long id;
    private String name;
    private String surname;
    private Date dob;
    private String address;
    private String phone;
    private ResponseStatus status;

}
