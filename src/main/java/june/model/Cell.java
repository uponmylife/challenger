package june.model;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang.time.FastDateFormat;

import java.util.Date;

@Getter
@ToString
public class Cell {
    private Date date;
    private String day;
    private boolean[] slots = new boolean[3];

    public Cell(Date date) {
        this.date = date;
        day = Record.toDay(date);
    }

    public void check(int index) {
        slots[index] = true;
    }
}
