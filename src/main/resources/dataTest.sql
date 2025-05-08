/* Roles */
INSERT INTO roles (name)
SELECT 'ROLE_USER';

INSERT INTO roles (name)
SELECT 'ROLE_ADMIN';

/* Users */
INSERT INTO users (username, password) VALUES ('Johansen', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (username, password) VALUES ('Abdelrahim', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (username, password) VALUES ('Pocholo', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

/* Roles Users */
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2);
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1);
INSERT INTO roles_users (role_id, user_id) VALUES (1, 3);


/* Profiles */
INSERT INTO profiles (user_id, name, phone, email, address, picture)
VALUES (1, 'Johansen Macho II', '1233456789', 'johansen@abdjaji.ma', 'Rue Zellaka, 3, 40000 Johanslandia', 'Moha picture');

INSERT INTO profiles (user_id, name, phone, email, address, picture)
VALUES (2, 'Abdelrahim Hassan II', '111222333', 'abdel@abdjaji.sq', 'Av Ausias March', 'ProfilePicture2');

INSERT INTO profiles (user_id, name, phone, email, address, picture)
VALUES (3, 'Pocholo  White Snow', '680222339', 'pocholo@erasmusloco.ma', 'Moria Second Floor on the Left', 'ProfilePicture3');

/* Cases */
INSERT INTO cases (status, address, region, city, case_date, description)
VALUES (0, 'Minas Tirith, Second Flor, door number 8', 'Gondor', 'Gondor', '2025-07-15', 'Johansen vs. Sauron; Sauron says the city was payed and founded just by himself');

INSERT INTO cases (status, address, region, city, case_date, description)
VALUES (0, 'La Caseta Del Pocholo de l"Albufera', 'La Ribera Guerrillera', 'País Valencià', '2025-07-20', 'Pocholo is in a situation of Eviction express - Santander Bank is gonna take the Pocholo''s place');
