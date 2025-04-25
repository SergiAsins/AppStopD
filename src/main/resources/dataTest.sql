/* Roles */
INSERT INTO roles (name)
SELECT 'ROLE_USER';

INSERT INTO roles (name)
SELECT 'ROLE_ADMIN';

/* Users */
INSERT INTO users (username, password) VALUES ('Johansen', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (username, password) VALUES ('Abdelrahim', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (username, password) VALUES ('Mike', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

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
VALUES (3, 'Gandalf The White', '680222339', 'gandalf@erasmusloco.cmrK', 'Moria Second Floor on the Left', 'ProfilePicture3');