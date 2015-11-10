package org.teamhad.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class IndicatorIT extends HADIT {

    private final static String enabledClass = "btn-success";
    private final static String disabledClass = "btn-danger";

    private static final Logger logger = LoggerFactory.getLogger(IndicatorIT.class);

    private String name;
    private boolean enabled;

    @DataProvider(name = "IndicatorTestData")
    public static Object[][] getData(){
        return new Object[][] {
                {"Front Door Test", "Front Door", true},
                {"Back Window Test", "Back Window", true}
        };
    }

    @Factory(dataProvider = "IndicatorTestData")
    public IndicatorIT(
            String testName,
            String name,
            boolean enabled) {
        super(testName);
       logger.info("Factory initializing parameters for test: " + testName);
        this.name = name;
        this.enabled = enabled;
       logger.info("Factory successfully initialized parameters for test: " + testName);
    }

    @Test
    public void indicatorExistsTest() throws Exception {
        // Find the text input element by its name
        WebElement indicator = driver.findElement(By.cssSelector(".indicator[name='" + name + "']"));

        Assert.assertTrue(indicator!=null && indicator.isDisplayed(), "Indicator is displayed.");
    }

    @Test
    public void indicatorButtonExistsTest() throws Exception {
        // Find the text input element by its name
        WebElement indicatorButton = driver.findElement(By.cssSelector(".indicator[name='" + name + "'] button"));

        Assert.assertTrue(indicatorButton!=null && indicatorButton.isDisplayed(), "Indicator button is displayed.");
    }

    @Test
    public void indicatorButtonClassTest() throws Exception {
        // Find the text input element by its name
        WebElement indicatorButton = driver.findElement(By.cssSelector(".indicator[name='" + name + "'] button"));

        String actualButtonClass = indicatorButton!=null? indicatorButton.getAttribute("class") : "";
        String expectedButtonClass = enabled? enabledClass : disabledClass;
        Assert.assertTrue(actualButtonClass.contains(expectedButtonClass), "Button class incorrect. Expected: " + expectedButtonClass + ", Actual: " + actualButtonClass);
    }
}