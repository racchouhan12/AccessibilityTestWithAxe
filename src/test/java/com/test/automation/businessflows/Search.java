package com.test.automation.businessflows;

import com.test.automation.screens.SearchScreen;
import com.test.automation.utilities.AxeAccessibility;

public class Search {

    public void searchText(String searchText) {
        AxeAccessibility.testAccessibilityWithOptions();
        SearchScreen.getInstance().searchTextOnGoogleHomePage(searchText);
    }

}
