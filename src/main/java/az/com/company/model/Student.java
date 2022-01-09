package az.com.company.model;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private long id;
    private String name;
    private String surname;
    private Date dob;
    private String address;
    private String phone;
    private Date dataDate;
    private Integer active;
}
