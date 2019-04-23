package com.rjy.spark.client.conf;


import com.rjy.spark.common.FileUtils;
import com.rjy.spark.common.LoggerUtils;
import com.rjy.spark.common.Strings;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class YamlParser {

    private static final Logger logger = LoggerUtils.getLogger(YamlParser.class);

    Yaml yaml = null;
    String yamlName;

    public YamlParser(String fileName) {
        this.yaml = new Yaml();
        this.yamlName = fileName;
    }

    public Map<String, String> parse() {

        Map properties = null;

        File yamlFile = FileUtils.openFile(yamlName);

        try {
            FileInputStream fileInputStream = new FileInputStream(yamlFile);

            properties = yaml.loadAs(fileInputStream, Map.class);

            logger.info("find load in path: " + yamlFile.getAbsolutePath());

            System.out.println(properties);
        }catch (FileNotFoundException e) {
            logger.error("file not found in path: " + yamlFile.getAbsolutePath());
        }


        return properties;
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        return null;
    }

    public static void main(String[] args) {

        YamlParser yamlParser = new YamlParser("jdbc_work_flow.yml");
        yamlParser.parse();

    }
}
