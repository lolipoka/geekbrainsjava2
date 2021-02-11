package ru.geekbrains.lesson6.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    private BufferedReader in;
    private BufferedReader userIn;
    private PrintWriter out;
    private Socket socket;
    private final String name = "server";

    public ChatServer() {
        openConnection();
    }

    private void openConnection() {

        try (ServerSocket serverSocket = new ServerSocket(8189)) {

            System.out.println("Server started.");

            socket = serverSocket.accept();

            System.out.println("Client connected.");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            userIn = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(socket.getOutputStream(), true);

            final String exitWord = "bye!";

            out.printf("[%s]: Welcome to our chat!\n", name);

            while (true) {

                if (in.ready()) {
                    String clientReply = in.readLine();

                    if (clientReply.equalsIgnoreCase(exitWord)) {
                        System.out.println("Client disconnected.");
                        closeConnection();
                        break;
                    }

                    if (!clientReply.trim().isEmpty()) {
                        System.out.println(clientReply);
                    }
                }

                if (userIn.ready()) {
                    String userInput = userIn.readLine();
                    if (!userInput.trim().isEmpty()) {
                        out.printf("[%s]: %s\n", name, userInput);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            userIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.close();

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }
}
