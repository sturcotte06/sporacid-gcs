/*
	Copyright: Sporacid Studios 2013
	Author: Patrick Lavallee
	Initial Revision: 31/10/2013 Happy Halloween !!!
	
	Description: Destroys the structure of the database.
					- Tables & Constraints
					- Sequences
*/

-----------------------------------
-- DROPPING TABLES & CONSTRAINTS --
-----------------------------------
DROP TABLE membres_formations;
DROP TABLE membres_clubs;
DROP TABLE membres_allergies;
DROP TABLE fournisseurs_items;
DROP TABLE suivies;
DROP TABLE commandites;
DROP TABLE items;
DROP TABLE units;
DROP TABLE fournisseurs;
DROP TABLE adresses;
DROP TABLE formations;
DROP TABLE membres;
DROP TABLE contact_urgences;
DROP TABLE liens_parente;
DROP TABLE allergies;
DROP TABLE clubs;
DROP TABLE concentrations;
DROP TABLE suivie_statuts;
DROP TABLE roles;

------------------------
-- DROPPING SEQUENCES --
------------------------
DROP SEQUENCE clubs_id_seq;
DROP SEQUENCE membres_id_seq;
DROP SEQUENCE concentrations_id_seq;
DROP SEQUENCE formations_id_seq;
DROP SEQUENCE adresses_id_seq;
DROP SEQUENCE fournisseurs_id_seq;  
DROP SEQUENCE items_id_seq;
DROP SEQUENCE units_id_seq;
DROP SEQUENCE commandites_id_seq;
DROP SEQUENCE suivie_statuts_seq;
DROP SEQUENCE liens_parente_id_seq;
DROP SEQUENCE contacts_urgence_id_seq;
DROP SEQUENCE allergies_id_seq;
DROP SEQUENCE roles_id_seq;