package june.model;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang.time.FastDateFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
@Entity
@IdClass(Record.PK.class)
public class Record {
    @Id
    private Long subjectId;
    @Id
    private String day;
    @Id
    private Integer slot;

    public Record() {
    }

    public Record(Long subjectId, String day, Integer slot) {
        this.subjectId = subjectId;
        this.day = day;
        this.slot = slot;
    }

    public static class PK implements Serializable {
        private Long subjectId;
        private String day;
        private Integer slot;

        public PK() {
        }

        public PK(Long subjectId, String day, Integer slot) {
            this.subjectId = subjectId;
            this.day = day;
            this.slot = slot;
        }
    }

    public static String toDay(Date date) {
        return FastDateFormat.getInstance("yyyyMMdd").format(date);
    }
}
