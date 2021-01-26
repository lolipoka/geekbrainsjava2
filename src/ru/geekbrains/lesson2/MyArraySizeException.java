package ru.geekbrains.lesson2;

public class MyArraySizeException extends RuntimeException {

    public MyArraySizeException() {
        super("Array size is too big!");
    }

    public MyArraySizeException(String message) {
        super(message);
    }
}
