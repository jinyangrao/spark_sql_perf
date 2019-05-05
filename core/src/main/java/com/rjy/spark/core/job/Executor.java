package com.rjy.spark.core.job;

/**
 * for extension, use this interface can implement your own executor.
 * */
public interface Executor {

    /**run this job.
     * this is a major function to start the job.
     *
     * */
    public void startJob();

    /**
     * observer job's status
     *
     * return: false or true
     * */
    public boolean isFinished();

    public boolean isRunning();

    public boolean isFailed();

    /**
     * stop this job
     *
     * 0: means stop job immediately
     * others: means stop job int second later.
     *
     * return: true means stop job successful
     * return: false means job already
     * */
    public boolean stopJob(int second);
}
