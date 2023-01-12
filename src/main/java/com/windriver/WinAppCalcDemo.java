package com.windriver;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import io.appium.java_client.windows.WindowsDriver;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WinAppCalcDemo
{
    WindowsDriver driver = null;
    public String appPath="Microsoft.WindowsCalculator_8wekyb3d8bbwe!App";

    @BeforeTest
    public void testSetUp() throws Exception
    {
        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability("ms:experimental-webdriver", true);
        capability.setCapability("app",appPath);
        capability.setCapability("platformName", "Windows");
        capability.setCapability("deviceName", "Windows10Machine");

        WinDriver.start();

        driver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), capability);
    }

    @Test(description="Demonstration of Mouse Actions using ActionChains")
    public void test_mouse_interactions() throws InterruptedException
    {
        Actions action = new Actions(driver);

        WebElement menuItem = driver.findElementByAccessibilityId("TogglePaneButton");
        action.click(menuItem);
        action.perform();

        Thread.sleep(3000);

        WebElement scientific = driver.findElementByAccessibilityId("Standard");
        action.click(scientific);
        action.perform();
    }

    @Test(description="Demonstration of Button click", priority = 1)
    public void button_click_interactions() throws InterruptedException
    {
        driver.findElementByAccessibilityId("num1Button").click();

        driver.findElement(By.name("Plusz")).click();
        driver.findElement(By.name("Kilenc")).click();
        driver.findElement(By.name("Egyenlő")).click();

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        WebElement resultsElement = driver.findElementByAccessibilityId("CalculatorResults");

        String resultantText = "10";
        String resultsElementText = resultsElement.getText().replace("Megjelenített érték: ","").trim();

        Assert.assertEquals(resultantText, resultsElementText);
    }

    @AfterTest
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }
}