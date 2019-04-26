package com.rjy.spark.core.job;


/**
 * job interface to define some comment api
 * and we define every yaml job must be a or more thread job.
 * single job:only 1 thread.
 * current job: client num thread
 * */
public interface ExecuteJob extends Runnable{

    /**
     * for different job run in different sql mode:
     * sql: 1 query execute by 1 query.
     * batch: 1 file execute by 1 file.
     * */
    public void perpareQueryScript();

    /**
     * execute statement before all query:
     * */
    public void executeStmBeforeAll();

    /**
     * execute statement after all query:
     * */
    public void executeStmAfterAll();

}
