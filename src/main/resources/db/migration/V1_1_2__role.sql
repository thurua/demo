DROP TABLE IF EXISTS public.role;
CREATE TABLE public.role
(
  id serial,
  created_by integer,
  created_on timestamp with time zone,
  modified_by integer,
  modified_on timestamp with time zone,
  name text,
  remarks text,
  status character(3),
  CONSTRAINT role_pkey PRIMARY KEY (id)
);

INSERT INTO "public"."role" VALUES (1, 1, '2018-02-12 10:03:17.54127-08', 1, '2018-02-12 10:03:17.54127-08', 'Investor', 'Investor', 'ACT');
INSERT INTO "public"."role" VALUES (2, 1, '2018-02-12 10:03:17.54127-08', 1, '2018-02-12 10:03:17.54127-08', 'CapBridgeAdmin', 'Capbridge Admin', 'ACT');
INSERT INTO "public"."role" VALUES (3, 8, '2018-02-15 06:02:35.265-08', 2, '2018-02-26 03:04:54.473-08', 't1', 't1', 'INA');
INSERT INTO "public"."role" VALUES (6, 1, '2018-02-22 00:34:44.994-08', 2, '2018-02-26 03:05:10.375-08', 't2', 't2', 'INA');
INSERT INTO "public"."role" VALUES (11, 2, '2018-02-25 20:06:14.415-08', 2, '2018-02-26 17:53:27.306-08', 'a1', 'a2', 'INA');
INSERT INTO "public"."role" VALUES (12, 2, '2018-02-25 20:07:51.893-08', 2, '2018-02-26 17:54:03.358-08', 'y', 'y', 'INA');
INSERT INTO "public"."role" VALUES (4, 1, '2018-02-21 23:15:44.61-08', 2, '2018-03-02 05:16:54.846-08', 'test ', '12124124', 'INA');
INSERT INTO "public"."role" VALUES (13, 2, '2018-02-25 21:17:11.917-08', 2, '2018-03-02 05:17:00.648-08', 'Toàn', 'Toàn', 'INA');
INSERT INTO "public"."role" VALUES (8, 2, '2018-02-25 19:44:01.411-08', 2, '2018-03-02 05:17:07.887-08', 'Admin (Toan) 2', 'Test 2', 'INA');
INSERT INTO "public"."role" VALUES (9, 2, '2018-02-25 20:00:17.269-08', 2, '2018-03-02 05:17:13.153-08', 'Admin (Toan) 3', 'Test 3', 'INA');
INSERT INTO "public"."role" VALUES (14, 2, '2018-02-26 18:24:10.916-08', 2, '2018-03-02 05:17:18.22-08', 'ok', 'ok', 'INA');
INSERT INTO "public"."role" VALUES (10, 2, '2018-02-25 20:05:26.386-08', 2, '2018-03-02 05:17:24.653-08', 'Admin (Toan) 4', 'Test 4', 'INA');
INSERT INTO "public"."role" VALUES (7, 2, '2018-02-25 19:36:27.515-08', 2, '2018-03-02 05:17:29.642-08', 'Admin (Toan) 1', 'Test 1', 'INA');
INSERT INTO "public"."role" VALUES (5, 1, '2018-02-21 23:17:37.683-08', 2, '2018-03-08 00:46:51.549-08', 'Company Admin', 'Company Admin', 'ACT');
INSERT INTO "public"."role" VALUES (17, 8, '2018-03-05 19:04:16.166-08', 2, '2018-03-08 00:47:04.788-08', 'ẻt', 'êt', 'INA');
INSERT INTO "public"."role" VALUES (16, 2, '2018-03-05 01:51:16.546-08', 2, '2018-03-08 00:47:11.301-08', 'sdfa', 'sdf', 'INA');
INSERT INTO "public"."role" VALUES (15, 2, '2018-03-04 20:28:18.65-08', 2, '2018-03-08 00:47:18.305-08', 'abc', 'abc', 'INA');

