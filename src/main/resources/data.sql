/** insert default admin user*/
INSERT INTO users (id, name, role, username, password)
VALUES (0, 'admin user', 'ROLE_ADMIN', 'admin',
        '$2a$12$Y/2yoJv4OOmYxxZpTr2RHOshgUW2dRn0sl85o.cmfcdfaRU8A03NG') ON CONFLICT (id) DO NOTHING;