package ru.geekbrains.lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MyClient extends JFrame {

    private JTextField msgInputField;
    private JTextArea chatArea;
    private JTextField loginField;
    private JPasswordField passField;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean isAuthorized;

    public MyClient() {
        openConnection();
        prepareGUI();
    }

    private void setAuthorized(boolean authStatus) {
        isAuthorized = authStatus;
    }

    public void openConnection() {
        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            setAuthorized(false);
            Thread t = new Thread(this::run);
            t.setDaemon(true);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run() {
        try {
            while (true) {
                String strFromServer = in.readUTF();
                if(strFromServer.startsWith("/authok")) {
                    chatArea.append("Авторизация прошла успешно. Теперь можно общаться.\n");
                    setAuthorized(true);
                    break;
                }
                chatArea.append(strFromServer + "\n");
            }
            while (true) {
                String strFromServer = in.readUTF();
                if (strFromServer.equalsIgnoreCase("/end")) {
                    break;
                }
                chatArea.append(strFromServer);
                chatArea.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
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

    public void sendMessage() {
        if (!msgInputField.getText().trim().isEmpty()) {
            try {
                out.writeUTF(msgInputField.getText());
                msgInputField.setText("");
                msgInputField.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
            }
        }
    }

    public void onAuthClick() {
        if (!isAuthorized) {
            try {
                out.writeUTF("/auth " + loginField.getText() + " " + String.valueOf(passField.getPassword()));
                loginField.setText("");
                passField.setText("");
                msgInputField.grabFocus();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            chatArea.append("Вы уже авторизованы.\n");
        }
    }

    public void prepareGUI() {
        // Параметры окна
        setBounds(600, 300, 500, 500);
        setTitle("Клиент");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Поля авторизации
        JPanel authPanel = new JPanel(new BorderLayout());

        loginField = new JTextField("login1", 15);
        loginField.addActionListener(e -> onAuthClick());

        passField = new JPasswordField(15);
        passField.addActionListener(e -> onAuthClick());

        JButton auth = new JButton("Авторизоваться");
        auth.addActionListener(e -> onAuthClick());

        authPanel.add(loginField, BorderLayout.LINE_START);
        authPanel.add(passField, BorderLayout.CENTER);
        authPanel.add(auth, BorderLayout.LINE_END);

        add(authPanel, BorderLayout.NORTH);

        // Текстовое поле для вывода сообщений
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Нижняя панель с полем для ввода сообщений и кнопкой отправки сообщений
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton btnSendMsg = new JButton("Отправить");
        bottomPanel.add(btnSendMsg, BorderLayout.EAST);
        msgInputField = new JTextField();
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(msgInputField, BorderLayout.CENTER);
        btnSendMsg.addActionListener(e -> sendMessage());
        msgInputField.addActionListener(e -> sendMessage());

        // Настраиваем действие на закрытие окна
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    out.writeUTF("/end");
                    closeConnection();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyClient::new);
    }
}
