-- Cross join detection and error checking is done in JoinSuite since explain output is
-- used in the error message and the ids are not stable. Only positive cases are checked here.

create or replace temporary view nt1 as select * from values
  ("one", 1),
  ("two", 2),
  ("three", 3)
  as nt1(k, v1);

create or replace temporary view nt2 as select * from values
  ("one", 1),
  ("two", 22),
  ("one", 5)
  as nt2(k, v2);

-- Cross joins with and without predicates
SELECT * FROM nt1 cross join nt2;
SELECT * FROM nt1 cross join nt2 where nt1.k = nt2.k;
SELECT * FROM nt1 cross join nt2 on (nt1.k = nt2.k);
SELECT * FROM nt1 cross join nt2 where nt1.v1 = 1 and nt2.v2 = 22;

SELECT a.key, b.key FROM
(SELECT k key FROM nt1 WHERE v1 < 2) a
CROSS JOIN
(SELECT k key FROM nt2 WHERE v2 = 22) b;

-- Join reordering 
create or replace temporary view A(a, va) as select * from nt1;
create or replace temporary view B(b, vb) as select * from nt1;
create or replace temporary view C(c, vc) as select * from nt1;
create or replace temporary view D(d, vd) as select * from nt1;

-- Allowed since cross join with C is explicit
select * from ((A join B on (a = b)) cross join C) join D on (a = d);
-- Cross joins with non-equal predicates
SELECT * FROM nt1 CROSS JOIN nt2 ON (nt1.k > nt2.k);

-- Cleanup and Reset
drop view if exists nt1;
drop view if exists nt2;
drop view if exists A;
drop view if exists B;
drop view if exists C;
drop view if exists D;