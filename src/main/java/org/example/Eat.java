package org.example;

public class Eat {
    public void startEating() throws InterruptedException {
        int count = 5;
        Fork[] forks = new Fork[count];
        Philosophers[] philosophers = new Philosophers[count];

        for (int i = 0; i < count; i++) {
            forks[i] = new Fork();
        }

        for (int i = 0; i < count; i++) {
            philosophers[i] = new Philosophers(i, forks[i], forks[(i + 1) % count]);
            philosophers[i].start();
        }

        for (Philosophers philosophers1 : philosophers) {
            philosophers1.join();
        }

        System.out.println("Все философы закончили есть!");
    }
}
