package com.example.nguyenthanhxuan.music;

/**
 * Created by Nguyen Thanh Xuan on 5/17/2018.
 */

public class Song {
    private String idbh;
    private String user;
    private String SongName;
    private String content;

    public Song() {
    }

    public Song(String idbh, String user, String songName, String content) {
        this.idbh = idbh;
        this.user = user;
        SongName = songName;
        this.content = content;
    }

    public String getIdbh() {
        return idbh;
    }

    public void setIdbh(String idbh) {
        this.idbh = idbh;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
