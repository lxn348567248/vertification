package me.liuningning.verification.loop;

public class Message {
     long time;
     public String identify;
    Message next;
    Handler target;

    /**
     * guard sPool
     */
    private static Object lock = new Object();
    private static int sCount = 0;
    private static Message sPool;


    public static Message obtain() {
        synchronized (lock) {
            if (sPool != null) {
                Message msg = sPool;
                sPool = msg.next;
                msg.next = null;
                sCount--;
                return msg;
            }
        }

        return new Message();
    }

    void reUse() {
        synchronized (lock) {
            if (sCount > 50) {
                return;
            }
            this.time = 0;
            this.identify = "";
            this.next = null;

            this.next = sPool;
            sPool = this;
            sCount++;
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "time=" + time +
                '}';
    }
}
