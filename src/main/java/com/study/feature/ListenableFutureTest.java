package com.study.feature;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * https://github.com/google/guava/wiki/ListenableFutureExplained
 *
 * http://ifeve.com/google-guava-functional/
 */
public class ListenableFutureTest {

    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        ListenableFuture<String> str = service.submit(new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(1000 * 2);
                return "This is test";
            }
        });

        Futures.addCallback(
            str,
            new FutureCallback<String>() {
                public void onSuccess(String result) {
                    System.out.println("Success : " + result);
                }
                public void onFailure(Throwable t) {
                }
            },
            service);
        System.out.println("Main thread over");
    }

}
