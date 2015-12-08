package org.teamhad.qa.tests;

import com.lazerycode.selenium.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.teamhad.qa.listeners.TestNGMethodRenamer;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners({TestNGMethodRenamer.class})
public class HADIT extends DriverFactory {

    private String dashboardURL = "localhost:8080/userHome.html";
    private String expectedPageTitle = "Home Automation Dashboard";

    private static final Logger logger = LoggerFactory.getLogger(HADIT.class);

    protected WebDriver driver;
    private String testName;

    public HADIT(String testName) {
        this.testName = testName;
    }

    @BeforeMethod(alwaysRun = true)
    public void setupMethod() throws Exception {
        loadDashboard();
    }

    public void loadDashboard() throws Exception{
        // Create a new WebDriver instance
        this.driver = createDriverIfNeeded(driver);
        goToDashboardIfNeeded();
    }

    private void goToDashboardIfNeeded() throws Exception {
        if (driver.getCurrentUrl().contains(dashboardURL) && driver.getTitle() == expectedPageTitle) {
            //logger.info("Dashboard already loaded.");
        } else {
            driver.get(dashboardURL);
            //logger.info("Dashboard not found. Loading new dashboard.");
        }
    }

    private void verifyPage(){
        // Check the title of the page
        logger.info("Verify page title is: " + expectedPageTitle);
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle, expectedPageTitle, "Page title incorrect.");
        logger.info("On correct page. Page title is: " + actualPageTitle);
    }

    private WebDriver createDriverIfNeeded(WebDriver driver) throws Exception {
        if (driver == null || driver.getWindowHandle() == null || driver.getWindowHandle() == "") {
            //logger.info("No driver found. Getting new driver.");
            return getDriver();
        } else {
            //logger.info("Driver found. Using old driver.");
            return driver;
        }
    }
}