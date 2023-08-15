package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        Message message = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "select m.id, m.author_id, u.login, u.password, m.room_id, r.name, m.text, m.sent_at\n" +
                    "from messages m\n" +
                    "    join users u on m.author_id = u.id\n" +
                    "    join rooms r on m.room_id = r.id\n" +
                    "where m.id = " + id;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        long messageId = resultSet.getLong(1);

                        long author_id = resultSet.getLong(2);
                        String userLogin = resultSet.getString(3);
                        String userPassword = resultSet.getString(4);

                        long room_id = resultSet.getLong(5);
                        String roomName = resultSet.getString(6);
                        String text = resultSet.getString(7);
                        LocalDateTime sent_at = resultSet.getTimestamp(8).toLocalDateTime();
                        message = new Message(messageId, new User(author_id, userLogin, userPassword),
                                new Chatroom(room_id, roomName), text, sent_at);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(message);
    }

    @Override
    public void save(Message message) {
        try (Connection connection = dataSource.getConnection()) {
            String insertQueryIntoUsers = "INSERT INTO users (login, password) " +
                    "VALUES ('" + message.getAuthor().getLogin() + "' , '" +
                    message.getAuthor().getPassword() + "') RETURNING id;";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQueryIntoUsers);

            ResultSet resultSet = preparedStatement.executeQuery();

            long lastGeneratedId;

            if (resultSet.next()) {
                lastGeneratedId = resultSet.getLong("id");

                message.getAuthor().setId(lastGeneratedId);
            }

            String insertQueryIntoRooms = "INSERT INTO rooms (name, creator_id) " +
                    "VALUES ('" + message.getRoom().getName() + "' , '" +
                    message.getRoom().getCreator().getId() + "') RETURNING id;";

            preparedStatement = connection.prepareStatement(insertQueryIntoRooms);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lastGeneratedId = resultSet.getLong("id");

                message.getRoom().setId(lastGeneratedId);
            }

            String insertQueryIntoMessages =
                    "INSERT INTO messages (author_id, room_id, text, sent_at)\n" +
                            "VALUES (" + message.getAuthor().getId() +
                            ", " + message.getRoom().getId() +
                            ", '" + message.getText() + "', '" + message.getDateTime() + "') RETURNING id";

            preparedStatement = connection.prepareStatement(insertQueryIntoMessages);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lastGeneratedId = resultSet.getLong("id");

                message.setId(lastGeneratedId);
            }

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message) {
        try (Connection connection = dataSource.getConnection()) {
            String updateQuery = "UPDATE messages SET author_id = " + message.getAuthor().getId() +
                    ", room_id = " + message.getRoom().getId() + ", text = '" +
                    message.getText() + "' , sent_at = " + message.getDateTime() +
                    " WHERE id = " + message.getId();

            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
