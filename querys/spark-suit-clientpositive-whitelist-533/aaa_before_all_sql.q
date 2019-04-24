-- Set environment for all the sql.
-- Create tables that some queries need to use: such as src, src1, srcpart

-- Set Spark cross join enable
SET spark.sql.crossJoin.enabled=true;
-- SET spark.sql.session.timeZone='America/Los_Angeles';
-- SET spark.sql.inMemoryColumnarStorage.compressed=5;
-- SET spark.sql.inMemoryColumnarStorage.partitionPruning=true;

-- Create database for suit
CREATE DATABASE spark_suit_db IF NOT EXIST;

-- create tables
CREATE TABLE src (key INT, value STRING)
LOAD DATA INPATH 'kv1.txt' INTO TABLE src;

CREATE TABLE src1 (key INT, value STRING)
LOAD DATA INPATH 'kv3.txt' INTO TABLE src1;

CREATE TABLE srcpart (key INT, value STRING) PARTITIONED BY (ds STRING, hr STRING);

LOAD DATA INPATH 'kv1.txt'
OVERWRITE INTO TABLE srcpart PARTITION (ds='2008-04-08',hr='11');

LOAD DATA INPATH 'kv1.txt'
OVERWRITE INTO TABLE srcpart PARTITION (ds='2008-04-08',hr='12');

LOAD DATA INPATH 'kv1.txt'
OVERWRITE INTO TABLE srcpart PARTITION (ds='2008-04-09',hr='11');

LOAD DATA INPATH 'kv1.txt'
OVERWRITE INTO TABLE srcpart PARTITION (ds='2008-04-09',hr='12');