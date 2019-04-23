package com.rjy.spark.common;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.regex.Pattern;

/**
 * utils for get log4j logger
 * */
public class LoggerUtils {

    public static Logger getLogger(Class c) {
        Logger logger = Logger.getLogger(c);

        setLogPropertyConfV2();

        return logger;
    }

    // this must be a bug
    private static void setLogPropertyConfV1() {
        String s= LoggerUtils.class.getResource(Strings.EMPTY_STRING).toString();

        Pattern p = Pattern.compile("common");

        String confSubPath = "conf/log4j.properties";

        PropertyConfigurator.configure(p.split(s)[0] + confSubPath);

    }

    // this must be a bug
    private static void setLogPropertyConfV2() {

        String path = System.getProperty("user.dir");

        String confSubPath = "/conf/log4j.properties";

        PropertyConfigurator.configure(path + confSubPath);

    }


    public static void main(String[] args) {
//        String s = LoggerUtils.class.getResource("").toString();

//        Pattern p = Pattern.compile("common");

//        String[] strings = p.split(s);

//        System.out.println(System.getProperty("user.dir"));

    }

}
