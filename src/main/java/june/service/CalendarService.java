package june.service;

import june.model.Cell;
import june.model.DateScope;
import june.model.Record;
import june.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalendarService {
    @Autowired
    private RecordRepository recordRepository;

    public List<Cell> show(int weekAgo) {
        DateScope dateScope = new DateScope(weekAgo);
        List<Cell> cells = new ArrayList();
        Map<String, Cell> cellMap = new HashMap();
        for (Date date : dateScope.getDates()) {
            Cell cell = new Cell(date);
            cells.add(cell);
            cellMap.put(cell.getDay(), cell);
        }

        String startDay = Record.toDay(dateScope.getStartDate());
        String endDay = Record.toDay(dateScope.getEndDate());
        for (Record record : recordRepository.findByDayBetween(startDay, endDay)) {
            Cell cell = cellMap.get(record.getDay());
            cell.check(record.getSlot());
        }

        return cells;
    }

    public boolean toggle(String day, int slot) {
        Record.RecordKey recordKey = new Record.RecordKey(day, slot);
        Record record = recordRepository.findOne(recordKey);
        if (record == null) {
            recordRepository.save(new Record(day, slot));
            return true;
        } else {
            recordRepository.delete(recordKey);
            return false;
        }
    }
}
