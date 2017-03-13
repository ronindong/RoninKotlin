package com.ronin.learn.dagger2;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/3/13.
 */

public class ComponentInject {

    @Inject
    Cloth cloth;

    @Inject
    Person person;


    public void init() {

        MainComponent mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule()).build();
        mainComponent.inject(this);
        System.out.println("init inject color: " + cloth.getColor() + "," + person);
    }
}
