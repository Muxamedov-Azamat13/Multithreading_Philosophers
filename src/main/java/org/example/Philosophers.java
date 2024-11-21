package org.example;


public class Philosophers extends Thread {
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
