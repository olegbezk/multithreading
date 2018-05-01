package com.multithreading.demo14;

import java.util.concurrent.Semaphore;

public class Connection {

    private int connections;

    private Semaphore semaphore = new Semaphore(10, true);

    private Connection() {
    }

    public static Connection getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void connect() {

        try {
            semaphore.acquire();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doConnect();
        } finally {
            semaphore.release();
        }
    }

    private void doConnect() {

        synchronized (this) {
            connections++;
            System.out.println("Current amount of connections: " + connections);
        }

        try {
            Thread.sleep(1000); // do some job
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connections--;
        }
    }

    private static class InstanceHolder {
        private static Connection INSTANCE = new Connection();
    }
}
