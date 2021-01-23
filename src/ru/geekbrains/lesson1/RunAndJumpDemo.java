package ru.geekbrains.lesson1;

public class RunAndJumpDemo {
    public static void main(String[] args) {
        Competable[] competitors = getCompetitors();
        Obstacle[] obstacles = getObstacles();

        for (Obstacle obstacle : obstacles) {
            for (Competable competitor : competitors) {
                if (competitor.isOutOfCompetition()) {
                    continue;
                }
                if (obstacle instanceof ThreadMill) {
                    competitor.run((ThreadMill) obstacle);
                } else {
                    competitor.jump((Wall) obstacle);
                }
            }
            System.out.println();
        }
    }

    private static Competable[] getCompetitors() {
        Competable[] competitors = new Competable[3];

        competitors[0] = new Robot("Marvyn", 30_000.0, 1.0);
        competitors[1] = new Cat("Willie", 200.0, 3.0);
        competitors[2] = new Human("John Doe",  10_000.0, 2.0);

        return competitors;
    }

    private static Obstacle[] getObstacles() {
        Obstacle[] obstacles = new Obstacle[10];

        obstacles[0] = new ThreadMill(100.0);
        obstacles[1] = new Wall(1.0);
        obstacles[2] = new ThreadMill(200.0);
        obstacles[3] = new Wall(1.0);
        obstacles[4] = new ThreadMill(200.0);
        obstacles[5] = new Wall(1.5);
        obstacles[6] = new ThreadMill(300.0);
        obstacles[7] = new Wall(2.0);
        obstacles[8] = new ThreadMill(1000.0);
        obstacles[9] = new Wall(2.5);

        return obstacles;
    }
}
