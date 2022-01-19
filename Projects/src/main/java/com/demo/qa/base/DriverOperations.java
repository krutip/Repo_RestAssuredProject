package com.demo.qa.base;

import java.util.concurrent.TimeUnit;


import com.demo.qa.util.DemoTestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DriverOperations extends DemoTestUtil {

    static int timeout = Integer.parseInt(prop.getProperty("DynamicWait"));
    static WebDriverWait wait = new WebDriverWait(driver, timeout);

    //Clear input field and enter data and Tab out
    public static void clearFieldAndEnterStringData(WebElement element, String inputdata) {
        // Assert.assertTrue(isDisplayed(element), " Web Element: " + element + " is ", " displayed.");
        try {
            element.clear();
            element.sendKeys(inputdata);
            System.out.println("Clear field " + element + " and entered data " + inputdata);
        } catch (Exception e) {
            System.out.println("Failed operation while clearing field " + element + " and entering data " + inputdata + " and error is: " + e.getMessage());
        }
    }

    //To Assert whether element is displayed or not
    public static boolean isDisplayed(WebElement element) {

        boolean resultFlag;
        try {
            resultFlag = element.isDisplayed();
            System.out.println("element :" + element + " is displayed and result is: " + resultFlag);
        } catch (Exception e) {
            System.out.println("element :" + element + " is not displayed and result is: false");
            resultFlag = false;
        }
        return resultFlag;
    }


    //Use Action to Open Navigation Menu and to navigate
    public static WebDriver expandTopNavigationMenuAndPerformNavitation(String ParentLink, String ChildLink) {
        Actions action = new Actions(driver);
        WebElement Parent = driver.findElement(By.linkText(ParentLink));
        // Assert.assertTrue(isDisplayed(Parent), " Parent Element: " + Parent + " is ", " displayed.");
        WebElement Child;
        try {
            getWhenElementVisible(Parent);
            Parent.click();
            action.moveToElement(Parent).build().perform();
            Child = driver.findElement(By.linkText(ChildLink));
            // Assert.assertTrue(isDisplayed(Child), " Child Element: " + Child + " is ", " displayed.");
            Child.click();
            System.out.println("Navigated to " + Parent + " and clicked on child element" + Child);
        } catch (Exception e) {
            Child = driver.findElement(By.linkText(ChildLink));
            System.out.println("Error thrown while navigating to " + Parent + " and clicking on child menu element :" + Child + " and error is: " + e.getMessage());
        }
        return driver;
    }

    public static void clickWhenElementIsClickable(WebElement element) {
        try {
            getWhenElementVisible(element);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({\r\n" +
                    "            behavior: 'auto',\r\n" +
                    "            block: 'center',\r\n" +
                    "            inline: 'center'\r\n" +
                    "        });", element);
            element.click();

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({\r\n" +
                    "            behavior: 'auto',\r\n" +
                    "            block: 'center',\r\n" +
                    "            inline: 'center'\r\n" +
                    "        });", element);
            element.click();
        }
    }

    public static WebElement ElementIsClickable(WebElement element) {
        element = wait.until(ExpectedConditions.elementToBeClickable(element));
        //Assert.assertTrue(isDisplayed(element), " Web Element: " + element + " is ", " displayed.");
        return element;
    }

    //Dynamic wait
    public static WebElement getWhenElementVisible(WebElement element) {
        element = wait.until(ExpectedConditions.visibilityOf(element));
        // Assert.assertTrue(isDisplayed(element), " Web Element: " + element + " is ", " displayed.");
        return element;
    }

    //Dummy Element verification to add wait in script
    public static void waitforElement() {
        try {
            long Implicit_DeDupe_wait = Long.parseLong(prop.getProperty("ImplicitDeDupeScreenWait"));
            driver.manage().timeouts().implicitlyWait(Implicit_DeDupe_wait, TimeUnit.SECONDS);
            System.out.println("Finding the element");
            driver.findElement(By.xpath("//div//img[@data-src='googleusercontent.com/-8EHWNrWDChI/AAAAAAAAAAI/AAAAAAAAAAA']"));
            ;
            System.out.println("Found the element");// condition you are certain won't be true
        } catch (Exception e) {
            long Implicit_wait = Long.parseLong(prop.getProperty("ImplicitWait"));
            driver.manage().timeouts().implicitlyWait(Implicit_wait, TimeUnit.SECONDS);
            System.out.println("Element Not Found: " + e.getMessage());
        }
        return;
    }

    public static void waitandClick(WebElement element) {
        try {
            ElementIsClickable(element);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({\r\n" +
                    "            behavior: 'auto',\r\n" +
                    "            block: 'center',\r\n" +
                    "            inline: 'center'\r\n" +
                    "        });", element);
            element.click();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({\r\n" +
                    "            behavior: 'auto',\r\n" +
                    "            block: 'center',\r\n" +
                    "            inline: 'center'\r\n" +
                    "        });", element);
            element.click();
        }
    }

    public static WebElement getWhenElementContainsText(WebElement element, String expectedSelectionOption) {
        getWhenElementVisible(element);
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedSelectionOption));
        String actualSelectionOptions = element.getText();
        if (actualSelectionOptions.contains(expectedSelectionOption)) {
            System.out.println(" Web Element: " + element + " is displayed and contains Text is:" + expectedSelectionOption);
        }
        return element;
    }

    public static WebDriver moveToElement(WebElement element) {
        Actions action = new Actions(driver);
        // Assert.assertTrue(isDisplayed(element), " Element: " + element + " is ", " displayed.");
        try {
            getWhenElementVisible(element);
            action.moveToElement(element).build().perform();
            System.out.println("Navigated to " + element);
        } catch (Exception e) {
            System.out.println("Error thrown while navigating to " + element);
        }
        return driver;
    }


    //use to log out from application
    public static void logOut() throws Exception {
//				WebElement parent = driver.findElement(By.id("LoggedInAs"));
//				WebElement child = driver.findElement(By.id("btnLogOut"));
//				waitforElement();
//				waitandClick(parent);
//				waitandClick(child);
//				driver.close();
//				driver.quit();

        expandTopNavigationMenuAndPerformNavitation("User: administrator", "Log out");
        driver.close();
        driver.quit();
    }

    //Handle drop box and select option by visible text:
    public static void selectDropdownOptionByVisibleText(WebElement element, String VisibleText) {
        //Assert.assertTrue(isDisplayed(element), " Web Element: " + element + " is ", " displayed.");
        try {
            Select select = new Select(element);
            select.selectByVisibleText(VisibleText);
            System.out.println("Selected Element " + element + " with visible text:" + VisibleText);
        } catch (Exception e) {
            System.out.println("Error occured while Selecting Element " + element + " with visible text:" + VisibleText + " and error is: " + e.getMessage());
        }
    }

    //Handle drop box and select option by value:
    public static void selectDropdownOptionByValue(WebElement element, String Value) {
        // Assert.assertTrue(isDisplayed(element), " Web Element: " + element + " is ", " displayed.");
        try {
            Select select = new Select(element);
            select.selectByValue(Value);
            System.out.println("Selected Element " + element + " with value:" + Value);
        } catch (Exception e) {
            System.out.println("Error occured while Selecting Element " + element + " with value:" + Value + " and error is: " + e.getMessage());
        }
    }

    //Handle drop box and select option by Index:
    public static void selectDropdownOptionByIndex(WebElement element, int Index) {
        //Assert.assertTrue(isDisplayed(element), " Web Element: " + element + " is ", " displayed.");
        try {
            Select select = new Select(element);
            select.selectByIndex(Index);
            System.out.println("Selected Element " + element + " with Index:" + Index);
        } catch (Exception e) {
            System.out.println("Error occured while Selecting Element " + element + " with Index:" + Index + " and error is: " + e.getMessage());
        }
    }

}
