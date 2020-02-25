-- Naucne Oblasti
insert into naucna_oblast (naziv) values ("Bioloske nauke");
insert into naucna_oblast (naziv) values ("Geo nauke");
insert into naucna_oblast (naziv) values ("Matematicke nauke");
insert into naucna_oblast (naziv) values ("Nauke o zastiti zivotne sredine");
insert into naucna_oblast (naziv) values ("Racunarske nauke");
insert into naucna_oblast (naziv) values ("Fizicke nauke");
insert into naucna_oblast (naziv) values ("Fizicko-hemijske nauke");
insert into naucna_oblast (naziv) values ("Hemijske nauke");

-- Korisnici
insert into korisnik (username, password, aktiviran, drzava, email, enabled, grad, ime, non_locked, prezime, recenzent, titula, latitude, longitude)
values ("aut1", '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, "Srbija", "aut1@gmail.com", 1, "Novi Sad", "Autor", 1, "Docent", 1, "dr", '25.6', '45.7');
insert into korisnik (username, password, aktiviran, drzava, email, enabled, grad, ime, non_locked, prezime, recenzent, titula, latitude, longitude)
values ("rec1", '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, "Srbija", "rec1@gmail.com", 1, "Novi Sad", "Dime", 1, "Dimic", 1, "dr", '25.6', '45.7');
insert into korisnik (username, password, aktiviran, drzava, email, enabled, grad, ime, non_locked, prezime, recenzent, titula, latitude, longitude)
values ("rec2", '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, "Srbija", "rec2@gmail.com", 1, "Novi Sad", "Mile", 1, "Milic", 1, "dr", '25.6', '45.7');
insert into korisnik (username, password, aktiviran, drzava, email, enabled, grad, ime, non_locked, prezime, recenzent, titula, latitude, longitude)
values ("rec3", '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, "Srbija", "rec3@gmail.com", 1, "Novi Sad", "Ana", 1, "Nas", 1, "dr", '25.6', '45.7');
insert into korisnik (username, password, aktiviran, drzava, email, enabled, grad, ime, non_locked, prezime, recenzent, titula, latitude, longitude)
values ("ur1", '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, "Srbija", "ur1@gmail.com", 1, "Novi Sad", "Boban", 1, "Bob", 1, "dr", '25.6', '45.7');
insert into korisnik (username, password, aktiviran, drzava, email, enabled, grad, ime, non_locked, prezime, recenzent, titula, latitude, longitude)
values ("ur2", '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, "Srbija", "ur2@gmail.com", 1, "Novi Sad", "Stojke", 1, "Doktor", 1, "dr", '25.6', '45.7');
insert into korisnik (username, password, aktiviran, drzava, email, enabled, grad, ime, non_locked, prezime, recenzent, titula, latitude, longitude)
values ("admin", '$2a$10$DAcNYGf.8duU0iHRcuYw8uNJ8oeJULYlwZoM/H4eQXk2zdN4dn9oe', 1, "Srbija", "admin@gmail.com", 1, "Novi Sad", "Admin", 1, "Adam", 1, "dr", '25.6', '45.7');

-- Korisnici i NaucneOblasti
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (2, 1);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (2, 2);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (2, 3);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (2, 4);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (3, 1);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (3, 2);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (3, 3);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (3, 4);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (4, 1);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (4, 2);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (4, 3);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (4, 4);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (5, 1);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (5, 2);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (5, 3);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (5, 4);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (6, 1);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (6, 2);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (6, 3);
insert into korisnik_naucna_oblast (korisnik_id, naucna_oblast_id) values (6, 4);

-- Authority
insert into authority (name) VALUES ('admin');
insert into authority (name) VALUES ('reviewer');
insert into authority (name) VALUES ('editor');
insert into authority (name) VALUES ('author');

--user_authority
insert into korisnik_authority (korisnik_id, authority_id) values (1, 4);
insert into korisnik_authority (korisnik_id, authority_id) values (2, 2);
insert into korisnik_authority (korisnik_id, authority_id) values (3, 2);
insert into korisnik_authority (korisnik_id, authority_id) values (4, 2);
insert into korisnik_authority (korisnik_id, authority_id) values (5, 3);
insert into korisnik_authority (korisnik_id, authority_id) values (6, 3);
insert into korisnik_authority (korisnik_id, authority_id) values (7, 1);

-- Casopisi
insert into casopis (aktiviran, issn, nacin_placanja, naziv, glavni_urednik_id) values (1, "1122334455", "pretplata", "Prvi Casopis", 7);
insert into casopis (aktiviran, issn, nacin_placanja, naziv, glavni_urednik_id) values (1, "1111223344", "pretplata", "Drugi Casopis", 7);

-- Casopisi i Naucne Oblasti
insert into casopis_naucna_oblast (casopis_id, naucna_oblast_id) values (1, 1);
insert into casopis_naucna_oblast (casopis_id, naucna_oblast_id) values (1, 2);
insert into casopis_naucna_oblast (casopis_id, naucna_oblast_id) values (2, 4);
insert into casopis_naucna_oblast (casopis_id, naucna_oblast_id) values (2, 5);

--Casopisi i Recenzenti
insert into casopis_recenzenti (casopis_id, korisnik_id) values (1, 2);
insert into casopis_recenzenti (casopis_id, korisnik_id) values (1, 3);
insert into casopis_recenzenti (casopis_id, korisnik_id) values (1, 4);
insert into casopis_recenzenti (casopis_id, korisnik_id) values (2, 2);
insert into casopis_recenzenti (casopis_id, korisnik_id) values (2, 3);
insert into casopis_recenzenti (casopis_id, korisnik_id) values (2, 4);

--Casopisi i Urednici
insert into casopis_urednici (casopis_id, korisnik_id) values (1, 5);
insert into casopis_urednici (casopis_id, korisnik_id) values (1, 6);

--NaucniRadovi i Recenzenti
insert into naucni_rad_recenzenti (naucni_rad_id, korisnik_id) values (2, 2);
insert into naucni_rad_recenzenti (naucni_rad_id, korisnik_id) values (3, 3);
insert into naucni_rad_recenzenti (naucni_rad_id, korisnik_id) values (4, 4);

