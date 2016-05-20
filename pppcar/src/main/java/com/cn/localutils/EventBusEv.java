package com.cn.localutils;

/**
 * Created by nurmemet on 2016/5/17.
 */
public class EventBusEv {

    public EventBusEv(String event, Object data) {
        this.event = event;
        this.data = data;
    }

    public String event;
    public Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
