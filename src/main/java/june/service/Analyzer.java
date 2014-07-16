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
        int continuousWeeks = 0;
        List<Integer> list = new ArrayList<Integer>(weekMap.values());
        for (int i=list.size()-1; i>=0; i--) {
            // todo: 이번주이고 아직 가능성이 있다면 성공으로 본다.
            if (list.get(i) < goalCount) break;
            continuousWeeks++;
        }

        if (continuousWeeks > 0) return (continuousWeeks - 1) * 7 + getDaysInWeek(today);

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

    int getDaysInWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int days = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return days == 0 ? 7 : days;
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
