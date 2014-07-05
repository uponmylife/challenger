package june.model;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@ToString
@Entity
public class Goal {
    @ManyToOne(optional = false)
    private Subject subject;
    @Id
    @GeneratedValue
    private Long id;
    private Integer slot;
    private String title;
    private Date started;

    public Goal() {
    }

    public Goal(Integer slot, Subject subject, String title) {
        this.slot = slot;
        this.subject = subject;
        this.title = title;
        started = new Date();
    }
}
