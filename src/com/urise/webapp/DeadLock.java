package com.urise.webapp;

public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        final MainConcurrency a = new MainConcurrency("a");
        final MainConcurrency b = new MainConcurrency("b");

        Thread thread0 = new Thread("thread0") {
            @Override
            public void run() {
                getDeadLock(a, b);
            }
        };

        Thread thread1 = new Thread("thread1") {
            @Override
            public void run() {
                getDeadLock(b, a);
            }
        };

        thread0.start();
        thread1.start();
    }

    public static void getDeadLock(MainConcurrency a, MainConcurrency b) {
        synchronized (a) {
            System.out.println("Поток " + Thread.currentThread().getName() + " захватил объект " + a.name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Поток " + Thread.currentThread().getName() + " ждёт захвата " + b.name + " объекта.");
            synchronized (b){
                a.inc();
                b.inc();
            }
        }
    }
}