package me.liuningning.verification.loop;

public class MessageQueue {
    private Message header;
    private Message tail;

    public MessageQueue() {
        header = tail = null;
    }

     synchronized void enqueue(Message msg,long time) {
        msg.time  = time;
        if (header == null) {
            header = tail = msg;
            return;
        }
        Message current = header;
        if (current.time > msg.time) {
            msg.next = header;
            header = msg;
            return;
        }

        Message pre = null;

        while (current != null) {

            if (current.time > msg.time) {
                break;
            }
            pre = current;
            current = current.next;
        }

        pre.next = msg;
        if (current == null) {
            tail = msg;
        } else {
            msg.next = current;
        }

        this.notifyAll();

    }


     synchronized Message next() {


        while (header == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Message msg = header;
        header = msg.next;
        msg.next = null;

        return msg;

    }


}
