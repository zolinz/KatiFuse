package concurrencytest;

import java.util.Date;

/**
 * Created by Zoli on 16/09/2016.
 */
public class StartApp {

    public static void main(String ... args) throws InterruptedException {

        Thread one = new Thread(new HelloRunnable("thread 1"));

        Thread two = new Thread(new HelloRunnable("thread 2"));


        one.start();
        //Thread.sleep(2000);
        //one.interrupt();
        //Thread.sleep(4000);
        two.start();

        System.out.println("finish" + new Date());
    }
}
