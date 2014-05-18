ALTER TABLE Membres_Preferences DROP CONSTRAINT FKMembres_Pr229875;
ALTER TABLE Contacts_Urgence DROP CONSTRAINT FKContacts_U164890;
ALTER TABLE Evenements DROP CONSTRAINT FKEvenements917475;
ALTER TABLE Membres DROP CONSTRAINT FKMembres495106;
ALTER TABLE Suivies DROP CONSTRAINT FKSuivies436259;
ALTER TABLE Membres_Formations DROP CONSTRAINT FKMembres_Fo495691;
ALTER TABLE Membres_Allergies DROP CONSTRAINT FKMembres_Al548936;
ALTER TABLE Membres_Allergies DROP CONSTRAINT FKMembres_Al895678;
ALTER TABLE Membres_Formations DROP CONSTRAINT FKMembres_Fo906962;
ALTER TABLE Fournisseurs_Items DROP CONSTRAINT FKFournisseu723122;
ALTER TABLE Fournisseurs_Items DROP CONSTRAINT FKFournisseu24556;
ALTER TABLE Suivies DROP CONSTRAINT FKSuivies572159;
ALTER TABLE Items DROP CONSTRAINT FKItems252341;
ALTER TABLE Fournisseurs DROP CONSTRAINT FKFournisseu604575;
ALTER TABLE Commandites DROP CONSTRAINT FKCommandite968053;
ALTER TABLE Suivies DROP CONSTRAINT FKSuivies746520;
ALTER TABLE Commandites DROP CONSTRAINT FKCommandite304970;
ALTER TABLE Commandites DROP CONSTRAINT FKCommandite426027;
ALTER TABLE Membres_Clubs_Roles DROP CONSTRAINT FKMembres_Cl391451;
ALTER TABLE Membres_Clubs_Roles DROP CONSTRAINT FKMembres_Cl153481;
ALTER TABLE Membres_Clubs DROP CONSTRAINT FKMembres_Cl269114;
ALTER TABLE Membres_Clubs DROP CONSTRAINT FKMembres_Cl936196;
ALTER TABLE Contacts_Urgence_Membres DROP CONSTRAINT FKContacts_U960303;
ALTER TABLE Contacts_Urgence_Membres DROP CONSTRAINT FKContacts_U30385;
DROP TABLE IF EXISTS Evenements CASCADE;
DROP TABLE IF EXISTS Contacts_Urgence_Membres CASCADE;
DROP TABLE IF EXISTS Membres_Clubs_Roles CASCADE;
DROP TABLE IF EXISTS Audits CASCADE;
DROP TABLE IF EXISTS Membres_Preferences CASCADE;
DROP TABLE IF EXISTS Roles CASCADE;
DROP TABLE IF EXISTS Allergies CASCADE;
DROP TABLE IF EXISTS Membres_Allergies CASCADE;
DROP TABLE IF EXISTS Liens_Parente CASCADE;
DROP TABLE IF EXISTS Contacts_Urgence CASCADE;
DROP TABLE IF EXISTS Membres_Formations CASCADE;
DROP TABLE IF EXISTS Suivies CASCADE;
DROP TABLE IF EXISTS Formations CASCADE;
DROP TABLE IF EXISTS Units CASCADE;
DROP TABLE IF EXISTS Commandites CASCADE;
DROP TABLE IF EXISTS Items CASCADE;
DROP TABLE IF EXISTS Fournisseurs_Items CASCADE;
DROP TABLE IF EXISTS Fournisseurs CASCADE;
DROP TABLE IF EXISTS Suivie_Statuts CASCADE;
DROP TABLE IF EXISTS Concentrations CASCADE;
DROP TABLE IF EXISTS Membres CASCADE;
DROP TABLE IF EXISTS Membres_Clubs CASCADE;
DROP TABLE IF EXISTS Adresses CASCADE;
DROP TABLE IF EXISTS Clubs CASCADE;