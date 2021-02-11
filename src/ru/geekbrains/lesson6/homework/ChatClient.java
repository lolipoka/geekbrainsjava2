package ru.geekbrains.lesson6.homework;

import java.io.*;
import java.net.Socket;

public class ChatClient {

    private final String SERVER_ADDRESS = "localhost";
    private final int SERVER_PORT = 8189;

    private Socket socket;
    private BufferedReader in;
    private BufferedReader userIn;
    private PrintWriter out;
    private final String name = "client";

    public ChatClient() {
        try {
            openConnection();
        } catch (IOException e) {
            System.out.println("Failed to connect.");
//            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {

        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        userIn = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(this::run).start();
    }

    public void closeConnection() {
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

    private void run() {
        try {
            final String exitWord = "bye!";

            while (true) {

                if (in.ready()) {
                    String serverReply = in.readLine();

                    if (serverReply.equalsIgnoreCase(exitWord)) {
                        System.out.println("Server stopped.");
                        closeConnection();
                        break;
                    }
                    if (!serverReply.trim().isEmpty()) {
                        System.out.println(serverReply);                    }
                }

                if (userIn.ready()) {
                    String userInput = userIn.readLine();
                    if (!userInput.trim().isEmpty()) {
                        out.printf("[%s]: %s\n", name, userInput);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClient();
    }
}
