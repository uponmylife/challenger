package june.service;

import june.model.Goal;
import june.model.Record;
import june.model.Subject;
import june.repository.RecordRepository;
import june.service.impl.AnalyzerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private RecordRepository recordRepository;

    public void analyze(Subject subject) {
        for (int i=0; i<3; i++) {
            List<Record> records = recordRepository.findBySubjectIdAndSlotOrderByDayAsc(subject.getId(), i);
            Goal goal = subject.getGoals().get(i);
            AnalyzerImpl analyzer = new AnalyzerImpl(goal.getCount(), makeDayList(records));
            goal.setAnalyzer(analyzer);
        }
    }

    private List<String> makeDayList(List<Record> records) {
        List<String> days = new ArrayList<String>();
        for (Record record : records) days.add(record.getDay());
        return days;
    }
}
