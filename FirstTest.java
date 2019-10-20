package tests;

import com.automation.logger.AutomationLogger;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import scenarioBase.BaseTest;

public class FirstTest extends BaseTest {

    final private static Logger log = AutomationLogger.getInstance().getLogger(FirstTest.class);

    @Test
    public void verifyFirstTest() {


        log.info("Test Success 1 !!!");

        pass();
    }

    @Test
    public void verifyFirstTest1() {


        log.info("Test Success 2 !!!");

        pass();
    }
}
