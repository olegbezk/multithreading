package com.parrallel.computing.fork.join.framework.recursive.task;

import java.util.concurrent.ForkJoinPool;

public class App {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        final SimpleRecursiveTask simpleRecursiveTask = new SimpleRecursiveTask(200);

        System.out.println(pool.invoke(simpleRecursiveTask));
    }
}
