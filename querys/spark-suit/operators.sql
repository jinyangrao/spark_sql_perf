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

-- unary minus and plus
select -100;
select +230;
select -5.2;
select +6.8e0;
select -key, +key from testdata where key = 2;
select -(key + 1), - key + 1, +(key + 5) from testdata where key = 1;
select -max(key), +max(key) from testdata;
select - (-10);
select + (-key) from testdata where key = 32;
select - (+max(key)) from testdata;
select - - 3;
select - + 20;
select + + 100;
select - - max(key) from testdata;
select + - key from testdata where key = 33;

-- div
select 5 / 2;
select 5 / 0;
select 5 / null;
select null / 5;
select 5 div 2;
select 5 div 0;
select 5 div null;
select null div 5;

-- other arithmetics
select 1 + 2;
select 1 - 2;
select 2 * 5;
select 5 % 3;
select pmod(-7, 3);

-- check operator precedence.
-- We follow Oracle operator precedence in the table below that lists the levels of precedence
-- among SQL operators from high to low:
------------------------------------------------------------------------------------------
-- Operator                                          Operation
------------------------------------------------------------------------------------------
-- +, -                                              identity, negation
-- *, /                                              multiplication, division
-- +, -, ||                                          addition, subtraction, concatenation
-- =, !=, <, >, <=, >=, IS NULL, LIKE, BETWEEN, IN   comparison
-- NOT                                               exponentiation, logical negation
-- AND                                               conjunction
-- OR                                                disjunction
------------------------------------------------------------------------------------------
explain select 'a' || 1 + 2;
explain select 1 - 2 || 'b';
explain select 2 * 4  + 3 || 'b';
explain select 3 + 1 || 'a' || 4 / 2;
explain select 1 == 1 OR 'a' || 'b' ==  'ab';
explain select 'a' || 'c' == 'ac' AND 2 == 3;

-- math functions
select cot(1);
select cot(null);
select cot(0);
select cot(-1);

-- ceil and ceiling
select ceiling(0);
select ceiling(1);
select ceil(1234567890123456);
select ceiling(1234567890123456);
select ceil(0.01);
select ceiling(-0.10);

-- floor
select floor(0);
select floor(1);
select floor(1234567890123456);
select floor(0.01);
select floor(-0.10);

-- comparison operator
select 1 > 0.00001;

-- mod
select mod(7, 2), mod(7, 0), mod(0, 2), mod(7, null), mod(null, 2), mod(null, null);

-- length
select BIT_LENGTH('abc');
select CHAR_LENGTH('abc');
select CHARACTER_LENGTH('abc');
select OCTET_LENGTH('abc');

-- abs
select abs(-3.13), abs('-2.19');

-- positive/negative
select positive('-1.11'), positive(-1.11), negative('-1.11'), negative(-1.11);

-- pmod
select pmod(-7, 2), pmod(0, 2), pmod(7, 0), pmod(7, null), pmod(null, 2), pmod(null, null);
select pmod(cast(3.13 as decimal), cast(0 as decimal)), pmod(cast(2 as smallint), cast(0 as smallint));

-- Cleanup and Reset
drop view if exists testdata;