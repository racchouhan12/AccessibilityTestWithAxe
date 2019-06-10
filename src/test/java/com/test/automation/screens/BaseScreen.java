package com.test.automation.screens;

import com.test.automation.utilities.ThisRun;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public abstract class BaseScreen {

    final WebDriver driver;
    protected static Logger logger = LogManager.getLogger(BaseScreen.class.getName());

    protected BaseScreen() {
        logger.debug("BaseScreen initialized...");
        ThisRun thisRun = ThisRun.getInstance();
        driver = thisRun.driver();

    }

    protected void naviagateTo(String URL) {
        driver.navigate().to(URL);
    }

    protected void sendText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    protected void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    protected void scrollDownBy(int pixel) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + pixel + ")");
    }

    protected void waitForElementAndSendTextInElement( By by, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(text);
    }

    protected void waitForElementToBeClickableAndClickOnElement(By by) {
        waitForElementToBeClickableAndClickOnElement(by, 10);
    }

    protected void waitForElementToBeClickableAndClickOnElement(By by, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    protected String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    protected Object executeJavaScript(String script) {
        return ((JavascriptExecutor)driver).executeScript(script);
    }
}
