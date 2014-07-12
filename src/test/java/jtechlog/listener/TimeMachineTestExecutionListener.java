package jtechlog.listener;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.util.Date;

public class TimeMachineTestExecutionListener extends AbstractTestExecutionListener {

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        TimeMachine timeMachine = testContext.getTestMethod().getAnnotation(TimeMachine.class);

        if (timeMachine != null) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd hh:mm");
            DateTime realTime = new DateTime(new Date());
            DateTime machineTime = formatter.parseDateTime(timeMachine.targetDate());

            long offset = machineTime.getMillis() - realTime.getMillis();
            DateTimeUtils.setCurrentMillisOffset(offset);
        }
    }
}
