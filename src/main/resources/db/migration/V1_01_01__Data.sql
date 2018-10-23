INSERT INTO PUBLIC."code_type"("code", "display_as", "status")
	VALUES ('Salutation', 'Salutation', 'ACT');

INSERT INTO PUBLIC."code"("parent_id", "code_type", "value", "display_as", "sequence", "status")
	(SELECT "id", "code", 'Mr.', 'Mr.', 1, 'ACT' FROM PUBLIC."code_type" WHERE "code" = 'Salutation');
INSERT INTO PUBLIC."code"("parent_id", "code_type", "value", "display_as", "sequence", "status")
	(SELECT "id", "code", 'Mrs.', 'Mrs.', 2, 'ACT' FROM PUBLIC."code_type" WHERE "code" = 'Salutation');
INSERT INTO PUBLIC."code"("parent_id", "code_type", "value", "display_as", "sequence", "status")
	(SELECT "id", "code", 'Ms.', 'Ms.', 3, 'ACT' FROM PUBLIC."code_type" WHERE "code" = 'Salutation');
INSERT INTO PUBLIC."code"("parent_id", "code_type", "value", "display_as", "sequence", "status")
	(SELECT "id", "code", 'Mdm.', 'Mdm.', 4, 'ACT' FROM PUBLIC."code_type" WHERE "code" = 'Salutation');
INSERT INTO PUBLIC."code"("parent_id", "code_type", "value", "display_as", "sequence", "status")
	(SELECT "id", "code", 'Dr.', 'Dr.', 5, 'ACT' FROM PUBLIC."code_type" WHERE "code" = 'Salutation');
INSERT INTO PUBLIC."code"("parent_id", "code_type", "value", "display_as", "sequence", "status")
	(SELECT "id", "code", 'Prof.', 'Prof.', 6, 'ACT' FROM PUBLIC."code_type" WHERE "code" = 'Salutation');