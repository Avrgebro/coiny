package com.arsenic.coiny.Model.Chat;

import com.stfalcon.chatkit.commons.models.IMessage;

import java.util.Date;

/*
 * Created by: jose
 * Company: Dom Peru
 * Date: 10/19/19
 */
public class Message implements IMessage {

    private String id;
    private String text;
    private Author author;
    private Date createdAt;

    public Message(String id, Author author, String text) {
        this(id, author, text, new Date());
    }

    public Message(String id, Author author, String text, Date createdAt) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.createdAt = createdAt;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Author getUser() {
        return author;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

}
