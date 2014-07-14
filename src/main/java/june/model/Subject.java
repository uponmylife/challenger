package june.model;

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
        goals.add(new Goal(this, "", 0));
        goals.add(new Goal(this, "", 0));
        goals.add(new Goal(this, "", 0));
    }

    public Subject(String name, String password) {
        this();
        this.name = name;
        this.password = password;
    }

    public void setGoal(Integer slot, String title, Integer count) {
        goals.get(slot).setTitle(title);
        goals.get(slot).setCount(count);
    }
}
