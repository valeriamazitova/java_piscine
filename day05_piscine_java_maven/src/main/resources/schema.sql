CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE rooms
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    creator_id INT REFERENCES users (id)
);

CREATE TABLE messages
(
    id        SERIAL PRIMARY KEY,
    author_id INT REFERENCES users (id),
    room_id   INT REFERENCES rooms (id),
    text      TEXT NULL,
    sent_at   TIMESTAMP NULL
);

-- DROP TABLE IF EXISTS messages cascade;
-- DROP TABLE IF EXISTS rooms cascade;
-- DROP TABLE IF EXISTS users cascade;