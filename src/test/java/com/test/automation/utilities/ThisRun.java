package com.test.automation.utilities;

import com.test.automation.helpers.KEYS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class ThisRun {

    private static ThisRun ourInstance;
    private HashMap<String, Object> sessionState = new HashMap<>();
    private static Logger logger = LogManager.getLogger(ThisRun.class.getName());

    public static ThisRun getInstance() {

        if (ourInstance == null) {
            logger.debug("ThisRun is null intialize it....");
            return ourInstance = new ThisRun();
        }
        return ourInstance;
    }

    private ThisRun() {
        sessionState.clear();
        add(KEYS.PROJECT_PATH, System.getProperty("user.dir"));
        add(KEYS.TEST_RESOURCES, getAsString("PROJECT_PATH")+"/src/test/resources");
        add(KEYS.REPORT_PATH, getAsString("PROJECT_PATH")+"/reports");
        add(KEYS.FEATURE_FILES_PATH, getAsString("PROJECT_PATH")+"/src/test/resources/feature");
        add(KEYS.OS_NAME, System.getProperty("os.name"));
    }

    public void add(String key, Object value) {
        sessionState.put(key, value);
    }

    public void add(KEYS key, Object value) {
        add(key.name(), value);
    }

    public Object get(String key) {
        return sessionState.get(key);
    }

    public String getAsString(String key) {
        return sessionState.get(key).toString();
    }

    public WebDriver driver() {
        return (WebDriver) sessionState.get("DRIVER");
    }

    public void waitFor(int seconds) {
        try{
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
