--record labels
insert into record_label(RECORD_LABEL_ID, name) values (1, 'Universal Music Group');
insert into record_label(RECORD_LABEL_ID, name) values (2, 'Sony Music Entertainment');
insert into record_label(RECORD_LABEL_ID, name) values (3, 'Warner Music Group');


-- address


insert into address(ADDRESS_ID, street, NUMBER, city) values (1, 'Bulevardul Iuliu Maniu', 20, 'Bucuresti');
insert into address(ADDRESS_ID, street, NUMBER, city) values (2, 'Bulevardul Energeticienilor', 11, 'Bucuresti');

-- artists

insert into artist (ARTIST_ID, first_name, last_name, cnp, stage_name, FK_RECORDLABEL_ID, FK_ADDRESS_ID) values (1,'Adele', 'Adkins', '8980505017464', 'Adele', 1, 1);
insert into artist (ARTIST_ID, first_name, last_name, cnp, stage_name, FK_RECORDLABEL_ID, FK_ADDRESS_ID) values (2,'Elena Alexandra', 'Apostoleanu', '2861016135926', 'INNA', 2, 2);


-- albums
--albums first artist
insert into albums (ID_ALBUM, album_name, album_year, no_tracks, ARTIST_ID) VALUES (1,'21', 2011, 11, 1);
insert into albums (ID_ALBUM, album_name, album_year, no_tracks, ARTIST_ID) VALUES (2,'25', 2015, 11, 1);
-- albums second artist
insert into albums (ID_ALBUM, album_name, album_year, no_tracks, ARTIST_ID) VALUES (3,'Hot', 2009, 12, 2);
insert into albums (ID_ALBUM, album_name, album_year, no_tracks, ARTIST_ID) VALUES (4,'Nirvana', 2017, 12, 2);


-- songs
--first artist - first album
insert into songs(id_song, song_title, song_length, language, id_album) values (1,'Rolling in the Deep', '3m 48s','ENGLISH', 1);
insert into songs(id_song, song_title, song_length, language, id_album) values (2,'Rumour Has It', '3m 43s','ENGLISH', 1);
insert into songs(id_song, song_title, song_length, language, id_album) values (3,'Turning Tables', '4m 10s','ENGLISH', 1);
insert into songs(id_song, song_title, song_length, language, id_album) values (4,'Don''t You Remember', '4m 03s','ENGLISH', 1);
insert into songs(id_song, song_title, song_length, language, id_album) values (5,'Set Fire to the Rain', '4m 02s','ENGLISH', 1);
insert into songs(id_song, song_title, song_length, language, id_album) values (6,'He Won''t Go', '4m 38s','ENGLISH', 1);
insert into songs(id_song, song_title, song_length, language, id_album) values (7,'Take It All', '3m 48s','ENGLISH', 1);
insert into songs(id_song, song_title, song_length, language, id_album) values (8,'I''ll Be Waiting', '4m 01s','ENGLISH', 1);
insert into songs(id_song, song_title, song_length, language, id_album) values (9,'One and Only', '5m 49s','ENGLISH', 1);
insert into songs(id_song, song_title, song_length, language, id_album) values (10,'Lovesong', '5m 16s','ENGLISH', 1);
insert into songs(id_song, song_title, song_length, language, id_album) values (11,'Someone Like You', '4m 45s','ENGLISH', 1);

--first artist - second album
insert into songs(id_song, song_title, song_length, language, id_album) values (12,'Hello', '4m 55s','ENGLISH', 2);
insert into songs(id_song, song_title, song_length, language, id_album) values (13,'Send My Love (To Your New Lover)', '3m 43s','ENGLISH', 2);
insert into songs(id_song, song_title, song_length, language, id_album) values (14,'I Miss You', '5m 49s','ENGLISH', 2);
insert into songs(id_song, song_title, song_length, language, id_album) values (15,'When We Were Young', '4m 50s','ENGLISH', 2);
insert into songs(id_song, song_title, song_length, language, id_album) values (16,'Remedy', '4m 05s','ENGLISH', 2);
insert into songs(id_song, song_title, song_length, language, id_album) values (17,'Water Under the Bridge', '4m 00s','ENGLISH', 2);
insert into songs(id_song, song_title, song_length, language, id_album) values (18,'River Lea', '3m 45s','ENGLISH', 2);
insert into songs(id_song, song_title, song_length, language, id_album) values (19,'Love in the Dark', '4m 46s','ENGLISH', 2);
insert into songs(id_song, song_title, song_length, language, id_album) values (20,'Million Years Ago', '3m 47s','ENGLISH', 2);
insert into songs(id_song, song_title, song_length, language, id_album) values (21,'All I Ask', '4m 32s','ENGLISH', 2);
insert into songs(id_song, song_title, song_length, language, id_album) values (22,'Sweetest Devotion', '4m 11s','ENGLISH', 2);

