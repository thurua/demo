-- Table: public.appsettings

DROP TABLE IF EXISTS public.appsettings;

CREATE TABLE public.appsettings
(
  key varchar(255),
  data_type character varying(10),
  description text,
  modified_by integer NOT NULL,
  modified_on timestamp with time zone,
  value text,
  CONSTRAINT appsettings_pkey PRIMARY KEY (key)
);