package june.repository;

import june.model.Record;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecordRepository extends CrudRepository<Record, Record.RecordKey> {
    List<Record> findByDayBetween(String start, String end);
}
