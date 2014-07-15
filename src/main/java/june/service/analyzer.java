package june.service;

import june.model.Record;

import java.util.*;

public class Analyzer {
    private int goalCount;
    private Date today;
    private Map<String, Integer> weekMap = new LinkedHashMap<String, Integer>();

    public Analyzer(int goalCount, List<String> days) {
        this(goalCount, days, new Date());
    }

    public Analyzer(int goalCount, List<String> days, Date today) {
        this.today = today;
        this.goalCount = goalCount;
        makeWeekMap(days);
        applyRecord(days);
    }

    public int elapsedWeeks() {
        return weekMap.size();
    }

    public int achievedWeeks() {
        int achieves = 0;
        for (Integer count : weekMap.values()) if (count >= goalCount) achieves++;
        return achieves;
    }

    public int recentContinuousDays() {
        return 0;
    }

    public int maxContinuousDays() {
        int continuousWeeks = 0;
        int maxContinuousWeeks = 0;
        for (Integer count : weekMap.values()) {
            if (count >= goalCount) {
                continuousWeeks++;
                if (continuousWeeks > maxContinuousWeeks) maxContinuousWeeks = continuousWeeks;
            } else {
                continuousWeeks = 0;
            }
        }
        return maxContinuousWeeks * 7;
    }

    String getFirstDayOfWeek(String day) {
        Date date = Record.fromDay(day);
        Calendar cal = getFirstCalendarOfWeek(date);
        return Record.toDay(cal.getTime());
    }

    private void makeWeekMap(List<String> days) {
        Date firstDate = Record.fromDay(days.get(0));
        Calendar cal = getFirstCalendarOfWeek(firstDate);
        while (cal.getTimeInMillis() <= today.getTime()) {
            weekMap.put(Record.toDay(cal.getTime()), 0);
            cal.add(Calendar.DAY_OF_YEAR, 7);
        }
    }

    private void applyRecord(List<String> days) {
        for (String day : days) {
            String key = getFirstDayOfWeek(day);
            weekMap.put(key, weekMap.get(key) + 1);
        }
    }

    Map<String, Integer> getWeekMap() {
        return weekMap;
    }

    private Calendar getFirstCalendarOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal;
    }
}
