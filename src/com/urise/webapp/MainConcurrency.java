package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10_000;
    public static int counter;
    public String name;

    public MainConcurrency(String name) {
        this.name = name;
    }

    public MainConcurrency() {
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);

 //      List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
                latch.countDown();
            });
            thread.start();
//            threads.add(thread);
        }
 /*       threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/
        latch.await();
        System.out.println(counter);
    }

    public synchronized void inc() {
        System.out.println(Thread.currentThread().getName());
        counter++;
    }
}