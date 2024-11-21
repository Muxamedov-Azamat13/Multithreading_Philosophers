package org.example;

import java.util.concurrent.locks.Lock;

/**
 * Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * Вилки лежат на столе между каждой парой ближайших философов.
 * Каждый философ может либо есть, либо размышлять.
 * Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */

public class Philosophers extends Thread{
    private final int id;
    private final Fork rightFork;
    private final Fork leftFork;

    public Philosophers(int id, Fork rightFork, Fork leftFork) {
        this.id = id;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                think();
                eat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Философ " + id + " закончил есть!");
    }

    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " размышляет!");
        Thread.sleep((long) (Math.random() * 500));
    }

    private void eat() throws InterruptedException {
        leftFork.pickUp();
        try {
            rightFork.pickUp();
            try {
                System.out.println("Философ " + id + " есть!");
                Thread.sleep((long) (Math.random() * 500));
            } finally {
                rightFork.putDown();
            }
        } finally {
            leftFork.putDown();
        }
    }

}
