package com.rjy.spark.client;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public abstract class Job {

    /**
     * every job must have a runner conf
     * */
    protected Properties properties;

    protected String jobName;

    public void setJobProperties(Properties properties) {
        this.properties = properties;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public String toString() {
        String s = jobName + " configure below: \n" +
                properties.toString();

        return s;
    }

    /**
     * for child to implement
     * */
//    public abstract Map<String, List<String>>

    public abstract void loadOrDefaultConf();

    public abstract void runJob();

}
