package com.test.automation.businessflows;

import com.test.automation.screens.ResultScreen;
import com.test.automation.utilities.AxeAccessibility;

public class Result {

     public void clickOnResultsLinkByText(String resultTextToBeClicked) {
            AxeAccessibility.testAccessibilityWithOptions();
            ResultScreen.getInstance().clickOnResultByLinkText(resultTextToBeClicked);
     }
}
