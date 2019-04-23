package com.rjy.spark.client.conf;


import com.rjy.spark.common.LoggerUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.net.URL;

public class YamlParser {

    private static final Logger logger = LoggerUtils.getLogger(YamlParser.class);

    Yaml yaml = null;

    public YamlParser() {
        this.yaml = new Yaml();
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        YamlParser.class.getClassLoader();
        logger.error("load class");
//        System.out.println(url);
    }
}
