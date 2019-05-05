package com.rjy.spark.client;

import com.google.common.collect.Ordering;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.rjy.spark.client.alphanum.AlphanumComparator;
import com.rjy.spark.client.conf.YamlParser;
import com.rjy.spark.common.FileUtils;
import com.rjy.spark.common.Strings;
import com.rjy.spark.common.Utils;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

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

    public List<String> getSqlFiles() {
        return sqlFiles;
    }

    public Map<String, String> getCurrentUser() {
        return currentUser;
    }

    public void initJob() {

        initUser();

        initSqlFile();

        sortSqlFileName();

    }


    /**
     * init sql file
     * using regex match string：
     * such as:
     * test.hql
     * test.q
     * test.sql
     * test.qqq
     * test.aq
     * test.999q
     * can be matched
     * */
    public void initSqlFile() {

        String querys = job.getQuery();

        String path = FileUtils.getFolderPath(Strings.SOURCE_DIR + Utils.getSysPathFileter() + querys);

        File sqlFiles = new File(path);

        File[] sqls = sqlFiles.listFiles();

        for(File sql: sqls) {

            String sqlPath = sql.getName();

            String sqlFileNameRegex = "^.+\\..*q.*$";

            if(sqlPath.matches(sqlFileNameRegex)) {

                this.sqlFiles.add(sql.getAbsolutePath());

            }

        }
    }

    /**
     * sort sql file function: expect
     * using alphanumComparator
     * */
    public void sortSqlFileName() {

        Collections.sort(sqlFiles, new AlphanumComparator());

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
