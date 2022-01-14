package az.com.company.response;

import lombok.Data;

@Data
public class ResponseLesson {
    private Long lessonId;
    private String lessonName;
    private Integer lessonTime;
    private Double lessonPrice;
    private ResponseStatus status;
}
