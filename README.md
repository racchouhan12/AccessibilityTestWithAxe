# aXe Selenium (Java) Integration README #

This example demonstrates how to use aXe to run web accessibility tests in Java
projects with the Selenium browser automation tool and Java development tools.

Selenium integration enables testing of full pages and sites.

## Requirements ##

1. Java8 or higher should be installed.
2. Set JAVA_HOME
3. Install maven and set MAVEN_HOME
4. Add JAVA_HOME and MAVEN_HOME in your Path variable.
5. Chrome/FireFox browsers must be installed.

## To use the AXE helper library in your own tests ##

* `inject` will inject the required script into the page under test and any
iframes.  This only needs to be run against a given page once, and `Builder`
will take care of it for you if you use that.
* `report` will pretty-print a list of violations.
* `writeResults` will write the JSON violations list out to a file with the
specified name in the current working directory.

The `Builder` class allows tests to chain configuration and analyze pages. The
constructor takes in a `WebDriver` that has already navigated to the page under
test and a `java.net.URL` pointing to the aXe script; from there, you can set
`options()`, `include()` and `exclude()` selectors, `skipFrames()`, and finally,
`analyze()` the page.

* `options` wires a JSON string to aXe, allowing rules to be toggled on
or off. See the `testAccessibilityWithOptions` unit test for a sample
single-rule execution, and the [axe-core API documentation](https://github.com/dequelabs/axe-core/blob/master/doc/API.md#b-options-parameter)
for full documentation on the options object. The runOnly option with tags
may be of particular interest, allowing aXe to execute all rules with the
specified tag(s).
* `include` adds to the list of included selectors. If you do not call
`include` at all, aXe will run against the entire document.
* `exclude` adds to the list of excluded selectors. Exclusions allow you to
focus scope exactly where you need it, ignoring child elements you don't want
to test.
* `skipFrames` prevents aXe to be recursively injected into all iframes.
* `analyze` executes aXe with any configuration you have previously
defined. If you want to test a single `WebElement`, you may pass it into
`analyze` instead of using `include` and `exclude`.

## Framework structure:

1. Project is a maven project.
2. Our framework code lies mainly in src/test folder.
3. Project has three layer approach or calling:
    feature -> stepdefinitions -> businessflows -> screens

    1.      feature -> It is test/resources. It will contain all feature files (.feature).
    2.      stepdefinitions -> This folder has java files which contains step definitions corresponding in feature file.
    3.      businessflows -> This folder has java files which contains which contains business logic or assertions
    4.      screens -> This folder has java files which perform actual actions on the web browser.

4. In resources there are couple of more files like .exe, .properties.
5. In CommonProperties.properties we are passing browser name it will have more keys as framework expands.
6. We have utilities package which contains utilities for driver, reporting and maintaing session state of objects(ThisRun.java)
7. Call flows are as follows:
    1. run commands:
        a. mvn clean
        b. mvn install or mvn test
    2. Now execution will begin

        Call hierarchy are as follows:

        Runcuckes ->  Hooks:  -> features ->  stepdefinitions -> businessflows ->  screens

        ######  Note: ThisRun.java will be called in Hooks itself and driver intialization and other Keys are setup here

 ##  Rules to be followed:

 1. We should not call screens directly from stepdefinitions call hierarchy should be maintained (stepdefinitions -> businessflows -> screens).
 2. All assertions should be done in businessflows only
 3. Mutiple businessflows can be called from stepdefinitions

  ##  How to run Test:

  1. To run specific scenarios use command : run=@foo mvn test -PRunTest (For MAC)
     For Windows you might need to use
     1. set run=@foo
     2. mvn test -PRunTest
  2. To run all scenarios use command : mvn test -PRunTest

  ##  Documentation related to AXE can be founc in :
       https://www.deque.com/axe/axe-for-web/documentation/api-documentation/#api-name-axegetrules


