CREATE OR REPLACE TEMPORARY VIEW t1 AS SELECT * FROM VALUES (1) AS GROUPING(a);
CREATE OR REPLACE TEMPORARY VIEW t2 AS SELECT * FROM VALUES (1) AS GROUPING(a);
CREATE OR REPLACE TEMPORARY VIEW t3 AS SELECT * FROM VALUES (1), (1) AS GROUPING(a);
CREATE OR REPLACE TEMPORARY VIEW t4 AS SELECT * FROM VALUES (1), (1) AS GROUPING(a);

CREATE OR REPLACE TEMPORARY VIEW ta AS
SELECT a, 'a' AS tag FROM t1
UNION ALL
SELECT a, 'b' AS tag FROM t2;

CREATE OR REPLACE TEMPORARY VIEW tb AS
SELECT a, 'a' AS tag FROM t3
UNION ALL
SELECT a, 'b' AS tag FROM t4;

-- SPARK-19766 Constant alias columns in INNER JOIN should not be folded by FoldablePropagation rule
SELECT tb.* FROM ta INNER JOIN tb ON ta.a = tb.a AND ta.tag = tb.tag;

-- Clean up
DROP VIEW IF EXISTS t1;
DROP VIEW IF EXISTS t2;
DROP VIEW IF EXISTS t3;
DROP VIEW IF EXISTS t4;
DROP VIEW IF EXISTS ta;
DROP VIEW IF EXISTS tb;