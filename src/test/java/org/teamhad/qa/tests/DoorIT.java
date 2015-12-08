package org.teamhad.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class DoorIT extends HADIT {

    private final static String enabledClass = "btn-danger ";
    private final static String disabledClass = "btn-default";

    private static final Logger logger = LoggerFactory.getLogger(DoorIT.class);

    private String name;
    private boolean enabled;

    @DataProvider(name = "DoorTestData")
    public static Object[][] getData(){
        return new Object[][] {
                {"Front Door Test", "Front door lock", false},
                {"Garage Door Test", "Garage door lock", false},
                {"Basement Door Test", "Basement door lock", false},
                {"Living Room Test", "Living room window lock", false},
                {"Bedroom Test", "Bedroom window lock", false},
                {"Kitchen Door Test", "Kitchen window lock", false}
        };
    }

    @Factory(dataProvider = "DoorTestData")
    public DoorIT(
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
    public void doorElementExistsTest() throws Exception {
        // Find the text input element by its name
        WebElement doorElement = driver.findElement(By.cssSelector(".door[name='" + name + "']"));

        Assert.assertTrue(doorElement!=null && doorElement.isDisplayed(), "Door is displayed.");
    }

    @Test
    public void doorElementButtonExistsTest() throws Exception {
        // Find the text input element by its name
        WebElement doorElementButton = driver.findElement(By.cssSelector(".door[name='" + name + "'] button"));

        Assert.assertTrue(doorElementButton!=null && doorElementButton.isDisplayed(), "Door button is displayed.");
    }

    @Test
    public void doorElementButtonClassTest() throws Exception {
        // Find the text input element by its name
        WebElement doorElementButton = driver.findElement(By.cssSelector(".door[name='" + name + "'] button"));

        String actualButtonClass = doorElementButton!=null? doorElementButton.getAttribute("class") : "";
        String expectedButtonClass = enabled? enabledClass : disabledClass;
        Assert.assertTrue(actualButtonClass.contains(expectedButtonClass), "Button class incorrect. Expected: " + expectedButtonClass + ", Actual: " + actualButtonClass);
    }
}