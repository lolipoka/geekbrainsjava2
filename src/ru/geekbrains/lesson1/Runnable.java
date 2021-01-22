package ru.geekbrains.lesson1;

public interface Runnable {
    boolean run(double distance);

    default boolean run(ThreadMill threadMill) {
        return run(threadMill.getLength());
    }
}
