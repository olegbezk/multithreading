package com.multithreading;

import java.util.stream.IntStream;

public final class Common {

    private Common() {

    }

    public static void hello() {
        IntStream.range(0, 10).forEach(i -> {
            System.out.println("Hello " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
