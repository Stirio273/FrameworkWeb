CREATE TABLE login(
    id SERIAL PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(100),
    profile VARCHAR(50)
);

INSERT INTO login VALUES(default, 'Rakoto', '1234', 'admin');
INSERT INTO login VALUES(default, 'Rabe', 'qwerty', null);