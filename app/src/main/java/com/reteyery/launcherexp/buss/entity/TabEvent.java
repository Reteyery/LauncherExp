package com.reteyery.launcherexp.buss.entity;

public class TabEvent {
    private int code;
    private int id;
    private String msg;

    public TabEvent(int code, int id, String msg) {
        this.code = code;
        this.id = id;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
