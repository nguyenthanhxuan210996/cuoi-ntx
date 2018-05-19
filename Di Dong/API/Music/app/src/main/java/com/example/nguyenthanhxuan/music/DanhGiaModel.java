package com.example.nguyenthanhxuan.music;

/**
 * Created by Nguyen Thanh Xuan on 5/17/2018.
 */

public class DanhGiaModel {
    private int iddgia;
    private String value;
    private String userdg;


    public DanhGiaModel(int iddgia, String value, String userdg) {
        this.iddgia = iddgia;
        this.value = value;
        this.userdg = userdg;
    }

    public int getIddgia() {
        return iddgia;
    }

    public void setIddgia(int iddgia) {
        this.iddgia = iddgia;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUserdg() {
        return userdg;
    }

    public void setUserdg(String userdg) {
        this.userdg = userdg;
    }
}
