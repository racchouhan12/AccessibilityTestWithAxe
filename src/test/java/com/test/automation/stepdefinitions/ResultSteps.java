package com.test.automation.stepdefinitions;


import com.test.automation.businessflows.Result;
import cucumber.api.java8.En;


public class ResultSteps  implements En {

    final String searchLinkToBeclicked = "Selenium - Web Browser Automation";

   public ResultSteps() {

        When("I click on official Selenium HQ website$", () -> {
            new Result().clickOnResultsLinkByText(searchLinkToBeclicked);
        });
    }
}
