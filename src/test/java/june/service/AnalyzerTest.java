package june.service;

import june.model.Record;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnalyzerTest {
    List<String> days = Arrays.asList("20140701", "20140706", "20140707", "20140708", "20140711", "20140730", "20140731");
    Analyzer analyzer = new Analyzer(2, days, Record.fromDay("20140801"));

    @Test
    public void testElapsedWeeks() throws Exception {
        assertEquals(5, analyzer.elapsedWeeks());
    }

    @Test
    public void testAchievedWeeks() throws Exception {
        assertEquals(3, analyzer.achievedWeeks());
    }

    @Test
    public void testRecentContinuousDays() throws Exception {
        assertEquals(5, analyzer.recentContinuousDays());
        Analyzer analyzer1 = new Analyzer(5, days, Record.fromDay("20140801"));
        assertEquals(5, analyzer1.recentContinuousDays());
        Analyzer analyzer2 = new Analyzer(2, days, Record.fromDay("20140804"));
        assertEquals(8, analyzer2.recentContinuousDays());
        List<String> days1 = new ArrayList<String>(days);
        days1.add("20140804");
        days1.add("20140805");
        Analyzer analyzer3 = new Analyzer(2, days1, Record.fromDay("20140812"));
        assertEquals(16, analyzer3.recentContinuousDays());
    }

    @Test
    public void testMaxContinuousDays() throws Exception {
        assertEquals(14, analyzer.maxContinuousDays());
    }

    @Test
    public void testGetFirstDayOfWeek() throws Exception {
        assertEquals("20140714", analyzer.getFirstDayOfWeek("20140717"));
    }

    @Test
    public void testGetDaysInThisWeek() throws Exception {
        assertEquals(1, analyzer.getDaysInWeek(Record.fromDay("20140707")));
        assertEquals(3, analyzer.getDaysInWeek(Record.fromDay("20140709")));
        assertEquals(7, analyzer.getDaysInWeek(Record.fromDay("20140713")));
    }

    @Test
    public void testGetWeekMap() throws Exception {
        Map<String, Integer> weekMap = analyzer.getWeekMap();
        assertEquals(5, weekMap.size());
        assertTrue(weekMap.containsKey("20140630"));
        assertTrue(weekMap.containsKey("20140707"));
        assertTrue(weekMap.containsKey("20140714"));
        assertTrue(weekMap.containsKey("20140721"));
        assertTrue(weekMap.containsKey("20140728"));
    }

    @Test
    public void testUpdateCount() throws Exception {
        Map<String, Integer> weekMap = analyzer.getWeekMap();
        assertEquals(new Integer(2), weekMap.get("20140630"));
        assertEquals(new Integer(3), weekMap.get("20140707"));
        assertEquals(new Integer(0), weekMap.get("20140714"));
        assertEquals(new Integer(0), weekMap.get("20140721"));
        assertEquals(new Integer(2), weekMap.get("20140728"));
    }
}