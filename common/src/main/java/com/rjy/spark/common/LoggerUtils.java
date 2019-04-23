package com.rjy.spark.common;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.regex.Pattern;

/**
 * utils for get log4j logger
 * */
public class LoggerUtils {

    public static Logger getLogger(Class c) {
        Logger logger = Logger.getLogger(c);

        setBasicConfLog();

        return logger;
    }

    private static void setBasicConfLog() {
        BasicConfigurator.configure();
    }


    // this must be a bug
    private static void setLogPropertyConfV2() {

        String path = System.getProperty("user.dir");

        String sysPathFilter= Utils.getSysPathFileter();

        String confSubPath =sysPathFilter + "conf" + sysPathFilter + "log4j.properties";

        String log4jPath = path + confSubPath;

        System.out.println(log4jPath);

        PropertyConfigurator.configure(log4jPath);

    }


    public static void main(String[] args) throws Exception{
        Logger logger = LoggerUtils.getLogger(LoggerUtils.class);
        logger.info("test!!");
    }

}
