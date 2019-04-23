package com.rjy.spark.common;

public class EnumUtils {

    // enmu store here
    public static enum Action {
        SQL, BATCH;
    }

    public static enum ThreadMode {
        SINGLE, CURRENT;
    }

    public static enum MetricsMode {
        SIMPLE, RICH;
    }
}
