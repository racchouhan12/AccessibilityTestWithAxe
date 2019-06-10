package com.test.automation.stepdefinitions;

import com.test.automation.businessflows.Search;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;

public class SearchSteps implements En {

    public SearchSteps() {
       Given("^I search for \"([^\"]*)\"$", (String text) -> {
           new Search().searchText(text);
        });

    }

}
