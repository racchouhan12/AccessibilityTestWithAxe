package com.test.automation.utilities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class FileUtils {

    private static Logger logger = LogManager.getLogger(FileUtils.class.getName());

    public static void main(String arg[]) {
        FileUtils.renameFiles   ("C:\\Users\\P7110877\\Downloads\\BhagatSinghPACL", "[A-Za-z]*\\s\\W[0-9]*\\W", "Certificate");
    }

    public static boolean createFolder(String nameOfFolder) {
        File file = new File(nameOfFolder);
        if (!file.exists()) {
            if (file.mkdir()) {
                logger.debug("Directory is created: " + nameOfFolder);
                return true;
            } else {
                logger.error("Failed to create directory!");
                return false;
            }
        }
        return false;
    }

    public static void renameFiles(String dir, String oldStringRegexFormat, String newString) {
        try {
            File folder = new File(dir);
            File[] filesList = folder.listFiles();

            for (int i = 0; i < filesList.length; i++) {
                String newName = (filesList[i].toString().replaceAll(oldStringRegexFormat, newString + i));

                filesList[i].renameTo(new File(newName));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
