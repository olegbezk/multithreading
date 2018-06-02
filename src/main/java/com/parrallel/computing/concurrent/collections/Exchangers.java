package com.parrallel.computing.concurrent.collections;

import java.util.concurrent.Exchanger;

class FirstThread implements Runnable {

    private int count;

    private Exchanger<Integer> exchanger;

    public FirstThread(final Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {

            count += 1;
            System.out.println("FirstThread counter: " + count);

            try {
                count = exchanger.exchange(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondThread implements Runnable {

    private int count;

    private Exchanger<Integer> exchanger;

    public SecondThread(final Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {

            count -= 1;
            System.out.println("SecondThread counter: " + count);

            try {
                count = exchanger.exchange(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Exchangers {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(new FirstThread(exchanger)).start();
        new Thread(new SecondThread(exchanger)).start();
    }
}
