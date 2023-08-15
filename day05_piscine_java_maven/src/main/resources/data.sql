INSERT INTO users (login, password)
VALUES ('Kate', 'qwerty54321'),
       ('Peter', 'poiuytrewq809'),
       ('Grigory', '1998katmeow12345'),
       ('Jack', '1234567890'),
       ('Alice', 'qazwsxedc787878');

INSERT INTO rooms (name, creator_id)
VALUES ('intensive', 1),
       ('osnova', 1),
       ('volonteers', 1),
       ('java piscine', 1),
       ('c sharp piscine', 1);

INSERT INTO messages (author_id, room_id, text, sent_at)
VALUES (1, 1, 'Welcome to School 21 intensive!', '2023-08-01 10:00:00'),
       (2, 1, 'Hi, Kate, thank you!', '2023-08-01 10:05:00'),
       (4, 4, 'Can anyone help me with ex00 Day05?', '2023-08-02 12:00:00'),
       (3, 5, 'Please put slots on 18:00, Kzn', '2023-08-03 15:30:00'),
       (5, 3, 'I need a volonteer to help me with documents at 15:00', '2023-08-02 12:00:00');
