package com.rjy.spark.client;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.rjy.spark.client.conf.YamlParser;
import com.rjy.spark.common.FileUtils;
import com.rjy.spark.common.Strings;
import com.rjy.spark.common.Utils;

import java.io.File;
import java.util.*;

public class RunnableJobInstance<T extends Job> {

    private T job;

    // sql file list
    private List<String> sqlFiles = new ArrayList<>();

    private Map<String, String> currentUser = new HashMap<>();

    /**
     * this must concrute a Runable job for core
     * */
    public RunnableJobInstance(T job) {
        this.job = job;
    }

    /**
     * this function is a entrance to use job function
     * */
    public  T getJobRef() {
        return job;
    }

    public void initJob() {

        initUser();

        initSqlFile();

    }


    /**
     * init sql file
     * */
    public void initSqlFile() {

        String querys = job.getQuery();

        String path = FileUtils.getFolderPath(Strings.SOURCE_DIR + Utils.getSysPathFileter() + querys);

        File sqlFiles = new File(path);

        File[] sqls = sqlFiles.listFiles();

        for(File sql: sqls) {
            this.sqlFiles.add(sql.getAbsolutePath());
        }
    }

    /**
     * user class
    * */
    class User {

        String username;
        String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


    /**
     * init user json
     * */
    public void initUser() {

        String userJson = JobInstance.class.cast(job).getCurrentClient();

        JsonParser parser = new JsonParser();

        JsonArray jsonArray = parser.parse(userJson).getAsJsonArray();

        Gson gson = new Gson();

        jsonArray.forEach((userItem) -> {
            User user = gson.fromJson(userItem, User.class);

            currentUser.put(user.getUsername(), user.getPassword());

        });

    }

}
