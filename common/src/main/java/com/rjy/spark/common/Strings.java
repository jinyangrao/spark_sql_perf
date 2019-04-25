package com.rjy.spark.common;

public class Strings {

    public static String ACTIONS = "actions";
    /**
     * yaml job
     * */
    public static String DESC = "desc";
    public static String ACTION = "action";
    public static String QUERY = "query";
    public static String JDBC_URL = "jdbcurl";
    public static String THREAD_MODE = "threadmode";
    public static String JOB_CONF = "jobconf";

    /**
     * job args
     * */
    public static String JOB_STM_BEFOREALL = "job.statement.beforeall";
    public static String JOB_STM_AFTERALL = "job.statement.afterall";
    public static String JOB_PERFILE_PERSESSION = "job.perfilepersession";
    public static String JOB_METRICS_MODE = "job.metricsmode";
    public static String JOB_METRICS_RESTSERVER_MODE = "job.metricsrestserver";
    public static String JOB_GENERATE_RESULT = "job.generateresult";
    public static String JOB_VERIFIED_RESULT = "job.verifiedresult";
    public static String JOB_VERIFIED_PATH = "job.verifiedpath";
    public static String JDBC_CURRENT_TIME = "jdbc.current.time";
    public static String JDBC_CURRENT_CLIENT = "jdbc.current.client";

    public static String TRUE = "true";
    public static String FALSE = "false";

    public static String EMPTY_STRING = "";

    /**
     * default value
     * */
    public static String DEFAULT_DESC_VAR = "default desc";
    public static String DEFAULT_ACTION_VAR = "sql";
    public static String DEFAULT_QUERY_VAR = null;
    public static String DEFAULT_JDBC_URL_VAR = "jdbc:hive2://localhost:10000";
    public static String DEFAULT_THREAD_MODE_VAR = "single";
    public static String DEFAULT_JOB_CONF_VAR = null;

    public static String DEFAULT_JOB_STM_BEFOREALL_VAR = null;
    public static String DEFAULT_JOB_STM_AFTERALL_VAR = null;
    public static String DEFAULT_JOB_PERFILE_PERSESSION_VAR = "false";
    public static String DEFAULT_JOB_METRICS_MODE_VAR = "simple";
    public static String DEFAULT_JOB_METRICS_RESTSERVER_MODE_VAR = "http://localhost:4040";
    public static String DEFAULT_JOB_GENERATE_RESULT_VAR = "false";
    public static String DEFAULT_JOB_VERIFIED_RESULT_VAR = "false";
    public static String DEFAULT_JOB_VERIFIED_PATH_VAR = "";
    public static String DEFAULT_JDBC_CURRENT_TIME_VAR = "47";
    public static String DEFAULT_JDBC_CURRENT_CLIENT_VAR = "";

    /**
     * others
     * */
    public static String SOURCE_DIR = "querys";
    public static String CONF_DIR = "conf";
    public static String USE_DIR = "user.dir";
    public static String SPARK_JDBC_DRIVE = "org.apache.hive.jdbc.HiveDriver";

}
