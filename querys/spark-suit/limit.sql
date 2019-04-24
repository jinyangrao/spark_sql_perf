create or replace temporary view testdata as select * from values
  (1, "1"), (2, "2"), (3, "3"),(4, "4"),(5, "5"), (6, "6"), (7, "7"),(8, "8"), (9, "9"), (10, "10"),
  (11, "11"), (12, "12"), (13, "13"),(14, "14"),(15, "15"), (16, "16"), (17, "17"),(18, "18"), (19, "19"), (20, "20"),
  (21, "21"), (22, "22"), (23, "23"),(24, "24"),(25, "25"), (26, "26"), (27, "27"),(28, "28"), (29, "29"), (30, "30"),
  (31, "31"), (32, "32"), (33, "33"),(34, "34"),(35, "35"), (36, "36"), (37, "37"),(38, "38"), (39, "39"), (40, "40"),
  (41, "41"), (42, "42"), (43, "43"),(44, "44"),(45, "45"), (46, "46"), (47, "47"),(48, "48"), (49, "49"), (50, "50"),
  (51, "51"), (52, "52"), (53, "53"),(54, "54"),(55, "55"), (56, "56"), (57, "57"),(58, "58"), (59, "59"), (60, "60"),
  (61, "61"), (62, "62"), (63, "63"),(64, "64"),(65, "65"), (66, "66"), (67, "67"),(68, "68"), (69, "69"), (70, "70"),
  (71, "71"), (72, "72"), (73, "73"),(74, "74"),(75, "75"), (76, "76"), (77, "77"),(78, "78"), (79, "79"), (80, "80"),
  (81, "81"), (82, "82"), (83, "83"),(84, "84"),(85, "85"), (86, "86"), (87, "87"),(88, "88"), (89, "89"), (90, "90"),
  (91, "91"), (92, "92"), (93, "93"),(94, "94"),(95, "95"), (96, "96"), (97, "97"),(98, "98"), (99, "99"), (100, "100")
  as testdata(key, value);

create or replace temporary view arraydata as select * from values
  (array(1, 2, 3), array(array(1, 2, 3))),
  (array(2, 3, 4), array(array(2, 3, 4)))
  as arraydata(arraycol, nestedarraycol);

create or replace temporary view mapdata as select * from values
  (map(1,"a1", 2,"b1", 3,"c1", 4,"d1", 5,"e1")),
  (map(1,"a2", 2,"b2", 3,"c2", 4,"d2")),
  (map(1,"a3", 2,"b3", 3,"c3")),
  (map(1,"a4", 2,"b4")),
  (map(1,"a5"))
  as mapdata(mapcol);

-- limit on various data types
SELECT * FROM testdata LIMIT 2;
SELECT * FROM arraydata LIMIT 2;
SELECT * FROM mapdata LIMIT 2;

-- foldable non-literal in limit
SELECT * FROM testdata LIMIT 2 + 1;

SELECT * FROM testdata LIMIT CAST(1 AS int);

-- limit must be non-negative
SELECT * FROM testdata LIMIT -1;
SELECT * FROM testData TABLESAMPLE (-1 ROWS);

-- limit must be foldable
SELECT * FROM testdata LIMIT key > 3;

-- limit must be integer
SELECT * FROM testdata LIMIT true;
SELECT * FROM testdata LIMIT 'a';

-- limit within a subquery
SELECT * FROM (SELECT * FROM range(10) LIMIT 5) WHERE id > 3;

-- limit ALL
SELECT * FROM testdata WHERE key < 3 LIMIT ALL;

-- Cleanup and Reset
drop view if exists testdata;
drop view if exists arraydata;
drop view if exists mapdata;
