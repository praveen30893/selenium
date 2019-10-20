package com.automation.logger;

import org.apache.log4j.Logger;

/**
 * The Class AutomationLogger: SingleTon Class to get single instance of Logger
 * @author praveenmourya
 */
public class AutomationLogger{

    private static AutomationLogger instance = null;
    private static Object mutex= new Object();
    private Logger log;

    /**
     * Instantiates a new com.automation logger.
     */
    private AutomationLogger() {}

    /**
     * Gets the logger.
     *
     * @param clz the clz
     * @return the logger
     */
    @SuppressWarnings("rawtypes")
    public Logger getLogger(Class clz) {
        log = Logger.getLogger(clz.getName());
        return log;
    }

    /**
     * Gets the single instance of AutomationLogger.
     *
     * @return single instance of AutomationLogger
     */
    public static AutomationLogger getInstance() {
        if(instance == null)
            synchronized (mutex){
                if(instance==null)
                    instance = new AutomationLogger();
            }
        return instance;
    }
}