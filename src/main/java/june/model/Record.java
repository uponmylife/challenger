package june.model;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.time.FastDateFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
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

    public static final String DAY_FORMAT = "yyyyMMdd";

    public static String toDay(Date date) {
        return FastDateFormat.getInstance(DAY_FORMAT).format(date);
    }

    public static Date fromDay(String day) {
        try {
            return FastDateFormat.getInstance(DAY_FORMAT).parse(day);
        } catch (ParseException e) {
            throw new RuntimeException(e.toString());
        }
    }
}
