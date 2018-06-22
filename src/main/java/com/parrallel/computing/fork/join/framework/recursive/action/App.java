package com.parrallel.computing.fork.join.framework.recursive.action;

import java.util.concurrent.ForkJoinPool;

public class App {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(120);
        pool.invoke(simpleRecursiveAction);
    }
}
