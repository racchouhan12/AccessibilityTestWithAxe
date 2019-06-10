package com.test.automation.screens;

import com.test.automation.utilities.ThisRun;
import org.openqa.selenium.By;

public class SearchScreen extends BaseScreen{

    final String searchTextBoxLocator = "q";
    final String submitButtonLocator = "btnK";
    String script = "var scriptArray = new Array();" +
            "document.addEventListener('click', e => window.localStorage.setItem('clicks', scriptArray.push(e.target.nodeName)));";


    public static SearchScreen getInstance() {
         return new SearchScreen();
    }

    public void searchTextOnGoogleHomePage(String searchData) {
        String val = (String)executeJavaScript(script);
        sendText(By.name(searchTextBoxLocator), searchData);
        waitForElementToBeClickableAndClickOnElement(By.name(submitButtonLocator));
    }

    public void clickOnBuyNow() {
        //clickOnElement(By.xpath("//div[@class='product__listing product__list']/li[1]/div[2]/div[4]/div/div/div/div[2]/div/form[2]/button"));
        waitForElementToBeClickableAndClickOnElement(By.xpath(
                "//h2[contains(text(),'Apple iPhone XS (Space Grey, 512 GB, 4 GB RAM)')]/ancestor::li//button[@id='addToCartButton']"));
    }


}
