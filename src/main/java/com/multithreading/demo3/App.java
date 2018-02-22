package com.multithreading.demo3;

import com.multithreading.Common;

public class App {

    public static void main(String[] args) {
        Thread one = new Thread(Common::hello);

        one.start();
    }
}
