package june.service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AnalyzerTest {

    @Test
    public void testElapsedWeeks() throws Exception {

    }

    @Test
    public void testAchievedWeeks() throws Exception {

    }

    @Test
    public void testRecentContinuousDays() throws Exception {

    }

    @Test
    public void testMaxContinuousDays() throws Exception {

    }

    @Test
    public void testGetFirstDayOfWeek() throws Exception {
        assertEquals("20140714", new Analyzer().getFirstDayOfWeek("20140717"));
    }

    @Test
    public void testGetWeekMap() throws Exception {
        List<String> days = Arrays.asList("20140701", "20140707", "20140708", "20140711", "20140801");
        Map<String, Integer> weekMap = new Analyzer().getWeekMap(days);
        assertEquals(5, weekMap.size());
        assertTrue(weekMap.containsKey("20140630"));
        assertTrue(weekMap.containsKey("20140707"));
        assertTrue(weekMap.containsKey("20140714"));
        assertTrue(weekMap.containsKey("20140721"));
        assertTrue(weekMap.containsKey("20140728"));
    }

    @Test
    public void testUpdateCount() throws Exception {
        List<String> days = Arrays.asList("20140701", "20140707", "20140708", "20140711", "20140801");
        Map<String, Integer> weekMap = new Analyzer().getWeekMap(days);
        new Analyzer().updateCount(weekMap, days);
        assertEquals(new Integer(1), weekMap.get("20140630"));
        assertEquals(new Integer(3), weekMap.get("20140707"));
        assertEquals(new Integer(0), weekMap.get("20140714"));
        assertEquals(new Integer(0), weekMap.get("20140721"));
        assertEquals(new Integer(1), weekMap.get("20140728"));
    }
}