insert into ADDRESS(id, city, postal_code, state, country) values (1, 'City1', '123123213', 'State1', 'country1');
insert into ADDRESS(id, city, postal_code, state, country) values (2, 'City2', '123133223', 'State2', 'country2');
insert into ADDRESS(id, city, postal_code, state, country) values (3, 'City3', 'dfsfd3233', 'State3', 'country3');
insert into PRIVILEGES(id, caption, can_view, can_create, can_update, can_remove) values (1, 'Admin', TRUE, TRUE, TRUE, TRUE);
insert into PRIVILEGES(id, caption, can_view, can_create, can_update, can_remove) values (2, 'Employee', TRUE, TRUE, TRUE, FALSE);
insert into PRIVILEGES(id, caption, can_view, can_create, can_update, can_remove) values (3, 'Logged Customer', TRUE, TRUE, FALSE, FALSE);
insert into PRIVILEGES(id, caption, can_view, can_create, can_update, can_remove) values (4, 'Viewer', TRUE, FALSE, FALSE, FALSE);
insert into PRIVILEGES(id, caption, can_view, can_create, can_update, can_remove) values (5, 'Banned', FALSE, FALSE, FALSE, FALSE);
insert into PROFESSION(id, designation) values (1, 'warehouseman');
insert into PROFESSION(id, designation) values (2, 'accountant');
insert into ACCOUNT(id, username, password, email, privileges_id) values (1, 'Tomek123', 'Tomek123!', 'tomek123@gmail.com', 2);
insert into ACCOUNT(id, username, password, email, privileges_id) values (2, 'admin', 'admin!', 'admin@gmail.com', 1);
insert into ACCOUNT(id, username, password, email, privileges_id) values (3, 'account1', 'account1!', 'account1@gmail.com', 3);
insert into ACCOUNT(id, username, password, email, privileges_id) values (4, 'account2', 'account2!', 'account2@gmail.com', 3);
insert into CUSTOMER(id, name, surname, account_id, address_id) values (1, 'Tomek', 'Kokoszka', 3, 1);
insert into CUSTOMER(id, name, surname, account_id, address_id) values (2, 'Adam', 'Ewak', 4, 2);

insert into MATERIAL(id, caption) values (1, "material1");
insert into MATERIAL(id, caption) values (2, "material2");
insert into MATERIAL(id, caption) values (3, "material3");
insert into MATERIAL(id, caption) values (4, "material4");

insert into VENDOR(id, caption) values (1, "vendor1");
insert into VENDOR(id, caption) values (2, "vendor2");

insert into BRAND(id, caption) values (1, "brand1");
insert into BRAND(id, caption) values (2, "brand2");

insert into COLOR(id, oem, caption) values (1, "czerwony", "red");

insert into DIMENSION(id, value, unit) values (1, 1.3 , "dimension");

insert into DIMENSIONS(id, caption, height_id, width_id, length_id) values (1, "dimensions", 1, 1, 1);

insert into PRODUCTSPECIFICATION(id, caption, dimensions_id) values (1, "prodSpec", 1)