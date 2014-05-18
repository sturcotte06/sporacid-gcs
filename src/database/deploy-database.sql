CREATE TABLE Evenements (id  SERIAL NOT NULL, Clubs_id int4 NOT NULL, nom varchar(50) NOT NULL, description varchar(255) NOT NULL, date_debut date NOT NULL, date_fin date, PRIMARY KEY (id));
CREATE TABLE Contacts_Urgence_Membres (Contacts_Urgence_id int4 NOT NULL UNIQUE, Membres_id int4 NOT NULL UNIQUE, PRIMARY KEY (Contacts_Urgence_id, Membres_id));
CREATE TABLE Membres_Clubs_Roles (Roles_id int4 NOT NULL UNIQUE, Membres_Clubs_id int4 NOT NULL UNIQUE, PRIMARY KEY (Roles_id, Membres_Clubs_id));
CREATE TABLE Audits (id  SERIAL NOT NULL, username varchar(50) NOT NULL, message varchar(255), timestamp timestamp NOT NULL, PRIMARY KEY (id));
CREATE TABLE Membres_Preferences (id  SERIAL NOT NULL, membres_id int4 NOT NULL, "key" varchar(50) NOT NULL, value varchar(150) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Roles (id  SERIAL NOT NULL, nom varchar(50) NOT NULL, description varchar(255) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Allergies (id  SERIAL NOT NULL, description varchar(128) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Membres_Allergies (Membres_id int4 NOT NULL UNIQUE, Allergies_id int4 NOT NULL UNIQUE, PRIMARY KEY (Membres_id, Allergies_id));
CREATE TABLE Liens_Parente (id  SERIAL NOT NULL, description varchar(50) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Contacts_Urgence (id  SERIAL NOT NULL, liens_parente_id int4 NOT NULL, nom varchar(50) NOT NULL, prenom varchar(50) NOT NULL, telephone varchar(16) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Membres_Formations (Membres_id int4 NOT NULL, Formations_id int4 NOT NULL, date_suivie date NOT NULL, date_echeance date, PRIMARY KEY (Membres_id, Formations_id));
CREATE TABLE Suivies (Commandites_id int4 NOT NULL UNIQUE, Membres_id int4 NOT NULL UNIQUE, Suivie_Statuts_id int4 NOT NULL, date_suivie date NOT NULL, commentaire varchar(255) NOT NULL, PRIMARY KEY (Commandites_id, Membres_id));
CREATE TABLE Formations (id  SERIAL NOT NULL, titre varchar(50), description varchar(150), PRIMARY KEY (id));
CREATE TABLE Units (id  SERIAL NOT NULL, unit_code varchar(8) NOT NULL, systeme varchar(12) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Commandites (id  SERIAL NOT NULL, Fournisseurs_id int4 NOT NULL, Items_id int4 NOT NULL, Clubs_id int4 NOT NULL, valeur numeric(6, 2) NOT NULL, nature varchar(64) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Items (id  SERIAL NOT NULL, Units_id int4 NOT NULL, description varchar(255) NOT NULL, code_club varchar(32), qte_courante numeric(6, 3) NOT NULL, qty_min numeric(6, 3), qty_max numeric(6, 3), PRIMARY KEY (id));
CREATE TABLE Fournisseurs_Items (Fournisseurs_id int4 NOT NULL UNIQUE, Items_id int4 NOT NULL UNIQUE, code_fournisseur int4 NOT NULL, PRIMARY KEY (Fournisseurs_id, Items_id));
CREATE TABLE Fournisseurs (id  SERIAL NOT NULL, Adresses_id int4 NOT NULL, nom varchar(255) NOT NULL, contact varchar(64) NOT NULL, telephone varchar(24) NOT NULL, fax varchar(24), courriel varchar(255), PRIMARY KEY (id));
CREATE TABLE Suivie_Statuts (id  SERIAL NOT NULL, code varchar(50) NOT NULL, description varchar(150) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Concentrations (id  SERIAL NOT NULL, acronyme varchar(10) NOT NULL, description varchar(150), PRIMARY KEY (id));
CREATE TABLE Membres (id  SERIAL NOT NULL, Concentrations_id int4 NOT NULL, nom varchar(64) NOT NULL, prenom varchar(64) NOT NULL, courriel varchar(255) NOT NULL, code_permanent varchar(15) NOT NULL, code_universel varchar(10) NOT NULL, actif bool NOT NULL, telephone varchar(16), PRIMARY KEY (id));
CREATE TABLE Membres_Clubs (id  SERIAL NOT NULL, Clubs_id int4 NOT NULL, Membres_id int4 NOT NULL, date_debut date NOT NULL, date_fin date, PRIMARY KEY (id));
CREATE TABLE Adresses (id  SERIAL NOT NULL, no_civique int4 NOT NULL, rue varchar(64) NOT NULL, appartement varchar(10), ville varchar(128) NOT NULL, code_postal varchar(16) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Clubs (id  SERIAL NOT NULL, nom varchar(50) NOT NULL, description varchar(255), PRIMARY KEY (id));
ALTER TABLE Membres_Preferences ADD CONSTRAINT FKMembres_Pr229875 FOREIGN KEY (membres_id) REFERENCES Membres (id);
ALTER TABLE Contacts_Urgence ADD CONSTRAINT FKContacts_U164890 FOREIGN KEY (liens_parente_id) REFERENCES Liens_Parente (id);
ALTER TABLE Evenements ADD CONSTRAINT FKEvenements917475 FOREIGN KEY (Clubs_id) REFERENCES Clubs (id);
ALTER TABLE Membres ADD CONSTRAINT FKMembres495106 FOREIGN KEY (Concentrations_id) REFERENCES Concentrations (id);
ALTER TABLE Suivies ADD CONSTRAINT FKSuivies436259 FOREIGN KEY (Membres_id) REFERENCES Membres (id);
ALTER TABLE Membres_Formations ADD CONSTRAINT FKMembres_Fo495691 FOREIGN KEY (Membres_id) REFERENCES Membres (id);
ALTER TABLE Membres_Allergies ADD CONSTRAINT FKMembres_Al548936 FOREIGN KEY (Membres_id) REFERENCES Membres (id);
ALTER TABLE Membres_Allergies ADD CONSTRAINT FKMembres_Al895678 FOREIGN KEY (Allergies_id) REFERENCES Allergies (id);
ALTER TABLE Membres_Formations ADD CONSTRAINT FKMembres_Fo906962 FOREIGN KEY (Formations_id) REFERENCES Formations (id);
ALTER TABLE Fournisseurs_Items ADD CONSTRAINT FKFournisseu723122 FOREIGN KEY (Fournisseurs_id) REFERENCES Fournisseurs (id);
ALTER TABLE Fournisseurs_Items ADD CONSTRAINT FKFournisseu24556 FOREIGN KEY (Items_id) REFERENCES Items (id);
ALTER TABLE Suivies ADD CONSTRAINT FKSuivies572159 FOREIGN KEY (Commandites_id) REFERENCES Commandites (id);
ALTER TABLE Items ADD CONSTRAINT FKItems252341 FOREIGN KEY (Units_id) REFERENCES Units (id);
ALTER TABLE Fournisseurs ADD CONSTRAINT FKFournisseu604575 FOREIGN KEY (Adresses_id) REFERENCES Adresses (id);
ALTER TABLE Commandites ADD CONSTRAINT FKCommandite968053 FOREIGN KEY (Fournisseurs_id) REFERENCES Fournisseurs (id);
ALTER TABLE Suivies ADD CONSTRAINT FKSuivies746520 FOREIGN KEY (Suivie_Statuts_id) REFERENCES Suivie_Statuts (id);
ALTER TABLE Commandites ADD CONSTRAINT FKCommandite304970 FOREIGN KEY (Items_id) REFERENCES Items (id);
ALTER TABLE Commandites ADD CONSTRAINT FKCommandite426027 FOREIGN KEY (Clubs_id) REFERENCES Clubs (id);
ALTER TABLE Membres_Clubs_Roles ADD CONSTRAINT FKMembres_Cl391451 FOREIGN KEY (Roles_id) REFERENCES Roles (id);
ALTER TABLE Membres_Clubs_Roles ADD CONSTRAINT FKMembres_Cl153481 FOREIGN KEY (Membres_Clubs_id) REFERENCES Membres_Clubs (id);
ALTER TABLE Membres_Clubs ADD CONSTRAINT FKMembres_Cl269114 FOREIGN KEY (Clubs_id) REFERENCES Clubs (id);
ALTER TABLE Membres_Clubs ADD CONSTRAINT FKMembres_Cl936196 FOREIGN KEY (Membres_id) REFERENCES Membres (id);
ALTER TABLE Contacts_Urgence_Membres ADD CONSTRAINT FKContacts_U960303 FOREIGN KEY (Contacts_Urgence_id) REFERENCES Contacts_Urgence (id);
ALTER TABLE Contacts_Urgence_Membres ADD CONSTRAINT FKContacts_U30385 FOREIGN KEY (Membres_id) REFERENCES Membres (id);