package com.ronin.learn.dagger2;

/**
 * Created by Administrator on 2017/3/13.
 */

public class Cloth {
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "color："+ color;
    }
}
