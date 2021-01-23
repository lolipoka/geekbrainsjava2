package ru.geekbrains.lesson1;

public class Human implements Competable {
    private final String name;
    private final double maxDistance;
    private final double maxHeight;
    private boolean outOfCompetition;

    public Human(String name, double maxDistance, double maxHeight) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
        outOfCompetition = false;
    }

    @Override
    public boolean isOutOfCompetition() {
        return outOfCompetition;
    }

    @Override
    public boolean jump(double height) {
        if (height > maxHeight) {
            System.out.printf("%s failed to jump over %.2f meters.\n", name, height);
            outOfCompetition = true;
            return false;
        } else {
            System.out.printf("%s successfully jumped over %.2f meters.\n", name, height);
            return true;
        }
    }

    @Override
    public boolean run(double distance) {
        if (distance > maxDistance) {
            System.out.printf("%s failed to run %.2f meters.\n", name, distance);
            outOfCompetition = true;
            return false;
        } else {
            System.out.printf("%s successfully ran %.2f meters.\n", name, distance);
            return true;
        }
    }
}
