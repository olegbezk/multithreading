package com.parrallel.computing.dyning.philosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {

    private int id;

    private Lock lock;

    public Chopstick(final int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean pickUp(final Philosopher philosopher, final State state) throws InterruptedException {

        if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println(philosopher + " picked up " + state.toString() + " " + this);
            return true;
        }
        return false;
    }

    public void putDown(final Philosopher philosopher, final State state) {
        lock.unlock();
        System.out.println(philosopher + " put down " + state.toString() + " " + this);
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "id=" + id + '}';
    }
}
