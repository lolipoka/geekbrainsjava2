package ru.geekbrains.lesson1;

public class ThreadMill implements Obstacle {
    private double length;

    public ThreadMill(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }
}
