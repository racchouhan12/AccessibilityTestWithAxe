package com.test.automation.utilities;

import com.deque.axe.AXE;
import com.test.automation.helpers.KEYS;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

import static org.junit.Assert.assertTrue;

public class AxeAccessibility {

    private static ThisRun thisRun = ThisRun.getInstance();
    private static final URL scriptUrl = AxeAccessibility.class.getResource("/axe.min.js");
    private static final String rule = thisRun.getAsString(KEYS.RULE.toString());

       public void testAccessibility() {
        JSONObject responseJSON = new AXE.Builder(thisRun.driver(), scriptUrl).analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        if (violations.length() == 0) {
            assertTrue("No violations found", true);
        } else {
            assertTrue(AXE.report(violations), false);
        }
    }

    public static void testAccessibilityWithOptions() {
        JSONObject responseJSON = new AXE.Builder(thisRun.driver(), scriptUrl)
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

}
