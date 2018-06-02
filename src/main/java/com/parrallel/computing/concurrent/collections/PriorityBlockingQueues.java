package com.parrallel.computing.concurrent.collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstWorkerPBQ implements Runnable {

    private BlockingQueue<Person> stringBlockingQueue;

    public FirstWorkerPBQ(final BlockingQueue<Person> stringBlockingQueue) {
        this.stringBlockingQueue = stringBlockingQueue;
    }

    @Override
    public void run() {
        try {
            stringBlockingQueue.put(new Person("Adam", 12));
            stringBlockingQueue.put(new Person("Joe", 42));
            stringBlockingQueue.put(new Person("Daniel", 71));
            Thread.sleep(1000);
            stringBlockingQueue.put(new Person("Noel", 32));
            Thread.sleep(1000);
            stringBlockingQueue.put(new Person("Kevin", 34));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWorkerPBQ implements Runnable {

    private BlockingQueue<Person> stringBlockingQueue;

    public SecondWorkerPBQ(final BlockingQueue<Person> stringBlockingQueue) {
        this.stringBlockingQueue = stringBlockingQueue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(stringBlockingQueue.take());
            System.out.println(stringBlockingQueue.take());
            System.out.println(stringBlockingQueue.take());
            Thread.sleep(1000);
            System.out.println(stringBlockingQueue.take());
            Thread.sleep(1000);
            System.out.println(stringBlockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Person implements Comparable<Person> {

    private String name;

    private int age;

    public Person(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(final Person o) {
        return Integer.compare(this.age, o.getAge());
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class PriorityBlockingQueues {

    public static void main(String[] args) {
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>();

        new Thread(new FirstWorkerPBQ(queue)).start();
        new Thread(new SecondWorkerPBQ(queue)).start();
    }
}
