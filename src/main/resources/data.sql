INSERT INTO user_app (user_id, email, first_name, is_active, last_name, password, register_date, username) VALUES
(1, 'test@gmail.com', 'Carter', b'1', 'Hash', 'password', '2019-04-07 00:06:29', 'admin');

INSERT INTO role_app (role_id, roles) VALUES
(1, 'ADMIN'),
(2, 'USER');

INSERT INTO user_role (user_id, role_id) VALUES
(1, 1),
(1, 2);

INSERT INTO category (name) VALUES
('Drole'),
('Triste'),
('Etonnant');