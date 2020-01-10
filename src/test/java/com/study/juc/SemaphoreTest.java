package com.study.juc;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        SemaphoreTest st = new SemaphoreTest();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                try {
                    st.fetchData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    Semaphore semaphore = new Semaphore(10);

    public void fetchData() throws InterruptedException {
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        semaphore.release();

    }

}
