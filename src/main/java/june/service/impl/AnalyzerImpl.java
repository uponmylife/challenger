package june.service.impl;

import june.model.Record;
import june.service.Analyzer;

import java.util.*;

public class AnalyzerImpl implements Analyzer {
    private int goalCount;
    private Date today;
    private Map<String, Integer> weekMap = new LinkedHashMap<String, Integer>();

    public AnalyzerImpl(int goalCount, List<String> days) {
        this(goalCount, days, new Date());
    }

    public AnalyzerImpl(int goalCount, List<String> days, Date today) {
        this.today = today;
        this.goalCount = goalCount;
        makeWeekMap(days);
        applyRecord(days);
    }

    @Override
    public int elapsedWeeks() {
        return weekMap.size();
    }

    @Override
    public int achievedWeeks() {
        int achieves = 0;
        for (Integer count : weekMap.values()) if (count >= goalCount) achieves++;
        return achieves;
    }

    @Override
    public int recentContinuousDays() {
        int continuousWeeks = 0;
        List<Integer> list = new ArrayList<Integer>(weekMap.values());
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            int count = list.get(i);
            if (i == 0) count += getRestDaysInWeek();
            if (count < goalCount) break;
            continuousWeeks++;
        }

        if (continuousWeeks > 0) return (continuousWeeks - 1) * 7 + getDaysInWeek();

        return 0;
    }

    @Override
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


    int getDaysInWeek() {
        return getDaysInWeek(today);
    }

    int getDaysInWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int days = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return days == 0 ? 7 : days;
    }

    int getRestDaysInWeek() {
        return 7 - getDaysInWeek() + 1;
    }

    Map<String, Integer> getWeekMap() {
        return weekMap;
    }

    private void makeWeekMap(List<String> days) {
        if (days.size() == 0) return;
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

    private Calendar getFirstCalendarOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal;
    }
}
