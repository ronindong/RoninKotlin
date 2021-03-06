package com.ronin.eventbus.kotlin;

import com.ronin.learn.KotlinTest;
import com.ronin.learn.dagger2.ComponentInject;
import com.ronin.learn.util.XThread;

import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    public void testServer(){

        Serializable serializable= new Serializable(){};
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        int a = 10,b=20;
        method(a,b);
        System.out.println("a="+a);
        System.out.println("b="+b);

        XThread.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("----xthred");
            }
        });

    }

    private static void method(int a,int b){

    }

    @Test
    public void testKotlinInheritance() {
        KotlinTest kotlinTest = KotlinTest.Companion.instance();
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

    @Test
    public void testProxy(){

    }

}