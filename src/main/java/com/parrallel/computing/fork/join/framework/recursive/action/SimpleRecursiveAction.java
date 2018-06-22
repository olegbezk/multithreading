package com.parrallel.computing.fork.join.framework.recursive.action;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {

    private int simulatedWork;

    public SimpleRecursiveAction(final int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {
        if (simulatedWork > 100) {
            System.out.println("Parallel execution and split task..." + simulatedWork);
            final SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(simulatedWork / 2);
            final SimpleRecursiveAction simpleRecursiveAction2 = new SimpleRecursiveAction(simulatedWork / 2);

            simpleRecursiveAction.fork();
            simpleRecursiveAction2.fork();
        } else {
            System.out.println("No need in parallel execution, sequential algorithm is OK " + simulatedWork);
        }
    }
}
