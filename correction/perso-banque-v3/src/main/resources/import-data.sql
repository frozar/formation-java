--insert into banque (code_banque, numero_compte, id) values ('DGFIP', 0, '852dca63969f4563a0074403cf9e0072')
--insert into banque (code_banque, numero_compte, id) values ('DGFIP', 0, CAST('852dca63969f4563a0074403cf9e0072' AS BINARY))


--TRUNCATE TABLE operation;
--TRUNCATE TABLE personne;
--TRUNCATE TABLE compte_bancaire_operations;
--TRUNCATE TABLE compte_bancaire;
--TRUNCATE TABLE carte_bancaire;
--TRUNCATE TABLE banque;


drop table if exists banque CASCADE;
drop table if exists carte_bancaire CASCADE;
drop table if exists compte_bancaire CASCADE;
drop table if exists compte_bancaire_operations CASCADE;
drop table if exists operation CASCADE;
drop table if exists personne CASCADE;

create table banque (id binary not null, code_banque varchar(255), numero_compte integer not null, primary key (id));
create table carte_bancaire (id binary not null, code_pin varchar(255), date_expiration timestamp, num_carte varchar(255), banque_id binary, compte_courant_id binary, titulaire_id binary, primary key (id));
create table compte_bancaire (dtype varchar(31) not null, id binary not null, cle varchar(255), code_banque varchar(255), code_guichet varchar(255), num_compte varchar(255), numero_operation integer not null, solde double, tx_interet double, banque_id binary, titulaire_id binary, primary key (id));
create table compte_bancaire_operations (compte_bancaire_id binary not null, operations_id binary not null);
create table operation (id binary not null, date_operation timestamp, libelle varchar(255), montant double, num_operation integer, primary key (id));
create table personne (id binary not null, nom varchar(255), prenom varchar(255), primary key (id));
alter table banque add constraint UK_3plaldnuw3rekjd003qd1m57p unique (code_banque);
alter table compte_bancaire_operations add constraint UK_owq8j1022tayfl6o7eng4777s unique (operations_id);
alter table carte_bancaire add constraint FK9fqnbax1ksjfwtuua0kq4jo87 foreign key (banque_id) references banque;
alter table carte_bancaire add constraint FKqt51kotyrwi68q72gyssfx72n foreign key (compte_courant_id) references compte_bancaire;
alter table carte_bancaire add constraint FKbvbxghky5h6jcyv3jcn259wyf foreign key (titulaire_id) references personne;
alter table compte_bancaire add constraint FKbwtlktbulne1704o9686j4ss0 foreign key (banque_id) references banque;
alter table compte_bancaire add constraint FK66hy7636ji95xhoa4o97rw1ts foreign key (titulaire_id) references personne;
alter table compte_bancaire_operations add constraint FK4d3mcgqbbgsxt143dnvrl61lb foreign key (operations_id) references operation;
alter table compte_bancaire_operations add constraint FKg75at5e3wkv95m6mgt28dcunp foreign key (compte_bancaire_id) references compte_bancaire;


