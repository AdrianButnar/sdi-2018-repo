package laboratory.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.laboratory.domain.Student;

/**
 * @author AlexandruBuhai
 *
 * Is testing getters and setters necessary5? ?!#@???>?
 */
public class StudentTest {
    private static final Long ID = new Long (1);
    private static final Long NEW_ID = new Long (2);
    private static final String SERIAL_NUMBER = "baie2079";
    private static final String NEW_SERIAL_NUMBER = "baie2081";
    private static final String NAME = "Bob";
    private static final int GROUP = 123;

    private Student student;

    @Before
    public void setUp() throws Exception {
        student = new Student(SERIAL_NUMBER, NAME);
        student.setId(ID);
    }

    @After
    public void tearDown() throws Exception {
        student=null;
    }

    @Test
    public void testGetSerialNumber throws Exception {
        student.setSerialNumber(NEW_SERIAL_NUMBER);
        assert("Something went wrong in the serialNumber change",
        NEW_SERIAL_NUMBER, student.getSerialNumber())
    }
}
