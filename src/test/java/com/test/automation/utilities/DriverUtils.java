package com.test.automation.utilities;

import com.test.automation.helpers.KEYS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverUtils {

    private WebDriver driver;

    private ThisRun thisRun = ThisRun.getInstance();

    private static Logger logger = LogManager.getLogger(DriverUtils.class.getName());
    public String browser;

    public DriverUtils(String browser) {
        this.browser = browser;
    }


    private WebDriver instantiateChromeDriver() {
        String driverToBeLoaded = thisRun.getAsString(KEYS.OS_NAME.toString()).contains("Windows") ? "chromedriver_win.exe": "chromedriver_mac";

        System.setProperty("webdriver.chrome.driver", thisRun.getAsString(KEYS.TEST_RESOURCES.toString())+"/"+driverToBeLoaded);
        driver = new ChromeDriver();
        driver.get(thisRun.getAsString(KEYS.APP_URL.toString()));
        driver.manage().window().fullscreen();
        return driver;
    }

    private WebDriver instantiateFireFoxDriver() {
        String driverToBeLoaded = thisRun.getAsString(KEYS.OS_NAME.toString()).contains("Windows") ? "geckodriver_win.exe": "geckodriver_mac.exe";

        System.setProperty("webdriver.gecko.driver", thisRun.getAsString(KEYS.TEST_RESOURCES.toString())+"/"+driverToBeLoaded);
        driver = new FirefoxDriver();
        driver.get(thisRun.getAsString(KEYS.APP_URL.toString()));
        driver.manage().window().fullscreen();
        return driver;
    }

      private DesiredCapabilities getBrowserCapabilities(String browserType) {
        switch (browserType) {
            case "firefox":
                logger.info("Opening firefox driver in Node");
                return DesiredCapabilities.firefox();
            case "chrome":
                logger.info("Opening chrome driver in Node");
                return DesiredCapabilities.chrome();
            case "IE":
                logger.info("Opening IE driver in Node");
                return DesiredCapabilities.internetExplorer();
            default:
                throw new InvalidArgumentException("browser : " + browserType + " is invalid.");
        }
    }

    public WebDriver instantiateMobileEmulatorDriver()  {

         String driverToBeLoaded = thisRun.getAsString(KEYS.OS_NAME.toString()).contains("Windows") ? "chromedriver_win.exe": "chromedriver_mac";
         System.setProperty("webdriver.chrome.driver", thisRun.getAsString(KEYS.TEST_RESOURCES.toString())+"/"+driverToBeLoaded);

         driver = new ChromeDriver(setChromeOptions());
         driver.get(thisRun.getAsString(KEYS.APP_URL.toString()));
         return driver;
     }

     private List<String> addChromeArguments() {
         List<String> chromeArguments = new ArrayList<>();
         chromeArguments.add("--test-type");
         chromeArguments.add("--browser-test");
         chromeArguments.add("--disable-popup-blocking");
         chromeArguments.add("--disable-extensions");
         chromeArguments.add("--disable-infobars");
         chromeArguments.add("--disable-notifications");
         chromeArguments.add("--no-default-browser-check");
         chromeArguments.add("--allow-file-access");
         chromeArguments.add("--allow-file-access-from-files");
         chromeArguments.add("--allow-nacl-file-handle-api[2]");
         chromeArguments.add("--use-file-for-fake-audio-capture");
         chromeArguments.add("--allow-external-pages");
         chromeArguments.add("--enable-local-file-accesses");
         chromeArguments.add("--allow-external-pages");
         chromeArguments.add("--ash-enable-touch-view-testing");
         chromeArguments.add("--enable-touch-drag-drop");
         chromeArguments.add("--enable-touchview[7]");
         chromeArguments.add("--disable-extensions-file-access-check");
         return  chromeArguments;
     }

     private ChromeOptions setChromeOptions() {
         Map<String, String> mobileEmulation = new HashMap<>();
         mobileEmulation.put("deviceName", "Pixel 2");
         ChromeOptions options = new ChromeOptions();
         options.setExperimentalOption("mobileEmulation", mobileEmulation);
         options.addArguments(addChromeArguments());
         options.setAcceptInsecureCerts(true);
         options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
         return  options;
     }

    public WebDriver getDriver() {
        logger.info("Instantiating Driver for browser: "+browser);
        switch (browser.toLowerCase()) {
            case "mobileemulator":
                return instantiateMobileEmulatorDriver();
            case "chrome":
                return instantiateChromeDriver();
            case "firefox":
                return instantiateFireFoxDriver();
             default:
                 throw new InvalidArgumentException("Invalid browser type");
        }
    }



    public void quitBrowser() {
        driver.quit();
    }
}
