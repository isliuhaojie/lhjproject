package com.lhj.project.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class randomNumber {
    public static void main(String[] args) {
        double r = Math.random();
        double r2 = Math.random() * 10;
        long r3 = Math.round(Math.random() * 10);

        int i = new Random().nextInt(10);

        int i1 = ThreadLocalRandom.current().nextInt(1000);

        double floor = Math.floor(Math.random() * 11);

        System.out.println(i1);
    }
}
