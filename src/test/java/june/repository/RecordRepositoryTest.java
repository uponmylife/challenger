package june.repository;

import june.Runner;
import june.model.Record;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Runner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RecordRepositoryTest {
    @Autowired
    private RecordRepository repository;

    @Before
    public void setup() throws Exception {
        repository.save(new Record("20140627", 0));
        repository.save(new Record("20140629", 0));
        repository.save(new Record("20140629", 2));
        repository.save(new Record("20140616", 2));
        repository.save(new Record("20140628", 2));
        repository.save(new Record("20140630", 2));
        repository.save(new Record("20140701", 2));
    }

    @Test
    public void select() throws Exception {
        List<Record> list = repository.findByDayBetween("20140628", "20140630");
        System.out.println(list);
        assertEquals(4, list.size());
    }
}