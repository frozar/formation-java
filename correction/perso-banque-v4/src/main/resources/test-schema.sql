--https://www.baeldung.com/spring-boot-data-sql-and-schema-sql

drop table if exists banque CASCADE ;
drop table if exists carte_bancaire CASCADE ;
drop table if exists compte_bancaire CASCADE ;
drop table if exists compte_bancaire_operations CASCADE ;
drop table if exists operation CASCADE ;
drop table if exists personne CASCADE ;
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
