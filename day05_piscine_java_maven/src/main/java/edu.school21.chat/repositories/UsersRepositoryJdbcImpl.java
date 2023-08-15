package edu.school21.chat.repositories;

import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {

            String selectQuery = "SELECT *" +
                    "        FROM users" +
                    "        ORDER BY id" +
                    "        LIMIT " + size + " OFFSET " + page;

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        long userId = resultSet.getLong("id");
                        String login = resultSet.getString("login");
                        String password = resultSet.getString("password");

                        users.add(new User(userId, login, password));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  users;
    }
}
