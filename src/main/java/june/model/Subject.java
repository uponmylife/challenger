package june.model;

import june.controller.form.SubjectForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Subject {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    private List<Goal> goals;
    @Transient
    private List<Record> records;

    public Subject() {
        goals = new ArrayList();
        goals.add(new Goal(this, 0, "", 0));
        goals.add(new Goal(this, 1, "", 0));
        goals.add(new Goal(this, 2, "", 0));
    }

    public Subject(String name, String password) {
        this();
        this.name = name;
        this.password = password;
    }

    public void setGoal(Integer slot, String title, Integer count) {
        Goal goal = findBySlot(slot);
        if (goal == null) return;
        goal.setTitle(title);
        goal.setCount(count);
    }

    private Goal findBySlot(Integer slot) {
        for (Goal goal : goals) if (goal.getSlot() == slot) return goal;
        return null;
    }

    public void setSubjectForm(SubjectForm subjectForm) {
        name = subjectForm.getSubjectName();
        setGoal(0, subjectForm.getGoalTitle0(), subjectForm.getGoalCount0());
        setGoal(1, subjectForm.getGoalTitle1(), subjectForm.getGoalCount1());
        setGoal(2, subjectForm.getGoalTitle2(), subjectForm.getGoalCount2());
    }
}
