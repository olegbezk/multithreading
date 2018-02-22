package com.multithreading.demo2;

import static com.multithreading.Common.hello;

class Runner implements Runnable {

    @Override
    public void run() {
        hello();
    }
}

public class App {

    public static void main(String[] args) {

        Thread one = new Thread(new Runner());
        Thread two = new Thread(new Runner());

        one.start();
        two.start();
    }
}
