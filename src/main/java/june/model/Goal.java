package june.model;

import june.service.Analyzer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString(exclude = "subject")
@Entity
public class Goal {
    @ManyToOne(optional = false)
    private Subject subject;
    @Id
    @GeneratedValue
    private Long id;
    private Integer slot;
    private String title;
    private Integer count;
    @Transient
    private Analyzer analyzer;

    public Goal() {
    }

    public Goal(Subject subject, Integer slot, String title, Integer count) {
        this.slot = slot;
        this.subject = subject;
        this.title = title;
        this.count = count;
    }
}
