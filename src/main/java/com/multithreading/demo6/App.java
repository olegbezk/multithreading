package com.multithreading.demo6;

/**
 * Using multiple locks and synchronized code blocks
 */
public class App {

    public static void main(String[] args) {
        new Worker().main();
    }
}
