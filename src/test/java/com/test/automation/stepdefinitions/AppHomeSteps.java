package com.test.automation.stepdefinitions;

import com.test.automation.businessflows.AppHome;

import cucumber.api.java8.En;

public class AppHomeSteps implements En {

    public AppHomeSteps() {

        Then("I verify official website is launched$", () -> {
            new AppHome().verifyHomeScreenIsOpen();
        });
    }
}
