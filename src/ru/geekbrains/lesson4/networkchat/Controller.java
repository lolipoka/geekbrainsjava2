package ru.geekbrains.lesson4.networkchat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {
    @FXML
    TextArea mainTextArea;

    @FXML
    TextField userInputField, userNameArea;

    public void btnSendClickAction(ActionEvent actionEvent) {
        sendMessage();
    }

    private void sendMessage() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();

        if (!userInputField.getText().isEmpty()) {
            mainTextArea.appendText(String.format("[%s] @ %s:\n%s\n", userNameArea.getText(), dtf.format(currentTime), userInputField.getText()));
            userInputField.clear();
            userInputField.requestFocus();
        }
    }
}
