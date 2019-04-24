package com.rjy.spark.client.conf;


import com.google.common.base.Preconditions;
import com.rjy.spark.common.FileUtils;
import com.rjy.spark.common.LoggerUtils;
import com.rjy.spark.common.Strings;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class YamlParser {

    private static final Logger logger = LoggerUtils.getLogger(YamlParser.class);

    Yaml yaml = null;

    String yamlName;

    public YamlParser(String fileName) {

        this.yaml = new Yaml();

        this.yamlName = fileName;
    }


    public Map parse() {

        Map properties = null;

        File yamlFile = FileUtils.openFile(yamlName);

        try {
            FileInputStream fileInputStream = new FileInputStream(yamlFile);

            properties = yaml.loadAs(fileInputStream, Map.class);

            logger.info("find load in path: " + yamlFile.getAbsolutePath());

        } catch (FileNotFoundException e) {
            logger.error("file not found in path: " + yamlFile.getAbsolutePath());
        }


        return properties;
    }

    public List<Properties> getProperties() {
        List<Properties> properties = new ArrayList<Properties>();

        Map argsMap = parse();


        List jobList = (List) argsMap.get(Strings.ACTIONS);

        Preconditions.checkNotNull(jobList);

        jobList.forEach(job -> {

            Map jobMap = (Map) job;

            Properties jobProperties = new Properties();

            jobProperties.setProperty(Strings.DESC, (String) jobMap.getOrDefault(Strings.DESC, Strings.DEFAULT_DESC_VAR));

            jobProperties.setProperty(Strings.ACTION, (String) jobMap.getOrDefault(Strings.ACTION, Strings.DEFAULT_ACTION_VAR));

            jobProperties.setProperty(Strings.QUERY, (String) jobMap.getOrDefault(Strings.QUERY, Strings.DEFAULT_QUERY_VAR));

            jobProperties.setProperty(Strings.JDBC_URL, (String) jobMap.getOrDefault(Strings.JDBC_URL, Strings.DEFAULT_JDBC_URL_VAR));

            jobProperties.setProperty(Strings.THREAD_MODE, (String) jobMap.getOrDefault(Strings.THREAD_MODE, Strings.DEFAULT_THREAD_MODE_VAR));


            Optional<Map> jobConf = Optional.of((Map) jobMap.get(Strings.JOB_CONF));

            if (jobConf.isPresent()) {

                jobConf.get().forEach((key, values) -> {

                    if (values instanceof Integer)
                        jobProperties.setProperty((String) key, Integer.toString((Integer) values));
                    else
                        jobProperties.setProperty((String) key, (String) values);
                });
            } else {
                logger.warn("job conf can't be found in yaml, using default conf");
            }

            properties.add(jobProperties);
        });

        return properties;
    }

    public static void main(String[] args) {

        YamlParser yamlParser = new YamlParser("jdbc_work_flow.yml");
        List<Properties> jobConf = yamlParser.getProperties();

    }
}
