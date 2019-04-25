package com.rjy.spark.client;


import com.rjy.spark.client.conf.YamlParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JobProducer {

    private String yamlFile;

    List<RunnableJobInstance> jobs = new ArrayList<>();

    public JobProducer(String yamlFile) {

        this.yamlFile = yamlFile;

    }

    /**
     * parpare the job
     * */
    public JobProducer jobBuilder() {

        YamlParser yamlParser = new YamlParser(yamlFile);

        List<Properties> jobConf = yamlParser.getProperties();

        for (Properties conf : jobConf) {

            JobInstance job = new JobInstance(conf);

            job.loadOrDefaultConf();

            RunnableJobInstance<JobInstance> rJob = new RunnableJobInstance<>(job);

            rJob.initJob();

            jobs.add(rJob);

        }
        return this;

    }

    /**
     * send jobs
     * */
    public List<RunnableJobInstance> produceJob() {
        return jobs;
    }

    public static void main(String[] args) {

        JobProducer jobProducer = new JobProducer("jdbc_work_flow.yml");

        List<RunnableJobInstance> list = jobProducer.jobBuilder().produceJob();

        System.out.println("");


    }

}
