package com.multithreading.anonymous.thread;

import com.multithreading.Common;

/**
 * Creating anonymous thread
 */
public class App {

    public static void main(String[] args) {
        Thread one = new Thread(Common::hello);

        one.start();
    }
}
