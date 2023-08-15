package edu.school21.chat.models;

import java.util.List;

public class Chatroom {
    private Long id;
    private final String name;
    private final User creator;
    private final List<Message> messages;

    public Chatroom(Long id, String name) {
        this.id = id;
        this.name = name;
        creator = null;
        messages = null;
    }

    public Chatroom(Long id, String name, User creator, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public User getCreator() {
        return creator;
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
                ", name='" + name + '\'' +
                ", creator=" + creator +
                ", messages=" + messages +
                '}';
    }
}
