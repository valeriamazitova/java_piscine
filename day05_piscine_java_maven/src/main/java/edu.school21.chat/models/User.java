package edu.school21.chat.models;

import java.util.ArrayList;
public class User {
    private Long id;
    private final String login;
    private final String password;
    private final ArrayList<Chatroom> createdRooms;
    private final ArrayList<Chatroom> rooms;

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        createdRooms = null;
        rooms = null;
    }

    public User(Long id, String login, String password,
                ArrayList<Chatroom> createdRooms, ArrayList<Chatroom> rooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", rooms=" + rooms +
                '}';
    }
}
