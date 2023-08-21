CREATE TABLE user_table (
    id BIGINT PRIMARY KEY,
    email VARCHAR(255)
);

INSERT INTO user_table (id, email)
VALUES
    (1, 'user1@example.com'),
    (2, 'user2@example.com'),
    (3, 'user3@example.com'),
    (4, 'user4@example.com'),
    (5, 'user5@example.com');