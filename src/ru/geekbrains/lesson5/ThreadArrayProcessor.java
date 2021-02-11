package ru.geekbrains.lesson5;

class ThreadArrayProcessor implements Runnable {
    private final float[] arr;
    private final Thread thread;

    public ThreadArrayProcessor(String name, float[] arr) {
        this.arr = arr;
        thread = new Thread(this, name);
        thread.start();
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        ThreadArrayProcessingDemo.processArray(arr);

        long endTime = System.currentTimeMillis();
        System.out.printf("Thread '%s' processing time: %d.\n", Thread.currentThread().getName(), endTime - startTime);
    }

    public Thread getThread() {
        return thread;
    }
}
