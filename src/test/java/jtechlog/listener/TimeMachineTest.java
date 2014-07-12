package jtechlog.listener;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThat;
import static uk.co.it.modular.hamcrest.date.DateMatchers.within;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners(TimeMachineTestExecutionListener.class)
public class TimeMachineTest {

    @Test
    @TimeMachine(targetDate = "2014-01-01 10:00")
    public void travelToPast() {
        // When
        DateTime now = new DateTime();

        // Then
        DateTime expected = new DateTime(2014, 1, 1, 10, 0);
        assertThat(now.toDate(), within(5, TimeUnit.SECONDS, expected.toDate()));
    }
}
