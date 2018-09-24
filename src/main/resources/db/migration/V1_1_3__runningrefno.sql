-- Table: public.runningrefno

DROP TABLE IF EXISTS public.runningrefno;

CREATE TABLE public.runningrefno
(
  id serial,
  created_on timestamp with time zone NOT NULL,
  last_ref_no character varying(20),
  modified_on timestamp with time zone,
  seq_group character varying(4) NOT NULL,
  seq_no integer NOT NULL,
  year_code character varying(4),
  CONSTRAINT runningrefno_pkey PRIMARY KEY (id)
);