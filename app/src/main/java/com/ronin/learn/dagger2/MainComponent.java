package com.ronin.learn.dagger2;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/13.
 */
@Component(modules = {MainModule.class})
public interface MainComponent {
    void inject(ComponentInject inject);

}
