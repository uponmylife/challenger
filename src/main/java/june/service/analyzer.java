package june.service;

import june.model.Record;

import java.util.*;

public class Analyzer {
    private Map<String, Integer> weekMap;

    public Analyzer() {
    }

    public Analyzer(List<String> days) {
        Map<String, Integer> map = getWeekMap(days);
        weekMap = updateCount(map, days);
    }

    public Integer elapsedWeeks() {
        return 0;
    }

    public Integer achievedWeeks() {
        return 0;
    }

    public Integer recentContinuousDays() {
        return 0;
    }

    public Integer maxContinuousDays() {
        return 0;
    }

    String getFirstDayOfWeek(String day) {
        Date date = Record.fromDay(day);
        Calendar cal = getFirstCalendarOfWeek(date);
        return Record.toDay(cal.getTime());
    }

    Map<String, Integer> getWeekMap(List<String> days) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Date firstDate = Record.fromDay(days.get(0));
        Date lastDate = Record.fromDay(days.get(days.size() - 1));
        Calendar cal = getFirstCalendarOfWeek(firstDate);
        while (cal.getTimeInMillis() <= lastDate.getTime()) {
            map.put(Record.toDay(cal.getTime()), 0);
            cal.add(Calendar.DAY_OF_YEAR, 7);
        }
        return map;
    }

    Map<String, Integer> updateCount(Map<String, Integer> map, List<String> days) {
        for (String day : days) {
            String key = getFirstDayOfWeek(day);
            map.put(key, map.get(key) + 1);
        }
        return map;
    }

    private Calendar getFirstCalendarOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal;
    }
}
