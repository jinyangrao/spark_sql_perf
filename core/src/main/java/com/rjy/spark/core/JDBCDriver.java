package com.rjy.spark.core;

import com.rjy.spark.common.Strings;



/**
 * this class represent a jdbc driver
 * */
public class JDBCDriver {

    static String driverClassName = Strings.SPARK_JDBC_DRIVE;


    /**
     * load Driver class
     * */
    static void loadDriver() {
        try {

            Class.forName(driverClassName);

        }catch (ClassNotFoundException e) {

            e.printStackTrace();

        }
    }
}
