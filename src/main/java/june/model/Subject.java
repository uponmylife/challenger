package june.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
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
        goals.add(new Goal(0, this, ""));
        goals.add(new Goal(1, this, ""));
        goals.add(new Goal(2, this, ""));
    }

    public Subject(String name, String password) {
        this();
        this.name = name;
        this.password = password;
    }
}
