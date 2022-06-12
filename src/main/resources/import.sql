insert into type (id, name) values (1,'Муфты подключения переднего моста');
insert into type (id, name) values (2,'Комплект подшипника ступицы колеса');
insert into type (id, name) values (3,'Датчик детонации');

insert into model (id, main_name, sub_name) values (1,'Volkswagen', 'Polo');
insert into model (id, main_name, sub_name) values (2,'Kia', 'Sportage');
insert into model (id, main_name, sub_name) values (3,'NTL', 'Bearing');
insert into model (id, main_name) values (4,'Vaf');
insert into model (id, main_name) values (5,'Zikmar');
insert into model (id, main_name) values (6,'Renault');
insert into model (id, main_name) values (7,'Nissan');
insert into model (id, main_name) values (8,'Audi');
insert into model (id, main_name) values (9,'Skoda');

insert into supplier (id, name) values (1,'Автостандарт');
insert into supplier (id, name) values (2,'Деталь логистик');
insert into supplier (id, name) values (3,'Герц');

insert into part (id, type_id, original_model_id, supplier_id, price) values (1,1,1,1,9650);
insert into part (id, type_id, original_model_id, supplier_id, price) values (2,1,2,2,11000);
insert into part (id, type_id, original_model_id, supplier_id, price) values (3,1,3,3,4400);
insert into part (id, type_id, original_model_id, supplier_id, price) values (4,2,1,1,5200);
insert into part (id, type_id, original_model_id, supplier_id, price) values (5,2,2,2,6000);
insert into part (id, type_id, original_model_id, supplier_id, price) values (6,2,4,3,4000);
insert into part (id, type_id, original_model_id, supplier_id, price) values (7,3,1,1,1800);
insert into part (id, type_id, original_model_id, supplier_id, price) values (8,3,2,2,2300);
insert into part (id, type_id, original_model_id, supplier_id, price) values (9,3,5,3,1600);

insert into part_replace (part_id, replace_model_id) values (3,1);
insert into part_replace (part_id, replace_model_id) values (3,6);
insert into part_replace (part_id, replace_model_id) values (3,2);
insert into part_replace (part_id, replace_model_id) values (3,7);
insert into part_replace (part_id, replace_model_id) values (6,1);
insert into part_replace (part_id, replace_model_id) values (6,8);
insert into part_replace (part_id, replace_model_id) values (6,2);
insert into part_replace (part_id, replace_model_id) values (6,9);
insert into part_replace (part_id, replace_model_id) values (9,1);
insert into part_replace (part_id, replace_model_id) values (9,6);
insert into part_replace (part_id, replace_model_id) values (9,2);
insert into part_replace (part_id, replace_model_id) values (9,7);

insert into role (id, name) values (1, 'ROLE_ADMIN');
insert into role (id, name) values (2, 'ROLE_USER');