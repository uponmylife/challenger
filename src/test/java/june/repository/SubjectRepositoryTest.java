package june.repository;

import june.Runner;
import june.model.Subject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Runner.class)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class SubjectRepositoryTest {
    @Autowired
    private SubjectRepository repository;

    @Before
    public void setUp() throws Exception {
        Subject subject1 = new Subject("작은 성공을 느껴보자", "");
        repository.save(subject1);
        Subject subject2 = new Subject("건강한 몸에서 건강한 마음", "");
        repository.save(subject2);
    }

    @Test
    public void test() throws Exception {

    }
}