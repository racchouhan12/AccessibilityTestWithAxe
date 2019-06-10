package com.test.automation.utilities;

import com.deque.axe.AXE;
import com.test.automation.helpers.KEYS;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URL;

import static org.junit.Assert.assertTrue;

public class AxeAccessibility {

    private static ThisRun thisRun = ThisRun.getInstance();
    private static final URL scriptUrl = AxeAccessibility.class.getResource("/axe.min.js");
    private static final String rule = thisRun.getAsString(KEYS.RULE.toString());
    private static final WebDriver driver = thisRun.driver();

    /**
     * Basic test
     */

       public void testAccessibility() {
        JSONObject responseJSON = new AXE.Builder(thisRun.driver(), scriptUrl).analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        if (violations.length() == 0) {
            assertTrue("No violations found", true);
        } else {
            assertTrue(AXE.report(violations), false);
        }
    }

    /**
     * Test with options
     */

    public static void testAccessibilityWithOptions() {
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
                .options("{\n" +
                        "  runOnly: {\n" +
                        "    type: \"tag\",\n" +
                        "    values: [\""+rule+"\"]\n" +
                        "  }\n" +
                        "}")
                .analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        if (violations.length() == 0) {
            assertTrue("No violations found", true);
        } else {
              assertTrue(AXE.report(violations), false);
        }
    }

    /**
     * Test with skip frames
     */
    public static void testAccessibilityWithSkipFrames() {
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
                .skipFrames()
                .analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        if (violations.length() == 0) {
            assertTrue("No violations found", true);
        } else {
            assertTrue(AXE.report(violations), false);
        }
    }



    /**
     * Test a specific selector or selectors
     */

    public static  void testAccessibilityWithTwoSelector(String... include) {
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
                .include(include[0])
                .include(include[1])
                .analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        if (violations.length() == 0) {
            assertTrue("No violations found", true);
        } else {
            assertTrue(AXE.report(violations), false);
        }
    }

    /**
     * Test includes and excludes
     */

    public static void testAccessibilityWithIncludesAndExcludes(String includeTag, String excludeTag) {
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
                .include(includeTag)
                .exclude(excludeTag)
                .analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        if (violations.length() == 0) {
            assertTrue("No violations found", true);
        } else {
            assertTrue(AXE.report(violations), false);
        }
    }

    /**
     * Test a WebElement
     */

    public void testAccessibilityWithWebElement(String webElement) {

        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
                .analyze(driver.findElement(By.tagName(webElement)));

        JSONArray violations = responseJSON.getJSONArray("violations");

        if (violations.length() == 0) {
            assertTrue("No violations found", true);
        } else {
            assertTrue(AXE.report(violations), false);
        }
    }

    /**
     * Test a page with Shadow DOM violations
     */

    public static void testAccessibilityWithShadowElement() {
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        if (violations.length() == 1) {
			assertTrue(AXE.report(violations), true);
        } else {
            assertTrue("No violations found", false);

        }
    }

}
