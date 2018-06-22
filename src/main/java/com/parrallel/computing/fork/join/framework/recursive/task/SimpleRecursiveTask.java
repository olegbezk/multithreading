package com.parrallel.computing.fork.join.framework.recursive.task;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveTask extends RecursiveTask<Integer> {

    private int simulatedWork;

    public SimpleRecursiveTask(final int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected Integer compute() {
        if (simulatedWork > 100) {
            System.out.println("Parallel execution needed..." + simulatedWork);

            final SimpleRecursiveTask simpleRecursiveTask = new SimpleRecursiveTask(simulatedWork / 2);
            final SimpleRecursiveTask simpleRecursiveTask2 = new SimpleRecursiveTask(simulatedWork / 2);

            simpleRecursiveTask.fork();
            simpleRecursiveTask2.fork();

            int solution = 0;

            solution += simpleRecursiveTask.join();
            solution += simpleRecursiveTask2.join();

            return solution;
        } else {
            System.out.println("No need in parallel execution..." + simulatedWork);
            return simulatedWork * 2;
        }
    }
}
