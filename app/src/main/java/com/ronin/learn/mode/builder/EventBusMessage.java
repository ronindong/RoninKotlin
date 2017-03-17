package com.ronin.learn.mode.builder;

/**
 * Created by SunGQ on 2017/3/14.
 */
public enum EventBusMessage {

    INSTANCE;

    private int key;
    //传递int消息
    private int i_value;
    //传递String消息
    private String s_value;
    //传递model消息
    private Object o_value;


    public void setKey(int key) {
        this.key = key;
    }

    public void setI_value(int i_value) {
        this.i_value = i_value;
    }

    public void setS_value(String s_value) {
        this.s_value = s_value;
    }

    public void setO_value(Object o_value) {
        this.o_value = o_value;
    }

    public int getI_value() {
        return i_value;
    }

    public int getKey() {
        return key;
    }

    public Object getO_value() {
        return o_value;
    }

    public String getS_value() {
        return s_value;
    }

    public final static EventBusMessage of(int key) {
        return of(key, -1);
    }

    public final static EventBusMessage of(int key, int i_value) {
        return of(key, i_value, "", null);
    }

    public final static EventBusMessage of(int key, String s_value) {
        return of(key, -1, s_value, null);
    }

    public final static EventBusMessage of(int key, int i_value, String s_value, Object o_value) {
        INSTANCE.setKey(key);
        INSTANCE.setI_value(i_value);
        INSTANCE.setS_value(s_value);
        INSTANCE.setO_value(o_value);
        System.out.println(INSTANCE);
        return INSTANCE;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[key:" + key);
        sb.append(",i_value:" + i_value);
        sb.append(",s_value:" + s_value);
        sb.append(",o_value:" + o_value + "]");
        return sb.toString();
    }
}
