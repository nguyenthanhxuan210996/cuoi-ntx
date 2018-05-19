package com.example.nguyenthanhxuan.music;

/**
 * Created by Nguyen Thanh Xuan on 5/17/2018.
 */

public class BinhLuan {
    private int iddanhgia;
    private String danhgia;
    private String songid;
    private String userdanhgia;

    public BinhLuan(int iddanhgia, String danhgia, String songid, String userdanhgia) {
        this.iddanhgia = iddanhgia;
        this.danhgia = danhgia;
        this.songid = songid;
        this.userdanhgia = userdanhgia;
    }

    public int getIddanhgia() {
        return iddanhgia;
    }

    public void setIddanhgia(int iddanhgia) {
        this.iddanhgia = iddanhgia;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }

    public String getUserdanhgia() {
        return userdanhgia;
    }

    public void setUserdanhgia(String userdanhgia) {
        this.userdanhgia = userdanhgia;
    }
}
