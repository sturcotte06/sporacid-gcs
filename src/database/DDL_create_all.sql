/*
	Copyright: Sporacid Studios 2013
	Author: Patrick Lavallee
	Initial Revision: 31/10/2013 Happy Halloween !!!
	
	Description: Creates the structure of the database.
					- Sequences
					- Tables
					- Constraints (PK,FK)
*/

------------------------
-- CREATING SEQUENCES --
------------------------
CREATE SEQUENCE clubs_id_seq  INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1; 
CREATE SEQUENCE membres_id_seq  INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1; 
CREATE SEQUENCE concentrations_id_seq  INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1; 
CREATE SEQUENCE formations_id_seq  INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1; 
CREATE SEQUENCE adresses_id_seq  INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1;
CREATE SEQUENCE fournisseurs_id_seq  INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1;
CREATE SEQUENCE items_id_seq  INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1;
CREATE SEQUENCE units_id_seq  INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1;
CREATE SEQUENCE commandites_id_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1;
CREATE SEQUENCE suivie_statuts_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1;
CREATE SEQUENCE contacts_urgence_id_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1;
CREATE SEQUENCE liens_parente_id_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1;
CREATE SEQUENCE allergies_id_seq INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1;
-----------------------------------
-- CREATING TABLES & CONSTRAINTS --
-----------------------------------
CREATE TABLE clubs
(id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('clubs_id_seq'::regclass)
,nom VARCHAR(50) NOT NULL
,description VARCHAR(512) NOT NULL
);

CREATE TABLE concentrations
(id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('concentrations_id_seq'::regclass)
,acronyme VARCHAR(3) NOT NULL
,description VARCHAR(128) NOT NULL
);

CREATE TABLE liens_parente
(id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('liens_parente_id_seq'::regclass)
,description VARCHAR(32) NOT NULL
);

CREATE TABLE allergies
(id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('allergies_id_seq'::regclass)
,description VARCHAR(64)
);

CREATE TABLE contact_urgences
(id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('contacts_urgence_id_seq'::regclass)
,lien_parente_id INTEGER NOT NULL REFERENCES liens_parente(id)
,nom VARCHAR(64) NOT NULL
,prenom VARCHAR(64) NOT NULL
,telephone VARCHAR(16)
);

CREATE TABLE membres
(id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('membres_id_seq'::regclass)
,concentration_id INTEGER NOT NULL REFERENCES concentrations(id)
,contact_id INTEGER NOT NULL REFERENCES contact_urgences(id)
,nom VARCHAR(64) NOT NULL
,prenom VARCHAR(64) NOT NULL
,concentration VARCHAR(3) NOT NULL
,courriel VARCHAR(256) NOT NULL
,code_permanent VARCHAR(13) NOT NULL
,telephone VARCHAR(24)
);

CREATE TABLE formations
(id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('formations_id_seq'::regclass)
,titre VARCHAR(64) NOT NULL
,description VARCHAR(256) NOT NULL
);

CREATE TABLE membres_clubs
(membre_id INTEGER NOT NULL REFERENCES membres(id)
,club_id INTEGER NOT NULL REFERENCES clubs(id)
,date_debut DATE NOT NULL
,date_fin DATE
);
ALTER TABLE membres_clubs ADD CONSTRAINT membres_clubs_pkey PRIMARY KEY (membre_id, club_id);

CREATE TABLE membres_formations
(membre_id INTEGER NOT NULL REFERENCES membres(id)
,formation_id INTEGER NOT NULL REFERENCES formations(id)
,date_suivie DATE NOT NULL
,date_expiration DATE 
);
ALTER TABLE membres_formations ADD CONSTRAINT membres_formations_pkey PRIMARY KEY(membre_id, formation_id);

CREATE TABLE membres_allergies
(membre_id INTEGER NOT NULL REFERENCES membres(id)
,allergie_id INTEGER NOT NULL REFERENCES allergies(id)
);
ALTER TABLE membres_allergies ADD CONSTRAINT membres_allergies_pkey PRIMARY KEY(membre_id, allergie_id);

CREATE TABLE adresses
(id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('adresses_id_seq'::regclass)
,no_civique INTEGER NOT NULL
,rue VARCHAR(64) NOT NULL
,appartement VARCHAR(8)
,ville VARCHAR(64) NOT NULL
,code_postal VARCHAR(16) NOT NULL
,suite INTEGER
);

CREATE TABLE fournisseurs
(id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('fournisseurs_id_seq'::regclass)
,adresse_id INTEGER NOT NULL REFERENCES adresses(id)
,nom VARCHAR(256) NOT NULL
,contact VARCHAR(128) NOT NULL
,telephone VARCHAR(24) NOT NULL
,fax INTEGER
,courriel VARCHAR(256)
);

CREATE TABLE units
(id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('units_id_seq'::regclass)
,code VARCHAR(8)
,systeme VARCHAR(12)
);

CREATE TABLE items
(id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('items_id_seq'::regclass)
,unit_id INTEGER NOT NULL REFERENCES units(id)
,description VARCHAR(256) NOT NULL
,code_club VARCHAR(32)
,qty_courante NUMERIC(6,3) NOT NULL
,qty_min NUMERIC(6,3)
,qty_max NUMERIC(6,3)
);

CREATE TABLE fournisseurs_items
(fournisseur_id INTEGER NOT NULL REFERENCES fournisseurs(id)
,item_id INTEGER NOT NULL REFERENCES items(id)
,code_fournisseur VARCHAR(32)
,prix MONEY
);
ALTER TABLE fournisseurs_items ADD CONSTRAINT fournisseurs_items_pkey PRIMARY KEY(fournisseur_id, item_id);

CREATE TABLE commandites
(id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('commandites_id_seq'::regclass)
,fournisseur_id INTEGER NOT NULL REFERENCES fournisseurs(id)
,item_id INTEGER NOT NULL REFERENCES items(id)
,valeur MONEY NOT NULL
,nature VARCHAR(64)
,recu BOOLEAN
);

CREATE TABLE Suivie_Statuts
(id INTEGER PRIMARY KEY NOT NULL DEFAULT NEXTVAL('suivie_statuts_seq'::regclass)
,code VARCHAR(32) NOT NULL
,description VARCHAR(128) NOT NULL
);

CREATE TABLE Suivies
(membre_id INTEGER NOT NULL REFERENCES membres(id)
,commandite_id INTEGER NOT NULL REFERENCES commandites(id)
,statut_id INTEGER NOT NULL REFERENCES Suivie_Statuts(id)
,date_suivie DATE NOT NULL
,commentaire VARCHAR(256) NOT NULL
);
ALTER TABLE suivies ADD CONSTRAINT suivies_pkey PRIMARY KEY(membre_id, commandite_id);