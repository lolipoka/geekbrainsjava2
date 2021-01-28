package ru.geekbrains.lesson2;

public class ExceptionDemo {
    public static int sum = 0;
    public static final int SIZE = 4;

    public static void main(String[] args) {
        String[][] testArray = new String[4][4];

        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray.length; j++) {
                if (i != 3) { // Condition for parsable integers.
                    testArray[i][j] = Integer.toString((i + j) * j);
                } else {
                    testArray[i][j] = (i + j) * j + "a"; // Add random string so it cannot be parsed by Integer.parseInt().
                }
            }
        }

        try {
            processArray(testArray);
        } catch (MyArraySizeException | MyArrayDataException e) { // The code is the same for both types, so we can combine them.
            System.out.println(e.getMessage());
            System.out.printf("Sum is %d.\n", sum);
        }
    }

    private static void processArray(String[][] arr) {

        // Assume that "normal" array is always 4x4, so it must be enough to check only first element's length.
        if (arr.length != SIZE || arr[0].length != SIZE) {
            throw new MyArraySizeException();
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        System.out.printf("Sum is %d.\n", sum);
    }
}
