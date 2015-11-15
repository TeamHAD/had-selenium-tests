package org.teamhad.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class SwitchIT extends HADIT {

    private final static String enabledClass = "btn-warning";
    private final static String disabledClass = "btn-default";

    private static final Logger logger = LoggerFactory.getLogger(SwitchIT.class);

    private String name;
    private boolean enabled;

    @DataProvider(name = "SwitchTestData")
    public static Object[][] getData(){
        return new Object[][] {
                {"Front Door Test", "Outside Light", true},
                {"Back Window Test", "Inside Light", true}
        };
    }

    @Factory(dataProvider = "SwitchTestData")
    public SwitchIT(
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
    public void switchElementExistsTest() throws Exception {
        // Find the text input element by its name
        WebElement switchElement = driver.findElement(By.cssSelector(".switch[name='" + name + "']"));

        Assert.assertTrue(switchElement!=null && switchElement.isDisplayed(), "Switch is displayed.");
    }

    @Test
    public void switchElementButtonExistsTest() throws Exception {
        // Find the text input element by its name
        WebElement switchElementButton = driver.findElement(By.cssSelector(".switch[name='" + name + "'] button"));

        Assert.assertTrue(switchElementButton!=null && switchElementButton.isDisplayed(), "Switch button is displayed.");
    }

    @Test
    public void switchElementButtonClassTest() throws Exception {
        // Find the text input element by its name
        WebElement switchElementButton = driver.findElement(By.cssSelector(".switch[name='" + name + "'] button"));

        String actualButtonClass = switchElementButton!=null? switchElementButton.getAttribute("class") : "";
        String expectedButtonClass = enabled? enabledClass : disabledClass;
        Assert.assertTrue(actualButtonClass.contains(expectedButtonClass), "Button class incorrect. Expected: " + expectedButtonClass + ", Actual: " + actualButtonClass);
    }

    @Test
    public void switchElementButtonClickTest() throws Exception {
        // Find the text input element by its name
        WebElement switchElementButton = driver.findElement(By.cssSelector(".switch[name='" + name + "'] button"));

        switchElementButton.click();
        logger.info("Clicked switch: " + name);

        String actualButtonClass = switchElementButton!=null? switchElementButton.getAttribute("class") : "";
        String expectedButtonClass = !enabled? enabledClass : disabledClass;
        Assert.assertTrue(actualButtonClass.contains(expectedButtonClass), "Button class incorrect. Expected: " + expectedButtonClass + ", Actual: " + actualButtonClass);
    }
}