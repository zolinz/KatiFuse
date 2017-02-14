package concurrencytest;

import java.util.Date;

/**
 * Created by Zoli on 16/09/2016.
 */
public class HelloRunnable implements Runnable {

    String msg;

    public HelloRunnable(String msg){
        this.msg = msg;
    }

    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(msg + "got interrupted"  );
        }
        System.out.println(msg + new Date());
    }
}
