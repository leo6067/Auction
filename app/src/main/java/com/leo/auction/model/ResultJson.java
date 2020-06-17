package com.leo.auction.model;

/**
 * Created by Leo on 2018/6/25.
 */

public class ResultJson  {


    /**
     * state : 100
     * message : 您的账号在其他地方登陆，如不是您本人操作，请修改密码！
     */

    private int state;
    private String message;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
