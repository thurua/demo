INSERT INTO public.role (name, remarks, status) VALUES 
('ROOT', 'ROOT', 'ACT'),
('SystemAdmin', 'System Admin', 'ACT'),
('CompanyAdmin', 'Company Admin', 'ACT'),
('DBAdmin', 'DB Admin', 'ACT'),
('User', 'User', 'ACT');

INSERT INTO public.function(code, display_as, parent_id, sequence) VALUES 
('Dashboard', 'Dashboard', NULL, 0),
('ManageUser', 'Manage User', NULL, 1);

INSERT INTO public.function(code, display_as, parent_id, sequence) VALUES 
('ManageUser_Search', 'Search', (select id from public.function where code='ManageUser'), 2),
('ManageUser_Create', 'Create', (select id from public.function where code='ManageUser'), 3),
('ManageUser_Edit', 'Edit', (select id from public.function where code='ManageUser'), 4);


INSERT INTO public.function(code, display_as, parent_id, sequence) VALUES
('ManageRole', 'Manage Role', NULL, 5);

INSERT INTO public.function(code, display_as, parent_id, sequence) VALUES
('ManageRole_Search', 'Search', (select id from public.function where code='ManageRole'), 6),
('ManageRole_Create', 'Create', (select id from public.function where code='ManageRole'), 7),
('ManageRole_Edit', 'Edit', (select id from public.function where code='ManageRole'), 8);


INSERT INTO public.rolefunction (function_id, role_id) VALUES 
((select id from public.function where code='Dashboard'), (select id from public.role where name='ROOT')),
((select id from public.function where code='ManageUser'), (select id from public.role where name='ROOT')),
((select id from public.function where code='ManageUser_Search'), (select id from public.role where name='ROOT')),
((select id from public.function where code='ManageUser_Create'), (select id from public.role where name='ROOT')),
((select id from public.function where code='ManageUser_Edit'), (select id from public.role where name='ROOT')),

((select id from public.function where code='ManageRole'), (select id from public.role where name='ROOT')),
((select id from public.function where code='ManageRole_Search'), (select id from public.role where name='ROOT')),
((select id from public.function where code='ManageRole_Create'), (select id from public.role where name='ROOT')),
((select id from public.function where code='ManageRole_Edit'), (select id from public.role where name='ROOT'));