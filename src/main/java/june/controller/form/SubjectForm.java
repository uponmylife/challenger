package june.controller.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubjectForm {
    private Long subjectId;
    private String subjectName;
    private String goalTitle0;
    private Integer goalCount0;
    private String goalTitle1;
    private Integer goalCount1;
    private String goalTitle2;
    private Integer goalCount2;
}
