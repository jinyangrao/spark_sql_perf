CREATE OR REPLACE TEMPORARY VIEW t1 AS SELECT * FROM VALUES (1) AS GROUPING(a);
CREATE OR REPLACE TEMPORARY VIEW t2 AS SELECT * FROM VALUES (1) AS GROUPING(a);

CREATE OR REPLACE TEMPORARY VIEW empty_table as SELECT a FROM t2 WHERE false;

SELECT * FROM t1 INNER JOIN empty_table;
SELECT * FROM t1 CROSS JOIN empty_table;
SELECT * FROM t1 LEFT OUTER JOIN empty_table;
SELECT * FROM t1 RIGHT OUTER JOIN empty_table;
SELECT * FROM t1 FULL OUTER JOIN empty_table;
SELECT * FROM t1 LEFT SEMI JOIN empty_table;
SELECT * FROM t1 LEFT ANTI JOIN empty_table;

SELECT * FROM empty_table INNER JOIN t1;
SELECT * FROM empty_table CROSS JOIN t1;
SELECT * FROM empty_table LEFT OUTER JOIN t1;
SELECT * FROM empty_table RIGHT OUTER JOIN t1;
SELECT * FROM empty_table FULL OUTER JOIN t1;
SELECT * FROM empty_table LEFT SEMI JOIN t1;
SELECT * FROM empty_table LEFT ANTI JOIN t1;

SELECT * FROM empty_table INNER JOIN empty_table;
SELECT * FROM empty_table CROSS JOIN empty_table;
SELECT * FROM empty_table LEFT OUTER JOIN empty_table;
SELECT * FROM empty_table RIGHT OUTER JOIN empty_table;
SELECT * FROM empty_table FULL OUTER JOIN empty_table;
SELECT * FROM empty_table LEFT SEMI JOIN empty_table;
SELECT * FROM empty_table LEFT ANTI JOIN empty_table;

-- Clean up
DROP VIEW IF EXISTS t1;
DROP VIEW IF EXISTS t2;
DROP VIEW IF EXISTS empty_table;