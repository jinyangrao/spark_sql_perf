package com.rjy.spark.client;

import java.util.List;
import java.util.Map;

public class RunnableJobInstance {

    private Job job;

    // sql file list
    private List<String> sqlFile;

    private List<Map<String, String>> currentUser;

    /**
     * this must concrute a Runable job for core
     * */
    public RunnableJobInstance(Job job) {
        this.job = job;
    }

    public void initJob() {

    }




}
