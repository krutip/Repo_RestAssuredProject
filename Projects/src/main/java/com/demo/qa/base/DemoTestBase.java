package com.demo.qa.base;

import com.demo.qa.util.DemoTestUtil;
import com.demo.qa.util.WebEventListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DemoTestBase {
    public static WebDriver driver;
    public static Properties prop;
    public static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;

    public DemoTestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    "D:\\Git projects\\ZinniaXTest\\src\\main\\java\\com\\zinniax\\qa\\config\\config.properties");

            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {

        String browserName = prop.getProperty("browser");

       /* //Check if parameter passed from TestNG is 'firefox'
        if(browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        //Check if parameter passed as 'chrome'
        else if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }*/
        

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        e_driver = new EventFiringWebDriver(driver);
        // Now create object of EventListerHandler to register it with EventFiringWebDriver
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(DemoTestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(DemoTestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.get(prop.getProperty("baseurl"));

    }

    public void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("window.scrollBy(0,-500");
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("window.scrollBy(0,500");
    }
}

