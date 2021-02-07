package ru.geekbrains.lesson5;

import java.util.Arrays;

public class ThreadArrayProcessingDemo {

    private static final int SIZE = 10_000_000;
    private static final int HALF_SIZE = SIZE / 2;

    public static void main(String[] args) {
        processArrayOneThread();
        System.out.println();
        processArrayTwoThreads();
    }

    private static void processArrayOneThread() {
        System.out.println("One thread array processing:");

        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1f);

        long startTime = System.currentTimeMillis();

        processArray(arr);

        long endTime = System.currentTimeMillis();
        System.out.printf("%s time: %d.\n", "Total processing", endTime - startTime);
    }

    private static void processArrayTwoThreads() {
        System.out.println("Two thread array processing:");

        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1f);

        long startTime = System.currentTimeMillis();

        float[] firstHalfArray = new float[HALF_SIZE];
        System.arraycopy(arr, 0, firstHalfArray, 0, HALF_SIZE);

        float[] secondHalfArray = new float[HALF_SIZE];
        System.arraycopy(arr, HALF_SIZE, secondHalfArray, 0, HALF_SIZE);

        printTime(startTime, "Splitting");

        ThreadArrayProcessor thread1 = new ThreadArrayProcessor("first half array", firstHalfArray);
        ThreadArrayProcessor thread2 = new ThreadArrayProcessor("second half array", secondHalfArray);

        try {
            thread1.getThread().join();
            thread2.getThread().join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted!");
        }

        long mergeStartTime = System.currentTimeMillis();

        System.arraycopy(firstHalfArray, 0, arr, 0, HALF_SIZE);
        System.arraycopy(secondHalfArray, 0, arr, HALF_SIZE, HALF_SIZE);

        printTime(mergeStartTime, "Merging");

        printTime(startTime, "Total processing");
    }

    private static void printTime(long startTime, String type) {
        long endTime = System.currentTimeMillis();
        System.out.printf("%s time: %d.\n", type, endTime - startTime);
    }

    public static void processArray(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

}
