package ru.geekbrains.java3_lesson5;

import java.util.concurrent.*;

public class MainClass {

    private static final int CARS_COUNT = 4;
    protected static CountDownLatch lineOfStart = new CountDownLatch(CARS_COUNT);
    protected static CyclicBarrier roadSection = new CyclicBarrier(CARS_COUNT);
    protected static Semaphore tunnel = new Semaphore(CARS_COUNT/2,true);
    protected static CountDownLatch lineOfFinish = new CountDownLatch(CARS_COUNT);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        while (lineOfStart.getCount() > 0) //Проверяем, собрались ли все автомобили
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        while (lineOfFinish.getCount() > 0) //Проверяем, все ли проехали финальную дистанцию
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}


