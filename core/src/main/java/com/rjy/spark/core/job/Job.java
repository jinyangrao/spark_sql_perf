package com.rjy.spark.core.job;

import com.google.common.base.Preconditions;
import com.rjy.spark.client.JobInstance;
import com.rjy.spark.client.RunnableJobInstance;
import com.rjy.spark.common.Strings;

import java.util.HashMap;
import java.util.Map;

public class Job implements Executor{

    RunnableJobInstance rawJobs;

    /**
     * for sub class to extend.
     * */
    protected String desc;

    protected boolean isSQLExec;

    protected SQLStatement sqls;

    protected String jdbcUrl;

    protected boolean isSingleThreadMode;

    protected boolean isPreFPesSMode;

    protected boolean isSimpleMetricsMode;

    protected String metricsServer;

    protected boolean isGenResult;

    protected boolean isVerifiedResult;

    protected String verifiedPath;

    protected int currentTime;

    protected Map<String, String> currentUser = new HashMap<>();

    public Job(RunnableJobInstance rawJobs) {
        this.rawJobs = rawJobs;
    }

    public void initConf() {

        Preconditions.checkNotNull(rawJobs);

        JobInstance jobs = (JobInstance) rawJobs.<JobInstance>getJobRef();

        desc = jobs.getDesc();

        isSQLExec = jobs.getAction().equals(Strings.DEFAULT_ACTION_VAR);

//        sqls = jobs.




    }


    @Override
    public void startJob() {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isFailed() {
        return false;
    }

    @Override
    public boolean stopJob(int second) {
        return false;
    }
}
