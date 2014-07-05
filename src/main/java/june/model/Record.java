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
@IdClass(Record.RecordKey.class)
public class Record {
    @Id
    private String day;
    @Id
    private Integer slot;

    public Record() {
    }

    public Record(String day, Integer slot) {
        this.day = day;
        this.slot = slot;
    }

    public static class RecordKey implements Serializable {
        private String day;
        private Integer slot;

        public RecordKey() {
        }

        public RecordKey(String day, Integer slot) {
            this.day = day;
            this.slot = slot;
        }
    }

    public static String toDay(Date date) {
        return FastDateFormat.getInstance("yyyyMMdd").format(date);
    }
}
