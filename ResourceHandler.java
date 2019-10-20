package com.automation.resourceHandler;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.automation.logger.AutomationLogger;

/**
 * The Class ResourceHandler.
 * @author praveenmourya
 */

@SuppressWarnings("serial")
public class ResourceHandler extends Properties{

    final private static Properties prop = new Properties();

    private static FileInputStream inStream = null;

    public static Logger log = AutomationLogger.getInstance().getLogger(ResourceHandler.class);

    /**
     * Gets the property value.
     *
     * @param key the key
     * @return the property value
     */
    public static String getPropertyValue(String key)
    {
		 try{
            if(System.getProperty(key) == null || System.getProperty(key).length() == 0 ) {
                inStream = new FileInputStream("config.properties");
                prop.load(inStream);
            }
            else
                return System.getProperty(key);
        }
        catch(Exception e) {
            System.out.print(e.toString());
        }

        System.setProperty(key, prop.getProperty(key).toString());

        return System.getProperty(key);
    }
}
