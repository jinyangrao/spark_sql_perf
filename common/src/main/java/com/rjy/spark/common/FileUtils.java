package com.rjy.spark.common;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileUtils {

    private static final Logger logger = LoggerUtils.getLogger(FileUtils.class);

    public static File openYamlFile(String fileName) {

        String filePath = getFilePathV1(fileName, Strings.CONF_DIR);

        File file = new File(filePath);

        return file;
    }

    private static String getFilePathV1(String fileName, String dir) {

        String path = System.getProperty(Strings.USE_DIR);

        String filePath = path + Utils.getSysPathFileter() + dir + Utils.getSysPathFileter() + fileName;

        logger.info("get file in path: " + filePath);

        return filePath;
    }

    public static String getFolderPath(String dir) {

        String path = getFilePathV1("", dir);

        return path;
    }

}
