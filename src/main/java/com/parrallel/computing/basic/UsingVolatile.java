package com.parrallel.computing.basic;

class Worker implements Runnable {

    private volatile boolean isTerminated = false;

    @Override
    public void run() {
        while (!isTerminated) {
            System.out.println("Hello from worker class!");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(final boolean terminated) {
        isTerminated = terminated;
    }
}

public class UsingVolatile {

    public static void main(String[] args) {
        final Worker worker = new Worker();

        Thread th1 = new Thread(worker);
        th1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.setTerminated(true);

        System.out.println("Finished...");
    }
}
