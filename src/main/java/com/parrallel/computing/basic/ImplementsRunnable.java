package com.parrallel.computing.basic;

class Runner3 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner1: " + i);
        }
    }
}

class Runner4 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner2: " + i);
        }
    }
}

public class ImplementsRunnable {

    public static void main(String[] args) {

        Thread th1 = new Thread(new Runner3());
        Thread th2 = new Thread(new Runner4());

        th1.start();
        th2.start();
    }
}
