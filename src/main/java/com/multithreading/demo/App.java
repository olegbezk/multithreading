package com.multithreading.demo;

import static com.multithreading.Common.hello;

class Runner extends Thread {

    public void run() {
        hello();
    }
}

public class App {

    public static void main(String[] args) {
        Runner one = new Runner();
        one.start();

        Runner two = new Runner();
        two.start();
    }
}