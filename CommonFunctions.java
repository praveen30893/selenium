package com.automation.commons;

import com.automation.base.TestDriverFactory;
import com.automation.logger.AutomationLogger;
import com.automation.resourceHandler.ResourceHandler;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The Class CommonFunctions.
 *
 * @author praveenmourya
 */

public class CommonFunctions {

    public Logger log;
    TestDriverFactory base = new TestDriverFactory();
    private static WebDriver driver = null;
    int waitForElementTimeOut = 10;

    public CommonFunctions() {
        AutomationLogger logInstance = AutomationLogger.getInstance();
        PropertyConfigurator.configure(ResourceHandler.getPropertyValue("logger.properties"));
        log = logInstance.getLogger(this.getClass());
    }

    public static String getEpoch(String... method) {

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        DateFormat dateFormat;
        if(method.length == 0) {
            dateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
        }else {
            dateFormat = new SimpleDateFormat(method[0]);
        }
        Timestamp currentTimeStamp = new Timestamp(now.getTime());
        return dateFormat.format(currentTimeStamp);

    }

    public static String getConfigValue(String keyValue){
        return ResourceHandler.getPropertyValue(keyValue);
    }



}
