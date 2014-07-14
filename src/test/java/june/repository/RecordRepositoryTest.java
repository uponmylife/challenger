package june.repository;

import june.Runner;
import june.model.Record;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Runner.class)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class RecordRepositoryTest {
    @Autowired
    private RecordRepository repository;

    @Before
    public void setup() throws Exception {
//        repository.save(new Record(1234l, "20140627", 0));
//        repository.save(new Record(1234l, "20140629", 0));
//        repository.save(new Record(1234l, "20140629", 2));
//        repository.save(new Record(1234l, "20140616", 2));
//        repository.save(new Record(4321l, "20140628", 2));
//        repository.save(new Record(4321l, "20140630", 2));
//        repository.save(new Record(4321l, "20140701", 2));
    }

    @Test
    public void select() throws Exception {
        for (Record record : repository.findBySubjectIdAndDayBetween(1234l, "20140628", "20140630")) System.out.println(record);
    }

    @Test
    public void delete() throws Exception {
        System.out.println(repository.removeBySubjectId(58l));
    }
}