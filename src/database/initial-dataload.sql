Insert into clubs values (nextval('clubs_id_seq'), 'PRECI','Programme de Regroupement Étudiant pour la Coopération Internationale');
Insert into clubs values (nextval('clubs_id_seq'), 'LiETS','Ligue d`Improvisation de l`ETS');
Insert into clubs values (nextval('clubs_id_seq'), 'Evolution','Véhicule à faible consommation');
Insert into clubs values (nextval('clubs_id_seq'), 'Baja','Véhicule amphibie tout terrain');

INSERT INTO roles(id, nom, description) VALUES (nextval('roles_id_seq'), 'Membre', 'Un membre.');
INSERT INTO roles(id, nom, description) VALUES (nextval('roles_id_seq'), 'Capitaine', 'Un capitaine.');

INSERT INTO membres (id,concentrations_id,nom,prenom,courriel,code_permanent,code_universel,actif,telephone) VALUES (1,1,'Patrick','Olsen','nibh.Aliquam@tristiquesenectus.co.uk','XUYJ98427084','HS00689',true,'3716562099');
INSERT INTO membres (id,concentrations_id,nom,prenom,courriel,code_permanent,code_universel,actif,telephone) VALUES (2,2,'Moses','Melendez','Donec.consectetuer.mauris@mattisvelit.edu','SXGD95781049','KF32898',true,'1881189845');
INSERT INTO membres (id,concentrations_id,nom,prenom,courriel,code_permanent,code_universel,actif,telephone) VALUES (3,3,'Giacomo','Cote','velit.Cras.lorem@duiSuspendisseac.ca','IJKG71845113','XU92060',true,'6534742324');
INSERT INTO membres (id,concentrations_id,nom,prenom,courriel,code_permanent,code_universel,actif,telephone) VALUES (4,4,'Jade','Ford','sem.ut.dolor@nonbibendum.co.uk','GXLM93228319','EZ35441',true,'8522879201');
INSERT INTO membres (id,concentrations_id,nom,prenom,courriel,code_permanent,code_universel,actif,telephone) VALUES (5,5,'Chandler','Mccarty','in.dolor@atpretiumaliquet.org','ACGN79164633','HJ85616',true,'3055537869');

Insert into concentrations values (nextval('concentrations_id_seq'), 'CTN','Génie de la construction');
Insert into concentrations values (nextval('concentrations_id_seq'), 'ELE','Génie électrique');
Insert into concentrations values (nextval('concentrations_id_seq'), 'GOL','Génie des opérations et de la logistique');
Insert into concentrations values (nextval('concentrations_id_seq'), 'GPA','Génie de production automatisée');
Insert into concentrations values (nextval('concentrations_id_seq'), 'LOG','Génie logiciel');
Insert into concentrations values (nextval('concentrations_id_seq'), 'MEC','Génie mécanique');
Insert into concentrations values (nextval('concentrations_id_seq'), 'TI','Génie des technologies de l`information');

INSERT INTO units (id,unit_code,systeme) VALUES (0,'UNIT0','SYSTEME1');
INSERT INTO units (id,unit_code,systeme) VALUES (1,'UNIT1','SYSTEME2');
INSERT INTO units (id,unit_code,systeme) VALUES (2,'UNIT2','SYSTEME3');
INSERT INTO units (id,unit_code,systeme) VALUES (3,'UNIT3','SYSTEME4');
INSERT INTO units (id,unit_code,systeme) VALUES (4,'UNIT4','SYSTEME5');
INSERT INTO units (id,unit_code,systeme) VALUES (5,'UNIT5','SYSTEME6');

INSERT INTO items (id,units_id,description,code_club,qte_courante,qty_min,qty_max) VALUES (1,1,'urna justo faucibus lectus, a',1,3,1,15);
INSERT INTO items (id,units_id,description,code_club,qte_courante,qty_min,qty_max) VALUES (2,2,'vehicula aliquet libero. Integer in',2,4,1,14);
INSERT INTO items (id,units_id,description,code_club,qte_courante,qty_min,qty_max) VALUES (3,3,'in lobortis tellus justo sit',3,5,1,21);
INSERT INTO items (id,units_id,description,code_club,qte_courante,qty_min,qty_max) VALUES (4,4,'at, velit. Pellentesque ultricies dignissim',4,10,1,14);

