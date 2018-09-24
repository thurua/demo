-- Table: public.users

-- DROP TABLE public.appsetting;

CREATE TABLE public.appsetting
(
    key character varying(255) NOT NULL,
    data_type character varying(20) NOT NULL,
    description text,
    value text NOT NULL,
    PRIMARY KEY (key)
);

-- DROP TABLE public.users;
CREATE TABLE public.users
(
    id SERIAL,
    first_name TEXT,
    last_name TEXT,
    email TEXT,
    user_name text,
    password_hash character varying,
    company TEXT,
    contact_no TEXT,
    country TEXT,
	forgot_pwd_token text,
	forgot_pwd_expiry_time timestamp with time zone,
    is_delete boolean default false,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

-- DROP TABLE public.role;
CREATE TABLE public.role
(
  id SERIAL,
  name text,
  remarks text,
  status character(3),
  is_delete boolean default false,
  PRIMARY KEY (id)
);

-- DROP TABLE public.userrole;
CREATE TABLE public.userrole
(
  id SERIAL,
  role_id integer NOT NULL,
  user_id integer NOT NULL,
  is_delete boolean default false,
  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES public.role (id),
  FOREIGN KEY (user_id) REFERENCES public.users (id)
);

-- DROP TABLE public.function;
CREATE TABLE public.function
(
  id SERIAL,
  code text NOT NULL,
  display_as text NOT NULL,
  parent_id integer,
  sequence smallint NOT NULL DEFAULT 0,
  is_delete boolean default false,
  PRIMARY KEY (id),
  FOREIGN KEY (parent_id) REFERENCES public.function (id)
);

-- DROP TABLE public.rolefunction;
CREATE TABLE public.rolefunction
(
  id SERIAL,
  function_id integer NOT NULL,
  role_id integer NOT NULL,
  is_delete boolean default false,
  PRIMARY KEY (id),
  FOREIGN KEY (function_id) REFERENCES public.function (id),
  FOREIGN KEY (role_id) REFERENCES public.role (id)
);


-- DROP TABLE public.countrycallingcode;

CREATE TABLE public.countrycallingcode
(
    id serial,
    iso2 character(2),
    iso3 character(3),
    name text,
    nice_name text,
    num_code smallint,
    phone_code integer,
	is_delete boolean default false,
    CONSTRAINT countrycallingcode_pkey PRIMARY KEY (id)
);

-- DROP TABLE public.company;
CREATE TABLE public.company
(
    id serial,
    name text,
    remarks text,
    is_delete boolean default false,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);



CREATE TABLE public.codetype
(
    id serial NOT NULL,
    code text,
    display_as text,
    status character(3),
    PRIMARY KEY (id)
);



-- Table: public.code

CREATE TABLE public.code
(
    id serial NOT NULL,
	parent_id integer NOT NULL,
	code_type text,
	value text,
	display_as text,
	status character(3),
	is_delete boolean NOT NULL DEFAULT false,
	PRIMARY KEY (id),
	FOREIGN KEY (parent_id) REFERENCES public.codetype (id)    
);


CREATE TABLE public.notification
(
    id SERIAL,
    user_id integer NOT NULL,
	content TEXT NOT NULL,
	is_read boolean default false,
	is_delete boolean default false,
	FOREIGN KEY (user_id) REFERENCES public.users (id)
);

CREATE TABLE public.passwordchangehistory
(
  id serial NOT NULL,
  change_mode character varying(50) NOT NULL,
  changed_on timestamp with time zone,
  user_id integer NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES public.users (id)
);

-- Table: public.useraccess

CREATE TABLE public.useraccess
(
  id serial NOT NULL,
  login_on timestamp with time zone,
  logout_on timestamp with time zone,
  login_uuid uuid NOT NULL,
  user_id integer NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES public.users (id)
);

CREATE TABLE public.emailtemplate
(
    id serial NOT NULL,
    name character varying(500) NOT NULL,
    display_as character varying(500),
    description text,
    subject text,
    body text,
    status character(3),
    PRIMARY KEY (id)
);

CREATE TABLE public.monitorsmsresource
(
    id serial NOT NULL,
	resource_key character varying(50) NOT NULL,
	access_on timestamp with time zone NOT NULL,
	tried smallint NOT NULL,
	is_locked boolean NOT NULL DEFAULT false,
    PRIMARY KEY (id)
);


--Table: public.otpauthentication

CREATE TABLE public.otpauthentication
(
	id serial NOT NULL,
	user_id integer,
	module character varying(50) NOT NULL,
	is_otp_verified boolean NOT NULL default false,
	otp character varying(50),
	client_key TEXT,
	opt_expiry_on timestamp with time zone,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES public.users (id)  
);


-------------

INSERT INTO public.company(name, remarks) 
VALUES 
('Phasellus congue','Phasellus congue'),
('Aliquam sed','Aliquam sed'),
('Donec hendrerit','Donec hendrerit'),
('Cras malesuada','Cras malesuada'),
('Suspendisse ligula','Suspendisse ligula');

INSERT INTO public.countrycallingcode(iso2, name, nice_name, iso3, num_code, phone_code)
VALUES
('SG', 'SINGAPORE', 'Singapore', 'SGP', 702, 65),
('AF', 'AFGHANISTAN', 'Afghanistan', 'AFG', 4, 93),
('IN', 'INDIA', 'India', 'IND', 356, 91);


	INSERT INTO public.appsetting(
	key, data_type, description, value)
	VALUES	('DefaultUserRole_Key', 'STRING', 'Default role used for assigning it to new users', 'ROOT');

	INSERT INTO public.appsetting(
	key, data_type, description, value)
	VALUES ('TwoFactorTokenExpiryMins_Key', 'INTEGER', '2FA token Expiry Time in mins','20');

	INSERT INTO public.appsetting(
	key, data_type, description, value)
	VALUES ('Enable2fa_Key', 'STRING', 'Enable/Disable 2fa validation','Y');

	INSERT INTO public.appsetting(
	key, data_type, description, value)
	VALUES ('OTPSmsMsgLogin_Key', 'STRING', 'Sms message for Login','Your One-Time Password for login is *OTP* ');

	INSERT INTO public.appsetting(
	key, data_type, description, value)
	VALUES ('OTPSmsMsgSignup_Key', 'STRING', 'Sms message for SignUp','Your One-Time Password for Signup is *OTP* ');

	INSERT INTO public.appsetting(
	key, data_type, description, value)
	VALUES ('SmsResourceMinInterval_Key', 'STRING','Min valid interval for next sms request', '1');

	INSERT INTO public.appsetting(
	key, data_type, description, value)
	VALUES ('SmsResourceLockInterval_Key', 'STRING','Max Lock interval for locking sms request', '20');

	INSERT INTO public.appsetting(
	key, data_type, description, value)
	VALUES ('SmsResourceMaxTrialCount_Key', 'STRING','Max tries allowed for sms request', '20');

	INSERT INTO public.appsetting(
	key, data_type, description, value)
	VALUES ('SendGridSenderId_Key', 'STRING','Email Notification Sender Id', 'test@crimsonworks.com');
	
	INSERT INTO public.codetype(code, display_as, status)
VALUES ('RoleStatus', 'Role Status', 'ACT');	

INSERT INTO public.code(parent_id, code_type, value, display_as, status, is_delete) VALUES 
((select id from public.codetype where code = 'RoleStatus'), (select code from public.codetype where code = 'RoleStatus'), 'ACT', 'Active', 'ACT', false),
((select id from public.codetype where code = 'RoleStatus'), (select code from public.codetype where code = 'RoleStatus'), 'INA', 'InActive', 'ACT', false);

