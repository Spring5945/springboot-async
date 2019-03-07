package com.zhengyu.eventsource;

import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent {
    private String msg;

    public UserEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
