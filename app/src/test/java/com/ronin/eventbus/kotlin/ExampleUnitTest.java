package com.ronin.eventbus.kotlin;

import com.ronin.learn.KotlinTest;
import com.ronin.learn.dagger2.ComponentInject;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

    }

    @Test
    public void testKotlinInheritance() {
        KotlinTest kotlinTest = new KotlinTest();
        kotlinTest.testKotlinInheritance();
        kotlinTest.testKotlinArray();

    }

    @Test
    public void testDagger2(){
        new ComponentInject().init();
    }


}