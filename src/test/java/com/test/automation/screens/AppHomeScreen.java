package com.test.automation.screens;


public class AppHomeScreen extends BaseScreen {

    public static AppHomeScreen getInstance() {
        return new AppHomeScreen();
    }

    public String getCurrentScreenURL() {
        return getCurrentURL();

    }
}
