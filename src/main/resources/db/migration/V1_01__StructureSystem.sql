ALTER TABLE IF EXISTS PUBLIC."code"
DROP CONSTRAINT "fk_code";

DROP TABLE IF EXISTS PUBLIC."app_setting";
CREATE TABLE PUBLIC."app_setting"
(
	"key"					VARCHAR(255) PRIMARY KEY,
	"data_type"				VARCHAR(20),
	"description"			TEXT,
	"value"					TEXT
);

DROP TABLE IF EXISTS PUBLIC."code_type";
CREATE TABLE PUBLIC."code_type"
(
	"id" 					SERIAL PRIMARY KEY,
	"code"					TEXT,
	"display_as"			TEXT,
	"status" 				CHAR(3)
);

DROP TABLE IF EXISTS PUBLIC."code";
CREATE TABLE PUBLIC."code"
(
	"id" 					SERIAL PRIMARY KEY,
	"parent_id"				INT4,
	"code_type"				TEXT,
	"value"					TEXT,
	"display_as"			TEXT,
	"status" 				CHAR(3),
	"sequence"				INT4,
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE
);

DROP TABLE IF EXISTS PUBLIC."password_change_history";
CREATE TABLE PUBLIC."password_change_history"
(
	"id" 					SERIAL PRIMARY KEY,
	"user_sfid"				TEXT,
	"user_name"				TEXT,
	"change_by"				VARCHAR(50),
	"changed_on"			TIMESTAMP
);

ALTER TABLE PUBLIC."code" 
ADD CONSTRAINT "fk_code" FOREIGN KEY ("parent_id") REFERENCES PUBLIC."code"("id");