package com.ronin.learn.dagger2;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/3/13.
 */

public class Person {

    @Inject
    public Person() {

    }

    @Override
    public String toString() {
        return "person info";
    }
}
