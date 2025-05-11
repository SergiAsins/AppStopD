-- he commands for erase data from tables and restart id identities:

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
INSERT INTO cases (status, address, region, city, case_date, description, url_image)
VALUES (0, 'Minas Tirith, Second Flor, door number 8', 'Gondor', 'Gondor', '2025-07-15', 'Johansen vs. Sauron', 'https://media.istockphoto.com/id/1018966346/es/foto/mujer-brasile%C3%B1a-en-su-casa-en-la-cocina-de-una-estufa-de-madera.jpg?s=1024x1024&w=is&k=20&c=s18MKyLkrd2--b4gPNEauXZJwgZcHdTn6WGH8hi_CFw=');

INSERT INTO cases (status, address, region, city, case_date, description, url_image)
VALUES
(0, 'The House of the Dragon', 'Mordor', 'Monte del Destino', '2025-08-01', 'Smaug reclama la colina pero Bilbo alega "finder''s keepers"', 'https://cdn.pixabay.com/photo/2017/04/10/22/28/residence-2219972_1280.jpg'),

(1, '221B Baker Street', 'London', 'London', '2025-09-22', 'Sherlock vs. Mrs. Hudson por veladas de violín a las 3AM', 'https://cdn.pixabay.com/photo/2016/11/29/03/53/house-1867187_1280.jpg'),

(0, 'Torre de Pisa', 'Toscana', 'Pisa', '2025-10-15', 'El constructor exige pago extra por "diseño artístico no convencional"', 'https://cdn.pixabay.com/photo/2013/11/13/21/14/san-francisco-210230_1280.jpg'),

(2, 'Casa de Papel', 'Madrid', 'Madrid', '2025-11-30', 'El Profesor demanda a Netflix por derechos de imagen', 'https://cdn.pixabay.com/photo/2017/02/24/21/00/door-2096367_1280.jpg'),

(0, 'Forth Eorlingas!', 'Rohan', 'Edoras', '2025-12-25', 'Éomer desaloja a Grima por "mal Feng Shui con cráneos"', 'https://cdn.pixabay.com/photo/2015/03/05/21/08/residential-complex-660901_1280.jpg'),

(1, 'Apartamento 4A', 'Gotham', 'Ciudad Gótica', '2026-01-20', 'Joker subarrienda a Pingüino sin permiso del propietario: Bruce Wayne', 'https://cdn.pixabay.com/photo/2016/10/02/22/12/house-1710566_1280.jpg');