DROP TABLE IF EXISTS public.userrole;
CREATE TABLE public.userrole
(
  id serial,
  role_id integer NOT NULL,
  user_id integer NOT NULL,
  CONSTRAINT userrole_pkey PRIMARY KEY (id),
  CONSTRAINT fkf9a7cojfuvf40x6co16kxa1jb FOREIGN KEY (role_id)
      REFERENCES public.role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkgphvxy57wlxf4mppbn57woq1h FOREIGN KEY (user_id)
      REFERENCES public.user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO "public"."userrole" VALUES (1, 2, 1);
INSERT INTO "public"."userrole" VALUES (3, 1, 3);
INSERT INTO "public"."userrole" VALUES (2, 2, 2);
INSERT INTO "public"."userrole" VALUES (4, 1, 4);
INSERT INTO "public"."userrole" VALUES (6, 1, 6);
INSERT INTO "public"."userrole" VALUES (7, 1, 7);
INSERT INTO "public"."userrole" VALUES (9, 1, 9);
INSERT INTO "public"."userrole" VALUES (10, 1, 11);
INSERT INTO "public"."userrole" VALUES (12, 1, 13);
INSERT INTO "public"."userrole" VALUES (13, 1, 14);
INSERT INTO "public"."userrole" VALUES (14, 1, 15);
INSERT INTO "public"."userrole" VALUES (15, 1, 16);
INSERT INTO "public"."userrole" VALUES (11, 4, 12);
INSERT INTO "public"."userrole" VALUES (16, 1, 49);
INSERT INTO "public"."userrole" VALUES (17, 1, 50);
INSERT INTO "public"."userrole" VALUES (20, 1, 53);
INSERT INTO "public"."userrole" VALUES (21, 1, 54);
INSERT INTO "public"."userrole" VALUES (22, 1, 55);
INSERT INTO "public"."userrole" VALUES (18, 15, 51);
INSERT INTO "public"."userrole" VALUES (19, 15, 52);
INSERT INTO "public"."userrole" VALUES (8, 2, 8);
INSERT INTO "public"."userrole" VALUES (23, 1, 56);
INSERT INTO "public"."userrole" VALUES (24, 1, 57);
INSERT INTO "public"."userrole" VALUES (25, 1, 58);
INSERT INTO "public"."userrole" VALUES (26, 1, 59);
INSERT INTO "public"."userrole" VALUES (27, 1, 60);
INSERT INTO "public"."userrole" VALUES (28, 1, 61);
INSERT INTO "public"."userrole" VALUES (29, 1, 62);
INSERT INTO "public"."userrole" VALUES (30, 1, 63);
INSERT INTO "public"."userrole" VALUES (31, 1, 64);
INSERT INTO "public"."userrole" VALUES (32, 1, 65);
INSERT INTO "public"."userrole" VALUES (33, 1, 66);
INSERT INTO "public"."userrole" VALUES (34, 1, 67);
INSERT INTO "public"."userrole" VALUES (35, 1, 68);
INSERT INTO "public"."userrole" VALUES (36, 1, 69);
INSERT INTO "public"."userrole" VALUES (37, 1, 70);
INSERT INTO "public"."userrole" VALUES (38, 1, 71);
INSERT INTO "public"."userrole" VALUES (39, 1, 72);
INSERT INTO "public"."userrole" VALUES (40, 1, 73);
INSERT INTO "public"."userrole" VALUES (41, 1, 74);
INSERT INTO "public"."userrole" VALUES (42, 1, 75);
INSERT INTO "public"."userrole" VALUES (43, 1, 76);
INSERT INTO "public"."userrole" VALUES (44, 1, 77);
INSERT INTO "public"."userrole" VALUES (45, 1, 78);
INSERT INTO "public"."userrole" VALUES (46, 1, 79);
INSERT INTO "public"."userrole" VALUES (47, 1, 80);
INSERT INTO "public"."userrole" VALUES (48, 1, 81);
INSERT INTO "public"."userrole" VALUES (49, 1, 82);
INSERT INTO "public"."userrole" VALUES (50, 1, 83);
INSERT INTO "public"."userrole" VALUES (51, 1, 84);
INSERT INTO "public"."userrole" VALUES (52, 1, 85);
INSERT INTO "public"."userrole" VALUES (53, 1, 86);
INSERT INTO "public"."userrole" VALUES (54, 1, 87);
INSERT INTO "public"."userrole" VALUES (55, 1, 88);
INSERT INTO "public"."userrole" VALUES (56, 1, 89);
INSERT INTO "public"."userrole" VALUES (57, 1, 90);
INSERT INTO "public"."userrole" VALUES (58, 1, 91);
INSERT INTO "public"."userrole" VALUES (59, 1, 92);
INSERT INTO "public"."userrole" VALUES (60, 1, 93);
INSERT INTO "public"."userrole" VALUES (61, 1, 94);
INSERT INTO "public"."userrole" VALUES (62, 1, 95);
INSERT INTO "public"."userrole" VALUES (63, 1, 96);
INSERT INTO "public"."userrole" VALUES (64, 1, 97);
INSERT INTO "public"."userrole" VALUES (5, 5, 5);
INSERT INTO "public"."userrole" VALUES (65, 1, 98);
INSERT INTO "public"."userrole" VALUES (66, 1, 99);
INSERT INTO "public"."userrole" VALUES (67, 1, 100);