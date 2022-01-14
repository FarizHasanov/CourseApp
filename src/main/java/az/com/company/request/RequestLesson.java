package az.com.company.request;

import lombok.Data;

@Data
public class RequestLesson {

    private Long lessonId;
    private String name;
    private Integer time;
    private Double price;

}
