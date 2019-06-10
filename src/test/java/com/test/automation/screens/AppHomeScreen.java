package com.test.automation.screens;

import org.junit.Assert;

public class AppHomeScreen extends BaseScreen {

    public static AppHomeScreen getInstance() {
        return new AppHomeScreen();
    }

    public String getCurrentScreenURL() {
        return getCurrentURL();

    }
}
