package ru.geekbrains.lesson2;

public class MyArrayDataException extends NumberFormatException {
    public MyArrayDataException() {
        super("Cannot parse int from string!");
    }

    public MyArrayDataException(int i, int j) {
        super(String.format("Cannot parse int from string in arr[%d][%d]!", i, j));
    }
}