insert into banque (code_banque, numero_compte, id) values ('DGFIP', 0,  CAST('068468ad27014ff488d03a0ed66109e7' AS BINARY));
insert into personne (nom, prenom, id) values ('Blanchard', 'Paulette', CAST('ed69e54c33a746b5965f2d41e367efbb' AS BINARY));
insert into personne (nom, prenom, id) values ('Guibert', 'Dominique', CAST('cc25291867794e51ab44982004ab29da' AS BINARY));
insert into personne (nom, prenom, id) values ('Guillou', 'Thibault', CAST('b9f66c781e894971b7869b7149a4ce94' AS BINARY));
insert into personne (nom, prenom, id) values ('Labbe', 'André', CAST('d1c2c60973764d138a6d2d4a8e4466bd' AS BINARY));
insert into compte_bancaire (banque_id, cle, code_banque, code_guichet, num_compte, numero_operation, solde, titulaire_id, dtype, id) values (CAST('068468ad27014ff488d03a0ed66109e7' AS BINARY), '99', 'DGFIP', '1234', '0000000000', 1, 0.0, CAST('ed69e54c33a746b5965f2d41e367efbb' AS BINARY), 'CompteCourant', CAST('52361e0154974d85b72519bca3af81c3' AS BINARY));
insert into operation (date_operation, libelle, montant, num_operation, id) values (TO_TIMESTAMP('Nov 18 15:17:49 2021', 'Mon DD HH24:MI:SS YYYY'), 'Dépôt chèque', 100.0, 1, CAST('f2d4ae9c11314473aee03bc98ea02cf2' AS BINARY));
insert into compte_bancaire (banque_id, cle, code_banque, code_guichet, num_compte, numero_operation, solde, titulaire_id, dtype, id) values (CAST('068468ad27014ff488d03a0ed66109e7' AS BINARY), '99', 'DGFIP', '1235', '0000000001', 1, 0.0, CAST('cc25291867794e51ab44982004ab29da' AS BINARY), 'CompteCourant', CAST('0ce1b9263f794edd9d59f113364634d9' AS BINARY));
insert into operation (date_operation, libelle, montant, num_operation, id) values (TO_TIMESTAMP('Nov 18 15:17:49 2021', 'Mon DD HH24:MI:SS YYYY'), 'Dépôt espèces', 200.0, 1, CAST('66a3658735a34af28f715c78201f9033' AS BINARY));
insert into operation (date_operation, libelle, montant, num_operation, id) values (TO_TIMESTAMP('Nov 18 15:17:49 2021', 'Mon DD HH24:MI:SS YYYY'), 'Débit', -50.0, 2, CAST('5207009803d84527ac3b70de22f57fd2' AS BINARY));
insert into operation (date_operation, libelle, montant, num_operation, id) values (TO_TIMESTAMP('Nov 18 15:17:49 2021', 'Mon DD HH24:MI:SS YYYY'), 'Débit', -300.0, 3, CAST('8225aea08e9642969f435e859675e49e' AS BINARY));
insert into compte_bancaire (banque_id, cle, code_banque, code_guichet, num_compte, numero_operation, solde, titulaire_id, tx_interet, dtype, id) values (CAST('068468ad27014ff488d03a0ed66109e7' AS BINARY), '99', 'DGFIP', '5677', '0000000002', 1, 3000.0, CAST('b9f66c781e894971b7869b7149a4ce94' AS BINARY), 0.02, 'CompteEpargne', CAST('b29273f60962479e954ccae6e092cc61' AS BINARY));
insert into operation (date_operation, libelle, montant, num_operation, id) values (TO_TIMESTAMP('Nov 18 15:17:49 2021', 'Mon DD HH24:MI:SS YYYY'), 'Dépôt', 1000.0, 1, CAST('98b697ba9aaf4db894b517dd0d052cc6' AS BINARY));
insert into compte_bancaire (banque_id, cle, code_banque, code_guichet, num_compte, numero_operation, solde, titulaire_id, tx_interet, dtype, id) values (CAST('068468ad27014ff488d03a0ed66109e7' AS BINARY), '99', 'DGFIP', '5678', '0000000003', 1, 10000.0, CAST('d1c2c60973764d138a6d2d4a8e4466bd' AS BINARY), 0.02, 'CompteEpargne', CAST('c8d5aa9d54524feba337f188a781ca17' AS BINARY));
insert into carte_bancaire (banque_id, code_pin, compte_courant_id, date_expiration, num_carte, titulaire_id, id) values (CAST('068468ad27014ff488d03a0ed66109e7' AS BINARY), '5613', CAST('52361e0154974d85b72519bca3af81c3' AS BINARY), TO_TIMESTAMP('Nov 18 15:17:49 2024', 'Mon DD HH24:MI:SS YYYY'), '5532 8887 3018 1326', CAST('ed69e54c33a746b5965f2d41e367efbb' AS BINARY), CAST('db8427c09f604373a8b93fdaa20ff545' AS BINARY));
insert into carte_bancaire (banque_id, code_pin, compte_courant_id, date_expiration, num_carte, titulaire_id, id) values (CAST('068468ad27014ff488d03a0ed66109e7' AS BINARY), '5613', CAST('52361e0154974d85b72519bca3af81c3' AS BINARY), TO_TIMESTAMP('Nov 18 15:17:49 2024', 'Mon DD HH24:MI:SS YYYY'), '5532 8887 3018 1326', CAST('cc25291867794e51ab44982004ab29da' AS BINARY), CAST('dc3b0c91ecd7a6f2c419ca033d37646f' AS BINARY));
