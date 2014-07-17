package june.repository;

import june.model.Record;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecordRepository extends CrudRepository<Record, Record.PK> {
    List<Record> findBySubjectIdAndDayBetween(Long subjectId, String start, String end);
    List<Record> findBySubjectIdAndSlotOrderByDayAsc(Long subjectId, Integer slot);
    Long removeBySubjectId(Long subjectId);
}
