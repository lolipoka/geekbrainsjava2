package ru.geekbrains.lesson_7_8;

public interface AuthService {
    void start();
    String getNickByLoginPass(String login, String pass);
    void stop();
}