INSERT INTO adresses (id,no_civique,rue,appartement,ville,code_postal) VALUES (0,1,'P.O. Box 733, 4867 Pellentesque. Av.',1,'Greenwich','26231');
INSERT INTO adresses (id,no_civique,rue,appartement,ville,code_postal) VALUES (1,2,'6206 Suspendisse Avenue',2,'Breton','22078');
INSERT INTO adresses (id,no_civique,rue,appartement,ville,code_postal) VALUES (2,3,'P.O. Box 932, 5843 Donec Road',3,'Borgerhout','21679');
INSERT INTO adresses (id,no_civique,rue,appartement,ville,code_postal) VALUES (3,4,'8448 Mollis. St.',4,'Anchorage','5048');
INSERT INTO adresses (id,no_civique,rue,appartement,ville,code_postal) VALUES (4,5,'P.O. Box 585, 7672 Enim. Avenue',5,'Cargovil','0729LE');
INSERT INTO adresses (id,no_civique,rue,appartement,ville,code_postal) VALUES (5,6,'928-6538 Cursus. Avenue',6,'Attimis','76747');
INSERT INTO adresses (id,no_civique,rue,appartement,ville,code_postal) VALUES (6,7,'388-2939 Enim Rd.',7,'Stokkem','56673-407');
INSERT INTO adresses (id,no_civique,rue,appartement,ville,code_postal) VALUES (7,8,'566-6018 Lorem St.',8,'Barbania','7329WQ');
INSERT INTO adresses (id,no_civique,rue,appartement,ville,code_postal) VALUES (8,9,'1311 Torquent Av.',9,'Merbes-Sainte-Marie','H0H 4M9');
INSERT INTO adresses (id,no_civique,rue,appartement,ville,code_postal) VALUES (9,10,'Ap #318-7322 Vivamus St.',10,'Serskamp','97859-876');

INSERT INTO allergies (id,description) VALUES (0,'nibh. Phasellus nulla. Integer vulputate,');
INSERT INTO allergies (id,description) VALUES (1,'scelerisque sed, sapien. Nunc pulvinar');
INSERT INTO allergies (id,description) VALUES (2,'id nunc interdum feugiat. Sed');
INSERT INTO allergies (id,description) VALUES (3,'per inceptos hymenaeos. Mauris ut');
INSERT INTO allergies (id,description) VALUES (4,'Aliquam auctor, velit eget laoreet');
INSERT INTO allergies (id,description) VALUES (5,'tellus sem mollis dui, in');

INSERT INTO fournisseurs (id,adresses_id,nom,contact,telephone,fax,courriel) VALUES (1,1,'Bibendum Fermentum Company','Gilbert, Sean Q.','(805) 561-7970','(984) 452-9655','cubilia.Curae.Phasellus@cubilia.org');
INSERT INTO fournisseurs (id,adresses_id,nom,contact,telephone,fax,courriel) VALUES (2,2,'Non Corporation','Yates, Cailin E.','(574) 670-8278','(793) 724-5490','Curae.Phasellus@aliquamenimnec.edu');
INSERT INTO fournisseurs (id,adresses_id,nom,contact,telephone,fax,courriel) VALUES (3,3,'Imperdiet Corp.','Jennings, Brynn P.','(541) 442-7761','(756) 911-2750','Etiam.laoreet.libero@dignissimMaecenas.net');
INSERT INTO fournisseurs (id,adresses_id,nom,contact,telephone,fax,courriel) VALUES (4,4,'Adipiscing Enim Corporation','Hickman, Wade M.','(850) 532-2571','(268) 523-2179','eget@montesnasceturridiculus.edu');
INSERT INTO fournisseurs (id,adresses_id,nom,contact,telephone,fax,courriel) VALUES (5,5,'Urna Nunc Quis Company','Douglas, Dustin M.','(173) 840-7137','(318) 662-4800','Phasellus.in.felis@pellentesquemassalobortis.com');

INSERT INTO commandites (id,fournisseurs_id,items_id,clubs_id,valeur,nature) VALUES (1,1,1,1,908,'ut, pellentesque eget, dictum placerat,');
INSERT INTO commandites (id,fournisseurs_id,items_id,clubs_id,valeur,nature) VALUES (2,2,2,2,9686,'posuere vulputate, lacus. Cras interdum.');
INSERT INTO commandites (id,fournisseurs_id,items_id,clubs_id,valeur,nature) VALUES (3,3,3,3,1628,'feugiat placerat velit. Quisque varius.');
INSERT INTO commandites (id,fournisseurs_id,items_id,clubs_id,valeur,nature) VALUES (4,4,4,4,5702,'molestie dapibus ligula. Aliquam erat');