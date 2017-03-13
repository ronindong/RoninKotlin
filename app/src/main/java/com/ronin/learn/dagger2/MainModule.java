package com.ronin.learn.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/13.
 */

@Module
public class MainModule {

    @Provides
    public Cloth getCloth(){
        Cloth cloth = new Cloth();
        cloth.setColor("Blue");
        return cloth;
    }
}
