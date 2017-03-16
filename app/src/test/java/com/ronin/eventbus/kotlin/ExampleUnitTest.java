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
        kotlinTest.testDagger2();

    }

    @Test
    public void testDagger2() {
        new ComponentInject().init();
    }

    public static void change(String str) {
        str = "akjfla";
    }

    @Test
    public void testString() {
        String str = "1";
        change(str);
        System.out.println(str);
        int a = 14982759;
        System.out.println("a:" + a);
        while (a > 0) {
            System.out.print(a % 10);
            a = a / 10;
        }
        System.out.println();

    }


}