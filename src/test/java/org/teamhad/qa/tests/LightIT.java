package org.teamhad.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class LightIT extends HADIT {

    private final static String enabledClass = "btn-warning";
    private final static String disabledClass = "btn-default";

    private static final Logger logger = LoggerFactory.getLogger(LightIT.class);

    private String name;
    private boolean enabled;

    @DataProvider(name = "LightTestData")
    public static Object[][] getData(){
        return new Object[][] {
                {"Outside Test", "Outside security light", false},
                {"Basement Test", "Basement light", false},
                {"Side Test", "Side outdoor light", false},
                {"Back Test", "Back outdoor light", false},
                {"Front Test", "Front outdoor light", false},
                {"Garage Test", "Garage light", false}
        };
    }

    @Factory(dataProvider = "LightTestData")
    public LightIT(
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
    public void lightExistsTest() throws Exception {
        // Find the text input element by its name
        WebElement light = driver.findElement(By.cssSelector(".light[name='" + name + "']"));

        Assert.assertTrue(light!=null && light.isDisplayed(), "Light is displayed.");
    }

    @Test
    public void lightButtonExistsTest() throws Exception {
        // Find the text input element by its name
        WebElement lightButton = driver.findElement(By.cssSelector(".light[name='" + name + "'] button"));

        Assert.assertTrue(lightButton!=null && lightButton.isDisplayed(), "Light button is displayed.");
    }

    @Test
    public void lightButtonClassTest() throws Exception {
        // Find the text input element by its name
        WebElement lightButton = driver.findElement(By.cssSelector(".light[name='" + name + "'] button"));

        String actualButtonClass = lightButton!=null? lightButton.getAttribute("class") : "";
        String expectedButtonClass = enabled? enabledClass : disabledClass;
        Assert.assertTrue(actualButtonClass.contains(expectedButtonClass), "Button class incorrect. Expected: " + expectedButtonClass + ", Actual: " + actualButtonClass);
    }
    
    @Test
    public void lightElementButtonClickTest() throws Exception {
        // Find the text input element by its name
        WebElement lightElementButton = driver.findElement(By.cssSelector(".light[name='" + name + "'] button"));

        lightElementButton.click();
        logger.info("Clicked light: " + name);

        String actualButtonClass = lightElementButton!=null? lightElementButton.getAttribute("class") : "";
        String expectedButtonClass = !enabled? enabledClass : disabledClass;
        Assert.assertTrue(actualButtonClass.contains(expectedButtonClass), "Button class incorrect. Expected: " + expectedButtonClass + ", Actual: " + actualButtonClass);
    }
}