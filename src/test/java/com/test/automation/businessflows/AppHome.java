package com.test.automation.businessflows;

import com.test.automation.screens.AppHomeScreen;
import com.test.automation.utilities.AxeAccessibility;
import org.junit.Assert;

public class AppHome {

    public void verifyHomeScreenIsOpen() {
        String currentURL = AppHomeScreen.getInstance().getCurrentScreenURL();
        String expectedURL = "https://www.seleniumhq.org/";
        Assert.assertEquals("Expected App Home page is not found", expectedURL, currentURL);
        AxeAccessibility.testAccessibilityWithOptions();
    }
}
