package edu.school21.chat.models;

import java.time.LocalDateTime;
public class Message {
    private Long id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime dateTime;

    public Message(Long id, User author, Chatroom room, String text, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Chatroom getRoom() {
        return room;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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
        return "Message : {" +
                "\n\tid=" + id +
                ",\n\tauthor=" + author +
                ",\n\troom=" + room +
                ",\n\ttext=\"" + text + '\"' +
                ",\n\tdateTime=" + dateTime +
                "\n}";
    }
}
