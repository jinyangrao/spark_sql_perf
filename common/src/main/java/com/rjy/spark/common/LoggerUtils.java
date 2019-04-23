package com.rjy.spark.common;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * utils for get log4j logger
 * */
public class LoggerUtils {

    public static Logger getLogger(Class c) {
        Logger logger = Logger.getLogger(c);

//        setLogPropertyConfV2();
//        forTestSetLoggerProperties();
        return logger;
    }

    // this must be a bug
    private static void setLogPropertyConfV1() {
        String path = LoggerUtils.class.getResource("/").toString();

        System.out.println(path);

        Pattern p = Pattern.compile("common");

        String filePathFilter = Utils.getSysPathFileter();

        String confSubPath = "conf"+ "/" + "log4j.properties";

        String logFilePath = p.split(path)[0] + confSubPath;

        System.out.println(logFilePath);

        PropertyConfigurator.configure(logFilePath);

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

    private static void forTestSetLoggerProperties() {
        String s = "C:\\Users\\57378\\code\\spark_sql_perf\\conf\\log4j.properties";
        PropertyConfigurator.configure(s);
    }


    public static void main(String[] args) throws Exception{
//        String s = LoggerUtils.class.getResource("").toString();

//        Pattern p = Pattern.compile("common");

//        String[] strings = p.split(s);

//        System.out.println(System.getProperty("user.dir"));
//        Logger logger = getLogger(LoggerUtils.class);

        File file = new File("C:\\Users\\57378\\code\\spark_sql_perf\\conf\\log4j.properties");

        FileInputStream fileInputStream = new FileInputStream(file);

//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//        FileReader fileReader = new FileReader(inputStreamReader);
//        System.out.println(inputStreamReader);

        PropertyConfigurator.configure("C:\\Users\\57378\\code\\spark_sql_perf\\conf\\log4j.properties");
        Logger logger = Logger.getLogger(LoggerUtils.class);
//        forTestSetLoggerProperties();

        logger.error("test");
    }

}
