package com.arsenic.coiny.Model.Chat;

/*
 * Created by: jose
 * Company: Dom Peru
 * Date: 10/19/19
 */

import com.stfalcon.chatkit.commons.models.IUser;

public class Author implements IUser{

    private String id;
    private String name;
    private String avatar;

    public Author(String id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

}
