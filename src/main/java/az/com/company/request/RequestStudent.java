package az.com.company.request;

import lombok.Data;

import java.util.Date;

@Data
public class RequestStudent {
    private Long studentId;
    private String name;
    private String surname;
    private Date dob;
    private String address;
    private String phone;
}
