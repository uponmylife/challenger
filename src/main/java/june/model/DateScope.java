package june.model;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@ToString
public class DateScope {
    private Date startDate;
    private Date endDate;

    public DateScope(int weekAgo) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.add(Calendar.DAY_OF_YEAR, 6);
        endDate = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, -(weekAgo * 7 + 6));
        startDate = cal.getTime();
    }

    public List<Date> getDates() {
        List<Date> list = new ArrayList<Date>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        while (cal.getTimeInMillis() <= endDate.getTime()) {
            list.add(cal.getTime());
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }
        return list;
    }
}
