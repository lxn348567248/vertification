package me.liuningning.verification.loop;

public class Looper {

     MessageQueue messageQueue;
    private static ThreadLocal<Looper> sLooper = new ThreadLocal<>();

    public Looper() {
        messageQueue = new MessageQueue();

    }

    public static void prepare() {
        sLooper.set(new Looper());

    }

    public static Looper looper() {
        if(sLooper==null){
            throw new RuntimeException("must init Looper");
        }
        return sLooper.get();
    }

    public static   void loop() {

        Looper looper = Looper.looper();

        while(true){
            Message msg = looper.messageQueue.next();
            msg.target.dispatcher(msg);
            msg.reUse();
        }


    }
}
