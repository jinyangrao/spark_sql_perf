package com.rjy.spark.client.conf;

public class EnumUtils {

    // enmu store here
    public static enum ActionMode {
        SQL, BATCH;
    }

    public static enum ThreadMode {
        SINGLE, CURRENT;
    }

    public static enum MetricsMode {
        execution, rich;
    }
}
