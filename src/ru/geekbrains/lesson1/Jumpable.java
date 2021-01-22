package ru.geekbrains.lesson1;

public interface Jumpable {
    boolean jump(double height);

    default boolean jump(Wall wall) {
        return jump(wall.getHeight());
    }
}
