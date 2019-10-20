package scenarioBase;

import com.automation.base.TestDriverFactory;
import com.automation.commons.CommonFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;


public class BaseTest extends CommonFunctions{

    public WebDriver driver;
    public static ExtentTest test;
    public static ExtentReports report;

    TestDriverFactory base = new TestDriverFactory();

    @BeforeTest
    public void setBrowserAndLogin() throws Exception {

        base.setupBrowser(getConfigValue("browser.name"));
        driver = base.getWebDriver();
    }

    @AfterTest(alwaysRun = true)
    public void closeSuite() {

        base.getWebDriver().manage().deleteAllCookies();
        base.tearDown();
    }

    @BeforeClass
    public static void startTest()
    {
        report = new ExtentReports(getConfigValue("automation.extent.report.path") + "\\ExtentReportResults_"+ getEpoch()+".html");
        test = report.startTest("ExtentDemo");
    }

    @AfterClass
    public static void endTest()
    {
        report.endTest(test);
        report.flush();
    }

    public void pass(){
        test.log(LogStatus.PASS, "This test is passed !");
    }

    public void fail(){
        test.log(LogStatus.FAIL, "This test is failed !");
    }


}
