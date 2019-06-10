Feature: Search in Google

   @foo
  Scenario: Search for SeleniumHQ and verify the website
     Given I search for "seleniumhq"
     When I click on official Selenium HQ website
     Then I verify official website is launched