-- second artist -- first album
insert into songs(id_song, song_title, song_length, language, id_album) values (23,'Hot', '3m 38s','ENGLISH', 3);
insert into songs(id_song, song_title, song_length, language, id_album) values (24,'Love', '3m 400s','ENGLISH', 3);
insert into songs(id_song, song_title, song_length, language, id_album) values (25,'Days Nights', '3m 37s','ENGLISH', 3);
insert into songs(id_song, song_title, song_length, language, id_album) values (26,'Fever', '3m 23s','ENGLISH', 3);
insert into songs(id_song, song_title, song_length, language, id_album) values (27,'Left Right', '3m 24s','ENGLISH', 3);
insert into songs(id_song, song_title, song_length, language, id_album) values (28,'Amazing', '3m 25s','ENGLISH', 3);
insert into songs(id_song, song_title, song_length, language, id_album) values (29,'Don''t Let the Music Die', '3m 38s','ENGLISH', 3);
insert into songs(id_song, song_title, song_length, language, id_album) values (30,'On and On', '3m 38s','ENGLISH', 3);
insert into songs(id_song, song_title, song_length, language, id_album) values (31,'Ladies', '3m 07s','ENGLISH', 3);
insert into songs(id_song, song_title, song_length, language, id_album) values (32,'Deja Vu', '4m 20s','ENGLISH', 3);
insert into songs(id_song, song_title, song_length, language, id_album) values (33,'On Fire', '3m 20s','ENGLISH', 3);
insert into songs(id_song, song_title, song_length, language, id_album) values (34,'Devil''s Paradise', '3m 47s','ENGLISH', 3);
-- second artist -- second album
insert into songs(id_song, song_title, song_length, language, id_album) values (35,'Ruleta', '3m 19s','SPANISH', 4);
insert into songs(id_song, song_title, song_length, language, id_album) values (36,'Gimme Gimme', '3m 11s','ENGLISH', 4);
insert into songs(id_song, song_title, song_length, language, id_album) values (37,'My Dreams', '3m 28s','ENGLISH', 4);
insert into songs(id_song, song_title, song_length, language, id_album) values (38,'INNA feat. Erick - Ruleta', '3m 18s','SPANISH', 4);
insert into songs(id_song, song_title, song_length, language, id_album) values (39,'Pentru Ca', '3m 18s','ROMANIAN', 4);
insert into songs(id_song, song_title, song_length, language, id_album) values (40,'Don''t Mind', '3m 14s','ENGLISH', 4);
insert into songs(id_song, song_title, song_length, language, id_album) values (41,'Lights', '2m 56s','ENGLISH', 4);
insert into songs(id_song, song_title, song_length, language, id_album) values (42,'Dream About the Ocean', '3m 06s','ENGLISH', 4);
insert into songs(id_song, song_title, song_length, language, id_album) values (43,'Nota de Plata', '3m 10s','ROMANIAN', 4);
insert into songs(id_song, song_title, song_length, language, id_album) values (44,'Tu Manera', '2m 58s','SPANISH', 4);
insert into songs(id_song, song_title, song_length, language, id_album) values (45,'Cum Ar Fi', '3m 00s','ROMANIAN', 4);
insert into songs(id_song, song_title, song_length, language, id_album) values (46,'Nirvana', '3m 14s','ENGLISH', 4);


-- managers

insert into manager(MANAGER_ID, first_name, last_name, FK_RECORD_LABEL_ID,FK_USER_ID) values (1, 'John', 'Smith', 1, null);
insert into manager(MANAGER_ID, first_name, last_name, FK_RECORD_LABEL_ID,FK_USER_ID) values (2, 'Sarah', 'Lee', 2, null);
insert into manager(MANAGER_ID, first_name, last_name, FK_RECORD_LABEL_ID,FK_USER_ID) values (3, 'David', 'Garcia', 3, null);

-- deals

insert into deals(DEAL_ID, signing_date, contract_length) values(1, '2018-04-16', 10);
insert into deals(DEAL_ID, signing_date, contract_length) values(2, '2015-10-20', 5);


-- consults

insert into consult(CONSULT_ID, date, comment, FK_MANAGER_ID, FK_ARTIST_ID) values(1,'2018-03-01', 'We had a great discussion about her new album. We talked about the concept and direction of the album and I gave her some ideas on how to promote it', 1, 1);
insert into consult(CONSULT_ID, date, comment, FK_MANAGER_ID, FK_ARTIST_ID) values(2,'2015-9-01', 'We discussed some exciting upcoming projects and I''m excited to see what we can accomplish together', 2, 2);


-- agreement

insert into agreement(CONSULT_ID, DEAL_ID) values(1, 1);
insert into agreement(CONSULT_ID, DEAL_ID) values(2, 2);
