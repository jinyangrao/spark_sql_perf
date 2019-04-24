package com.rjy.spark.client;


import com.google.common.base.Preconditions;
import com.rjy.spark.client.conf.YamlParser;
import com.rjy.spark.common.EnumUtils;
import com.rjy.spark.common.LoggerUtils;
import com.rjy.spark.common.Strings;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * you can define a another job by extend abstruct class Job
 */
public class JobInstance extends Job {

    private static final Logger logger = LoggerUtils.getLogger(JobInstance.class);

    private String desc;

    private String action;

    private String query;

    private String jdbcUrl;

    private String threadMode;

    private String beforeAllStm;

    private String afterAllStm;

    private boolean perFilePerSession;

    private String metricsMode;

    private String metricsrestserver;

    private boolean generateresult;

    private boolean verifiedresult;

    private String verifiedPath;

    private int currentTime;

    private String currentClient;

    public String getDesc() {
        return desc;
    }

    public String getAction() {
        return action;
    }

    public String getQuery() {
        return query;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getThreadMode() {
        return threadMode;
    }

    public String getBeforeAllStm() {
        return beforeAllStm;
    }

    public String getAfterAllStm() {
        return afterAllStm;
    }

    public boolean isPerFilePerSession() {
        return perFilePerSession;
    }

    public String getMetricsMode() {
        return metricsMode;
    }

    public String getMetricsrestserver() {
        return metricsrestserver;
    }

    public boolean isGenerateresult() {
        return generateresult;
    }

    public boolean isVerifiedresult() {
        return verifiedresult;
    }

    public String getVerifiedPath() {
        return verifiedPath;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public String getCurrentClient() {
        return currentClient;
    }


    /**
     * coustructor
     */
    public JobInstance(Properties properties, String jobName) {

        setJobName(jobName);

        setJobProperties(properties);
    }

    public JobInstance(Properties properties) {

        setJobName((String) properties.getOrDefault(Strings.ACTION, Strings.DEFAULT_ACTION_VAR));

        setJobProperties(properties);
    }

    /**
     * query must be assigned, others args can use default value:
     * <p>
     * such as an sample runnable job configured in yaml:
     * <p>
     * ---
     * actions:
     * -query: tpch
     */
    public void isRunnable() {
        Preconditions.checkNotNull(properties.getProperty(Strings.QUERY));
    }

    /**
     * load conf or load default conf to structure a job instance
     */
    @Override
    public void loadOrDefaultConf() {

        this.desc = (String) properties.getOrDefault(Strings.DESC, Strings.DEFAULT_DESC_VAR);

        this.action = (String) properties.getOrDefault(Strings.ACTION, Strings.DEFAULT_ACTION_VAR);

        this.query = (String) properties.getOrDefault(Strings.QUERY, Strings.DEFAULT_QUERY_VAR);

        this.jdbcUrl = (String) properties.getOrDefault(Strings.JDBC_URL, Strings.DEFAULT_JDBC_URL_VAR);

        this.threadMode = (String) properties.getOrDefault(Strings.THREAD_MODE, Strings.DEFAULT_THREAD_MODE_VAR);

        this.beforeAllStm = (String) properties.getOrDefault(Strings.JOB_STM_BEFOREALL, Strings.DEFAULT_JOB_STM_BEFOREALL_VAR);

        this.afterAllStm = (String) properties.getOrDefault(Strings.JOB_STM_AFTERALL, Strings.DEFAULT_JOB_STM_AFTERALL_VAR);

        if (properties.getOrDefault(Strings.JOB_PERFILE_PERSESSION, Strings.FALSE).equals(Strings.FALSE)) {
            this.perFilePerSession = false;
        } else {
            this.perFilePerSession = true;
        }

        this.metricsMode = (String) properties.getOrDefault(Strings.JOB_METRICS_MODE, Strings.DEFAULT_JOB_METRICS_MODE_VAR);

        this.metricsrestserver = (String) properties.getOrDefault(Strings.JOB_METRICS_RESTSERVER_MODE, Strings.DEFAULT_JOB_METRICS_RESTSERVER_MODE_VAR);

        if (properties.getOrDefault(Strings.JOB_GENERATE_RESULT, Strings.FALSE).equals(Strings.FALSE)) {
            this.generateresult = false;
        } else {
            this.generateresult = true;
        }

        if (properties.getOrDefault(Strings.JOB_VERIFIED_RESULT, Strings.FALSE).equals(Strings.FALSE)) {
            this.verifiedresult = false;
        } else {
            this.verifiedresult = true;
        }

        this.verifiedPath = (String) properties.getOrDefault(Strings.JOB_VERIFIED_PATH, Strings.DEFAULT_JOB_VERIFIED_PATH_VAR);

        this.currentTime = Integer.parseInt((String) properties.getOrDefault(Strings.JDBC_CURRENT_TIME, Strings.DEFAULT_JDBC_CURRENT_TIME_VAR));

        this.currentClient = (String) properties.getOrDefault(Strings.JDBC_CURRENT_CLIENT, Strings.DEFAULT_JDBC_CURRENT_CLIENT_VAR);

        logger.info("job load conf successfully");
    }


    @Override
    public void runJob() {

    }



    public static void main(String[] args) {

        YamlParser yamlParser = new YamlParser("jdbc_work_flow.yml");

        List<Properties> jobConf = yamlParser.getProperties();

        System.out.println(jobConf.size());

        for (Properties conf : jobConf) {
            Job job = new JobInstance(conf);

            job.loadOrDefaultConf();
        }
    }
}
