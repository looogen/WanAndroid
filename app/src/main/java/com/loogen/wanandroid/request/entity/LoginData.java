package com.loogen.wanandroid.request.entity;

import java.util.List;

public class LoginData {
    private String icon;
    private boolean admin;
    private int type;
    private String token;
    private String password;
    private String publicName;
    private List<String> chapterTops;
    private String nickname;
    private List<String> collectIds;
    private int id;
    private String email;
    private int coinCount;
    private String username;

    public String getIcon() {
        return icon;
    }

    public boolean isAdmin() {
        return admin;
    }

    public int getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    public String getPassword() {
        return password;
    }

    public String getPublicName() {
        return publicName;
    }

    public List<String> getChapterTops() {
        return chapterTops;
    }

    public String getNickname() {
        return nickname;
    }

    public List<String> getCollectIds() {
        return collectIds;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public String getUsername() {
        return username;
    }
}