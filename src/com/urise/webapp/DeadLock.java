package com.urise.webapp;

public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        final MainConcurrency LOCK_1 = new MainConcurrency();
        final MainConcurrency LOCK_2 = new MainConcurrency();

        new Thread(() -> {
            try {
                deadLock(LOCK_1, LOCK_2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        deadLock(LOCK_2, LOCK_1);
    }

    public static void deadLock(MainConcurrency a, MainConcurrency b) throws InterruptedException {
        synchronized (a) {
            Thread.sleep(1000);
            synchronized (b){
                a.inc();
                b.inc();
            }
        }
    }
}