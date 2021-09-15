package me.liuningning.verification.loop;

public class Handler {
    private Looper looper;

    Handler() {
        looper = Looper.looper();
    }

    public void sendMessage(long time, Message msg) {
        msg.time = time;
        msg.target = this;
        looper.messageQueue.enqueue(msg, msg.time);
    }

    public void dispatcher(Message msg) {
        System.out.println(msg);
    }


}
