package com.automation.base;

import com.automation.logger.AutomationLogger;
import com.automation.resourceHandler.ResourceHandler;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;

import java.io.File;

public class TestDriverFactory {

    /** The driver. */
    public static WebDriver driver	= null;
    /** The mutex. */
    private static Object   mutex   = new Object();
    /** The instance. */
    private static TestDriverFactory	instance    = null;

    public static Logger log = AutomationLogger.getInstance().getLogger(TestDriverFactory.class);

    public void setupBrowser(String browser) {
        if (browser.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", ResourceHandler.getPropertyValue("com.automation.ie.driver.path"));
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
            this.openURL(getApplicationURL());
            log.info("***************** IE Browser launched *****************");

        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", ResourceHandler.getPropertyValue("com.automation.gecko.driver.path"));
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            this.openURL(getApplicationURL());
            log.info("***************** Gecko Browser launched *****************");

        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", ResourceHandler.getPropertyValue("automation.chrome.driver.path"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--user-data-dir="+System.getProperty("user.home")+"/AppData/Local/Google/Chrome/User_Data".replace('/', File.separatorChar));
            options.addArguments("start-maximized");
            options.setExperimentalOption("useAutomationExtension", false);
            driver = new ChromeDriver(options);
            this.openURL(getApplicationURL());
            log.info("***************** Chrome Browser launched *****************");
        }
    }

    public void openURL(String url) {
        driver.get(url);
        log.info(url + " has been opened in browser successfully.");
    }

    @AfterClass
    public void tearDown() {
        if (!(getWebDriver() == null) && !(getWebDriver().toString().contains("null"))) {
            try {
                getWebDriver().close();
                getWebDriver().quit();
                if (!getWebDriver().toString().contains("null"))
                    getWebDriver().quit();
            }
            catch (Throwable t) {
                log.info("ERROR: Unable to close browser properly");
            }
        }
    }

    private String getConfigTimeoutValue() {
        return ResourceHandler.getPropertyValue("wait.timeout");
    }

    private String getApplicationURL() {
        return ResourceHandler.getPropertyValue("application.url");
    }


    public WebDriver getWebDriver() {
        return driver;
    }

    public TestDriverFactory() {}

    public static TestDriverFactory getInstance() {
        if (instance == null)
            synchronized (mutex) {
                if (instance == null)
                    instance = new TestDriverFactory();
            }
        return instance;
    }

}
