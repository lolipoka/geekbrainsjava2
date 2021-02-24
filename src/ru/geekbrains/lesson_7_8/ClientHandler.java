package ru.geekbrains.lesson_7_8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private static final long AUTH_TIMEOUT = 120_000L;
    private final MyServer myServer;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final long startTime;
    private String name;


    public String getName() {
        return name;
    }

    public ClientHandler(MyServer myServer, Socket socket) {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";

            startTime = System.currentTimeMillis();

            new Thread(this::run).start();
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    private void run() {
        try {
            authentication();
            readMessages();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void authentication() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/auth")) {
                String[] parts = str.split("\\s");
                if (parts.length != 3) {
                    sendMsg("Неверные логин/пароль");
                    continue;
                }
                String login = parts[1];
                String password = parts[2];
                String nick = myServer.getAuthService().getNickByLoginPass(login, password);
                if (nick != null) {
                    if (!myServer.isNickBusy(nick)) {
                        sendMsg("/authok " + nick);
                        name = nick;
                        myServer.broadcastMsg(name + " зашел в чат");
                        myServer.subscribe(this);
                        return;
                    } else {
                        sendMsg("Учетная запись уже используется");
                    }
                } else {
                    sendMsg("Неверные логин/пароль");
                }
            }
        }
    }

    public void readMessages() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith("/")) {
                if (str.equals("/end")) {
                    break;
                }
                if (str.startsWith("/w ")) {
                    String[] tokens = str.split("\\s");
                    String nick = tokens[1];
                    String msg = str.substring(4 + nick.length());
                    myServer.sendMsgToClient(this, nick, msg);
                }
                continue;
            }
            myServer.broadcastMsg(name + ": " + str);
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        myServer.unsubscribe(this);
        myServer.broadcastMsg(String.format("%s вышел из чата", name));
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
