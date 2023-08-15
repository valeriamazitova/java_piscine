package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("carrjohn");
        config.setPassword("1234");
        config.setMaximumPoolSize(10);

        DataSource dataSource = new HikariDataSource(config);
//        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);

//        User creator = new User(null,"user", "user", new ArrayList(), new ArrayList());
//        Chatroom room = new Chatroom(null,"room", creator, new ArrayList());
//        Message message = new Message(null, creator, room, "Hello!", LocalDateTime.now());
//        messagesRepository.save(message);
//
//        System.out.println(message.getId());

//        Optional<Message> messageOptional = messagesRepository.findById(6L);
//        if (messageOptional.isPresent()) {
//            Message message = messageOptional.get();
//            message.setText("Bye");
//            message.setDateTime(null);
//            messagesRepository.update(message);
//        }

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);

        List<User> usersRepositoryAll = usersRepository.findAll(1, 2);
        // after page 1 (1st string) outputs 2 strings (since size = 2)

        for (User user: usersRepositoryAll) {
            System.out.println(user);
        }
    }
}
