package me.liuningning.verification.loop;

import java.util.Random;

public class MainThread {

    /**
     * mock android handle looper
     *
     * ThreadLocal ()
     * @param args
     */

    public static void main(String[] args) {
        Looper.prepare();

        Handler handler = new Handler();

        new Thread(() -> {

            //mock send message
            for (;;) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = Message.obtain();

                handler.sendMessage(new Random().nextInt(),msg);
            }


        }).start();

        Looper.loop();
    }
}
