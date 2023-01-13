package com.windriver;

import com.windriver.WinDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import io.appium.java_client.windows.WindowsDriver;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WinAppCalcDemoTest
{
    WindowsDriver driver = null;
    public String appPath="C:\\ProgramData\\chocolatey\\lib\\oldcalc\\tools\\Old Calculator for Windows 10.exe";
    //public String appPath="C:\\Program Files\\OldClassicCalc\\calc1.exe";

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

    /*@Test(description="Demonstration of Mouse Actions using ActionChains")
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
    }*/

    @Test(description="Demonstration of Button click", priority = 1)
    public void button_click_interactions()
    {

        driver.findElement(By.name("1")).click();
        driver.findElement(By.name("Add")).click();
        driver.findElement(By.name("9")).click();
        driver.findElement(By.name("Equals")).click();

        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        WebElement resultsElement = driver.findElement(By.name("Result"));

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