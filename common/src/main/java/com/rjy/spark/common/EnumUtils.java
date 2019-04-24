package com.rjy.spark.common;

public class EnumUtils {

    // enmu store here
    public enum Action {
        SQL, BATCH;
    }

    public enum ThreadMode {
        SINGLE, CURRENT;
    }

    public enum MetricsMode {
        SIMPLE, RICH;
    }

    public enum BooleanBox {
        TRUE, FALSE;
    }
}
