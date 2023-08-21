package school21.spring.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import school21.spring.service.models.User;

import javax.print.DocFlavor;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    HikariDataSource dataSource;

    public UsersRepositoryJdbcImpl(HikariDS dataSource) {
        this.dataSource = dataSource.hikariDataSource;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;

        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT * FROM user_table WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            resultSet.next();

            user = new User(resultSet.getLong(1), resultSet.getString(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT * FROM user_table";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while(resultSet.next()) {
                userList.add(new User(resultSet.getLong(1),
                        resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM user_table WHERE id = ?")) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM user_table WHERE email = ?")) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            user = new User(resultSet.getLong(1),
                    resultSet.getString(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    public static class HikariDS {
        public HikariDataSource hikariDataSource;

        Properties properties;

        public HikariDS() {
            try {
                properties = new Properties();
                InputStream is = HikariDS.class.getClassLoader()
                        .getResourceAsStream("db.properties");
                properties.load(is);
                if (is != null) {
                    is.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            hikariDataSource = new HikariDataSource();
            hikariDataSource.setJdbcUrl(properties.getProperty("db.url"));
            hikariDataSource.setUsername("db.user");
            hikariDataSource.setPassword("db.password");
        }
    }
}
