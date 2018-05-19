package com.parrallel.computing.basic;

class Runner5 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner1: " + i);
        }
    }
}

class Runner6 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner2: " + i);
        }
    }
}

public class ExtendsThread {

    public static void main(String[] args) {

        final Runner5 th1 = new Runner5();
        final Runner6 th2 = new Runner6();

        th1.start();
        th2.start();
    }
}
