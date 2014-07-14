package june.controller.form;

import june.model.Subject;
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

    public Subject createModel() {
        Subject subject = new Subject();
        subject.setId(subjectId);
        subject.setName(subjectName);
        subject.setGoal(0, goalTitle0, goalCount0);
        subject.setGoal(1, goalTitle1, goalCount1);
        subject.setGoal(2, goalTitle2, goalCount2);
        return subject;
    }
}
