package june.service;

import june.model.Goal;
import june.model.Record;
import june.model.Subject;
import june.repository.RecordRepository;
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
            List<Record> records = recordRepository.findBySubjectIdAndSlot(subject.getId(), i);
            Goal goal = subject.getGoals().get(i);
            goal.setAnalyzer(new Analyzer(subject.getGoals().get(i).getCount(), makeDayList(records)));
        }
    }

    private List<String> makeDayList(List<Record> records) {
        List<String> days = new ArrayList<String>();
        for (Record record : records) days.add(record.getDay());
        return days;
    }
}
